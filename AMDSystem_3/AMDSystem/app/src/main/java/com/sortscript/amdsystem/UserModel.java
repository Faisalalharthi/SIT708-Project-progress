package com.sortscript.amdsystem;

import java.io.Serializable;

public class UserModel implements Serializable {
    String UserEmail,UserName,UserPhone,UserPassword;

    public UserModel() {
    }

    public UserModel(String userEmail, String userName, String userPhone, String userPassword) {
        UserEmail = userEmail;
        UserName = userName;
        UserPhone = userPhone;
        UserPassword = userPassword;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
