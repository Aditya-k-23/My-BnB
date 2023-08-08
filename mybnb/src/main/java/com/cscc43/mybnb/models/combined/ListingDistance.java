package com.cscc43.mybnb.models.combined;

import com.cscc43.mybnb.models.Listing;

public class ListingDistance {
  private Listing listing;
  private double distance;

  public ListingDistance(Listing listing) {
    this.listing = listing;
  }

  public void setListing(Listing listing) {
    this.listing = listing;
  }

  public Listing getListing() {
    return this.listing;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public double getDistance() {
    return this.distance;
  }
}
