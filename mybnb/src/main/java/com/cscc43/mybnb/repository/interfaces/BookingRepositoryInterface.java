package com.cscc43.mybnb.repository.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Booking;

@Repository
public interface BookingRepositoryInterface {
  public List<Booking> getAllBookings();

  public Booking getBookings(int id);

  public String addBooking(int renter_id, int listing_id, LocalDate start_date, LocalDate end_date);

  public void deleteBooking(int id);

  public int getCountBookings(LocalDate start_date, LocalDate end_date, String city, String postal_code);

  public String cancelBooking(int id);
}
