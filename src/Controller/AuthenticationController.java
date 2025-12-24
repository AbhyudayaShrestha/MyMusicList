/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.UserModel;


/**
 *
 * @author Abhyudaya Shrestha
 */
public class AuthenticationController{

    private UserModel userModel;
    
    public class ValidationResult {
        public boolean success;
        public String message;
        public boolean isAdmin;
        
        public ValidationResult(boolean success, String message, boolean isAdmin) {
            this.success = success;
            this.message = message;
            this.isAdmin = isAdmin;
        }
    }
    
    public AuthenticationController(UserModel userModel) {
        this.userModel = userModel;
    }
    
    public ValidationResult validateLogin(String username, String password, boolean isAdminLogin) {
        if (username == null || username.isEmpty()) {
            return new ValidationResult(false, "Username cannot be empty", false);
        }
        
        if (password == null || password.isEmpty()) {
            return new ValidationResult(false, "Password cannot be empty", false);
        }
        
        if (password.length() < 6) {
            return new ValidationResult(false, "Password must be at least 6 characters long", false);
        }
        
        if (isAdminLogin) {
            if (userModel.isValidAdmin(username, password)) {
                return new ValidationResult(true, "Admin Login Successful", true);
            } else {
                return new ValidationResult(false, "Invalid credentials", false);
            }
        } else {
            if (userModel.isValidUser(username, password)) {
                return new ValidationResult(true, "User Login Successful", false);
            } else {
                return new ValidationResult(false, "Invalid credentials", false);
            }
        }
    }
    
    public ValidationResult registerUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            return new ValidationResult(false, "Username cannot be empty", false);
        }
        
        if (password == null || password.isEmpty()) {
            return new ValidationResult(false, "Password cannot be empty", false);
        }
        
        if (password.length() < 6) {
            return new ValidationResult(false, "Password must be at least 6 characters long", false);
        }
        
        if (userModel.usernameExists(username)) {
            return new ValidationResult(false, "Username already exists. Please choose another.", false);
        }
        
        userModel.addUser(username, password);
        return new ValidationResult(true, "User account created successfully", false);
    }
}