/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;



import Model.Song;
import Model.SongQueue;
import java.util.ArrayList;
import Model.RecentlyPlayedStack;
/**
 *
 * @author Abhyudaya Shrestha
 */
public class QueueController {
    private SongQueue songQueue;
    
    // Validation result class
    public class QueueResult {
        public boolean success;
        public String message;
        public Song song;
        
        public QueueResult(boolean success, String message, Song song) {
            this.success = success;
            this.message = message;
            this.song = song;
        }
    }
    
    public QueueController() {
        this.songQueue = new SongQueue();   
    }
    
    /**
     * Add song to end of queue with validation
     */
    public QueueResult addToQueue(Song song) {
        if (song == null) {
            return new QueueResult(false, "Invalid song!", null);
        }
        
        if (songQueue.isFull()) {
            return new QueueResult(false, "The queue is full! Maximum " + 
                                 songQueue.getMaxSize() + " songs allowed.", null);
        }
        
        if (songQueue.addToQueue(song)) {
            return new QueueResult(true, "Song added to queue!", song);
        } else {
            return new QueueResult(false, "Failed to add song to queue!", null);
        }
    }
    
    /**
     * Get currently playing song
     */
    public Song getCurrentSong() {
        return songQueue.getCurrentlyPlaying();
    }
    
    /**
     * Play next song (removes current and plays next in queue)
     */
    public QueueResult playNextSong() {
        if (songQueue.isEmpty()) {
            return new QueueResult(false, "The queue is empty!", null);
        }
        
        Song nextSong = songQueue.playNext();
        
        if (nextSong != null) {
            return new QueueResult(true, "Now Playing", nextSong);
        } else {
            return new QueueResult(false, "No more songs in queue!", null);
        }
    }
    
    /**
     * Peek at next song without removing it
     */
    public Song peekNextSong() {
        return songQueue.peekNext();
    }
    
    /**
     * Get all songs in queue as list
     */
    public ArrayList<Song> getQueueList() {
        return songQueue.getAllSongs();
    }
    
    /**
     * Get current queue size
     */
    public int getQueueSize() {
        return songQueue.getSize();
    }
    
    /**
     * Get maximum queue capacity
     */
    public int getMaxQueueSize() {
        return songQueue.getMaxSize();
    }
    
    /**
     * Check if queue is empty
     */
    public boolean isQueueEmpty() {
        return songQueue.isEmpty();
    }
    
    /**
     * Check if queue is full
     */
    public boolean isQueueFull() {
        return songQueue.isFull();
    }
    
    /**
     * Clear the queue
     */
    public void clearQueue() {
        songQueue.clear();
    }
}
    

