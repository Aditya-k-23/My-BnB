package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.PaymentInfo;

@Repository
public interface PaymentInfoRepositoryInterface {
  public List<PaymentInfo> getAllPaymentInfo();

  public PaymentInfo getPaymentInfo(int id);

  public List<PaymentInfo> getPaymentInfoByUserId(int userId);

  public void addPaymentInfo(PaymentInfo paymentInfo);
}
