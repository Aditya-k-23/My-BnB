package com.cscc43.mybnb.models.rowmappers;

import com.cscc43.mybnb.models.Address;
import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.combined.ListingAddress;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ListingAddressMapper implements RowMapper<ListingAddress> {

  @Override
  public ListingAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
    Listing curListing = new Listing();
    Address curAddress = new Address();

    curListing.setId(rs.getInt("id"));
    curListing.setType(rs.getString("type"));
    curListing.setLatitude(rs.getDouble("latitude"));
    curListing.setLongitude(rs.getDouble("longitude"));
    curListing.setAddressLine(rs.getString("addressLine"));
    curListing.setCity(rs.getString("city"));
    curListing.setCountry(rs.getString("country"));
    curListing.setPostalCode(rs.getString("postalCode"));
    curListing.setAvgPrice(rs.getDouble("avgPrice"));
    curListing.setHostId(rs.getInt("hostId"));

    curAddress.setAddressLine(rs.getString("addressLine"));
    curAddress.setCity(rs.getString("city"));
    curAddress.setCountry(rs.getString("country"));
    curAddress.setPostalCode(rs.getString("postalCode"));

    return new ListingAddress(curListing, curAddress);
  }

}
