/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.UserModel;
import java.util.HashMap;


/**
 *
 * @author Abhyudaya Shrestha
 */

//Handles login and registration logic by communicating with UserModel

public class AuthenticationController{

    private UserModel userModel;
    
    public AuthenticationController(UserModel userModel) {
        this.userModel = userModel;
    }

    //Validates log in details and credentials
    
    public HashMap<String, Object> validateLogin(String username, String password, boolean isAdminLogin) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (username == null || username.isEmpty()) {
            result.put("success", false);
            result.put("message", "Username cannot be empty");
            result.put("isAdmin", false);
            return result;
        }
        
        if (password == null || password.isEmpty()) {
            result.put("success", false);
            result.put("message", "Password cannot be empty");
            result.put("isAdmin", false);
            return result;
        }
        
        if (password.length() < 6) {
            result.put("success", false);
            result.put("message", "Password must be at least 6 characters long");
            result.put("isAdmin", false);
            return result;
        }
        
        if (isAdminLogin) {
            if (userModel.isValidAdmin(username, password)) {
                result.put("success", true);
                result.put("message", "Admin Login Successful");
                result.put("isAdmin", true);
            } else {
                result.put("success", false);
                result.put("message", "Invalid username or password");
                result.put("isAdmin", false);
            }
        } else {
            if (userModel.isValidUser(username, password)) {
                result.put("success", true);
                result.put("message", "User Login Successful");
                result.put("isAdmin", false);
            } else {
                result.put("success", false);
                result.put("message", "Invalid username or password");
                result.put("isAdmin", false);
            }
        }
        return result;
    }
    
    //Validate registration details
    
    public HashMap<String, Object> registerUser(String username, String password) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (username == null || username.isEmpty()) {
            result.put("success", false);
            result.put("message", "Username cannot be empty");
            result.put("isAdmin", false);
            return result;
        }
        
        if (password == null || password.isEmpty()) {
            result.put("success", false);
            result.put("message", "Password cannot be empty");
            result.put("isAdmin", false);
            return result;
        }
        
        if (password.length() < 6) {
            result.put("success", false);
            result.put("message", "Password must be at least 6 characters long");
            result.put("isAdmin", false);
            return result;
        }
        
        if (userModel.usernameExists(username)) {
            result.put("success", false);
            result.put("message", "Username already exists. Please choose another.");
            result.put("isAdmin", false);
            return result;
        }
        
        userModel.addUser(username, password);
        result.put("success", true);
        result.put("message", "User account created successfully");
        result.put("isAdmin", false);
        return result;
    }
}