package pgdp.songs2;

import java.util.Random;

public class Album {
    private final String title;
    private final int releaseYear;
    private Song[] songs;

    public Album(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public static Song[] reverse(Song[] songs) {
        for (int i = 0; i < songs.length / 2; i++) {
            Song temp = songs[i];
            songs[i] = songs[songs.length - 1 - i];
            songs[songs.length - 1 - i] = temp;
        }
        return songs;
    }

    public String getTitle() {
        return title;
    }

    public int getreleaseYear() {
        return releaseYear;
    }

    public Song[] getSongs() {
        return songs;
    }

    public Song Song() {
        return Song();
    }

    public int addSongs(Song[] songs) {

        if (songs == null) {
            return 0;
        }
        int dubles = 0;
        Song[] arrsongs;
        if (this.songs == null) {
            this.songs = new Song[0];
            arrsongs = songs;
        } else {
            arrsongs = new Song[this.songs.length + songs.length];
            System.arraycopy(this.songs, 0, arrsongs, 0, this.songs.length);
            for (int i = songs.length - 1; i >= 0; i--) {
                arrsongs[arrsongs.length - 1 - i] = songs[i];
            }
        }
        for (int i = 0; i < arrsongs.length; i++) {
            for (int j = i + 1; j < arrsongs.length; j++) {
                if (arrsongs[i] != null && arrsongs[j] != null) {
                    if (arrsongs[j].isEqual(arrsongs[i])) {
                        arrsongs[j] = null;
                        dubles++;
                    }
                }
            }
        }
        int added = arrsongs.length - this.songs.length - dubles;
        this.songs = new Song[arrsongs.length - dubles];
        for (int i = 0, x = 0; i < arrsongs.length + 1 - 1; i++) {
            if (arrsongs[i] != null) {
                this.songs[x] = arrsongs[i];
                x++;
            }
        }
        return added;
    }

    public Song[] shuffle() {
        Random rand = new Random();
        for (int i = 0; i < this.songs.length; i++) {
            int shufffle = rand.nextInt(this.songs.length);
            Song temp = this.songs[i];
            this.songs[i] = this.songs[shufffle];
            this.songs[shufffle] = temp;
        }
        return songs;
    }

    public Song[] sortByTitle(boolean isAscending) {
        Song[] sortedsongs = new Song[this.songs.length];
        Song[] temp = this.songs;
        for (int i = 0; i < this.songs.length; i++) {
            for (int j = i + 1; j < this.songs.length; j++)
                if (this.songs[i].getTitle().compareTo(this.songs[j].getTitle()) > 0) {
                    Song tempo = this.songs[i];
                    this.songs[i] = this.songs[j];
                    this.songs[j] = tempo;
                }
        }
        if (!isAscending) {
            for (int i = 0; i < this.songs.length; i++) {
                sortedsongs[sortedsongs.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedsongs = this.songs;
        }
        this.songs = temp;
        return sortedsongs;
    }

    public Song[] sortByDuration(boolean isAscending) {
        Song[] sortedsongs = new Song[this.songs.length];
        Song[] temp = this.songs;
        for (int i = 0; i < this.songs.length; i++) {
            for (int j = i + 1; j < this.songs.length; j++)
                if (this.songs[i].getduration() > this.songs[j].getduration()) {
                    Song tempo = this.songs[i];
                    this.songs[i] = this.songs[j];
                    this.songs[j] = tempo;
                }
        }
        if (!isAscending) {
            for (int i = 0; i < this.songs.length; i++) {
                sortedsongs[sortedsongs.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedsongs = this.songs;
        }
        this.songs = temp;
        return sortedsongs;
    }

    public Song[] sortByReleaseYear(boolean isAscending) {
        Song[] sortedsongs = new Song[this.songs.length];
        Song[] temp = this.songs;
        for (int i = 0; i < this.songs.length; i++) {
            for (int j = i + 1; j < this.songs.length; j++)
                if (this.songs[i].getreleaseYear() > this.songs[j].getreleaseYear()) {
                    Song tempo = this.songs[i];
                    this.songs[i] = this.songs[j];
                    this.songs[j] = tempo;
                }
        }
        if (!isAscending) {
            for (int i = 0; i < this.songs.length; i++) {
                sortedsongs[sortedsongs.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedsongs = this.songs;
        }
        this.songs = temp;
        return sortedsongs;
    }

    public Song[] sortByPopularity(boolean isAscending) {
        Song[] temp = this.songs;
        Song[] sortedsongs = new Song[this.songs.length];
        for (int i = 0; i < this.songs.length; i++) {
            for (int j = i + 1; j < this.songs.length; j++)
                if (this.songs[i].getLikes() > this.songs[j].getLikes()) {
                    Song tempo = this.songs[i];
                    this.songs[i] = this.songs[j];
                    this.songs[j] = tempo;
                }
        }
        if (!isAscending) {
            for (int i = 0; i < this.songs.length; i++) {
                sortedsongs[sortedsongs.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedsongs = this.songs;
        }
        this.songs = temp;
        return sortedsongs;
    }

    @Override
    public String toString() {
        try {
            StringBuilder build = new StringBuilder();
            for (int i = 0; i < this.songs.length; i++) {
                build.append(songs[i]);
                if (i != this.songs.length - 1) {
                    build.append("|");
                }
            }
            return "Title=" + title + ", releaseYear=" + releaseYear + ", songs:{" + build + '}';
        } catch (Exception nullException) {
            return "Title=" + title + ", releaseYear=" + releaseYear + ", songs:{}";
        }
    }
}
