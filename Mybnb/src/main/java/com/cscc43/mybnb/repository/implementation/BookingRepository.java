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
  public Booking getBookings(int id) {
    try {
      return (Booking) jdbcTemplate.queryForObject("SELECT * FROM Booking WHERE id = ?;",
          new BeanPropertyRowMapper<>(Booking.class), id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public String addBooking(int renter_id, int listing_id, LocalDate start_date,
      LocalDate end_date) {
    try {
      jdbcTemplate.queryForObject(
          "CALL add_booking(?, ?, ?, ?);", String.class,
          listing_id,
          start_date,
          end_date,
          renter_id);
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
  public int getCountBookings(LocalDate start_date, LocalDate end_date,
      String city, String postal_code) {
    System.out.println(city + postal_code + "\n");

    String query = "SELECT COUNT(*) FROM Booking B\n" +
        "INNER JOIN Listing L ON L.listing_id=B.listing_id\n" +
        "WHERE (start_date BETWEEN ? AND ? OR end_date BETWEEN ? AND ?)\n";

    if (city != null) {
      query += "AND L.city = " + city;
    }

    if (postal_code != null) {
      query += "AND L.postal_code = " + postal_code;
    }

    query += ";";

    System.out.println(query + "\n");
    return jdbcTemplate.queryForObject(query, Integer.class,
        start_date, end_date, start_date, end_date);

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
