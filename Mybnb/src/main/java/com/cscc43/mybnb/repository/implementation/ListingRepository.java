package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.controller.ListingController;
import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.combined.CountryCityListing;
import com.cscc43.mybnb.models.combined.CountryCityPostalListing;
import com.cscc43.mybnb.models.combined.CountryListing;
import com.cscc43.mybnb.models.combined.ListingAddress;
import com.cscc43.mybnb.models.combined.ListingDistance;
import com.cscc43.mybnb.models.rowmappers.ListingDistancePrice;
import com.cscc43.mybnb.repository.interfaces.ListingRepositoryInterface;

@Repository
public class ListingRepository implements ListingRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Listing> getAllListings() {
    String query = "SELECT * FROM Listing;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Listing.class));
  }

  @Override
  public Listing getListing(int id) {
    try {
      String query = "SELECT * FROM Listing WHERE id = ?;";
      return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Listing.class), id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public void addListing(Listing listing) {
    String query = "INSERT INTO Listing (type, latitude, longitude, address_line, city, country, postal_code, avg_price, host_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    jdbcTemplate.update(query,
        listing.getType(),
        listing.getLatitude(),
        listing.getLongitude(),
        listing.getAddressLine(),
        listing.getCity(),
        listing.getCountry(),
        listing.getPostalCode(),
        listing.getAvgPrice(),
        listing.getHostId());
  }

  @Override
  public void deleteListing(int id) {
    jdbcTemplate.update("DELETE FROM Listing WHERE id = ?;", id);
  }

  @Override
  public void updateListing(Listing listing) {
    String query = "UPDATE Listing SET type = ?, latitude = ?, longitude = ?, address_line = ?, city = ?, country = ?, postal_code = ?, avg_price = ?, host_id = ? WHERE id = ?;";
    jdbcTemplate.update(query,
        listing.getType(),
        listing.getLatitude(),
        listing.getLongitude(),
        listing.getAddressLine(),
        listing.getCity(),
        listing.getCountry(),
        listing.getPostalCode(),
        listing.getAvgPrice(),
        listing.getHostId(),
        listing.getId());
  }

  private boolean isAlphaNumeric(String str) {
    return str != null && str.matches("^[a-zA-Z0-9]*$");
  }

  @Override
  public List<ListingDistance> getListingsInDistance(double latitude, double longitude, double radius,
      ListingController.OrderBy orderBy) {
    String query = "SELECT *, SQRT(POW(? - latitude, 2) + POW(? - longitude, 2)) AS distance FROM Listing WHERE distance <= ? ";

    String querySuffix = switch (orderBy) {
      case NONE -> ";";
      case DISTANCE -> "ORDER BY distance ASC;";
      case PRICE_ASC -> "ORDER BY avg_price ASC;";
      case PRICE_DESC -> "ORDER BY avg_price DESC;";
    };

    String finalQuery = query + querySuffix;

    return jdbcTemplate.query(finalQuery, new ListingDistancePrice(), latitude, longitude,
        radius);
  }

  @Override
  public List<ListingAddress> getListingsByPostalCode(String postal_code) {
    String query = "SELECT * FROM Listing WHERE postal_code = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ListingAddress.class), postal_code);
  }

  @Override
  public List<ListingAddress> getLisitingByAddressLine(String address_line) {
    String query = "SELECT * FROM Listing WHERE address_line = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ListingAddress.class), address_line);
  }

  @Override
  public List<Listing> getListingInBudget(double min_price, double max_price) {
    String query = "SELECT * FROM Listing INNER JOIN Period WHERE avg_price >= ? AND avg_price <= ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Listing.class), min_price, max_price);
  }

  @Override
  public List<CountryListing> getCountryListingCount() {
    String query = "SELECT country, COUNT(*) AS count FROM Listing GROUP BY country;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CountryListing.class));
  }

  @Override
  public List<CountryCityListing> getCountryCityListingCount() {
    String query = "SELECT country, city, COUNT(*) AS count FROM Listing GROUP BY country, city;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CountryCityListing.class));
  }

  @Override
  public List<CountryCityPostalListing> getCountryCityPostalListingCount() {
    String query = "SELECT country, city, postal_code, COUNT(*) AS count FROM Listing GROUP BY country, city, postal_code;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CountryCityPostalListing.class));
  }

  @Override
  public List<Listing> getAllListingsByAmenities(List<String> amenity_names) {
    String sqlQuery = getQueryToSearchByAmenities(amenity_names);
    return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Listing.class));
  }

  @Override
  public Float getRecommendedPrice(int listing_id) {
    String country = getListing(listing_id).getCountry();
    String city = getListing(listing_id).getCity();
    String postal_code = getListing(listing_id).getPostalCode();

    Float recommendedPrice = getRecommendedPricePerCountryCityPostal(listing_id, country, city, postal_code);
    if (recommendedPrice == null) {
      recommendedPrice = getRecommendedPricePerCountryCity(listing_id, country, city);
    }
    if (recommendedPrice == null) {
      recommendedPrice = getRecommendedPricePerCountry(listing_id, country);
    }
    if (recommendedPrice == null) {
      recommendedPrice = getRecommendedPriceGlobally(listing_id);
    }
    return recommendedPrice;
  }

  public String getQueryToSearchByAmenities(List<String> amenities) {
    String queryToSearch = "SELECT L.id FROM Listing L ";
    if (amenities != null) {
      for (int i = 0; i < amenities.size(); i++) {
        queryToSearch += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
        queryToSearch += "(SELECT * FROM ListingAmenity AS LA WHERE L.id = LA.listingID AND LA.amenity = '"
            + amenities.get(i) + "') ";
        jdbcTemplate.update("UPDATE AmenitySearch SET searchCount=searchCount+1 WHERE name = ?;",
            amenities.get(i));
      }
    }
    return queryToSearch;
  }

  public List<Listing> getListingByFullSearchQuery(String sqlQuery) {
    return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Listing.class));
  }

  private Float getRecommendedPriceGlobally(int listing_id) {
    try {
      String query = "SELECT AVG(avg_price) FROM Listing WHERE id != ?;";
      return jdbcTemplate.queryForObject(query, Float.class, listing_id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  private Float getRecommendedPricePerCountry(int listing_id, String country) {
    try {
      String query = "SELECT AVG(avg_price) FROM Listing WHERE id != ? AND country = ?;";
      return jdbcTemplate.queryForObject(query, Float.class, listing_id, country);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  private Float getRecommendedPricePerCountryCity(int listing_id, String country, String city) {
    try {
      String query = "SELECT AVG(avg_price) FROM Listing WHERE id != ? AND country = ? AND city = ?;";
      return jdbcTemplate.queryForObject(query, Float.class, listing_id, country, city);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  private Float getRecommendedPricePerCountryCityPostal(int listing_id, String country, String city,
      String postal_code) {
    try {
      String query = "SELECT AVG(avg_price) FROM Listing WHERE id != ? AND country = ? AND city = ? AND postal_code = ?;";
      return jdbcTemplate.queryForObject(query, Float.class, listing_id, country, city, postal_code);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}
