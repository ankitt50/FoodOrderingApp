package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

// DAO layer for the Order controller
@Repository
public class OrderDao {
  @PersistenceContext private EntityManager entityManager;

  // get orders by Restaurant
  public List<OrderEntity> getOrdersByRestaurant(RestaurantEntity restaurant) {
    try {
      return entityManager
          .createNamedQuery("getOrdersByRestaurant", OrderEntity.class)
          .setParameter("restaurant", restaurant)
          .getResultList();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // get Past Orders Of Customer
  public List<OrderEntity> getPastOrdersOfUsers(CustomerEntity customerEntity) {
    try {
      return entityManager
          .createNamedQuery("getPastOrdersOfUsers", OrderEntity.class)
          .setParameter("customer", customerEntity)
          .getResultList();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // get Coupon By Coupon Name
  public CouponEntity getCouponByCouponName(String couponName) {
    try {
      return entityManager
          .createNamedQuery("getCouponByCouponName", CouponEntity.class)
          .setParameter("couponName", couponName)
          .getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // getCouponByUuid
  public CouponEntity getCouponByUuid(String uuid) {
    try {
      return entityManager
          .createNamedQuery("getCouponByUuid", CouponEntity.class)
          .setParameter("uuid", uuid)
          .getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // save Order
  public OrderItemEntity saveOrder(OrderItemEntity orderItemEntity) {
    entityManager.persist(orderItemEntity);
    return orderItemEntity;
  }
}
