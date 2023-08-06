package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Booking;

@Repository
public interface BookingRepositoryInterface {
  public List<Booking> getAllBookings();

  public Booking getBookings(int id);

}
