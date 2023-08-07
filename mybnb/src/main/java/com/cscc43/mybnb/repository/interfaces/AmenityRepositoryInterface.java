package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Amenity;

@Repository
public interface AmenityRepositoryInterface {
  public List<Amenity> getAllAmenities();

  public Amenity getAmenity(String name);

  public void addAmenity(Amenity amenity);

  public List<Amenity> getRecommendedAmenities();

  public List<String> getAllAmenityNames(int listing_id);

  public Float getExpectedPriceHike(int listing_id, String amenity_name);
}
