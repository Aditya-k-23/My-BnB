package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class PaymentInfo {
  public enum Field {
    ID("id"),
    nameOnCard("nameOnCard"),
    cardNumber("cardNumber"),
    postalCode("postalCode"),
    expiryDate("expiryDate"),
    userId("userId");

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
  private String postalCode;
  private LocalDate expiryDate;
  private int userId;

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
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public LocalDate getExpiryDate() {
    return this.expiryDate;
  }

  public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "PaymentInfo {"
        + " id='" + getId() + "'"
        + ", nameOnCard='" + getNameOnCard() + "'"
        + ", cardNumber='" + getCardNumber() + "'"
        + ", postalCode='" + getPostalCode() + "'"
        + ", expiryDate='" + getExpiryDate() + "'"
        + ", userId='" + getUserId() + "'"
        + " }";
  }
}
