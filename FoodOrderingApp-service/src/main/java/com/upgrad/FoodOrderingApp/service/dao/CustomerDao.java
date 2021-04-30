package com.upgrad.FoodOrderingApp.service.dao;


import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {


    @PersistenceContext
    private EntityManager entityManager;

    public CustomerEntity customerSignup(CustomerEntity newCustomer) {
        entityManager.persist(newCustomer);
        return newCustomer;
    }

}