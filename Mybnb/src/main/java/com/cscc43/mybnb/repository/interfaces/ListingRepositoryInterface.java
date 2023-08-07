package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.combined.ListingAddress;
import com.cscc43.mybnb.models.combined.ListingDistance;
import com.cscc43.mybnb.controller.ListingController;
import com.cscc43.mybnb.models.combined.CountryCityListing;
import com.cscc43.mybnb.models.combined.CountryCityPostalListing;
import com.cscc43.mybnb.models.combined.CountryListing;

@Repository
public interface ListingRepositoryInterface {
  public List<Listing> getAllListings();

  public Listing getListing(int id);

  public void addListing(Listing listing);

  public void deleteListing(int id);

  public void updateListing(Listing listing);

  public List<ListingDistance> getListingsInDistance(double latitude, double longitude, double radius,
      ListingController.OrderBy orderBy);

  public List<ListingAddress> getListingsByPostalCode(String postalCode);

  public List<ListingAddress> getLisitingByAddressLine(String addressLine);

  public List<Listing> getListingInBudget(double min_price, double max_price);

  public List<CountryListing> getCountryListingCount();

  public List<CountryCityListing> getCountryCityListingCount();

  public List<CountryCityPostalListing> getCountryCityPostalListingCount();

  public List<Listing> getAllListingsByAmenities(List<String> amenityNames);

  public Float getRecommendedPrice(int listingId);
}
