package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class User {
  public enum Field {
    ID("id"),
    NAME("name"),
    SIN("sin"),
    addressLine("addressLine"),
    CITY("city"),
    COUNTRY("country"),
    postalCode("postalCode"),
    OCCUPATION("occupation"),
    BIRTHDATE("birthdate");

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
  private String name;
  private String sin;
  private String addressLine;
  private String city;
  private String country;
  private String postalCode;
  private String occupation;
  private LocalDate birthdate;

  public User() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getBirthdate() {
    return this.birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSIN() {
    return this.sin;
  }

  public void setSIN(String sin) {
    this.sin = sin;
  }

  public String getAddressLine() {
    return this.addressLine;
  }

  public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getOccupation() {
    return this.occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + this.id +
        ", name='" + this.name + '\'' +
        ", sin='" + this.sin + '\'' +
        ", addressLine='" + this.addressLine + '\'' +
        ", city='" + this.city + '\'' +
        ", country='" + this.country + '\'' +
        ", postalCode='" + this.postalCode + '\'' +
        ", occupation='" + this.occupation + '\'' +
        ", birthdate='" + this.birthdate + '\'' +
        '}';
  }

}
