/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abhyudaya Shrestha
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Model for managing the song library and user playlists
 * @author Abhyudaya Shrestha
 */
public class SongLibrary {
    private ArrayList<Song> songLibrary;  // Master song library (admin manages this)
    private HashMap<String, SongPlaylist> userPlaylists; // Each user's playlist
    
    public SongLibrary() {
        songLibrary = new ArrayList<>();
        userPlaylists = new HashMap<>();
    }
    
    // ============ CRUD OPERATIONS FOR SONG LIBRARY ============
    
    // CREATE - Add new song to library
    public boolean addToLibrary(Song s) {
        if (s == null) return false;
        songLibrary.add(s);
        return true;
    }
    
    // READ - Get all songs from library
    public ArrayList<Song> getSongLibrary() {
        return songLibrary;
    }
    
    // UPDATE - Update song at index in library
    public boolean updateLibrarySong(int index, Song updatedSong) {
        if (index < 0 || index >= songLibrary.size() || updatedSong == null) {
            return false;
        }
        songLibrary.set(index, updatedSong);
        return true;
    }
    
    // DELETE - Delete song at index from library
    public boolean deleteLibrarySong(int index) {
        if (index < 0 || index >= songLibrary.size()) {
            return false;
        }
        Song deletedSong = songLibrary.get(index);
        songLibrary.remove(index);
        
        // Optional: Remove this song from all user playlists
        // removeFromAllPlaylists(deletedSong);
        
        return true;
    }
    
    // Search song by name in library
    public int findLibrarySongIndex(String songName) {
        for (int i = 0; i < songLibrary.size(); i++) {
            if (songLibrary.get(i).getSongName().equalsIgnoreCase(songName)) {
                return i;
            }
        }
        return -1;
    }
    
    // Get song at specific index from library
    public Song getLibrarySongAt(int index) {
        if (index >= 0 && index < songLibrary.size()) {
            return songLibrary.get(index);
        }
        return null;
    }
    
    // Get library size
    public int getLibrarySize() {
        return songLibrary.size();
    }
    
    // Check if library is empty
    public boolean isLibraryEmpty() {
        return songLibrary.isEmpty();
    }
    
    // ============ PLAYLIST OPERATIONS ============
    
    // Get or create playlist for user
    public SongPlaylist getUserPlaylist(String username) {
        if (!userPlaylists.containsKey(username)) {
            userPlaylists.put(username, new SongPlaylist());
        }
        return userPlaylists.get(username);
    }
    
    // Add song from library to user's playlist
    public boolean addToUserPlaylist(String username, int songIndex) {
        Song song = getLibrarySongAt(songIndex);
        if (song == null) return false;
        
        SongPlaylist playlist = getUserPlaylist(username);
        playlist.addSong(song);
        return true;
    }
    
    // Add song object to user's playlist
    public boolean addToUserPlaylist(String username, Song song) {
        if (song == null) return false;
        
        SongPlaylist playlist = getUserPlaylist(username);
        playlist.addSong(song);
        return true;
    }
    
    // Remove song from user's playlist at position
    public boolean removeFromUserPlaylist(String username, int position) {
        SongPlaylist playlist = getUserPlaylist(username);
        return playlist.deleteAt(position);
    }
    
    // Get user's playlist as ArrayList for display
    public ArrayList<Song> getUserPlaylistAsList(String username) {
        SongPlaylist playlist = getUserPlaylist(username);
        return playlist.toArrayList();
    }
    
    // Clear user's playlist
    public void clearUserPlaylist(String username) {
        SongPlaylist playlist = getUserPlaylist(username);
        playlist.clear();
    }
    
    // Get user playlist size
    public int getUserPlaylistSize(String username) {
        SongPlaylist playlist = getUserPlaylist(username);
        return playlist.getSize();
    }
    
    
    // Check if a song exists in library
    public boolean songExistsInLibrary(String songName, String artistName) {
        for (Song song : songLibrary) {
            if (song.getSongName().equalsIgnoreCase(songName) && 
                song.getArtistName().equalsIgnoreCase(artistName)) {
                return true;
            }
        }
        return false;
    }
}
