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
      return jdbcTemplate.queryForObject("SELECT * FROM Host INNER JOIN User ON Host.id = User.id WHERE Host.id=?;",
          new BeanPropertyRowMapper<>(Host.class), id);
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
    String query = "SELECT country, hostId, COUNT(*) AS count" +
        "FROM Host H INNER JOIN Listing L ON L.hostId=H.hostId" +
        "GROUP BY country, hostId" +
        "ORDER BY country, hostId, count DESC;";
    return jdbcTemplate.query(query,
        new BeanPropertyRowMapper(CountryHostIDListing.class));
  }

  @Override
  public List<CountryCityHostIDListing> getHostsByListingsPerCountryCity() {
    String query = "SELECT country, city, hostId, COUNT(*) AS count" +
        "FROM Host H INNER JOIN Listing L ON L.hostId=H.hostId" +
        "GROUP BY country, city, hostId" +
        "ORDER BY country, city, hostId, count DESC;";
    return jdbcTemplate.query(query, new BeanPropertyRowMapper(CountryCityHostIDListing.class));
  }

  @Override
  public List<YearUserIDBookingCount> getHostsByCancellationsPerYear() {
    String query = "SELECT YEAR(startDate) AS year, hostId, COUNT(*) AS count" +
        "FROM Booking B INNER JOIN Listing L ON L.listingId=B.listingId" +
        "WHERE status = 'Cancelled'" +
        "GROUP BY YEAR(startDate), hostId" +
        "ORDER BY YEAR(booking_date), userId, count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper(YearUserIDBookingCount.class));
  }

  // Flag hosts that own more than 10% listings in a city or a country and provide
  // this report for each city and country
  @Override
  public List<ListingCountReport> getFlaggedHosts() {
    String query = "SELECT country, city, hostId, COUNT(*) AS count" +
        "FROM Host H INNER JOIN Listing L ON L.hostId=H.hostId" +
        "GROUP BY country, city, hostId" +
        "HAVING count > (SELECT COUNT(*) FROM Listing L2 WHERE L2.hostId=H.hostId) * 0.1" +
        "ORDER BY country, city, hostId, count DESC;";

    return jdbcTemplate.query(query, new BeanPropertyRowMapper(ListingCountReport.class));
  }

}
