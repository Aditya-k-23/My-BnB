package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.ListingReview;
import com.cscc43.mybnb.repository.interfaces.ListingReviewRepositoryInterface;

@Repository
public class ListingReviewRepository implements ListingReviewRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<ListingReview> getAllListingReviews() {
    String query = "SELECT * FROM ListingReview;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<ListingReview>(ListingReview.class));
  }

  @Override
  public ListingReview getListingReviewByReviewId(int id) {
    String query = "SELECT * FROM ListingReview WHERE id = ?;";
    return jdbcTemplate.queryForObject(query,
        new BeanPropertyRowMapper<ListingReview>(ListingReview.class), id);
  }

  @Override
  public List<ListingReview> getListingReviewsByListing(int listingId) {
    String query = "SELECT * FROM ListingReview WHERE listingId = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<ListingReview>(ListingReview.class), listingId);
  }

  @Override
  public List<ListingReview> getListingReviewsByReviewerId(int reviewerId) {
    String query = "SELECT * FROM ListingReview WHERE reviewerId = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<ListingReview>(ListingReview.class), reviewerId);
  }

  @Override
  public List<ListingReview> getListingReviewByBookingId(int bookingId) {
    String query = "SELECT * FROM ListingReview WHERE bookingId = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<ListingReview>(ListingReview.class), bookingId);
  }

  @Override
  public void addListingReview(ListingReview listingReview) {
    String query = "CALL add_listing_review(?, ?, ?, ?, ?);";
    jdbcTemplate.update(query,
        listingReview.getBookingId(),
        listingReview.getRenterId(),
        listingReview.getListingId(),
        listingReview.getRating(),
        listingReview.getComment());
  }

  @Override
  public void deleteListingReview(int reviewId) {
    String query = "DELETE FROM ListingReview WHERE id = ?;";
    jdbcTemplate.update(query, reviewId);
  }

}
