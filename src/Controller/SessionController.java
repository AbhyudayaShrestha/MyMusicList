/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserSession;
import java.util.HashMap;

/**
 *
 * @author Abhyudaya Shrestha
 */
public class SessionController {
     private HashMap<String, UserSession> userSessions;
    private UserSession currentSession;
    private String currentUsername;
    
    public class SessionResult {
        public boolean success;
        public String message;
        public UserSession session;
        
        public SessionResult(boolean success, String message, UserSession session) {
            this.success = success;
            this.message = message;
            this.session = session;
        }
    }
    
    public SessionController() {
        this.userSessions = new HashMap<>();
        this.currentSession = null;
        this.currentUsername = "";
    }
    
    /**
     * Start a new session for a user
     */
    public SessionResult startSession(String username) {
        if (username == null || username.trim().isEmpty()) {
            return new SessionResult(false, "Invalid username", null);
        }
        
        // Get or create session
        if (!userSessions.containsKey(username)) {
            userSessions.put(username, new UserSession(username));
        }
        
        currentSession = userSessions.get(username);
        currentUsername = username;
        
        return new SessionResult(true, "Session started for " + username, currentSession);
    }
    
    /**
     * End current session
     */
    public SessionResult endSession() {
        if (currentSession == null) {
            return new SessionResult(false, "No active session", null);
        }
        
        String username = currentUsername;
        currentSession = null;
        currentUsername = "";
        
        return new SessionResult(true, "Session ended for " + username, null);
    }
    
    /**
     * Get current active session
     */
    public UserSession getCurrentSession() {
        return currentSession;
    }
    
    /**
     * Get current username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }
    
    /**
     * Check if there's an active session
     */
    public boolean hasActiveSession() {
        return currentSession != null;
    }
    
    /**
     * Get queue controller for current session
     */
    public QueueController getCurrentQueueController() {
        if (currentSession == null) {
            return null;
        }
        return currentSession.getQueueController();
    }
    
    /**
     * Get stack controller for current session
     */
    public StackController getCurrentStackController() {
        if (currentSession == null) {
            return null;
        }
        return currentSession.getRecentlyPlayedController();
    }
}
    
