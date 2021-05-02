package com.upgrad.FoodOrderingApp.service.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "item_name")
    private String categoryName;

    @Column(name = "price")
    private int price;

    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private List<RestaurantEntity> restaurant;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_item",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<CategoryEntity> category;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItemEntity = new ArrayList<>();

    public ItemEntity() {
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RestaurantEntity> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<RestaurantEntity> restaurant) {
        this.restaurant = restaurant;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", restaurant=" + restaurant +
                ", category=" + category +
                '}';
    }
}
