package com.cscc43.mybnb.models.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.combined.ListingDistance;

public class ListingDistancePrice implements RowMapper<ListingDistance> {
  @Override
  public ListingDistance mapRow(ResultSet rs, int rowNum) throws SQLException {
    Listing curListing = new Listing();
    curListing.setId(rs.getInt("id"));
    curListing.setType(rs.getString("type"));
    curListing.setLatitude(rs.getDouble("latitude"));
    curListing.setLongitude(rs.getDouble("longitude"));
    curListing.setAddressLine(rs.getString("addressLine"));
    curListing.setCity(rs.getString("city"));
    curListing.setCountry(rs.getString("country"));
    curListing.setAvgPrice(rs.getDouble("avgPrice"));
    curListing.setHostId(rs.getInt("host_id"));

    ListingDistance curListingDistance = new ListingDistance(curListing);
    curListingDistance.setDistance(rs.getDouble("distance"));
    return curListingDistance;
  }
}
