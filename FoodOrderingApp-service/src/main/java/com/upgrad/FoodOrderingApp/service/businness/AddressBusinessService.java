package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AddressBusinessService {


    @Autowired
    private AddressDao addressDao;

    @Transactional
    public StateEntity getStateByUuid(String uuid) {
        StateEntity stateEntity =  addressDao.getStateByUuid(uuid) ;
        return stateEntity;
    }

    @Transactional
    public AddressEntity saveAddress(AddressEntity addressEntity, CustomerEntity customerEntity){
        addressEntity.setUuid(UUID.randomUUID().toString());
        addressEntity.setActive(1);
        List<CustomerEntity> customerWithAddressList = new ArrayList<CustomerEntity>();
        customerWithAddressList.add(customerEntity);
        addressEntity.setCustomers(customerWithAddressList);
        return addressDao.saveAddress(addressEntity);
    }

    @Transactional
    public List<AddressEntity> getAllSavedAddresses() {
        return addressDao.getAllSavedAddresses();
    }


    @Transactional
    public List<StateEntity> getAllStates() {
        return addressDao.getAllStates();
    }

    @Transactional
    public AddressEntity getAddressByUuid(final String uuid) {
        AddressEntity addressEntity = addressDao.getAddressByUuid(uuid);
        return addressEntity;
    }

    @Transactional
    public AddressEntity deleteAddress(AddressEntity addressEntity) {
        return  addressDao.deleteAddress(addressEntity);
    }

    public boolean isPincodeValid(String pincode) {
        if(pincode.length()!=6) {
            return false;
        }
        else {
            char[] charsInPinCode = pincode.toCharArray();
            boolean notPureNumber = false;
            for (char c: charsInPinCode) {
                if (c > 57 || c<48) {
                    notPureNumber = true;
                }
            }
            if (notPureNumber) {
                return false;
            }
            else {
                return true;
            }
        }
    }

}
