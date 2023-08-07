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
  public Amenity getAmenity(String amenityName) {
    return jdbcTemplate.queryForObject("SELECT * FROM Amenity WHERE name = ?;",
        new BeanPropertyRowMapper<>(Amenity.class), amenityName);
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
  public List<String> getAllAmenityNames(int listingId) {
    return jdbcTemplate.queryForList(
        "SELECT amenityName FROM ListingAmenity AS LA WHERE LA.listingId =" + listingId + ";",
        String.class);
  }

  private List<Integer> getListingIdsWithAllAmenities(int listingId, List<String> amenityNames) {
    String query = "SELECT DISTINCT listingId FROM Listing AS L INNER JOIN ListingAmenity AS LA  ON L.id = LA.listingId\n";

    if (amenityNames.isEmpty()) {
      return new ArrayList<>();
    }

    for (int i = 0; i < amenityNames.size(); i++) {
      query += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
      query += "(SELECT * FROM ListingAmenity AS LA WHERE L.id = LA.listingID AND LA.amenity = '"
          + amenityNames.get(i) + "')\n";
    }

    query += "AND L.id !=" + listingId + "\n" +
        "GROUP BY L.id\n" +
        "HAVING COUNT(LA.amenityName) = " + amenityNames.size() + ";";
    System.out.println(query);

    return jdbcTemplate.queryForList(query, Integer.class);
  }

  @Override
  public Float getExpectedPriceHike(int listingId, String amenityName) {
    List<String> amenityNames = getAllAmenityNames(listingId);

    amenityNames.add(amenityName);
    System.out.println(amenityNames);
    List<Integer> listingIdsWithAllAmenities = getListingIdsWithAllAmenities(listingId, amenityNames);
    if (listingIdsWithAllAmenities.isEmpty()) {
      return null;
    }

    String query = "SELECT L.avgPrice - AVG(mod.new_avgPrice)" +
        "AS avgPrice_of_similar_listings\n" +
        "FROM (SELECT L.avgPrice AS avgPrice_with_new_amenity" +
        "FROM Listing L";
    for (int i = 0; i < listingIdsWithAllAmenities.size(); i++) {
      query += (i == 0) ? " WHERE L.id = " : " OR L.id = ";
      query += listingIdsWithAllAmenities.get(i);
    }
    query += ") mod, Listing L WHERE L.id = " + listingId + ";";

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
