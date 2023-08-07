package com.cscc43.mybnb.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Amenity;
import com.cscc43.mybnb.repository.interfaces.AmenityRepositoryInterface;

@Repository
public class AmenityRepository implements AmenityRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Amenity> getAllAmenities() {
    return jdbcTemplate.query("SELECT * FROM Amenity;", new BeanPropertyRowMapper<>(Amenity.class));
  }

  @Override
  public Amenity getAmenity(String amenity_name) {
    return jdbcTemplate.queryForObject("SELECT * FROM Amenity WHERE name = ?;",
        new BeanPropertyRowMapper<>(Amenity.class), amenity_name);
  }

  @Override
  public void addAmenity(Amenity amenity) {
    jdbcTemplate.update("INSERT INTO Amenity (name, type) VALUES (?, ?);", amenity.getName(),
        amenity.getType());
  }

  @Override
  public List<Amenity> getRecommendedAmenities() {
    return jdbcTemplate.query("SELECT name, type FROM Amenity A INNER JOIN\n" +
        "(SELECT amenity FROM AmenitySearch WHERE searchCount>0 ORDER BY searchCount DESC LIMIT 10) recommendations\n" +
        "WHERE recommendations.name = A.name;",
        new BeanPropertyRowMapper<>(Amenity.class));
  }

  @Override
  public List<String> getAllAmenityNames(int listing_id) {
    return jdbcTemplate.queryForList(
        "SELECT amenity_name FROM ListingAmenity AS LA WHERE LA.listing_id =" + listing_id + ";",
        String.class);
  }

  private List<Integer> getListingIdsWithAllAmenities(int listing_id, List<String> amenity_names) {
    String query = "SELECT DISTINCT listing_id FROM Listing AS L INNER JOIN ListingAmenity AS LA  ON L.id = LA.listing_id\n";

    if (amenity_names.isEmpty()) {
      return new ArrayList<>();
    }

    for (int i = 0; i < amenity_names.size(); i++) {
      query += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
      query += "(SELECT * FROM ListingAmenity AS LA WHERE L.id = LA.listingID AND LA.amenity = '"
          + amenity_names.get(i) + "')\n";
    }

    query += "AND L.id !=" + listing_id + "\n" +
        "GROUP BY L.id\n" +
        "HAVING COUNT(LA.amenity_name) = " + amenity_names.size() + ";";
    System.out.println(query);

    return jdbcTemplate.queryForList(query, Integer.class);
  }

  @Override
  public Float getExpectedPriceHike(int listing_id, String amenity_name) {
    List<String> amenity_names = getAllAmenityNames(listing_id);

    amenity_names.add(amenity_name);
    System.out.println(amenity_names);
    List<Integer> listingIdsWithAllAmenities = getListingIdsWithAllAmenities(listing_id, amenity_names);
    if (listingIdsWithAllAmenities.isEmpty()) {
      return null;
    }

    String query = "SELECT L.avg_price - AVG(mod.new_avg_price)" +
        "AS avg_price_of_similar_listings\n" +
        "FROM (SELECT L.avg_price AS avg_price_with_new_amenity" +
        "FROM Listing L";
    for (int i = 0; i < listingIdsWithAllAmenities.size(); i++) {
      query += (i == 0) ? " WHERE L.id = " : " OR L.id = ";
      query += listingIdsWithAllAmenities.get(i);
    }
    query += ") mod, Listing L WHERE L.id = " + listing_id + ";";

    System.out.println(query);

    try {
      Float expected_hike = jdbcTemplate.queryForObject(query, Float.class);
      return Math.abs(expected_hike);
    } catch (EmptyResultDataAccessException e) {
      System.out.println(e);
      return null;
    }
  }

}
