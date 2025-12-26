/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;

/**
 * @author Abhyudaya Shrestha
 */
public class QueueModel {
    private final int queueSize = 10;
    private SongModel[] queue;
    private int front;
    private int rear;
    private int count;  
    private SongModel currentlyPlaying;
    
    public QueueModel() {
        this.queue = new SongModel[queueSize];
        this.front = 0;
        this.rear = -1;
        this.count = 0;  
        this.currentlyPlaying = null;
    }
    
    // Add song to the end of queue
    public boolean addToQueue(SongModel song) {
        try {
            if (isFull()) {
                return false;  
            }
            
            
            rear = (rear + 1) % queueSize;
            queue[rear] = song;
            count++;  // 
            return true;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Queue array index out of bounds: " + e.getMessage());
            return false;
        }
    }
    
    // Check if queue is full
    public boolean isFull() {
        return count == queueSize;  
    }
    
    // Check if queue is empty
    public boolean isEmpty() {
        return count == 0;  
    }
    
    // Get currently playing song
    public SongModel getCurrentlyPlaying() {
        return currentlyPlaying;
    }
    
    // Play next song
    public SongModel playNext() {
        if (isEmpty()) {
            currentlyPlaying = null;
            return null;
        }
        
        currentlyPlaying = queue[front];
        queue[front] = null;  
        front = (front + 1) % queueSize;  
        count--;  
        
        return currentlyPlaying;
    }
    
    // Peek at next song
    public SongModel peekNext() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }
    
    // Get current queue size
    public int getSize() {
        return count;  
    }
    
    // Max queue size
    public int getMaxSize() {
        return queueSize;
    }
    
    // Clear queue
    public void clear() {
        queue = new SongModel[queueSize];
        front = 0;
        rear = -1;
        count = 0;  
        currentlyPlaying = null;
    }
    
    // Get all songs in queue as ArrayList
    public ArrayList<SongModel> getAllSongs() {
        ArrayList<SongModel> list = new ArrayList<>();
        
        if (isEmpty()) {
            return list;
        }
        
        int current = front;
        for (int i = 0; i < count; i++) {
            if (queue[current] != null) {
                list.add(queue[current]);
            }
            current = (current + 1) % queueSize; 
        }
        
        return list;
    }
}
    
