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
    private HashMap<String, String> userAccount;
 
    ArrayList<Song> songs ;
    
    
    public MusicModel(){
        songs = new ArrayList<>();
        adminAccount = new HashMap<>();
        userAccount = new HashMap<>();
        AdminUsers();
        RegularUsers();
    }
    
    public void addSongs(Song s) {
        songs.add(s);
        
    }
    
    
      public ArrayList<Song> getSongs() {
        return songs;
    }
      
      
      
      
      
     
      
     public void AdminUsers(){
         adminAccount = new HashMap<>();
         adminAccount.put("Abhyudaya", "abhyudaya");
         adminAccount.put("Dken10", "abhyudaya");
     }
         
    // Initialize regular user accounts
    public void RegularUsers(){
        userAccount.put("user1", "abhyudaya");
        userAccount.put("user2", "abhyudaya");
 
    }
     
     public boolean isValidAdmin(String username, String password) {
        return adminAccount.containsKey(username) &&
               adminAccount.get(username).equals(password);
    }

     public boolean isValidUser(String username, String password){
         return userAccount.containsKey(username) &&
                 userAccount.get(username).equals(password);
     
     
    }
     public void addUser(String username, String password) {
        userAccount.put(username, password);
    }

     public boolean usernameExists(String username) {
        return adminAccount.containsKey(username) || 
        userAccount.containsKey(username);
    }

}





