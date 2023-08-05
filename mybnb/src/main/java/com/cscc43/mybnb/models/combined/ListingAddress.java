package com.cscc43.mybnb.models.combined;

import com.cscc43.mybnb.models.Address;
import com.cscc43.mybnb.models.Listing;

public class ListingAddress {

  private Listing listing;
  private Address address;

  public ListingAddress(Listing listing, Address address) {
    this.listing = listing;
    this.address = address;
  }

  public Listing getListing() {
    return this.listing;
  }

  public void setListing(Listing listing) {
    this.listing = listing;
  }

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
