package com.cscc43.mybnb.models;

public class Address {
  public enum Field {
    addressLine("addressLine"),
    CITY("city"),
    COUNTRY("country"),
    postalCode("postalCode");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private String addressLine;
  private String city;
  private String country;
  private String postalCode;

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

  @Override
  public String toString() {
    return "Address{" +
        "addressLine='" + this.addressLine + '\'' +
        ", city='" + this.city + '\'' +
        ", country='" + this.country + '\'' +
        ", postalCode='" + this.postalCode + '\'' +
        '}';
  }

}
