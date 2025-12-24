/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Abhyudaya Shrestha
 */


import Model.Song;

import Model.SongLibrary;

/**
 * Loads initial demo songs into the library for demonstration
 * @author Abhyudaya Shrestha
 */
public class InitialSongsLoader {
    
    public static void loadDemoSongs(SongLibrary library) {
        // Pop songs
        library.addToLibrary(new Song("Blinding Lights", "The Weeknd", "Synth-pop", "After Hours", 2019));
        library.addToLibrary(new Song("Shape of You", "Ed Sheeran", "Pop", "÷ (Divide)", 2017));
        library.addToLibrary(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", "Funk", "Uptown Special", 2014));
        library.addToLibrary(new Song("Levitating", "Dua Lipa", "Pop", "Future Nostalgia", 2020));
        
        // Rock songs
        library.addToLibrary(new Song("Hotel California", "Eagles", "Rock", "Hotel California", 1976));
        library.addToLibrary(new Song("Stairway to Heaven", "Led Zeppelin", "Rock", "Led Zeppelin IV", 1971));
        library.addToLibrary(new Song("Sweet Child O' Mine", "Guns N' Roses", "Rock", "Appetite for Destruction", 1987));
        library.addToLibrary(new Song("November Rain", "Guns N' Roses", "Rock", "Use Your Illusion I", 1991));
        
        // Soul/R&B songs
        library.addToLibrary(new Song("Someone Like You", "Adele", "Soul", "21", 2011));
        library.addToLibrary(new Song("Rolling in the Deep", "Adele", "Soul", "21", 2010));
        library.addToLibrary(new Song("Thinking Out Loud", "Ed Sheeran", "Soul", "x (Multiply)", 2014));
        
        // Classic Pop/MJ
        library.addToLibrary(new Song("Thriller", "Michael Jackson", "Pop", "Thriller", 1982));
        library.addToLibrary(new Song("Billie Jean", "Michael Jackson", "Pop", "Thriller", 1982));
        library.addToLibrary(new Song("Beat It", "Michael Jackson", "Pop", "Thriller", 1982));
        
        // Classic Rock
        library.addToLibrary(new Song("Imagine", "John Lennon", "Rock", "Imagine", 1971));
        library.addToLibrary(new Song("Hey Jude", "The Beatles", "Rock", "Hey Jude (Single)", 1968));
        library.addToLibrary(new Song("Let It Be", "The Beatles", "Rock", "Let It Be", 1970));
        
        // Hip Hop/Rap
        library.addToLibrary(new Song("Lose Yourself", "Eminem", "Hip Hop", "8 Mile Soundtrack", 2002));
        library.addToLibrary(new Song("HUMBLE.", "Kendrick Lamar", "Hip Hop", "DAMN.", 2017));
        library.addToLibrary(new Song("Sicko Mode", "Travis Scott", "Hip Hop", "Astroworld", 2018));
    }
}
