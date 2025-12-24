/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author Abhyudaya Shrestha
 */

public class UserModel {
    private HashMap<String, String> adminAccounts;
    private HashMap<String, String> userAccounts;
    
    public UserModel() {
        adminAccounts = new HashMap<>();
        userAccounts = new HashMap<>();
        initializeDefaultAccounts();
    }
    
    private void initializeDefaultAccounts() {
        adminAccounts.put("Abhyudaya", "abhyudaya");
        adminAccounts.put("Dken10", "abhyudaya");
        userAccounts.put("user1", "abhyudaya");
        userAccounts.put("user2", "abhyudaya");
    }
    
    public boolean isValidAdmin(String username, String password) {
        return adminAccounts.containsKey(username) &&
               adminAccounts.get(username).equals(password);
    }
    
    public boolean isValidUser(String username, String password) {
        return userAccounts.containsKey(username) &&
               userAccounts.get(username).equals(password);
    }
    
    public void addUser(String username, String password) {
        userAccounts.put(username, password);
    }
    
    public boolean usernameExists(String username) {
        return adminAccounts.containsKey(username) || 
               userAccounts.containsKey(username);
    }
}

