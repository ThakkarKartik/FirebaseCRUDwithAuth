package com.firebaseapp.firebaseapp2;

public class Artist {

    String ArtistID;
    String Name;
    String Genre;

    public Artist(){}
    public Artist(String artistID, String name, String genre) {
        ArtistID = artistID;
        Name = name;
        Genre = genre;
    }

    public String getArtistID() {
        return ArtistID;
    }

    public void setArtistID(String artistID) {
        ArtistID = artistID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
