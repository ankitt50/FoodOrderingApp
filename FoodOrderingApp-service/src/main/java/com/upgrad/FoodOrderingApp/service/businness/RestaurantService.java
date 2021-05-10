package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.dao.RestaurantDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Service layer for the Restaurant controller
@Service
public class RestaurantService {

  @Autowired private RestaurantDao restaurantDao;

  @Autowired private CategoryDao categoryDao;

  // get list of restaurant entities
  @Transactional
  public List<RestaurantEntity> getAllRestaurants() {
    return restaurantDao.getAllRestaurants();
  }

  // get list of restaurant entities
  @Transactional
  public List<RestaurantEntity> getRestaurantsByName(String restaurantName) {
    return restaurantDao.getRestaurantsByName(restaurantName);
  }

  // get category by UUID
  @Transactional
  public CategoryEntity getCategoryByUuid(String uuid) {
    return categoryDao.getCategoryByUuid(uuid);
  }

  // get Restaurant entity By Uuid
  @Transactional
  public RestaurantEntity getRestaurantByUuid(String restaurantUuid) {
    return restaurantDao.getRestaurantsByUuid(restaurantUuid);
  }

  // update Rating of Restaurant
  @Transactional
  public RestaurantEntity updateRatingOfRestaurant(RestaurantEntity restaurant) {
    return restaurantDao.updateRatingOfRestaurant(restaurant);
  }
}
