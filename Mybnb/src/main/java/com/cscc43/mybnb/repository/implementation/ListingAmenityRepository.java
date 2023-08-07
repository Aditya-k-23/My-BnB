package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Amenity;
import com.cscc43.mybnb.models.ListingAmenity;
import com.cscc43.mybnb.repository.interfaces.ListingAmenityRepositoryInterface;

@Repository
public class ListingAmenityRepository implements ListingAmenityRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<ListingAmenity> getAllListingAmenities() {
    String query = "SELECT * FROM ListingAmenity;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ListingAmenity.class));
  }

  @Override
  public List<ListingAmenity> getListingsWithAmenity(String amenityName) {
    String query = "SELECT * FROM ListingAmenity WHERE amenityName = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ListingAmenity.class), amenityName);
  }

  @Override
  public List<Amenity> getAmenitiesOfListing(int listingId) {
    String query = "SELECT * FROM ListingAmenity INNER JOIN Amenity ON ListingAmenity.name = Amenity.name WHERE listingId = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Amenity.class), listingId);
  }

  @Override
  public void addListingAmenity(int listingId, String amenityName) {
    String query = "INSERT INTO ListingAmenity (amenityName,listingId_) VALUE (?,?);";
    jdbcTemplate.update(query, amenityName, listingId);
  }

  @Override
  public void addListingAmenity(ListingAmenity listingAmenity) {
    String query = "INSERT INTO ListingAmenity (amenityName,listingId_) VALUE (?,?);";
    jdbcTemplate.update(query, listingAmenity.getAmenityName(), listingAmenity.getListingId());
  }

}
