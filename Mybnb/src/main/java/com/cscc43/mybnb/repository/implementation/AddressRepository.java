package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.Address;
import com.cscc43.mybnb.repository.interfaces.AddressRepositoryInterface;

@Repository
public class AddressRepository implements AddressRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Address> getAllAddresses() {
    return jdbcTemplate.query("SELECT * FROM Address;", new BeanPropertyRowMapper<>(Address.class));
  }

  @Override
  public Address getAddressById(int id) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM Address WHERE id = ?;",
          new BeanPropertyRowMapper<>(Address.class), id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public void addAddress(Address address) {
    jdbcTemplate.update("INSERT INTO Address (address_line, city, country, postal_code) VALUES (?, ?, ?, ?, ?);",
        address.getAddressLine(),
        address.getCity(),
        address.getCountry(), address.getPostalCode());
  }

}
