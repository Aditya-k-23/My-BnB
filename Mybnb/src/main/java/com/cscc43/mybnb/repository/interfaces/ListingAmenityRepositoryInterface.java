package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Amenity;
import com.cscc43.mybnb.models.ListingAmenity;

@Repository
public interface ListingAmenityRepositoryInterface {
  public List<ListingAmenity> getAllListingAmenities();

  public List<ListingAmenity> getListingsWithAmenity(String amenityName);

  public List<Amenity> getAmenitiesOfListing(int listingId);

  public void addListingAmenity(int listingId, String amenityName);

  public void addListingAmenity(ListingAmenity listingAmenity);
}
