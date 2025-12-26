/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StackModel;
import Model.SongModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Abhyudaya Shrestha
 */

//Manages stack operations using stack model

public class StackController {
        private StackModel recentlyPlayedStack;
    
    public StackController() {
        this.recentlyPlayedStack = new StackModel();
    }
    
    
    //Add song to recently played stack
    
    public HashMap<String, Object> addPlayedSong(SongModel song) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (song == null) {
            result.put("success", false);
            result.put("message", "Invalid song!");
            result.put("song", null);
            return result;
        }
        
        if (recentlyPlayedStack.addSongNoDuplicates(song)) {
            result.put("success", true);
            result.put("message", "Song added to recently played!");
            result.put("song", song);
        } else {
            result.put("success", false);
            result.put("message", "Failed to add song!");
            result.put("song", null);
        }
        return result;
    }
    
    
    //Get most recently played song
    
    public SongModel getMostRecentSong() {
        return recentlyPlayedStack.peek();
    }
    
    
    //Remove most recent song 
    
    public HashMap<String, Object> removeMostRecentSong() {
        HashMap<String, Object> result = new HashMap<>();
        
        if (recentlyPlayedStack.isEmpty()) {
            result.put("success", false);
            result.put("message", "Recently played list is empty!");
            result.put("song", null);
            return result;
        }
        
        SongModel removedSong = recentlyPlayedStack.pop();
        result.put("success", true);
        result.put("message", "Removed from recently played");
        result.put("song", removedSong);
        return result;
    }
    
    
    //Get all recently played songs
    
    public ArrayList<SongModel> getRecentlyPlayedSongs() {
        return recentlyPlayedStack.getAllSongs();
    }
    
    
    //Get recently played count
    
    public int getRecentlyPlayedCount() {
        return recentlyPlayedStack.getSize();
    }
    
    
    //Get maximum capacity
    
    public int getMaxCapacity() {
        return recentlyPlayedStack.getMaxSize();
    }
    
    
    //Check if recently played is empty
    
    public boolean isRecentlyPlayedEmpty() {
        return recentlyPlayedStack.isEmpty();
    }
    
    
    //Check if recently played is full
    
    public boolean isRecentlyPlayedFull() {
        return recentlyPlayedStack.isFull();
    }
    
    
    //Clear all recently played songs
    
    public HashMap<String, Object> clearRecentlyPlayed() {
        HashMap<String, Object> result = new HashMap<>();
        recentlyPlayedStack.clear();
        result.put("success", true);
        result.put("message", "Recently played list cleared!");
        result.put("song", null);
        return result;
    }
    
    
    //Search for song in recently played
    
    public boolean isSongInRecentlyPlayed(SongModel song) {
        return recentlyPlayedStack.containsSong(song);
    }
}