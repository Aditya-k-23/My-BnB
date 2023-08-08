package com.cscc43.mybnb.repository.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Renter;
import com.cscc43.mybnb.models.combined.RenterIDBookingCount;
import com.cscc43.mybnb.models.combined.RenterIDCityBookingCount;
import com.cscc43.mybnb.models.combined.YearUserIDBookingCount;
import com.cscc43.mybnb.repository.interfaces.RenterRepositoryInterface;

@Repository
public class RenterRepository implements RenterRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<Renter> getAllRenters() {
    return jdbcTemplate.query("SELECT * FROM Renter INNER JOIN User ON Renter.id = User.id;",
        new BeanPropertyRowMapper<>(Renter.class));
  }

  @Override
  public Renter getRenter(int id) {
    return new Renter(userRepository.getUser(id).getId());
  }

  @Override
  public void addRenter(Renter renter) {
    userRepository.addUser(renter);
    jdbcTemplate.update("INSERT INTO Renter (id) VALUES (?);", renter.getId());
  }

  @Override
  public void deleteRenter(int id) {
    jdbcTemplate.update("DELETE FROM Renter WHERE id = ?;", id);
    userRepository.deleteUser(id);
  }

  @Override
  public List<RenterIDBookingCount> getRenterRankedByBoookingsInPeriod(LocalDate startDate, LocalDate endDate) {
    String query = "SELECT R.id AS renterId, COUNT(B.id) AS booking_count " +
        "FROM Renter R INNER JOIN Booking B " +
        "ON R.id = B.renterId " +
        "WHERE B.startDate >= '" +
        startDate.toString() +
        "' AND B.endDate <= '" +
        endDate.toString() +
        "' GROUP BY R.id " +
        "ORDER BY booking_count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(RenterIDBookingCount.class));
  }

  @Override
  public List<RenterIDCityBookingCount> getRenterRankedByBoookingsInRangePerCity(LocalDate startDate, LocalDate endDate,
      int minBookingCount) {
    String query = "SELECT L.city AS city, B.renterId AS renterId, COUNT(B.id) AS booking_count " +
        "FROM Booking B, Listing L " +
        "WHERE (B.startDate BETWEEN '" +
        startDate.toString() +
        "' AND '" +
        endDate.toString() +
        "') AND (B.endDate BETWEEN '" +
        startDate.toString() +
        "' AND '" +
        endDate.toString() +
        "') " +
        "GROUP BY L.city, B.renterId HAVING (booking_count >= " +
        minBookingCount + ") " +
        "ORDER BY booking_count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(RenterIDCityBookingCount.class));
  }

  @Override
  public List<YearUserIDBookingCount> getRenterRankedByCancellationsInYear() {
    String query = "SELECT YEAR(B.startDate) AS year, B.renterId AS userId, COUNT(B.id) AS count " +
        "FROM Booking B " +
        "WHERE B.status = 'Cancelled' " +
        "GROUP BY year, userId " +
        "ORDER BY year, count DESC;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(YearUserIDBookingCount.class));
  }

}
