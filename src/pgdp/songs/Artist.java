package pgdp.songs;

import java.util.Objects;

public class Artist {
    public String firstName;
    public String lastName;
    public int birthYear;
    public Album[] albums;
    public Song[] singles;

    public Artist(String firstName, String lastName, int birthYear, Album[] albums, Song[] singles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.albums = albums;
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
        Album summer = new Album("album", 2022);
        for (int i = 0; i < (albums.length + 1); i++) {
            if (i < albums.length) {
                summer.addSongs(albums[i].getSongs());
            } else {
                summer.addSongs(singles);
            }
        }
        Song[] arr = summer.sortByPopularity(false);
        return arr[0];
    }

    public Song leastLikedSong() {
        Album summer = new Album("album", 2022);
        for (int i = 0; i < (albums.length + 1); i++) {
            if (i < albums.length) {
                summer.addSongs(albums[i].getSongs());
            } else {
                summer.addSongs(singles);
            }
        }
        Song[] arr = summer.sortByPopularity(true);
        return arr[0];
    }

    public int totalLikes() {
        Album summer = new Album("album", 2022);
        int totalLikes = 0;
        for (int i = 0; i < (albums.length + 1); i++) {
            if (i < albums.length) {
                summer.addSongs(albums[i].getSongs());
            } else {
                summer.addSongs(singles);
            }
        }
        for (int i = 0; i < summer.getSongs().length; i++) {
            totalLikes += summer.getSongs()[i].getLikes();
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
