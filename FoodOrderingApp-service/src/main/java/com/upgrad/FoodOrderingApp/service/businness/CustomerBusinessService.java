package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CustomerBusinessService {

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @Autowired
    private CustomerDao customerDao;

    public CustomerEntity customerSignup(CustomerEntity newCustomer) {


        String[] encryptedPasswordAndSalt = cryptographyProvider.encrypt(newCustomer.getPassword());
        newCustomer.setSalt(encryptedPasswordAndSalt[0]);
        newCustomer.setPassword(encryptedPasswordAndSalt[1]);
        newCustomer.setUuid(UUID.randomUUID().toString());

        return customerDao.customerSignup(newCustomer);
    }

}
