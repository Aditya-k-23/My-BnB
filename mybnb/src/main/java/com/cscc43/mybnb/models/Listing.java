package com.cscc43.mybnb.models;

public class Listing {
  public enum Field {
    ID("id"),
    TYPE("type"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    ADDRESS_LINE("address_line"),
    CITY("city"),
    COUNTRY("country"),
    POSTAL_CODE("postal_code"),
    AVG_PRICE("avg_price"),
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
  private String address_line;
  private String city;
  private String country;
  private String postal_code;
  private double avg_price;
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

  public double getAvgPrice() {
    return this.avg_price;
  }

  public void setAvgPrice(double avg_price) {
    this.avg_price = avg_price;
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
        ", address_line='" + this.address_line + '\'' +
        ", city='" + this.city + '\'' +
        ", country='" + this.country + '\'' +
        ", postal_code='" + this.postal_code + '\'' +
        ", avg_price='" + this.avg_price + '\'' +
        ", host_id='" + this.host_id + '\'' +
        '}';
  }

}
