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
public class MusicModel {
    private HashMap<String, String> adminAccount;
    private static ArrayList<Song> songs = new ArrayList<>();
    
    
    
    
    
    
    public void SongsForToday(){
        
        songs.add(new Song("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera", 1975));
        songs.add(new Song("Cant tell me nothing", "Kanye West", "Rap", "Graduation", 2007));
        songs.add(new Song("Last Christmas", "Wham", "Synth-pop", "Last Christmas", 1984));
        songs.add(new Song("Let Down", "RadioHead", "Rock", "OK Computer", 1997));
        songs.add(new Song("Country Roads", "John Denver", "Country", "Poems, Prayers and Promises", 1971));
        
    }
      public static ArrayList<Song> getSongs() {
        return songs;
    }
      
     public void AdminUsers(){
         adminAccount = new HashMap<>();
         adminAccount.put("Abhyudaya", "sybaunihga");
         adminAccount.put("Dken10", "bigahhdih");
     
     
     }
     
     public boolean isValidAdmin(String username, String password) {
        return adminAccount.containsKey(username) &&
               adminAccount.get(username).equals(password);
    }






}





