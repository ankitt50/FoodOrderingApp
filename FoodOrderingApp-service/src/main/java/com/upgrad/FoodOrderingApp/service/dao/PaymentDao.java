package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

// DAO layer for the Payment controller
@Repository
public class PaymentDao {

  @PersistenceContext private EntityManager entityManager;

  // get All Payment Methods
  public List<PaymentEntity> getAllPaymentMethods() {
    List<PaymentEntity> paymentMethods =
        entityManager.createNamedQuery("getAllPaymentMethods", PaymentEntity.class).getResultList();
    if (paymentMethods != null) {
      return paymentMethods;
    }
    return Collections.emptyList();
  }
}
