package com.cscc43.mybnb.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cscc43.mybnb.models.PaymentInfo;
import com.cscc43.mybnb.repository.interfaces.PaymentInfoRepositoryInterface;

@Repository
public class PaymentInfoRepository implements PaymentInfoRepositoryInterface {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<PaymentInfo> getAllPaymentInfo() {
    return jdbcTemplate.query("SELECT * FROM PaymentInfo;",
        new BeanPropertyRowMapper<>(PaymentInfo.class));
  }

  @Override
  public PaymentInfo getPaymentInfo(int id) {
    try {
      return (PaymentInfo) jdbcTemplate.queryForObject("SELECT * FROM PaymentInfo WHERE id = ?;",
          new BeanPropertyRowMapper<>(PaymentInfo.class), id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<PaymentInfo> getPaymentInfoByUserId(int userId) {
    return jdbcTemplate.query("SELECT * FROM PaymentInfo WHERE userId = ?;",
        new BeanPropertyRowMapper<>(PaymentInfo.class), userId);
  }

  @Override
  public void addPaymentInfo(PaymentInfo paymentInfo) {
    jdbcTemplate.update(
        "INSERT INTO PaymentInfo (nameOnCard, cardNumber, postalCode, expiryDate, userId) VALUES (?, ?, ?, ?, ?)",
        paymentInfo.getNameOnCard(),
        paymentInfo.getCardNumber(),
        paymentInfo.getPostalCode(),
        paymentInfo.getExpiryDate(),
        paymentInfo.getUserId());
  }
}
