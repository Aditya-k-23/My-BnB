package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.combined.CountryCityListing;
import com.cscc43.mybnb.models.combined.CountryCityPostalListing;
import com.cscc43.mybnb.models.combined.CountryListing;
import com.cscc43.mybnb.models.combined.ListingAddress;
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecommendedPriceFloat'");
  }

  @Override
  public Float getRecommendedPrice(int listing_id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecommendedPriceFloat'");
  }

}
