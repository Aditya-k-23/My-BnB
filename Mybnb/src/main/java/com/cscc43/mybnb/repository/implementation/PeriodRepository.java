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
  public void deletePeriod(int id, int listingId) {
    try {
      String query = "DELETE FROM Period WHERE id = " + id +
          " AND listingId = " + listingId + ";";
      jdbcTemplate.update(query);
      System.out.println(query);
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
        "FROM Period AS P INNER JOIN Listing AS L " +
        "ON L.id = P.listingId";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Listing.class));
  }

  @Override
  public List<Period> getPeriods(int listingId) {
    String query = "SELECT * FROM Period WHERE listingId = ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class), listingId);
  }

  @Override
  public List<Period> getPeriods(int listingId, LocalDate startDate, LocalDate endDate) {
    String query = "SELECT * FROM Period " +
        "WHERE ? <= DATE(endDate) " +
        "AND listingId = ? " +
        "AND DATE(startDate) <= ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class),
        startDate, listingId, endDate);
  }

  @Override
  public List<Period> getPeriods(LocalDate startDate, LocalDate endDate) {
    String query = "SELECT * FROM Period " +
        "WHERE ? <= DATE(endDate) " +
        "AND DATE(startDate) <= ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class),
        startDate, endDate);
  }

  @Override
  public List<Period> getPeriods(int listingId, LocalDate endDate) {
    String query = "SELECT * FROM Period " +
        "WHERE listingId = ? " +
        "AND DATE(startDate) <= ?;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper<>(Period.class),
        listingId, endDate);
  }

}
