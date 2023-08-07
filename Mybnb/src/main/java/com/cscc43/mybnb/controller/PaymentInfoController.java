package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.PaymentInfo;
import com.cscc43.mybnb.repository.implementation.PaymentInfoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/paymentInfo")
public class PaymentInfoController {

  @Autowired
  PaymentInfoRepository paymentInfoRepository;

  @GetMapping("/all")
  public List<PaymentInfo> getAllPaymentInfo() {
    return paymentInfoRepository.getAllPaymentInfo();
  }

  @GetMapping("/byUserId")
  public List<PaymentInfo> getPaymentInfoByUserId(int id) {
    return paymentInfoRepository.getPaymentInfoByUserId(id);
  }

  @PostMapping("/add")
  public void addPaymentInfo(@RequestBody PaymentInfo paymentInfo) {
    paymentInfoRepository.addPaymentInfo(paymentInfo);
  }

}
