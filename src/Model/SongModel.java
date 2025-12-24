/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/** 
 *
 * @author Abhyudaya Shrestha
 */
public class SongModel{
    
    protected String SongName;
    protected String ArtistName;
    protected String Genre;
    protected String Album;
    protected int ReleasedYear;

    public SongModel(String SongName, String ArtistName, String Genre, String Album, int ReleasedYear) {
        this.SongName = SongName;
        this.ArtistName = ArtistName;
        this.Genre = Genre;
        this.Album = Album;
        this.ReleasedYear = ReleasedYear;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String SongName) {
        this.SongName = SongName;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String ArtistName) {
        this.ArtistName = ArtistName;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }

    public int getReleasedYear() {
        return ReleasedYear;
    }

    public void setReleasedYear(int ReleasedYear) {
        this.ReleasedYear = ReleasedYear;
    }
    
    
}
