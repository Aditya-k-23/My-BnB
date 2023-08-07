package com.cscc43.mybnb.controller.requestbodies;

public class DeletePeriodBody {
  private int period_id;
  private int listing_id;

  public int getPeriodId() {
    return period_id;
  }

  public void setPeriodId(int period_id) {
    this.period_id = period_id;
  }

  public int getListingID() {
    return listing_id;
  }

  public void setListingID(int listing_id) {
    this.listing_id = listing_id;
  }

  @Override
  public String toString() {
    return "DeletePeriodBody{" +
        "period_id=" + period_id +
        ", listing_id=" + listing_id +
        '}';
  }
}
