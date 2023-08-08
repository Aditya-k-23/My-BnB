package com.cscc43.mybnb.models;

public class Listing {
  public enum Field {
    ID("id"),
    TYPE("type"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    addressLine("addressLine"),
    CITY("city"),
    COUNTRY("country"),
    postalCode("postalCode"),
    avgPrice("avgPrice"),
    HOST_ID("host_id");

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
  private String type;
  private double latitude;
  private double longitude;
  private String addressLine;
  private String city;
  private String country;
  private String postalCode;
  private double avgPrice;
  private int host_id;

  public Listing() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
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

  public double getAvgPrice() {
    return this.avgPrice;
  }

  public void setAvgPrice(double avgPrice) {
    this.avgPrice = avgPrice;
  }

  public int getHostId() {
    return this.host_id;
  }

  public void setHostId(int host_id) {
    this.host_id = host_id;
  }

  @Override
  public String toString() {
    return "Listing{" +
        "id=" + this.id +
        ", type='" + this.type + '\'' +
        ", latitude='" + this.latitude + '\'' +
        ", longitude='" + this.longitude + '\'' +
        ", addressLine='" + this.addressLine + '\'' +
        ", city='" + this.city + '\'' +
        ", country='" + this.country + '\'' +
        ", postalCode='" + this.postalCode + '\'' +
        ", avgPrice='" + this.avgPrice + '\'' +
        ", host_id='" + this.host_id + '\'' +
        '}';
  }

}
