/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Abhyudaya Shrestha
 */
public class SongQueue {
    private final int SIZE = 10;  // Maximum queue size
    private Song[] queue;
    private int front;
    private int rear;
    private Song currentlyPlaying;
    
    public SongQueue() {
        this.queue = new Song[SIZE];
        this.front = -1;
        this.rear = -1;
        this.currentlyPlaying = null;
    }
    
    /**
     * Add song to the end of queue
     * Returns true if successful, false if queue is full
     */
    public boolean addToQueue(Song song) {
        if (rear == SIZE - 1) {
            return false;  // Queue is full
        } else {
            if (front == -1) {
                front = 0;  // First element
            }
            rear++;
            queue[rear] = song;
            return true;
        }
    }
    
    /**
     * Check if queue is full
     */
    public boolean isFull() {
        return rear == SIZE - 1;
    }
    
    /**
     * Check if queue is empty
     */
    public boolean isEmpty() {
        return front == -1 || front > rear;
    }
    
    /**
     * Get currently playing song
     */
    public Song getCurrentlyPlaying() {
        return currentlyPlaying;
    }
    
    /**
     * Play next song - removes current song and plays next in queue
     * Returns the next song, or null if queue is empty
     */
    public Song playNext() {
        if (front > rear || front == -1) {
            // Queue is empty
            currentlyPlaying = null;
            front = -1;
            rear = -1;
            return null;
        } else {
            currentlyPlaying = queue[front];
            front++;
            return currentlyPlaying;
        }
    }
    
    /**
     * Peek at next song without removing it
     */
    public Song peekNext() {
        if (front == -1 || front > rear) {
            return null;
        }
        return queue[front];
    }
    
    /**
     * Get all songs in queue as ArrayList for display
     */
    public ArrayList<Song> getQueueAsList() {
        ArrayList<Song> list = new ArrayList<>();
        
        if (front == -1 || front > rear) {
            return list;  // Empty list
        }
        
        for (int i = front; i <= rear; i++) {
            if (queue[i] != null) {
                list.add(queue[i]);
            }
        }
        return list;
    }
    
    /**
     * Get current queue size (number of songs waiting)
     */
    public int getSize() {
        if (front == -1 || front > rear) {
            return 0;
        }
        return (rear - front + 1);
    }
    
    /**
     * Get maximum queue capacity
     */
    public int getMaxSize() {
        return SIZE;
    }
    
    /**
     * Clear the entire queue
     */
    public void clear() {
        queue = new Song[SIZE];
        front = -1;
        rear = -1;
        currentlyPlaying = null;
    }
    /**
 * Get all songs in queue as ArrayList for display
 */
public ArrayList<Song> getAllSongs() {
    ArrayList<Song> list = new ArrayList<>();
    
    if (front == -1 || front > rear) {
        return list;  // Empty list
    }
    
    for (int i = front; i <= rear; i++) {
        if (queue[i] != null) {
            list.add(queue[i]);
        }
    }
    return list;
}
}
    
    
