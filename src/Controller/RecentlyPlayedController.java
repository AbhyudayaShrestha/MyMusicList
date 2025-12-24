/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.RecentlyPlayedStack;
import Model.Song;
import java.util.ArrayList;
/**
 *
 * @author Abhyudaya Shrestha
 */
public class RecentlyPlayedController {
        private RecentlyPlayedStack recentlyPlayedStack;
    
    // Validation result class
    public class StackResult {
        public boolean success;
        public String message;
        public Song song;
        
        public StackResult(boolean success, String message, Song song) {
            this.success = success;
            this.message = message;
            this.song = song;
        }
    }
    
    public RecentlyPlayedController() {
        this.recentlyPlayedStack = new RecentlyPlayedStack();
    }
    
    /**
     * Add song to recently played stack
     */
    public StackResult addPlayedSong(Song song) {
        if (song == null) {
            return new StackResult(false, "Invalid song!", null);
        }
        
        if (recentlyPlayedStack.addSongNoDuplicates(song)) {
            return new StackResult(true, "Song added to recently played!", song);
        } else {
            return new StackResult(false, "Failed to add song!", null);
        }
    }
    
    /**
     * Get most recently played song (PEEK)
     */
    public Song getMostRecentSong() {
        return recentlyPlayedStack.peek();
    }
    
    /**
     * Remove most recent song (POP)
     */
    public StackResult removeMostRecentSong() {
        if (recentlyPlayedStack.isEmpty()) {
            return new StackResult(false, "Recently played list is empty!", null);
        }
        
        Song removedSong = recentlyPlayedStack.pop();
        return new StackResult(true, "Removed from recently played", removedSong);
    }
    
    /**
     * Get all recently played songs
     */
    public ArrayList<Song> getRecentlyPlayedSongs() {
        return recentlyPlayedStack.getAllSongs();
    }
    
    /**
     * Get recently played count
     */
    public int getRecentlyPlayedCount() {
        return recentlyPlayedStack.getSize();
    }
    
    /**
     * Get maximum capacity
     */
    public int getMaxCapacity() {
        return recentlyPlayedStack.getMaxSize();
    }
    
    /**
     * Check if recently played is empty
     */
    public boolean isRecentlyPlayedEmpty() {
        return recentlyPlayedStack.isEmpty();
    }
    
    /**
     * Check if recently played is full
     */
    public boolean isRecentlyPlayedFull() {
        return recentlyPlayedStack.isFull();
    }
    
    /**
     * Clear all recently played songs
     */
    public StackResult clearRecentlyPlayed() {
        recentlyPlayedStack.clear();
        return new StackResult(true, "Recently played list cleared!", null);
    }
    
    /**
     * Search for song in recently played
     */
    public boolean isSongInRecentlyPlayed(Song song) {
        return recentlyPlayedStack.containsSong(song);
    }
}
