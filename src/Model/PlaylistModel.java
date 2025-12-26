/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Abhyudaya Shrestha
 */


//Link list to manage songs in a playlist

public class PlaylistModel {
    private LinkedList<SongModel> playlist;    
    
    
    public PlaylistModel() {
        this.playlist = new LinkedList<>();
    }
    
    // Add song at the end 
    
    public void addSong(SongModel song) {
        playlist.add(song);
    }

    // Insert at specific position
    
    public boolean insertAt(int position, SongModel song) {
        if (position < 0 || position > playlist.size()) {
            return false;
        }
        playlist.add(position, song);
        return true;
    }

    // Delete song at position
    
    public boolean deleteAt(int position) {
        if (position < 0 || position >= playlist.size()) {
            return false;
        }
        playlist.remove(position);
        return true;
    }

    // Get song at position
    
    public SongModel getSongAt(int position) {
        if (position < 0 || position >= playlist.size()) {
            return null;
        }
        return playlist.get(position);
    }

    // Convert to ArrayList for display
    public ArrayList<SongModel> toArrayList() {
        return new ArrayList<>(playlist);
    }

    // Search for a song
    public int findSong(String songName) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getSongName().equalsIgnoreCase(songName)) {
                return i;
            }
        }
        return -1;
    }

    // Clear playlist
    public void clear() {
        playlist.clear();
    }
    
    //Size of playlist
    public int getSize() {
        return playlist.size();
    }   
    
    //Checks if playlist is empty
    public boolean isEmpty() {
        return playlist.isEmpty();
    }
}