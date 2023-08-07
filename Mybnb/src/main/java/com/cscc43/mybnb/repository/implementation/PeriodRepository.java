package com.cscc43.mybnb.repository.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.Period;
import com.cscc43.mybnb.repository.interfaces.PeriodRepositoryInterface;

@Repository
public class PeriodRepository implements PeriodRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public String addPeriod(Period period) {
    try {
      return jdbcTemplate.queryForObject("CALL sp_add_period(?, ?, ?, ?);",
          String.class, period.getListingId(), period.getStartDate(),
          period.getEndDate(), period.getPrice());
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void deletePeriod(int id, int listing_id) {
    try {
      String query = "DELETE FROM Period WHERE id = ? AND listing_id = ?;";
      jdbcTemplate.update(query, id, listing_id);
    } catch (EmptyResultDataAccessException e) {
      System.out.println("Period not found");
      return;
    }

  }

  @Override
  public List<Period> getAllPeriods() {
    return jdbcTemplate.query("SELECT * FROM Period;",
        new BeanPropertyRowMapper<>(Period.class));
  }

  @Override
  public List<Listing> getAvailableListings() {
    String query = "SELECT DISTINCT L.* " +
        "FROM Period AS P INNER JOIN Listing AS L" +
        "ON L.id = P.listing_id";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Listing.class));
  }

  @Override
  public List<Period> getPeriods(int listing_id) {
    String query = "SELECT * FROM Period WHERE listing_id = ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class), listing_id);
  }

  @Override
  public List<Period> getPeriods(int listing_id, LocalDate start_date, LocalDate end_date) {
    String query = "SELECT * FROM Period" +
        "WHERE DATE(start_date) <= ?" +
        "AND listing_id = ?" +
        "AND DATE(end_date) >= ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class),
        start_date, listing_id, end_date);
  }

  @Override
  public List<Period> getPeriods(LocalDate start_date, LocalDate end_date) {
    String query = "SELECT * FROM Period" +
        "WHERE DATE(start_date) <= ?" +
        "AND DATE(end_date) >= ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class),
        start_date, end_date);
  }

  @Override
  public List<Period> getPeriods(int listing_id, LocalDate end_date) {
    String query = "SELECT * FROM Period" +
        "WHERE listing_id = ?" +
        "AND DATE(end_date) >= ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class),
        listing_id, end_date);
  }

}
