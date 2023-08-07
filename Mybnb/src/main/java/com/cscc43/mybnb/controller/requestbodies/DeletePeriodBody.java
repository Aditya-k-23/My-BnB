package com.cscc43.mybnb.controller.requestbodies;

public class DeletePeriodBody {
  private int period_id;
  private int listingId;

  public int getPeriodId() {
    return period_id;
  }

  public void setPeriodId(int period_id) {
    this.period_id = period_id;
  }

  public int getListingID() {
    return listingId;
  }

  public void setListingID(int listingId) {
    this.listingId = listingId;
  }

  @Override
  public String toString() {
    return "DeletePeriodBody{" +
        "period_id=" + period_id +
        ", listingId=" + listingId +
        '}';
  }
}
