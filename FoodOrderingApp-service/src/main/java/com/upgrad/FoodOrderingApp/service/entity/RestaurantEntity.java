package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "customer_rating")
    private int customerRating;

    @Column(name = "average_price_for_two")
    private int avgPriceForTwo;

    @Column(name = "number_of_customers_rated")
    private int numberOfCustomersRated;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_category",
            joinColumns = { @JoinColumn(name = "restaurant_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<CategoryEntity> category;


    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_item",
            joinColumns = { @JoinColumn(name = "restaurant_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<ItemEntity> item;

    public RestaurantEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    public int getAvgPriceForTwo() {
        return avgPriceForTwo;
    }

    public void setAvgPriceForTwo(int avgPriceForTwo) {
        this.avgPriceForTwo = avgPriceForTwo;
    }

    public int getNumberOfCustomersRated() {
        return numberOfCustomersRated;
    }

    public void setNumberOfCustomersRated(int numberOfCustomersRated) {
        this.numberOfCustomersRated = numberOfCustomersRated;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public List<ItemEntity> getItem() {
        return item;
    }

    public void setItem(List<ItemEntity> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "RestaurantEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", customerRating=" + customerRating +
                ", avgPriceForTwo=" + avgPriceForTwo +
                ", numberOfCustomersRated=" + numberOfCustomersRated +
                ", address=" + address +
                '}';
    }
}
