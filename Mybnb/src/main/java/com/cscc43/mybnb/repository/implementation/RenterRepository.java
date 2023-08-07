package com.cscc43.mybnb.repository.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Renter;
import com.cscc43.mybnb.models.combined.RenterIDBookingCount;
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
  public List<RenterIDBookingCount> getRenterRankedByBoookingsInPeriod(LocalDate start_date, LocalDate end_date) {
    String query = "SELECT R.id, COUNT(B.id) AS booking_count " +
        "FROM Renter R, Booking B " +
        "WHERE R.id = B.renter_id " +
        "AND B.start_date >= " +
        start_date.toString() +
        " AND B.end_date <= " +
        end_date.toString() +
        " GROUP BY R.id " +
        "ORDER BY booking_count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(RenterIDBookingCount.class));
  }

  @Override
  public List<RenterIDBookingCount> getRenterRankedByBoookingsInRangePerCity(LocalDate start_date, LocalDate end_date,
      int minBookingCount) {
    String query = "SELECT L.city, B.renter_id, COUNT(B.id) AS booking_count " +
        "FROM Booking B, Listing L " +
        "WHERE (B.start_date BETWEEN '" +
        start_date.toString() +
        "' AND '" +
        end_date.toString() +
        "') AND (B.end_date BETWEEN '" +
        start_date.toString() +
        "' AND '" +
        end_date.toString() +
        "'')\n" +
        "GROUP BY L.city, B.renter_id HAVING (booking_count >= " +
        minBookingCount + ")\n" +
        "ORDER BY booking_count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(RenterIDBookingCount.class));
  }

  @Override
  public List<YearUserIDBookingCount> getRenterRankedByCancellationsInYear() {
    String query = "SELECT YEAR(B.start_date) AS year, B.renter_id, COUNT(B.id) AS cancelled_count " +
        "FROM Booking B " +
        "WHERE B.status = 'Cancelled' " +
        "GROUP BY year, B.renter_id " +
        "ORDER BY year, booking_count DESC;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(YearUserIDBookingCount.class));
  }

}
