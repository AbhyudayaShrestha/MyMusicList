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
public class RecentlyPlayedStack {
        private final int STACK_SIZE = 10;
    private Song[] stackArray;  // Array-based stack like TabPanes
    private int top;            // Stack pointer
    
    public RecentlyPlayedStack() {
        this.stackArray = new Song[STACK_SIZE];
        this.top = -1;  // Empty stack
    }
    
    /**
     * PUSH operation - Add song to stack (like TabPanes push method)
     */
    public boolean push(Song song) {
        if (song == null) return false;
        
        if (isFull()) {
            // Remove oldest song (bottom of stack)
            // Shift all elements down
            for (int i = 0; i < STACK_SIZE - 1; i++) {
                stackArray[i] = stackArray[i + 1];
            }
            top = STACK_SIZE - 2; // Adjust top pointer
        }
        
        top++;
        stackArray[top] = song;
        return true;
    }
    
    /**
     * PEEK operation - View top without removing
     */
    public Song peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[top];
    }
    
    /**
     * POP operation - Remove and return top song
     */
    public Song pop() {
        if (isEmpty()) {
            return null;
        }
        Song song = stackArray[top];
        stackArray[top] = null;
        top--;
        return song;
    }
    
    /**
     * Get all songs in stack (newest first)
     */
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        
        // Start from top (newest) to bottom (oldest)
        for (int i = top; i >= 0; i--) {
            if (stackArray[i] != null) {
                songs.add(stackArray[i]);
            }
        }
        return songs;
    }
    
    /**
     * Check if stack is empty
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Check if stack is full
     */
    public boolean isFull() {
        return top == STACK_SIZE - 1;
    }
    
    /**
     * Get current stack size
     */
    public int getSize() {
        return top + 1;
    }
    
    /**
     * Get maximum stack capacity
     */
    public int getMaxSize() {
        return STACK_SIZE;
    }
    
    /**
     * Clear the stack
     */
    public void clear() {
        for (int i = 0; i <= top; i++) {
            stackArray[i] = null;
        }
        top = -1;
    }
    
    /**
     * Check if song exists in stack (prevents duplicates)
     */
    public boolean containsSong(Song song) {
        for (int i = 0; i <= top; i++) {
            if (stackArray[i] != null && 
                stackArray[i].getSongName().equals(song.getSongName()) &&
                stackArray[i].getArtistName().equals(song.getArtistName())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Remove duplicate if exists and push to top
     * Similar to TabPanes but with Song objects
     */
    public boolean addSongNoDuplicates(Song song) {
        if (song == null) return false;
        
        // Remove duplicate if exists
        Song[] tempStack = new Song[STACK_SIZE];
        int tempTop = -1;
        
        // Copy non-duplicate songs to temp stack
        while (!isEmpty()) {
            Song current = pop();
            if (!current.getSongName().equals(song.getSongName()) || 
                !current.getArtistName().equals(song.getArtistName())) {
                tempTop++;
                tempStack[tempTop] = current;
            }
        }
        
        // Restore original stack
        while (tempTop >= 0) {
            push(tempStack[tempTop]);
            tempTop--;
        }
        
        // Add new song to top
        return push(song);
    }
}
    
