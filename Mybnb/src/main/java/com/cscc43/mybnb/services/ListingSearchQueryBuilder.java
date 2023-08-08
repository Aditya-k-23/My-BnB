package com.cscc43.mybnb.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cscc43.mybnb.controller.ListingController;
import com.cscc43.mybnb.repository.implementation.ListingRepository;

@Service
public class ListingSearchQueryBuilder {
  @Autowired
  private ListingRepository listingRepository;

  private String addQueryFilter(String baseQuery, String filter) {
    return baseQuery + " id IN (" + filter + ") AND ";
  }

  private boolean isValidPostalCode(String postalCode) {
    if (postalCode == null) {
      return false;
    }
    String modifiedPostalCode = postalCode.replaceAll(" ", "");
    return modifiedPostalCode.matches("^[a-zA-Z0-9]*$") && modifiedPostalCode.length() == 6;
  }

  public String buildSQLQuery(Double latitude, Double longitude, Double radius, String postalCode, String addressLine,
      Double minPrice, Double maxPrice, List<String> amenities, LocalDate startDate, LocalDate endDate,
      ListingController.OrderBy orderBy) {
    String query_prefix = "SELECT * ";
    String query = "FROM Listing WHERE ";
    boolean canOrderByDistance = false;

    if (latitude != null && longitude != null && radius != null) {
      query = addQueryFilter(query, String.format(
          "SELECT DISTINCT id AS distance FROM Listing WHERE SQRT(POW(%f - latitude, 2) + POW(%f - longitude, 2)) <= %f",
          latitude, longitude, radius));
      query_prefix = "SELECT *, SQRT(POW(" + latitude + " - latitude, 2) + POW(" + longitude
          + "-longitude, 2)) AS distance ";
      canOrderByDistance = true;
    }
    if (postalCode != null && isValidPostalCode(postalCode)) {
      String postalCodeFSA = postalCode.substring(0, 3);
      query = addQueryFilter(query,
          "SELECT DISTINCT L.id FROM Listing L WHERE postalCode LIKE CONCAT( '" + postalCodeFSA + "', '%')");
    }
    if (addressLine != null) {
      query = addQueryFilter(query,
          "SELECT DISTINCT id FROM Listing WHERE addressLine LIKE CONCAT( '%', '" + addressLine
              + "' ,'%')");
    }
    if (minPrice != null) {
      query = addQueryFilter(query,
          "SELECT DISTINCT L.id FROM Listing L INNER JOIN Period P on P.listingId=L.id WHERE price > " + minPrice);
    }
    if (maxPrice != null) {
      query = addQueryFilter(query,
          "SELECT DISTINCT L.id FROM Listing L INNER JOIN Period P on P.listingId=L.id WHERE price < " + maxPrice);
    }
    if (amenities != null && !amenities.isEmpty()) {
      String sqlQuery = listingRepository.getQueryToSearchByAmenities(amenities);
      query = addQueryFilter(query, sqlQuery);
    }
    if (startDate != null) {
      query = addQueryFilter(query,
          "SELECT DISTINCT L.id FROM Listing L INNER JOIN Period P on P.listingId=L.id WHERE '" + startDate.toString()
              + "' <= DATE(endDate)");
    }
    if (endDate != null) {
      query = addQueryFilter(query,
          "SELECT DISTINCT L.id FROM Listing L INNER JOIN Period P on P.listingId=L.id WHERE DATE(startDate) <= '"
              + endDate.toString() + "'");
    }
    String querySuffix = (orderBy == null) ? " TRUE;" : switch (orderBy) {
      case NONE -> "TRUE;";
      case DISTANCE -> canOrderByDistance ? " ORDER BY distance ASC;" : " TRUE;";
      case PRICE_ASC -> " TRUE ORDER BY avgPrice ASC;";
      case PRICE_DESC -> " TRUE ORDER BY avgPrice DESC;";
    };

    String retQuery = query_prefix + query + querySuffix;
    return retQuery;
  }
}
