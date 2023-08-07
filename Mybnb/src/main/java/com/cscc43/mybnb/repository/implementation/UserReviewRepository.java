package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.UserReview;
import com.cscc43.mybnb.repository.interfaces.UserReviewRepositoryInterface;

@Repository
public class UserReviewRepository implements UserReviewRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<UserReview> getAllUserReviews() {
    String query = "SELECT * FROM UserReview;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<UserReview>(UserReview.class));
  }

  @Override
  public UserReview getUserReviewByReviewId(int id) {
    String query = "SELECT * FROM UserReview WHERE id = ?;";
    return jdbcTemplate.queryForObject(query,
        new BeanPropertyRowMapper<UserReview>(UserReview.class), id);
  }

  @Override
  public List<UserReview> getUserReviewsForUser(int reviewed_id) {
    String query = "SELECT * FROM UserReview WHERE reviewed_id = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<UserReview>(UserReview.class), reviewed_id);
  }

  @Override
  public List<UserReview> getUserReviewsByUser(int reviewer_id) {
    String query = "SELECT * FROM UserReview WHERE reviewer_id = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<UserReview>(UserReview.class), reviewer_id);
  }

  @Override
  public List<UserReview> getUserReviewByBookingId(int booking_id) {
    String query = "SELECT * FROM UserReview WHERE booking_id = ?;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<UserReview>(UserReview.class), booking_id);
  }

  @Override
  public void addUserReview(UserReview userReview) {
    String query = "CALL add_user_review(?, ?, ?, ?, ?);";
    jdbcTemplate.update(query,
        userReview.getBookingId(),
        userReview.getReviewerId(),
        userReview.getReviewedId(),
        userReview.getRating(),
        userReview.getComment());
  }

  @Override
  public void deleteUserReview(int reviewId) {
    String query = "DELETE FROM UserReview WHERE id = ?;";
    jdbcTemplate.update(query, reviewId);
  }

}
