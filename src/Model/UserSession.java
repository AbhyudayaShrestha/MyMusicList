/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.QueueController;
import Controller.StackController;

/**
 *
 * @author Abhyudaya Shrestha
 */
public class UserSession {
    private String username;
    private QueueController queueController;
    private StackController recentlyPlayedController;
    
    public UserSession(String username) {
        this.username = username;
        this.queueController = new QueueController();
        this.recentlyPlayedController = new StackController();
    }
    
    public String getUsername() {
        return username;
    }
    
    public QueueController getQueueController() {
        return queueController;
    }
    
    public StackController getRecentlyPlayedController() {
        return recentlyPlayedController;
    }
}
    

