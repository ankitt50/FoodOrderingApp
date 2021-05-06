package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CustomerBusinessService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
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

    @GetMapping(path = "/api/restaurant/{restaurant_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantDetailsResponse> getRestaurantByUuid(@PathVariable(name = "restaurant_id") final String restaurantUuid) {
        RestaurantDetailsResponse response = new RestaurantDetailsResponse();

        RestaurantEntity restaurant = restaurantService.getRestaurantByUuid(restaurantUuid);
        response.setId(UUID.fromString(restaurant.getUuid()));
        response.setRestaurantName(restaurant.getRestaurantName());
        response.setPhotoURL(restaurant.getPhotoUrl());
        response.setCustomerRating(BigDecimal.valueOf(restaurant.getCustomerRating()));
        response.setAveragePrice(restaurant.getAveragePriceForTwo());
        response.setNumberCustomersRated(restaurant.getNumberOfCustomersRated());

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
        response.setAddress(responseAddress);

        List<CategoryList> categoryList = new ArrayList<>();
        for (CategoryEntity category : restaurant.getCategoryList()) {
            List<ItemList> itemList = new ArrayList<>();
            for (ItemEntity categoryItem : category.getItems()) {
                ItemList item = new ItemList();
                item.setId(UUID.fromString(categoryItem.getUuid()));
                item.setItemName(categoryItem.getItemName());
                item.setItemType(ItemList.ItemTypeEnum.fromValue(categoryItem.getType()));
                item.setPrice(categoryItem.getPrice());
                itemList.add(item);
            }

            CategoryList catList = new CategoryList();
            catList.setId(UUID.fromString(category.getUuid()));
            catList.setCategoryName(category.getCategoryName());
            catList.setItemList(itemList);
            categoryList.add(catList);
        }

        response.setCategories(categoryList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/restaurant/edit/{restaurant_id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantUpdatedResponse> updateRatingByRestaurantId(@RequestHeader(name = "authorization") final String authToken, @RequestHeader(name = "customer_rating") final Double givenCustomerRating, @PathVariable(name = "restaurant_id") final String restaurantUuid) throws AuthorizationFailedException {
        String token = getToken(authToken);
        customerBusinessService.checkAuthToken(token, "/restaurant/edit/{restaurant_id}");
        RestaurantEntity restaurant = restaurantService.getRestaurantByUuid(restaurantUuid);
        int numOfRatings = restaurant.getNumberOfCustomersRated();
        double customerRating = restaurant.getCustomerRating();
        customerRating = (customerRating*numOfRatings + givenCustomerRating)/(numOfRatings+1);
        numOfRatings++;

        restaurant.setNumberOfCustomersRated(numOfRatings);
        restaurant.setCustomerRating(customerRating);

        restaurant = restaurantService.updateRatingOfRestaurant(restaurant);

        RestaurantUpdatedResponse restUpd = new RestaurantUpdatedResponse();
        restUpd.setId(UUID.fromString(restaurant.getUuid()));
        restUpd.setStatus("RESTAURANT RATING UPDATED SUCCESSFULLY");

        return new ResponseEntity<>(restUpd, HttpStatus.OK);
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
