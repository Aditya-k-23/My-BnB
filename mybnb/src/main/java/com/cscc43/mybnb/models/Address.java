package com.cscc43.mybnb.models;

public class Address {
  public enum Field {
    ADDRESS_LINE("address_line"),
    CITY("city"),
    COUNTRY("country"),
    POSTAL_CODE("postal_code");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private String address_line;
  private String city;
  private String country;
  private String postal_code;

  public String getAddressLine() {
    return this.address_line;
  }

  public void setAddressLine(String address_line) {
    this.address_line = address_line;
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
    return this.postal_code;
  }

  public void setPostalCode(String postal_code) {
    this.postal_code = postal_code;
  }

  @Override
  public String toString() {
    return "Address{" +
        "address_line='" + this.address_line + '\'' +
        ", city='" + this.city + '\'' +
        ", country='" + this.country + '\'' +
        ", postal_code='" + this.postal_code + '\'' +
        '}';
  }

}
