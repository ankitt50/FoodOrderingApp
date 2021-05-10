package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.OrderDao;
import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Service layer for the Order controller
@Service
public class OrderService {
  @Autowired private OrderDao orderDao;

  // get coupon by coupon name
  @Transactional
  public CouponEntity getCouponByCouponName(String couponName) {
    return orderDao.getCouponByCouponName(couponName);
  }

  // get Coupon By Uuid
  @Transactional
  public CouponEntity getCouponByUuid(String uuid) {
    return orderDao.getCouponByUuid(uuid);
  }

  // get Past Orders Of Users
  @Transactional
  public List<OrderEntity> getPastOrdersOfUsers(CustomerEntity customerEntity) {
    return orderDao.getPastOrdersOfUsers(customerEntity);
  }

  // save order
  @Transactional
  public OrderItemEntity saveOrder(OrderItemEntity orderItemEntity) {
    return orderDao.saveOrder(orderItemEntity);
  }
}
