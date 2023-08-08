package com.cscc43.mybnb.repository.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Booking;
import com.cscc43.mybnb.repository.interfaces.BookingRepositoryInterface;

@Repository
public class BookingRepository implements BookingRepositoryInterface {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Booking> getAllBookings() {
    return jdbcTemplate.query("SELECT * FROM Booking;",
        new BeanPropertyRowMapper<>(Booking.class));
  }

  @Override
  public Booking getBooking(int id) {
    try {
      return (Booking) jdbcTemplate.queryForObject("SELECT * FROM Booking WHERE id = ?;",
          new BeanPropertyRowMapper<>(Booking.class), id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public String addBooking(int renterId, int listingId, LocalDate startDate,
      LocalDate endDate) {
    try {
      jdbcTemplate.queryForObject(
          "CALL sp_add_booking(?, ?, ?, ?);", String.class,
          listingId,
          startDate,
          endDate,
          renterId);
      return "Booking added successfully";
    } catch (Exception e) {
      return "Error while adding booking";
    }
  }

  @Override
  public void deleteBooking(int id) {
    jdbcTemplate.update("DELETE FROM Booking WHERE id = ?;", id);
  }

  @Override
  public int getCountBookings(LocalDate startDate, LocalDate endDate,
      String city, String postalCode) {
    System.out.println(city + postalCode + "\n");

    String query = "SELECT COUNT(*) FROM Booking B " +
        "INNER JOIN Listing L ON L.id=B.listingId " +
        "WHERE (startDate <= '" + endDate + "' AND " + " endDate >=  '" + startDate + "') ";

    if (city != null) {
      query += "AND L.city = " + city;
    }

    if (postalCode != null) {
      query += "AND L.postalCode = " + postalCode;
    }

    query += ";";

    System.out.println(query + "\n");
    return jdbcTemplate.queryForObject(query, Integer.class);

  }

  @Override
  public String cancelBooking(int id) {
    try {
      jdbcTemplate.queryForObject(
          "CALL cancel_booking(?);", String.class,
          id);
      return "Booking cancelled successfully";
    } catch (Exception e) {
      return "Error while cancelling booking";
    }
  }

}
