package com.cscc43.mybnb.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.Amenity;
import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.ListingAmenity;
import com.cscc43.mybnb.models.combined.ListingAddress;
import com.cscc43.mybnb.models.combined.ListingDistance;
import com.cscc43.mybnb.repository.implementation.ListingRepository;
import com.cscc43.mybnb.services.ListingSearchQueryBuilder;
import com.cscc43.mybnb.repository.implementation.ListingAmenityRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/listing")
public class ListingController {

  @Autowired
  ListingRepository listingRepository;

  @Autowired
  ListingAmenityRepository listingAmenityRepository;

  @Autowired
  ListingSearchQueryBuilder builder;

  public enum OrderBy {
    NONE,
    DISTANCE,
    PRICE_ASC,
    PRICE_DESC
  }

  @GetMapping("/all")
  public List<Listing> getAllListings() {
    return listingRepository.getAllListings();
  }

  @PostMapping("/add")
  public void addListing(@RequestBody Listing listing) {
    listingRepository.addListing(listing);
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteListing(@RequestParam("id") int listingId) {
    Listing existingListing = listingRepository.getListing(listingId);

    if (existingListing == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing with this ID doesn't exist!");
    }

    listingRepository.deleteListing(listingId);
    return ResponseEntity.status(HttpStatus.OK).body("Listing deleted successfully!");
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateListing(@RequestBody Listing listing) {
    Listing existingListing = listingRepository.getListing(listing.getId());

    if (existingListing == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing with this ID doesn't exist!");
    }
    if (existingListing.getHostId() != listing.getHostId()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("Invalid permissions: the hostID doesn't match this listing's ID!");
    }
    listingRepository.updateListing(listing);
    return ResponseEntity.status(HttpStatus.OK).body("Listing updated successfully!");
  }

  @GetMapping("/amenities")
  public List<Amenity> getAmenityForListing(@RequestParam("listingId") int listingId) {
    return listingAmenityRepository.getAmenitiesOfListing(listingId);
  }

  @GetMapping("/getAllListingAmenities")
  public List<ListingAmenity> getAllListingAmenities() {
    return listingAmenityRepository.getAllListingAmenities();
  }

  @GetMapping("/getWithinDistance")
  public List<ListingDistance> getAllListingsInDistance(
      @RequestParam("latitude") double latitude,
      @RequestParam("longitude") double longitude,
      @RequestParam(value = "radius", defaultValue = "50") double radius,
      @RequestParam("orderBy") OrderBy orderBy) {
    return listingRepository.getListingsInDistance(latitude, longitude, radius, orderBy);
  }

  @GetMapping("/getAdjacentPostalCodes")
  public List<ListingAddress> getListingsWithAdjacentPostalCodes(
      @RequestParam("postalCode") String postalCode) {
    return listingRepository.getListingsByPostalCode(postalCode);
  }

  @GetMapping("/getByAddressLine")
  public List<ListingAddress> getListingsByAddressLine(
      @RequestParam("addressLine") String addressLine) {
    return listingRepository.getLisitingByAddressLine(addressLine);
  }

  @GetMapping("/getInBudget")
  public List<Listing> getListingsInBudget(
      @RequestParam("minPrice") double minPrice,
      @RequestParam("maxPrice") double maxPrice) {
    return listingRepository.getListingInBudget(minPrice, maxPrice);
  }

  @GetMapping("/getByFullSearch")
  public List<Listing> getAllListingsByFullSearch(@RequestParam(value = "latitude", required = false) Double latitude,
      @RequestParam(value = "longitude", required = false) Double longitude,
      @RequestParam(value = "radius", required = false, defaultValue = "50") Double radius,
      @RequestParam(value = "minPrice", required = false) Double minPrice,
      @RequestParam(value = "maxPrice", required = false) Double maxPrice,
      @RequestParam(value = "postalCode", required = false) String postalCode,
      @RequestParam(value = "addressLine", required = false) String addressLine,
      @RequestParam(value = "amenities", required = false) List<String> amenities,
      @RequestParam(value = "orderBy", required = false) OrderBy orderBy,
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {
    String fullQuery = builder.buildSQLQuery(latitude, longitude, radius, postalCode, addressLine, minPrice, maxPrice,
        amenities, startDate, endDate, orderBy);

    return listingRepository.getListingByFullSearchQuery(fullQuery);
  }

  @GetMapping("/getByAmenities")
  public List<Listing> getAllListingsByAmenities(
      @RequestParam(value = "amenities", required = false) List<String> amenities) {
    return listingRepository.getAllListingsByAmenities(amenities);
  }
}
