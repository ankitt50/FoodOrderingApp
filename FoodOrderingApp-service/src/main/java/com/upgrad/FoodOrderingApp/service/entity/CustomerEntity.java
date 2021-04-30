package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;


    @Column(name = "uuid")
    @Size(max = 200)
    private String uuid;


    @Column(name = "email")
    @Size(max = 50)
    private String email;

    @Column(name = "password")
    @Size(max = 255)
    @NotNull
    private String password;

    @Column(name = "firstname")
    @NotNull
    @Size(max = 30)
    private String firstName;

    @Column(name = "lastname")
    @Size(max = 30)
    private String lastName;

    @Column(name = "contact_number")
    @NotNull
    @Size(max = 30)
    private String contactNumber;

    @Column(name = "salt")
    @NotNull
    @Size(max = 255)
    private String salt;

    public CustomerEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }


}
