/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.SessionModel;
import java.util.HashMap;

/**
 *
 * @author Abhyudaya Shrestha
 */

//Manages user sessions and track of current users in the program

public class SessionController {
    private HashMap<String, SessionModel> userSessions;
    private SessionModel currentSession;
    private String currentUsername;
    
    public SessionController() {
        this.userSessions = new HashMap<>();
        this.currentSession = null;
        this.currentUsername = "";
    }
    
    
    //Starts a new session for a user
    
    public HashMap<String, Object> startSession(String username) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Invalid username");
            result.put("session", null);
            return result;
        }
        
        // Get or create session
        if (!userSessions.containsKey(username)) {
            userSessions.put(username, new SessionModel(username));
        }
        
        currentSession = userSessions.get(username);
        currentUsername = username;
        
        result.put("success", true);
        result.put("message", "Session started for " + username);
        result.put("session", currentSession);
        return result;
    }
    
    
    //Ends current session
    
    public HashMap<String, Object> endSession() {
        HashMap<String, Object> result = new HashMap<>();
        
        if (currentSession == null) {
            result.put("success", false);
            result.put("message", "No active session");
            result.put("session", null);
            return result;
        }
        
        String username = currentUsername;
        currentSession = null;
        currentUsername = "";
        
        result.put("success", true);
        result.put("message", "Session ended for " + username);
        result.put("session", null);
        return result;
    }
    
    
    //Gets current active session
    
    public SessionModel getCurrentSession() {
        return currentSession;
    }
    
    
    // Get current username
    
    public String getCurrentUsername() {
        return currentUsername;
    }
    
    
    //Check if there's an active session
    
    public boolean hasActiveSession() {
        return currentSession != null;
    }
    
    
    //Get queue controller for current session
    
    public QueueController getCurrentQueueController() {
        if (currentSession == null) {
            return null;
        }
        return currentSession.getQueueController();
    }
    
    
    //Get stack controller for current session
    
    public StackController getCurrentStackController() {
        if (currentSession == null) {
            return null;
        }
        return currentSession.getRecentlyPlayedController();
    }
}