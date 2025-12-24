/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MusicModel;
import View.Test;

/**
 *
 * @author Abhyudaya Shrestha
 */
public class ManageProfiles{
    private MusicModel model;
    
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
    
    
    public ManageProfiles(MusicModel model){
        this.model = model;
        
    
    }
    
    public ValidationResult validateLogin(String username, String password, boolean isAdminLogin) {
        // Input validation
        if (username == null || username.isEmpty()) {
            return new ValidationResult(false, "Username cannot be empty", false);
        }
        
        if (password == null || password.isEmpty()) {
            return new ValidationResult(false, "Password cannot be empty", false);
        }
        
        if (password.length() < 6) {
            return new ValidationResult(false, "Password must be at least 6 characters long", false);
        }
        
        // Authenticate based on user type
        if (isAdminLogin) {
            if (model.isValidAdmin(username, password)) {
                return new ValidationResult(true, "Admin Login Successful", true);
            } else {
                return new ValidationResult(false, "Invalid credentials", false);
            }
        } else {
            if (model.isValidUser(username, password)) {
                return new ValidationResult(true, "User Login Successful", false);
            } else {
                return new ValidationResult(false, "Invalid credentials", false);
            }
        }
    }                
    
    // Register new user - returns result, doesn't show popup
    public ValidationResult registerUser(String username, String password) {
        // Validation
        if (username == null || username.isEmpty()) {
            return new ValidationResult(false, "Username cannot be empty", false);
        }
        
        if (password == null || password.isEmpty()) {
            return new ValidationResult(false, "Password cannot be empty", false);
        }
        
        if (password.length() < 6) {
            return new ValidationResult(false, "Password must be at least 6 characters long", false);
        }
        
        // Check if username already exists
        if (model.usernameExists(username)) {
            return new ValidationResult(false, "Username already exists. Please choose another.", false);
        }
        
        // Register based on type
            model.addUser(username, password);
            return new ValidationResult(true, "User account created successfully", false);        
    }
}


