package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CustomerBusinessService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class RestaurantController {

    @Autowired
    private CustomerBusinessService customerBusinessService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getAllRestaurants() {
        RestaurantListResponse response = new RestaurantListResponse();

        List<RestaurantEntity> allRestaurants = restaurantService.getAllRestaurants();
        for(RestaurantEntity restaurant : allRestaurants) {
            RestaurantList restaurantList = new RestaurantList();
            restaurantList.setId(UUID.fromString(restaurant.getUuid()));
            restaurantList.setRestaurantName(restaurant.getRestaurantName());
            restaurantList.setPhotoURL(restaurant.getPhotoUrl());
            restaurantList.setCustomerRating(BigDecimal.valueOf(restaurant.getCustomerRating()));
            restaurantList.setAveragePrice(restaurant.getAveragePriceForTwo());
            restaurantList.setNumberCustomersRated(restaurant.getNumberOfCustomersRated());

            RestaurantDetailsResponseAddress responseAddress = new RestaurantDetailsResponseAddress();
            responseAddress.setId(UUID.fromString(restaurant.getAddress().getUuid()));
            responseAddress.setFlatBuildingName(restaurant.getAddress().getFlatBuildNumber());
            responseAddress.setLocality(restaurant.getAddress().getLocality());
            responseAddress.setCity(restaurant.getAddress().getCity());
            responseAddress.setPincode(restaurant.getAddress().getPincode());

            RestaurantDetailsResponseAddressState state = new RestaurantDetailsResponseAddressState();
            state.setId(UUID.fromString(restaurant.getAddress().getState().getUuid()));
            state.setStateName(restaurant.getAddress().getState().getStateName());
            responseAddress.setState(state);
            restaurantList.setAddress(responseAddress);

            restaurantList.setCategories(restaurant.getCategoryList().toString());

            response.addRestaurantsItem(restaurantList);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // this method extracts the token from the JWT token string sent in the Request Header
    private String getToken(String authToken) {
        String token;
        if (authToken.startsWith("Bearer ")) {
            String [] bearerToken = authToken.split("Bearer ");
            token = bearerToken[1];
        } else {
            token = authToken;
        }
        return token;
    }

}
