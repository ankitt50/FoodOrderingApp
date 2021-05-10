package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

// Service layer for the Address controller
@Service
public class AddressBusinessService {

  @Autowired private AddressDao addressDao;

  // get state entity by UUID
  @Transactional
  public StateEntity getStateByUuid(String uuid) {
    StateEntity stateEntity = addressDao.getStateByUuid(uuid);
    return stateEntity;
  }

  // save address entity
  @Transactional
  public AddressEntity saveAddress(AddressEntity addressEntity, CustomerEntity customerEntity) {
    addressEntity.setUuid(UUID.randomUUID().toString());
    addressEntity.setActive(1);
    List<AddressEntity> customersAddressesList = customerEntity.getAddresses();
    customersAddressesList.add(addressEntity);

    CustomerEntity updatedCustomer = addressDao.saveAddress(customerEntity);
    boolean isAddressSaved = false;
    for (AddressEntity e : updatedCustomer.getAddresses()) {
      if (e.getUuid() == addressEntity.getUuid()) {
        isAddressSaved = true;
      }
    }

    if (isAddressSaved) {
      return addressEntity;
    } else {
      return null;
    }
  }

  // get list of saved addresses
  @Transactional
  public List<AddressEntity> getAllSavedAddresses() {
    return addressDao.getAllSavedAddresses();
  }

  // get all states
  @Transactional
  public List<StateEntity> getAllStates() {
    return addressDao.getAllStates();
  }

  // get address entity by UUID
  @Transactional
  public AddressEntity getAddressByUuid(String uuid) {
    return addressDao.getAddressByUuid(uuid);
  }

  // delete address entity
  @Transactional
  public AddressEntity deleteAddress(AddressEntity addressEntity, CustomerEntity customerEntity) {
    int index = -1;
    int currentIndex = 0;
    List<AddressEntity> customersAddressesList = customerEntity.getAddresses();
    for (AddressEntity e : customersAddressesList) {
      if (e.getUuid() == addressEntity.getUuid()) {
        index = currentIndex;
      }
      currentIndex++;
    }
    customersAddressesList.remove(index);
    CustomerEntity savedCustomer = addressDao.deleteAddress(customerEntity, addressEntity);
    boolean isAddressDeleted = true;
    for (AddressEntity e : savedCustomer.getAddresses()) {
      if (e.getUuid() == addressEntity.getUuid()) {
        isAddressDeleted = false;
      }
    }

    if (isAddressDeleted) {
      return addressEntity;
    } else {
      return null;
    }
  }

  // check for pin code validity
  public boolean isPincodeValid(String pincode) {
    if (pincode.length() != 6) {
      return false;
    } else {
      char[] charsInPinCode = pincode.toCharArray();
      boolean notPureNumber = false;
      for (char c : charsInPinCode) {
        if (c > 57 || c < 48) {
          notPureNumber = true;
        }
      }
      if (notPureNumber) {
        return false;
      } else {
        return true;
      }
    }
  }
}
