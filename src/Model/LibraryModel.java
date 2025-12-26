/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Abhyudaya Shrestha
 */

//Arraylist for songs library and hash map to manage each users playlist seperately

public class LibraryModel {
    private ArrayList<SongModel> songLibrary;
    private HashMap<String, PlaylistModel> userPlaylists;
    
    public LibraryModel() {
        songLibrary = new ArrayList<>();
        userPlaylists = new HashMap<>();
    }
    
    
    // Create-Add new song to songs library
    
    public boolean addToLibrary(SongModel s) {
        if (s == null) return false;
        songLibrary.add(s);
        return true;
    }
    
    // Read-Get all songs from songs library
    
    public ArrayList<SongModel> getSongLibrary() {
        return songLibrary;
    }
    
    // Update-Update song at index in songs library
    
    public boolean updateLibrarySong(int index, SongModel updatedSong) {
        if (index < 0 || index >= songLibrary.size() || updatedSong == null) {
            return false;
        }
        songLibrary.set(index, updatedSong);
        return true;
    }
    
    // Delete-Delete song at index from songs library
    
    public boolean deleteLibrarySong(int index) {
        if (index < 0 || index >= songLibrary.size()) {
            return false;
        }
        songLibrary.remove(index); 
        return true;
    }
    
    // Search song by name in songs library
    
    public int findLibrarySongIndex(String songName) {
        for (int i = 0; i < songLibrary.size(); i++) {
            if (songLibrary.get(i).getSongName().equalsIgnoreCase(songName)) {
                return i;
            }
        }
        return -1;
    }
    
    // Get song at specific index from songs library
    
    public SongModel getLibrarySongAt(int index) {
        if (index >= 0 && index < songLibrary.size()) {
            return songLibrary.get(index);
        }
        return null;
    }
    
    // Get songs library size
    
    public int getLibrarySize() {
        return songLibrary.size();
    }
    
    // Check if songs library is empty
    
    public boolean isLibraryEmpty() {
        return songLibrary.isEmpty();
    }
    
    // Get playlist and create playlist for user
    
    public PlaylistModel getUserPlaylist(String username) {
        if (!userPlaylists.containsKey(username)) {
            userPlaylists.put(username, new PlaylistModel());
        }
        return userPlaylists.get(username);
    }
    
    // Add song from library to user's playlist
    
    public boolean addToUserPlaylist(String username, int songIndex) {
        SongModel song = getLibrarySongAt(songIndex);
        if (song == null) return false;
        
        PlaylistModel playlist = getUserPlaylist(username);
        playlist.addSong(song);
        return true;
    }
    
    // Add song object to user's playlist
    
    public boolean addToUserPlaylist(String username, SongModel song) {
        if (song == null) return false;
        
        PlaylistModel playlist = getUserPlaylist(username);
        playlist.addSong(song);
        return true;
    }
    
    // Remove song from user's playlist at the position
    
    public boolean removeFromUserPlaylist(String username, int position) {
        PlaylistModel playlist = getUserPlaylist(username);
        return playlist.deleteAt(position);
    }
    
    // Get user's playlist as ArrayList for display
    
    public ArrayList<SongModel> getUserPlaylistAsList(String username) {
        PlaylistModel playlist = getUserPlaylist(username);
        return playlist.toArrayList();
    }
    
    // Clear user's playlist
    
    public void clearUserPlaylist(String username) {
        PlaylistModel playlist = getUserPlaylist(username);
        playlist.clear();
    }
    
    // Get user playlist size
    
    public int getUserPlaylistSize(String username) {
        PlaylistModel playlist = getUserPlaylist(username);
        return playlist.getSize();
    }
    
    
    // Check if a song exists in songs library
    
    public boolean songExistsInLibrary(String songName, String artistName) {
        for (SongModel song : songLibrary) {
            if (song.getSongName().equalsIgnoreCase(songName) && 
                song.getArtistName().equalsIgnoreCase(artistName)) {
                return true;
            }
        }
        return false;
    }
}
