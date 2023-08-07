package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.Amenity;
import com.cscc43.mybnb.repository.implementation.AmenityRepository;
import com.cscc43.mybnb.repository.implementation.ListingRepository;

@RestController
@RequestMapping(path = "/hostToolkit")
public class HostToolkit {
  @Autowired
  ListingRepository listingRepository;

  @Autowired
  AmenityRepository amenityRepository;

  @GetMapping(path = "/getRecommendedPrice")
  public Float getSuggestedPrice(@RequestParam("listing_id") int listing_id) {
    return listingRepository.getRecommendedPrice(listing_id);
  }

  @GetMapping(path = "/getRecommendedAmenities")
  public List<Amenity> getRecommendedAmenities() {
    return amenityRepository.getRecommendedAmenities();
  }

  @GetMapping(path = "/getExpectedPriceHike")
  public Float getExpectedPriceHike(@RequestParam("listing_id") int listing_id,
      @RequestParam("amenity_name") String amenity_name) {
    return amenityRepository.getExpectedPriceHike(listing_id, amenity_name);
  }
}
