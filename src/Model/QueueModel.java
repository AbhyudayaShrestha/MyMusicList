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

//Queue based of on Arraylist
public class QueueModel {
    private final int queueSize = 10;
    private SongModel[] queue;
    private int front;
    private int rear;
    private SongModel currentlyPlaying;

    //constructor for initialization    
    public QueueModel() {
        this.queue = new SongModel[queueSize];
        this.front = -1;
        this.rear = -1;
        this.currentlyPlaying = null;
    }
    

     //Add song to the end of queue
     //Provides boolean value true if it was done otherwise false since queue would be full
    
    public boolean addToQueue(SongModel song) {
            try {
                if (isFull()) {
                return false;  
                } else {
                if (front == -1) {
                    front = 0; 
                }
                rear++;
                queue[rear] = song;
                return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Queue array index out of bounds: " + e.getMessage());
            return false;
        }
    }   
    
     //Check if queue is full
    
    public boolean isFull() {
        return rear == queueSize - 1;
    }
    
    
     //Check if queue is empty
    
    public boolean isEmpty() {
        return front == -1 || front > rear;
    }
    
    
     //Get currently playing song
    
    public SongModel getCurrentlyPlaying() {
        return currentlyPlaying;
    }
    
    
     //Play next song by removing current song and plays next in queue
     //Returns the next song or null if queue is mpty
    
    public SongModel playNext() {
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
    
    
    //Checking out the next song
    
    public SongModel peekNext() {
        if (front == -1 || front > rear) {
            return null;
        }
        return queue[front];
    }
    
    
    
    
    //Get current queue queueSize
     
    public int getSize() {
        if (front == -1 || front > rear) {
            return 0;
        }
        return (rear - front + 1);
    }
    
    //Max queue queueSize
    
    public int getMaxSize() {
        return queueSize;
    }
    
    //Clear queue
    
    public void clear() {
        queue = new SongModel[queueSize];
        front = -1;
        rear = -1;
        currentlyPlaying = null;
    }

    //Get all songs in queue as ArrayList to display it
    
    public ArrayList<SongModel> getAllSongs() {
    ArrayList<SongModel> list = new ArrayList<>();
    
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
    
    
