package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class PaymentInfo {
  public enum Field {
    ID("id"),
    NAME_ON_CARD("name_on_card"),
    CARD_NUMBER("card_number"),
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
  private String name_on_card;
  private String card_number;
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
    return this.name_on_card;
  }

  public void setNameOnCard(String name_on_card) {
    this.name_on_card = name_on_card;
  }

  public String getCardNumber() {
    return this.card_number;
  }

  public void setCardNumber(String card_number) {
    this.card_number = card_number;
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
        + ", name_on_card='" + getNameOnCard() + "'"
        + ", card_number='" + getCardNumber() + "'"
        + ", postal_code='" + getPostalCode() + "'"
        + ", expiry_date='" + getExpiryDate() + "'"
        + ", user_id='" + getUserId() + "'"
        + " }";
  }
}
