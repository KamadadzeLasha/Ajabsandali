package pgdp.songs2;

import java.util.Objects;

public class Artist {
    public String firstName;
    public String lastName;
    public int birthYear;
    public Album[] albums;
    public Song[] singles;

    public Artist(String firstName, String lastName, int birthYear, Album[] albums) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.albums = albums;
    }

    public Artist(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public Artist(String firstName, String lastName, int birthYear, Album[] albums, Song[] singles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.albums = albums;
        this.singles = singles;
    }

    public Artist(String firstName, String lastName, int birthYear, Song[] singles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.singles = singles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Album[] getAlbums() {
        return albums;
    }

    public Song[] getSingles() {
        return singles;
    }

    public Song mostLikedSong() {
        Album sum = new Album("album", 2022);
        for (int i = 0; i < (albums.length + 1); i++) {
            if (albums.length > i) {
                sum.addSongs(albums[i].getSongs());
            } else {
                sum.addSongs(singles);
            }
        }
        Song[] arr = sum.sortByPopularity(false);
        return arr[0];
    }

    public Song leastLikedSong() {
        Album sum = new Album("album", 2022);
        for (int i = 0; i < (albums.length + 1); i++) {
            if (albums.length > i) {
                sum.addSongs(albums[i].getSongs());
            } else {
                sum.addSongs(singles);
            }
        }
        Song[] arr = sum.sortByPopularity(true);
        if (arr.length == 0) {
            arr = new Song[1];
        }
        return arr[0];

    }

    public int totalLikes() {
        Album sum = new Album("album", 2022);
        int totalLikes = 0;
        for (int i = 0; i < albums.length + 1; i++) {
            if (albums.length > i) {
                sum.addSongs(albums[i].getSongs());
            } else {
                sum.addSongs(singles);
            }
        }
        for (int i = 0; i < sum.getSongs().length; i++) {
            totalLikes += sum.getSongs()[i].getLikes();
        }
        return totalLikes;
    }

    public boolean isEqual(Artist other) {
        return Objects.equals(this.firstName, other.firstName) && (Objects.equals(this.lastName, other.lastName)) && this.birthYear == other.birthYear;
    }

    @Override
    public String toString() {
        return "Name:" + firstName + " " + lastName + ",Birth year:" + birthYear + ",Total likes:" + totalLikes();
    }
}