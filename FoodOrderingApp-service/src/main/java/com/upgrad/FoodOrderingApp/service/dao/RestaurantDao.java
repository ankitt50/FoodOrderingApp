package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

// DAO layer for the Restaurant controller
@Repository
public class RestaurantDao {
  @PersistenceContext private EntityManager entityManager;

  // get All Restaurants
  public List<RestaurantEntity> getAllRestaurants() {
    try {
      return entityManager
          .createNamedQuery("getAllRestaurants", RestaurantEntity.class)
          .getResultList();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // get Restaurants By Name
  public List<RestaurantEntity> getRestaurantsByName(String restaurantName) {
    try {
      return entityManager
          .createNamedQuery("getRestaurantsByName", RestaurantEntity.class)
          .setParameter("restaurantName", restaurantName)
          .getResultList();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // get Restaurants By Uuid
  public RestaurantEntity getRestaurantsByUuid(String restaurantUuid) {
    try {
      return entityManager
          .createNamedQuery("getRestaurantByUuid", RestaurantEntity.class)
          .setParameter("restaurantUuid", restaurantUuid)
          .getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // update Rating Of Restaurant
  public RestaurantEntity updateRatingOfRestaurant(RestaurantEntity restaurant) {
    entityManager.merge(restaurant);
    return restaurant;
  }
}
