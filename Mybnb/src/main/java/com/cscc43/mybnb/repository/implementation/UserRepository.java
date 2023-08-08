package com.cscc43.mybnb.repository.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.User;
import com.cscc43.mybnb.repository.interfaces.UserRepositoryInterface;

import java.util.List;
import java.util.ArrayList;

@Repository
public class UserRepository implements UserRepositoryInterface {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<User> getAllUsers() {
    return jdbcTemplate.query("SELECT * FROM User;",
        new BeanPropertyRowMapper(User.class));
  }

  @Override
  public User getUser(int userId) {
    try {
      return (User) jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?;",
          new BeanPropertyRowMapper(User.class), userId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public void addUser(User user) {

    String query = "INSERT INTO User(sin, name, addressLine, city, country, " +
        "postalCode, occupation, birthdate) VALUE ('" +
        user.getSIN() + "', '" +
        user.getName() + "', '" +
        user.getAddressLine() + "', '" +
        user.getCity() + "', '" +
        user.getCountry() + "', '" +
        user.getPostalCode() + "', '" +
        user.getOccupation() + "', '" +
        user.getBirthdate() + "');";
    jdbcTemplate.update(query);
  }

  @Override
  public void deleteUser(int id) {
    jdbcTemplate.update("DELETE FROM User WHERE id = ?;", id);
  }

  @Override
  public List<User> getUsersWithStatus() {
    return jdbcTemplate.query("SELECT * FROM User WHERE isActive=?;",
        new BeanPropertyRowMapper(User.class));
  }

  public List<String> getUserNames() {
    List<String> names = new ArrayList<>();
    String query = "SELECT name FROM User;";
    names.addAll(jdbcTemplate.queryForList(query, String.class));
    return names;
  }
}
