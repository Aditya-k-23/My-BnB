package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Host;
import com.cscc43.mybnb.models.ListingCountReport;
import com.cscc43.mybnb.models.combined.CountryCityHostIDListing;
import com.cscc43.mybnb.models.combined.CountryHostIDListing;
import com.cscc43.mybnb.models.combined.YearUserIDBookingCount;
import com.cscc43.mybnb.repository.interfaces.HostRepositoryInterface;

@Repository
public class HostRepository implements HostRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<Host> getAllHosts() {
    return jdbcTemplate.query("SELECT * FROM Host INNER JOIN User ON Host.id = User.id;",
        new BeanPropertyRowMapper<>(Host.class));
  }

  @Override
  public void addHost(Host host) {
    userRepository.addUser(host);
    jdbcTemplate.update("INSERT INTO Host (id) VALUES (?);", host.getId());
  }

  @Override
  public Host getHost(int id) {
    try {
      return new Host(userRepository.getUser(id).getId());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public void deleteHost(int id) {
    jdbcTemplate.update("DELETE FROM Host WHERE id = ?;", id);
    userRepository.deleteUser(id);
  }

  @Override
  public List<CountryHostIDListing> getHostsByListingsPerCountry() {
    String query = "SELECT country, host_id, COUNT(*) AS count" +
        "FROM Host H INNER JOIN Listing L ON L.host_id=H.host_id" +
        "GROUP BY country, host_id" +
        "ORDER BY country, host_id, count DESC;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper(CountryHostIDListing.class));
  }

  @Override
  public List<CountryCityHostIDListing> getHostsByListingsPerCountryCity() {
    String query = "SELECT country, city, host_id, COUNT(*) AS count" +
        "FROM Host H INNER JOIN Listing L ON L.host_id=H.host_id" +
        "GROUP BY country, city, host_id" +
        "ORDER BY country, city, host_id, count DESC;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper(CountryCityHostIDListing.class));
  }

  @Override
  public List<YearUserIDBookingCount> getHostsByCancellationsPerYear() {
    String query = "SELECT YEAR(start_date) AS year, host_id, COUNT(*) AS count" +
        "FROM Booking B INNER JOIN Listing L ON L.listing_id=B.listing_id" +
        "WHERE status = 'Cancelled'" +
        "GROUP BY YEAR(start_date), host_id" +
        "ORDER BY YEAR(booking_date), user_id, count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper(YearUserIDBookingCount.class));
  }

  // Flag hosts that own more than 10% listings in a city or a country and provide
  // this report for each city and country
  @Override
  public List<ListingCountReport> getFlaggedHosts() {
    String query = "SELECT country, city, host_id, COUNT(*) AS count" +
        "FROM Host H INNER JOIN Listing L ON L.host_id=H.host_id" +
        "GROUP BY country, city, host_id" +
        "HAVING count > (SELECT COUNT(*) FROM Listing L2 WHERE L2.host_id=H.host_id) * 0.1" +
        "ORDER BY country, city, host_id, count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper(ListingCountReport.class));
  }

}
