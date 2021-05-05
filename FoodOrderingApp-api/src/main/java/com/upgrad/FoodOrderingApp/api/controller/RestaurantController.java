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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class RestaurantController {

    @Autowired
    private CustomerBusinessService customerBusinessService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getAllRestaurants() {
        RestaurantListResponse response;

        List<RestaurantEntity> restaurants = restaurantService.getAllRestaurants();
        response = getRestaurantListResponseFromRestaurantEntity(restaurants);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/restaurant/name/{restaurant_name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getRestaurantsByName(@PathVariable(name = "restaurant_name") final String restaurantName) {
        RestaurantListResponse response;
        List<RestaurantEntity> allRestaurants = restaurantService.getAllRestaurants();
        List<RestaurantEntity> restaurants = allRestaurants.stream().filter(r -> r.getRestaurantName().toLowerCase().contains(restaurantName)).collect(Collectors.toList());

        response = getRestaurantListResponseFromRestaurantEntity(restaurants);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(path = "/restaurant/category/{category_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getRestaurantsByCategoryUuid(@PathVariable(name = "category_id") final String categoryUuid) {
        CategoryEntity category = restaurantService.getCategoryByUuid(categoryUuid);
        List<RestaurantEntity> restaurants = category.getRestaurants();

        RestaurantListResponse response;

        response = getRestaurantListResponseFromRestaurantEntity(restaurants);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    

    private RestaurantListResponse getRestaurantListResponseFromRestaurantEntity(List<RestaurantEntity> restaurants) {
        RestaurantListResponse response = new RestaurantListResponse();
        for(RestaurantEntity restaurant : restaurants) {
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

            Collections.sort(restaurant.getCategoryList());
            int len = restaurant.getCategoryList().toString().length();
            restaurantList.setCategories(restaurant.getCategoryList().toString().substring(1, len-2));

            response.addRestaurantsItem(restaurantList);
        }
        return response;
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
