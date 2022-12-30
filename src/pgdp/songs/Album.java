package pgdp.songs;

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
        if (songs == null) {
            return null;
        }
        Song[] reversedArray = new Song[songs.length];
        for (int i = 0; i < songs.length; i++) {
            reversedArray[reversedArray.length - 1 - i] = songs[i];
        }
        return reversedArray;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Song[] getSongs() {
        return songs;
    }

    public int addSongs(Song[] songs) {

        if (songs == null) {
            return 0;
        }
        Song[] arr;
        int numOfDuplicates = 0;
        if (this.songs == null) {
            this.songs = new Song[0];
            arr = songs;
        } else {
            arr = new Song[this.songs.length + songs.length];
            System.arraycopy(this.songs, 0, arr, 0, this.songs.length);
            for (int i = songs.length - 1; i >= 0; i--) {
                arr[arr.length - 1 - i] = songs[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != null && arr[j] != null) {
                    if (arr[j].isEqual(arr[i])) {
                        arr[j] = null;
                        numOfDuplicates++;
                    }
                }
            }
        }
        int added = arr.length - this.songs.length - numOfDuplicates;
        this.songs = new Song[arr.length - numOfDuplicates];
        for (int i = 0, x = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                this.songs[x] = arr[i];
                x++;
            }
        }
        return added;
    }

    public Song[] shuffle() {
        if (this.songs == null) {
            return null;
        }
        Random rand = new Random();
        for (int i = 0; i < this.songs.length; i++) {
            int randomIndexToSwap = rand.nextInt(this.songs.length);
            Song temp = this.songs[randomIndexToSwap];
            songs[randomIndexToSwap] = this.songs[i];
            songs[i] = temp;
        }
        return songs;
    }

    public Song[] sortByTitle(boolean isAscending) {
        if (this.songs == null) return null;
        Song[] temp = this.songs;
        Song[] sortedArray = new Song[this.songs.length];
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
                sortedArray[sortedArray.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedArray = this.songs;
        }
        this.songs = temp;
        return sortedArray;
    }

    public Song[] sortByDuration(boolean isAscending) {
        if (this.songs == null) return null;
        Song[] temp = this.songs;
        Song[] sortedArray = new Song[this.songs.length];
        for (int i = 0; i < this.songs.length; i++) {
            for (int j = i + 1; j < this.songs.length; j++)
                if (this.songs[i].getDuration() > this.songs[j].getDuration()) {
                    Song tempo = this.songs[i];
                    this.songs[i] = this.songs[j];
                    this.songs[j] = tempo;
                }
        }
        if (!isAscending) {
            for (int i = 0; i < this.songs.length; i++) {
                sortedArray[sortedArray.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedArray = this.songs;
        }
        this.songs = temp;
        return sortedArray;
    }

    public Song[] sortByReleaseYear(boolean isAscending) {
        if (this.songs == null) return null;
        Song[] temp = this.songs;
        Song[] sortedArray = new Song[this.songs.length];
        for (int i = 0; i < this.songs.length; i++) {
            for (int j = i + 1; j < this.songs.length; j++)
                if (this.songs[i].getReleaseYear() > this.songs[j].getReleaseYear()) {
                    Song tempo = this.songs[i];
                    this.songs[i] = this.songs[j];
                    this.songs[j] = tempo;
                }
        }
        if (!isAscending) {
            for (int i = 0; i < this.songs.length; i++) {
                sortedArray[sortedArray.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedArray = this.songs;
        }
        this.songs = temp;
        return sortedArray;
    }

    public Song[] sortByPopularity(boolean isAscending) {
        if (this.songs == null) return null;
        Song[] temp = this.songs;
        Song[] sortedArray = new Song[this.songs.length];
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
                sortedArray[sortedArray.length - 1 - i] = this.songs[i];
            }
        } else {
            sortedArray = this.songs;
        }
        this.songs = temp;
        return sortedArray;
    }

    @Override
    public String toString() {
        if (this.songs == null) {
            return "Title=" + title + ", releaseYear=" + releaseYear + ", songs:{}";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.songs.length; i++) {
                sb.append(songs[i]);
                if (i != this.songs.length - 1) {
                    sb.append("|");
                }
            }
            return "Title=" + title + ", releaseYear=" + releaseYear + ", songs:{" + sb + '}';
        }
    }
}
