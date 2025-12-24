/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Abhyudaya Shrestha
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class SongPlaylist {
private LinkedList<Song> playlist;    
    
    
    public SongPlaylist() {
    this.playlist = new LinkedList<>();
    }
    
    // Add song at the end (for playlist building)
   // Add song at the end (for playlist building)
public void addSong(Song song) {
    playlist.add(song);
}

// Insert at specific position
public boolean insertAt(int position, Song song) {
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
public Song getSongAt(int position) {
    if (position < 0 || position >= playlist.size()) {
        return null;
    }
    return playlist.get(position);
}

// Convert to ArrayList for display
public ArrayList<Song> toArrayList() {
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

public int getSize() {
    return playlist.size();
}

public boolean isEmpty() {
    return playlist.isEmpty();
}
}