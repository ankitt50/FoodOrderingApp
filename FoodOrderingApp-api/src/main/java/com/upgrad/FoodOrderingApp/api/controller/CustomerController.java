package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.service.businness.CustomerBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;

@RestController
@RequestMapping
public class CustomerController {


    @Autowired
    private CustomerBusinessService customerBusinessService;


    @RequestMapping(method = RequestMethod.POST, path = "/customer/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> customerSignup(final SignupCustomerRequest signupCustomerRequest) throws SignUpRestrictedException {

        final CustomerEntity newCustomer = new CustomerEntity();

        boolean emptyFieldFound = false;

        if (!signupCustomerRequest.getFirstName().equals("")) {
            newCustomer.setFirstName(signupCustomerRequest.getFirstName());
        }
        else {
            emptyFieldFound = true;
        }

        newCustomer.setLastName(signupCustomerRequest.getLastName());

        if(!signupCustomerRequest.getEmailAddress().equals("")) {
            newCustomer.setEmail(signupCustomerRequest.getEmailAddress());
        }
        else {
            emptyFieldFound = true;
        }

        if(!signupCustomerRequest.getPassword().equals("")) {
            newCustomer.setPassword(signupCustomerRequest.getPassword());
        }
        else {
            emptyFieldFound = true;
        }

        if(!signupCustomerRequest.getContactNumber().equals("")) {
            newCustomer.setContactNumber(signupCustomerRequest.getContactNumber());
        }
        else {
            emptyFieldFound = true;
        }

        if (emptyFieldFound) {
            throw new SignUpRestrictedException("SGR-005", "Except last name all fields should be filled");
        }
        else {
            CustomerEntity savedCustomer = customerBusinessService.customerSignup(newCustomer);
        }

        SignupCustomerResponse signupCustomerResponse = new SignupCustomerResponse().id(savedCustomer.getUuid()).status("CUSTOMER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupCustomerResponse>(signupCustomerResponse, HttpStatus.CREATED);

    }

}

