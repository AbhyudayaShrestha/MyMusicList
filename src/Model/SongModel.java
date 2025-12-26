/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/** 
 *
 * @author Abhyudaya Shrestha
 */
//Data containers
public class SongModel {
    
    protected String songName;
    protected String artistName;
    protected String genre;
    protected String album;
    protected int releasedYear;
    
//Constructor initializes declared variables
    public SongModel(String SongName, String ArtistName, String Genre, String Album, int ReleasedYear) {
        this.songName = SongName;
        this.artistName = ArtistName;
        this.genre = Genre;
        this.album = Album;
        this.releasedYear = ReleasedYear;
    }

 //getters and setters   
    public String getSongName() {
        return songName;
    }

    public void setSongName(String SongName) {
        this.songName = SongName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String ArtistName) {
        this.artistName = ArtistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String Genre) {
        this.genre = Genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String Album) {
        this.album = Album;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int ReleasedYear) {
        this.releasedYear = ReleasedYear;
    }
    
    
}
