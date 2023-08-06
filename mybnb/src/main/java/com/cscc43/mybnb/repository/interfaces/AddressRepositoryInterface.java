package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Address;

@Repository
public interface AddressRepositoryInterface {
  public List<Address> getAllAddresses();

  public Address getAddressById(int id);

  public void addAddress(Address address);
}
