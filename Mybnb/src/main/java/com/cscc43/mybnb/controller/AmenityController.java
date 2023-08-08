package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.Amenity;
import com.cscc43.mybnb.repository.implementation.AmenityRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
public class AmenityController {
  @Autowired
  AmenityRepository amenityRepository;

  @GetMapping("/getALlAmenities")
  public List<Amenity> getAllAmenities() {
    return amenityRepository.getAllAmenities();
  }

  @GetMapping("/getAmenity")
  public Amenity getAmenity(@RequestParam("name") String name) {
    return amenityRepository.getAmenity(name);
  }

  @PostMapping(value = "/addAmenity")
  public void addAmenity(@RequestBody Amenity amenity) {
    amenityRepository.addAmenity(amenity);
  }

}
