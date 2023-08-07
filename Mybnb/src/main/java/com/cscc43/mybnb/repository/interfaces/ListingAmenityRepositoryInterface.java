package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.ListingAmenity;

@Repository
public interface ListingAmenityRepositoryInterface {
  public List<ListingAmenity> getAllListingAmenities();

  public List<ListingAmenity> getListingsWithAmenity(String amenity_name);

  public void addListingAmenity(int listing_id, String amenity_name);

  public void addListingAmenity(ListingAmenity listingAmenity);
}
