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
public class StackModel {
    private final int stackSize = 10;
    private SongModel[] stackArray; 
    private int top;            
    
    public StackModel() {
        this.stackArray = new SongModel[stackSize];
        this.top = -1;
    }
    
    
    //Adding song to stack
    // Remove oldest song and shifting all elements down
    
    public boolean push(SongModel song) {
        if (song == null) return false;
        
        if (isFull()) {
            for (int i = 0; i < stackSize - 1; i++) {
                stackArray[i] = stackArray[i + 1];
            }
            top = stackSize - 2;
        }
        
        top++;
        stackArray[top] = song;
        return true;
    }
    
    //Viewing the song at the top
    
    public SongModel peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[top];
    }
    
    //Removing and returning the top song
    
    public SongModel pop() {
        if (isEmpty()) {
            return null;
        }
        SongModel song = stackArray[top];
        stackArray[top] = null;
        top--;
        return song;
    }
    
    
    //Get all songs in stack 
    
    public ArrayList<SongModel> getAllSongs() {
        ArrayList<SongModel> songs = new ArrayList<>();
        
        for (int i = top; i >= 0; i--) {
            if (stackArray[i] != null) {
                songs.add(stackArray[i]);
            }
        }
        return songs;
    }
    
    
    //Check if stack is empty
    
    public boolean isEmpty() {
        return top == -1;
    }
    
    
    //Check if stack is full
    
    public boolean isFull() {
        return top == stackSize - 1;
    }
    
    
    //Get current stack size
    
    public int getSize() {
        return top + 1;
    }
    
    
    //Get maximum stack capacity
    
    public int getMaxSize() {
        return stackSize;
    }
    
    
    //Clear the stack
    
    public void clear() {
        for (int i = 0; i <= top; i++) {
            stackArray[i] = null;
        }
        top = -1;
    }
    
    //Checks if a song aldready is in stack
    
    public boolean containsSong(SongModel song) {
        for (int i = 0; i <= top; i++) {
            if (stackArray[i] != null && 
                stackArray[i].getSongName().equals(song.getSongName()) &&
                stackArray[i].getArtistName().equals(song.getArtistName())) {
                return true;
            }
        }
        return false;
    }
    
    //Removing duplicate songs and pushing it to the top
    
    public boolean addSongNoDuplicates(SongModel song) {
        if (song == null) return false;
        
        SongModel[] tempStack = new SongModel[stackSize];
        int tempTop = -1;
        
        while (!isEmpty()) {
            SongModel current = pop();
            if (!current.getSongName().equals(song.getSongName()) || 
                !current.getArtistName().equals(song.getArtistName())) {
                tempTop++;
                tempStack[tempTop] = current;
            }
        }
               
        while (tempTop >= 0) {
            push(tempStack[tempTop]);
            tempTop--;
        }
               
        return push(song);
    }
}
    
