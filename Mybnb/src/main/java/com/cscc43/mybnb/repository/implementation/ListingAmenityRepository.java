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
  public List<ListingAmenity> getListingsWithAmenity(String amenity_name) {
    String query = "SELECT * FROM ListingAmenity WHERE amenity_name = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ListingAmenity.class), amenity_name);
  }

  @Override
  public List<Amenity> getAmenitiesOfListing(int listing_id) {
    String query = "SELECT * FROM ListingAmenity INNER JOIN Amenity ON ListingAmenity.name = Amenity.name WHERE listing_id = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Amenity.class), listing_id);
  }

  @Override
  public void addListingAmenity(int listing_id, String amenity_name) {
    String query = "INSERT INTO ListingAmenity (amenity_name,listing_id_) VALUE (?,?);";
    jdbcTemplate.update(query, amenity_name, listing_id);
  }

  @Override
  public void addListingAmenity(ListingAmenity listingAmenity) {
    String query = "INSERT INTO ListingAmenity (amenity_name,listing_id_) VALUE (?,?);";
    jdbcTemplate.update(query, listingAmenity.getAmenityName(), listingAmenity.getListingId());
  }

}
