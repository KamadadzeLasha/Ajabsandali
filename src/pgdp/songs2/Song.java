package pgdp.songs2;

import java.util.Objects;

public class Song {
    private String title;
    private int releaseYear;
    private int duration ;
    private int likes ;

    public Song(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = 60;
        this.likes = 0;
    }

    public Song(String title, int releaseYear, int duration) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = 0;
    }

    public Song(String title, int releaseYear, int duration, int likes) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public int getreleaseYear() {
        return releaseYear;
    }

    public int getduration() {
        return duration;
    }

    public int getLikes() {
        return likes;
    }

    public void Like() {
        likes++;
    }

    public void unlike() {
        if (likes > 0) likes--;
        else this.likes = 0;
    }

    public boolean changeDuration(int duration) {
        if (duration < 0 || duration > 720 || duration == this.duration) {
            return false;
        } else {
            this.duration = duration;
            return true;
        }
    }

    public String toString() {
        return "Title:" + this.title + ",Duration:" + this.duration / 60f + " minutes,Release year:" + this.releaseYear + ",Likes:" + this.likes;
    }

    public boolean isEqual(Song other) {
        return Objects.equals(this.title, other.title) && (this.releaseYear == other.releaseYear) && this.duration == other.duration;
    }
}
