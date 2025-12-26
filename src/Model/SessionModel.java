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

//Created to store personal data of queue and recently played for each user
public class SessionModel {
    private String username;
    private QueueController queueController;
    private StackController recentlyPlayedController;
    
    public SessionModel(String username) {
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
    

