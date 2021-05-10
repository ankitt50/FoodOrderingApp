package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

// DAO layer for the Address controller
@Repository
public class AddressDao {

  @PersistenceContext private EntityManager entityManager;

  // get State By Uuid
  public StateEntity getStateByUuid(String uuid) {

    try {
      return entityManager
          .createNamedQuery("StateUuid", StateEntity.class)
          .setParameter("uuid", uuid)
          .getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // get All States
  public List<StateEntity> getAllStates() {
    try {
      return entityManager.createNamedQuery("getAllStates", StateEntity.class).getResultList();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // save Address
  public CustomerEntity saveAddress(CustomerEntity customerEntity) {
    entityManager.persist(customerEntity);
    return customerEntity;
  }

  // get Address By Uuid
  public AddressEntity getAddressByUuid(String uuid) {
    try {
      return entityManager
          .createNamedQuery("AddressUuid", AddressEntity.class)
          .setParameter("uuid", uuid)
          .getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

  // delete Address
  public CustomerEntity deleteAddress(CustomerEntity customerEntity, AddressEntity addressEntity) {
    entityManager.persist(customerEntity);
    deleteAddressWIthUuid(addressEntity);
    return customerEntity;
  }

  // delete Address With Uuid
  public void deleteAddressWIthUuid(AddressEntity addressEntity) {
    entityManager.remove(addressEntity);
  }

  // get All Saved Addresses
  public List<AddressEntity> getAllSavedAddresses() {
    try {
      return entityManager
          .createNamedQuery("getAllSavedAddresses", AddressEntity.class)
          .getResultList();
    } catch (NoResultException exception) {
      return null;
    }
  }
}
