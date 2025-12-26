/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.LibraryModel;
import Model.SongModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Abhyudaya Shrestha
 */

//Manages playlist operations using library model

public class PlaylistController {
       private LibraryModel library;
    
    public PlaylistController(LibraryModel library) {
        this.library = library;
    }
    
    
    //Add song to user's playlist by song name
    
    public HashMap<String, Object> addSongToPlaylist(String username, String songName) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Invalid username");
            result.put("song", null);
            return result;
        }
        
        if (songName == null || songName.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Invalid song name");
            result.put("song", null);
            return result;
        }
        
        // Get library songs
        ArrayList<SongModel> librarySongs = library.getSongLibrary();
        
        if (librarySongs.isEmpty()) {
            result.put("success", false);
            result.put("message", "No songs available in library!");
            result.put("song", null);
            return result;
        }
        
        // Find song
        SongModel foundSong = null;
        int foundIndex = -1;
        
        for (int i = 0; i < librarySongs.size(); i++) {
            if (librarySongs.get(i).getSongName().equalsIgnoreCase(songName)) {
                foundSong = librarySongs.get(i);
                foundIndex = i;
                break;
            }
        }
        
        if (foundSong == null) {
            result.put("success", false);
            result.put("message", "Song '" + songName + "' not found in Music Library!");
            result.put("song", null);
            return result;
        }
        ArrayList<SongModel> currentPlaylist = library.getUserPlaylistAsList(username);
        for (SongModel song : currentPlaylist) {
        if (song.getSongName().equalsIgnoreCase(foundSong.getSongName()) && 
            song.getArtistName().equalsIgnoreCase(foundSong.getArtistName())) {
            result.put("success", false);
            result.put("message", "'" + foundSong.getSongName() + "' is already in your playlist!");
            result.put("song", null);
            return result;
            }
        }
        // Add to playlist
        if (library.addToUserPlaylist(username, foundIndex)) {
            result.put("success", true);
            result.put("message", "'" + foundSong.getSongName() + "' by " + foundSong.getArtistName() + " added to playlist!");
            result.put("song", foundSong);
        } else {
            result.put("success", false);
            result.put("message", "Failed to add song!");
            result.put("song", null);
        }
        return result;
    }
    
    
    //Remove song from user's playlist by song name
    
    public HashMap<String, Object> removeSongFromPlaylist(String username, String songName) {
        HashMap<String, Object> result = new HashMap<>();
        
        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Invalid username");
            result.put("song", null);
            return result;
        }
        
        if (songName == null || songName.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Invalid song name");
            result.put("song", null);
            return result;
        }
        
        // Get user's playlist
        ArrayList<SongModel> playlist = library.getUserPlaylistAsList(username);
        
        if (playlist.isEmpty()) {
            result.put("success", false);
            result.put("message", "Your playlist is empty!");
            result.put("song", null);
            return result;
        }
        
        // Find song in playlist
        int foundIndex = -1;
        SongModel foundSong = null;
        
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getSongName().equalsIgnoreCase(songName)) {
                foundIndex = i;
                foundSong = playlist.get(i);
                break;
            }
        }
        
        if (foundIndex == -1) {
            result.put("success", false);
            result.put("message", "Song '" + songName + "' not found in your playlist!");
            result.put("song", null);
            return result;
        }
        
        // Remove from playlist
        if (library.removeFromUserPlaylist(username, foundIndex)) {
            result.put("success", true);
            result.put("message", "Song removed from playlist!");
            result.put("song", foundSong);
        } else {
            result.put("success", false);
            result.put("message", "Failed to remove song!");
            result.put("song", null);
        }
        return result;
    }
    
    
    //Get user's playlist
    
    public ArrayList<SongModel> getUserPlaylist(String username) {
        return library.getUserPlaylistAsList(username);
    }
    
    
    //Get playlist size
    
    public int getPlaylistSize(String username) {
        return library.getUserPlaylistSize(username);
    }
    
    
    //Clear user's playlist
    
    public HashMap<String, Object> clearPlaylist(String username) {
        HashMap<String, Object> result = new HashMap<>();
        library.clearUserPlaylist(username);
        result.put("success", true);
        result.put("message", "Playlist cleared!");
        result.put("song", null);
        return result;
    }
}