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
  public Float getSuggestedPrice(@RequestParam("listingId") int listingId) {
    return listingRepository.getRecommendedPrice(listingId);
  }

  @GetMapping(path = "/getRecommendedAmenities")
  public List<Amenity> getRecommendedAmenities() {
    return amenityRepository.getRecommendedAmenities();
  }

  @GetMapping(path = "/getExpectedPriceHike")
  public Float getExpectedPriceHike(@RequestParam("listingId") int listingId,
      @RequestParam("amenityName") String amenityName) {
    return amenityRepository.getExpectedPriceHike(listingId, amenityName);
  }
}
