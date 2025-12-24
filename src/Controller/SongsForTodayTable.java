/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MusicModel;
import Model.Song;
import View.Test;

/**
 *
 * @author Abhyudaya Shrestha
 */
public class SongsForTodayTable {
    MusicModel m;
    Test t;

    public SongsForTodayTable(MusicModel m, Test t) {
        this.m = m;
        this.t = t;
        SongsForToday();
        t.updateTable(m.getSongs());
        
    }
 
    
    public void SongsForToday(){
        
        
        m.addSongs(new Song("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera", 1975));
        m.addSongs(new Song("Cant tell me nothing", "Kanye West", "Rap", "Graduation", 2007));
        m.addSongs(new Song("Last Christmas", "Wham", "Synth-pop", "Last Christmas", 1984));
        m.addSongs(new Song("Let Down", "RadioHead", "Rock", "OK Computer", 1997));
        m.addSongs(new Song("Country Roads", "John Denver", "Country", "Poems, Prayers and Promises", 1971));
        
        
    }
    
}
