package com.mybank.entity;

import javax.persistence.*;

@Entity
@Table(name="userTable")
public class UserEntity {

    public UserEntity(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String emailId;

    private String password;

    private String userName;

    private int age;


    @Column(unique = true, nullable = false)
    private String phoneNumber;
    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserEntity(int id, String emailId, String password, String userName, int age, String phoneNumber) {
        this.id = id;
        this.emailId = emailId;
        this.password = password;
        this.userName = userName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
