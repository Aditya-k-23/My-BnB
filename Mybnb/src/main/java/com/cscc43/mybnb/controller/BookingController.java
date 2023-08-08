package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.controller.requestbodies.AddBookingBody;
import com.cscc43.mybnb.controller.requestbodies.CancelBookingBody;
import com.cscc43.mybnb.models.Booking;
import com.cscc43.mybnb.repository.implementation.BookingRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/booking")
public class BookingController {

  @Autowired
  BookingRepository bookingRepository;

  @GetMapping("/all")
  public List<Booking> getAllBookings() {
    return bookingRepository.getAllBookings();
  }

  @GetMapping("/get")
  public Booking getBooking(@RequestParam("id") int id) {
    return bookingRepository.getBooking(id);
  }

  @PostMapping("/add")
  public void addBooking(@RequestBody AddBookingBody requestBody) {
    String result = bookingRepository.addBooking(requestBody.getRenterId(),
        requestBody.getListingId(),
        requestBody.getStartDate(),
        requestBody.getEndDate());
    System.out.println(result);
  }

  @PostMapping("/cancel")
  public void cancelBooking(@RequestBody CancelBookingBody requestBody) {
    Booking curBooking = bookingRepository.getBooking(requestBody.getBookingId());
    if (curBooking != null) {
      bookingRepository.deleteBooking(requestBody.getBookingId());
    }
  }
}
