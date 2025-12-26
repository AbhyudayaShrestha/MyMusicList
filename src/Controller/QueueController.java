/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.SongModel;
import Model.QueueModel;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Abhyudaya Shrestha
 */

//Handles queue operations using queue model
public class QueueController {
    private QueueModel songQueue;
    
    public QueueController() {
        this.songQueue = new QueueModel();   
    }
    
    
    //Add song to end of queue with validation
    
    public HashMap<String, Object> addToQueue(SongModel song) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (song == null) {
            result.put("success", false);
            result.put("message", "Invalid song!");
            result.put("song", null);
            return result;
        }
        
        if (songQueue.isFull()) {
            result.put("success", false);
            result.put("message", "The queue is full! Maximum " + songQueue.getMaxSize() + " songs allowed.");
            result.put("song", null);
            return result;
        }
        
        if (songQueue.addToQueue(song)) {
            result.put("success", true);
            result.put("message", "Song added to queue!");
            result.put("song", song);
        } else {
            result.put("success", false);
            result.put("message", "Failed to add song to queue!");
            result.put("song", null);
        }
        return result;
    }
    
    
    //Get currently playing song
    
    public SongModel getCurrentSong() {
        return songQueue.getCurrentlyPlaying();
    }
    
    
    //Play next song by removing current and playing next in queue)
    
    public HashMap<String, Object> playNextSong() {
        HashMap<String, Object> result = new HashMap<>();
        
        if (songQueue.isEmpty()) {
            result.put("success", false);
            result.put("message", "The queue is empty!");
            result.put("song", null);
            return result;
        }
        
        SongModel nextSong = songQueue.playNext();
        
        if (nextSong != null) {
            result.put("success", true);
            result.put("message", "Now Playing");
            result.put("song", nextSong);
        } else {
            result.put("success", false);
            result.put("message", "No more songs in queue!");
            result.put("song", null);
        }
        return result;
    }
    
    
    //Peek at next song without removing it
    
    public SongModel peekNextSong() {
        return songQueue.peekNext();
    }
    
    
    //Get all songs in queue as list
    
    public ArrayList<SongModel> getQueueList() {
        return songQueue.getAllSongs();
    }
    
    
    //Get current queue size
    
    public int getQueueSize() {
        return songQueue.getSize();
    }
    
    
    //Get maximum queue capacity
    
    public int getMaxQueueSize() {
        return songQueue.getMaxSize();
    }
    
    
    //Check if queue is empty
    
    public boolean isQueueEmpty() {
        return songQueue.isEmpty();
    }
    
    
    //Check if queue is full
    
    public boolean isQueueFull() {
        return songQueue.isFull();
    }
    
    
    //Clear the queue
    
    public void clearQueue() {
        songQueue.clear();
    }
}