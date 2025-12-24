/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Abhyudaya Shrestha
 */

import Model.SongLibrary;
import Model.Song;

/**
 * Controller for managing song library CRUD operations
 * @author Abhyudaya Shrestha
 */
public class SongController {
    private SongLibrary library;
    
    public SongController(SongLibrary library) {
        this.library = library;
    }
    
    // CREATE - Add to library
    public boolean createSong(String songName, String artistName, String genre, 
                             String album, int releasedYear) {
        // Validation
        if (songName == null || songName.trim().isEmpty()) {
            return false;
        }
        if (artistName == null || artistName.trim().isEmpty()) {
            return false;
        }
        if (releasedYear < 1900 || releasedYear > 2025) {
            return false;
        }
        
        // Check if song already exists
        if (library.songExistsInLibrary(songName, artistName)) {
            return false; // Duplicate song
        }
        
        Song newSong = new Song(songName, artistName, genre, album, releasedYear);
        return library.addToLibrary(newSong);
    }
    
    // UPDATE
    public boolean updateSong(int index, String songName, String artistName, 
                             String genre, String album, int releasedYear) {
        // Validation
        if (songName == null || songName.trim().isEmpty()) {
            return false;
        }
        if (artistName == null || artistName.trim().isEmpty()) {
            return false;
        }
        if (releasedYear < 1900 || releasedYear > 2025) {
            return false;
        }
        
        Song updatedSong = new Song(songName, artistName, genre, album, releasedYear);
        return library.updateLibrarySong(index, updatedSong);
    }
    
    // DELETE
    public boolean deleteSong(int index) {
        return library.deleteLibrarySong(index);
    }
    
    // GET ALL from library
    public java.util.ArrayList<Song> getAllSongs() {
        return library.getSongLibrary();
    }
    
    // FIND in library
    public int findSong(String songName) {
        return library.findLibrarySongIndex(songName);
    }
    
    // Get song at index
    public Song getSongAt(int index) {
        return library.getLibrarySongAt(index);
    }
    
    // Get library size
    public int getLibrarySize() {
        return library.getLibrarySize();
    }
}
