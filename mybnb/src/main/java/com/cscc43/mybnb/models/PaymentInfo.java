package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class PaymentInfo {
  public enum Field {
    ID("id"),
    nameOnCard("nameOnCard"),
    cardNumber("cardNumber"),
    POSTAL_CODE("postal_code"),
    EXPIRY_DATE("expiry_date"),
    USER_ID("user_id");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private int id;
  private String nameOnCard;
  private String cardNumber;
  private String postal_code;
  private LocalDate expiry_date;
  private int user_id;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNameOnCard() {
    return this.nameOnCard;
  }

  public void setNameOnCard(String nameOnCard) {
    this.nameOnCard = nameOnCard;
  }

  public String getCardNumber() {
    return this.cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getPostalCode() {
    return this.postal_code;
  }

  public void setPostalCode(String postal_code) {
    this.postal_code = postal_code;
  }

  public LocalDate getExpiryDate() {
    return this.expiry_date;
  }

  public void setExpiryDate(LocalDate expiry_date) {
    this.expiry_date = expiry_date;
  }

  public int getUserId() {
    return this.user_id;
  }

  public void setUserId(int user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "PaymentInfo {"
        + " id='" + getId() + "'"
        + ", nameOnCard='" + getNameOnCard() + "'"
        + ", cardNumber='" + getCardNumber() + "'"
        + ", postal_code='" + getPostalCode() + "'"
        + ", expiry_date='" + getExpiryDate() + "'"
        + ", user_id='" + getUserId() + "'"
        + " }";
  }
}
