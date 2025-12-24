/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.LibraryModel;
import Model.SongModel;
import java.util.ArrayList;

/**
 *
 * @author Abhyudaya Shrestha
 */
public class PlaylistController {
       private LibraryModel library;
    
    public class PlaylistResult {
        public boolean success;
        public String message;
        public SongModel song;
        
        public PlaylistResult(boolean success, String message, SongModel song) {
            this.success = success;
            this.message = message;
            this.song = song;
        }
    }
    
    public PlaylistController(LibraryModel library) {
        this.library = library;
    }
    
    /**
     * Add song to user's playlist by song name
     */
    public PlaylistResult addSongToPlaylist(String username, String songName) {
        if (username == null || username.trim().isEmpty()) {
            return new PlaylistResult(false, "Invalid username", null);
        }
        
        if (songName == null || songName.trim().isEmpty()) {
            return new PlaylistResult(false, "Invalid song name", null);
        }
        
        // Get library songs
        ArrayList<SongModel> librarySongs = library.getSongLibrary();
        
        if (librarySongs.isEmpty()) {
            return new PlaylistResult(false, "No songs available in library!", null);
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
            return new PlaylistResult(false, 
                "Song '" + songName + "' not found in Music Library!", null);
        }
        
        // Add to playlist
        if (library.addToUserPlaylist(username, foundIndex)) {
            return new PlaylistResult(true, 
                "'" + foundSong.getSongName() + "' by " + 
                foundSong.getArtistName() + " added to playlist!", foundSong);
        } else {
            return new PlaylistResult(false, "Failed to add song!", null);
        }
    }
    
    /**
     * Remove song from user's playlist by song name
     */
    public PlaylistResult removeSongFromPlaylist(String username, String songName) {
        if (username == null || username.trim().isEmpty()) {
            return new PlaylistResult(false, "Invalid username", null);
        }
        
        if (songName == null || songName.trim().isEmpty()) {
            return new PlaylistResult(false, "Invalid song name", null);
        }
        
        // Get user's playlist
        ArrayList<SongModel> playlist = library.getUserPlaylistAsList(username);
        
        if (playlist.isEmpty()) {
            return new PlaylistResult(false, "Your playlist is empty!", null);
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
            return new PlaylistResult(false, 
                "Song '" + songName + "' not found in your playlist!", null);
        }
        
        // Remove from playlist
        if (library.removeFromUserPlaylist(username, foundIndex)) {
            return new PlaylistResult(true, "Song removed from playlist!", foundSong);
        } else {
            return new PlaylistResult(false, "Failed to remove song!", null);
        }
    }
    
    /**
     * Get user's playlist
     */
    public ArrayList<SongModel> getUserPlaylist(String username) {
        return library.getUserPlaylistAsList(username);
    }
    
    /**
     * Get playlist size
     */
    public int getPlaylistSize(String username) {
        return library.getUserPlaylistSize(username);
    }
    
    /**
     * Clear user's playlist
     */
    public PlaylistResult clearPlaylist(String username) {
        library.clearUserPlaylist(username);
        return new PlaylistResult(true, "Playlist cleared!", null);
    }
}
    

