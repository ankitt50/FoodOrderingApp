package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.PaymentDao;
import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

// Service layer for the Payment controller
@Service
public class PaymentService {

  @Autowired private PaymentDao paymentDao;

  // retrieve list of payment entities
  public List<PaymentEntity> getAllPaymentMethods() {
    return paymentDao.getAllPaymentMethods();
  }
}
