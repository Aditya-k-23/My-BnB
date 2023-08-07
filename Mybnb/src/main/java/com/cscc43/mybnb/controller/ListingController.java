package com.cscc43.mybnb.controller;

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
import com.cscc43.mybnb.models.combined.ListingDistance;
import com.cscc43.mybnb.repository.implementation.ListingRepository;
import com.cscc43.mybnb.services.ListingSearchQueryBuilder;
import com.cscc43.mybnb.repository.implementation.ListingAmenityRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
  public ResponseEntity<String> deleteListing(@RequestParam("id") int listing_id) {
    Listing existingListing = listingRepository.getListing(listing_id);

    if (existingListing == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing with this ID doesn't exist!");
    }

    listingRepository.deleteListing(listing_id);
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

}
