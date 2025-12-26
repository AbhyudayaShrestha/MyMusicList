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

//Manages CRUD operations and song queries using library model
public class LibraryController {
    private LibraryModel library;
    
    public LibraryController(LibraryModel library) {
        this.library = library;
    }
    
    // Create 
    public boolean createSong(String songName, String artistName, String genre, 
                             String album, int releasedYear) {
        if (songName == null || songName.trim().isEmpty()) {
            return false;
        }
        if (artistName == null || artistName.trim().isEmpty()) {
            return false;
        }
        if (releasedYear < 1900 || releasedYear > 2025) {
            return false;
        }
        
        if (library.songExistsInLibrary(songName, artistName)) {
            return false;
        }
        
        SongModel newSong = new SongModel(songName, artistName, genre, album, releasedYear);
        return library.addToLibrary(newSong);
    }
    
    // Read
    
    public ArrayList<SongModel> getAllSongs() {
        return library.getSongLibrary();
    }
    
    // Update
    
    public boolean updateSong(int index, String songName, String artistName, 
                             String genre, String album, int releasedYear) {
        if (songName == null || songName.trim().isEmpty()) {
            return false;
        }
        if (artistName == null || artistName.trim().isEmpty()) {
            return false;
        }
        if (releasedYear < 1900 || releasedYear > 2025) {
            return false;
        }
        
        SongModel updatedSong = new SongModel(songName, artistName, genre, album, releasedYear);
        return library.updateLibrarySong(index, updatedSong);
    }
    
    // Delete
    public boolean deleteSong(int index) {
        return library.deleteLibrarySong(index);
    }
    
    // Search
    public int findSongIndex(String songName) {
        return library.findLibrarySongIndex(songName);
    }
    
    // Get
    public SongModel getSongAt(int index) {
        return library.getLibrarySongAt(index);
    }
    
    public int getLibrarySize() {
        return library.getLibrarySize();
    }
    
    // loading pre set songs
    public void loadDemoSongs() {
        library.addToLibrary(new SongModel("Blinding Lights", "The Weeknd", "Synth-pop", "After Hours", 2019));
        library.addToLibrary(new SongModel("Shape of You", "Ed Sheeran", "Pop", "÷ (Divide)", 2017));
        library.addToLibrary(new SongModel("Uptown Funk", "Mark Ronson ft. Bruno Mars", "Funk", "Uptown Special", 2014));
        library.addToLibrary(new SongModel("Levitating", "Dua Lipa", "Pop", "Future Nostalgia", 2020));
        library.addToLibrary(new SongModel("Hotel California", "Eagles", "Rock", "Hotel California", 1976));
        library.addToLibrary(new SongModel("Stairway to Heaven", "Led Zeppelin", "Rock", "Led Zeppelin IV", 1971));
        library.addToLibrary(new SongModel("Sweet Child O' Mine", "Guns N' Roses", "Rock", "Appetite for Destruction", 1987));
        library.addToLibrary(new SongModel("November Rain", "Guns N' Roses", "Rock", "Use Your Illusion I", 1991));
        library.addToLibrary(new SongModel("Someone Like You", "Adele", "Soul", "21", 2011));
        library.addToLibrary(new SongModel("Rolling in the Deep", "Adele", "Soul", "21", 2010));
        library.addToLibrary(new SongModel("Thinking Out Loud", "Ed Sheeran", "Soul", "x (Multiply)", 2014));
        library.addToLibrary(new SongModel("Thriller", "Michael Jackson", "Pop", "Thriller", 1982));
        library.addToLibrary(new SongModel("Billie Jean", "Michael Jackson", "Pop", "Thriller", 1982));
        library.addToLibrary(new SongModel("Beat It", "Michael Jackson", "Pop", "Thriller", 1982));
        library.addToLibrary(new SongModel("Imagine", "John Lennon", "Rock", "Imagine", 1971));
        library.addToLibrary(new SongModel("Hey Jude", "The Beatles", "Rock", "Hey Jude (Single)", 1968));
        library.addToLibrary(new SongModel("Let It Be", "The Beatles", "Rock", "Let It Be", 1970));
        library.addToLibrary(new SongModel("Lose Yourself", "Eminem", "Hip Hop", "8 Mile Soundtrack", 2002));
        library.addToLibrary(new SongModel("HUMBLE.", "Kendrick Lamar", "Hip Hop", "DAMN.", 2017));
        library.addToLibrary(new SongModel("Sicko Mode", "Travis Scott", "Hip Hop", "Astroworld", 2018));
        library.addToLibrary(new SongModel("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera", 1975));
        library.addToLibrary(new SongModel("Cant Tell Me Nothing", "Kanye West", "Rap", "Graduation", 2007));
        library.addToLibrary(new SongModel("Last Christmas", "Wham", "Synth-pop", "Last Christmas", 1984));
    library.addToLibrary(new SongModel("Let Down", "RadioHead", "Rock", "OK Computer", 1997));
    library.addToLibrary(new SongModel("Country Roads", "John Denver", "Country", "Poems, Prayers and Promises", 1971));   
    }
    
}
