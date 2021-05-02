package com.upgrad.FoodOrderingApp.service.entity;


import javax.persistence.*;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "payment_name")
    private String paymentName;
}
