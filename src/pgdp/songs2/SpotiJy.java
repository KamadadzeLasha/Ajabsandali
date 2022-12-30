package pgdp.songs2;

public class SpotiJy {
    private Artist[] artists;

    public Artist[] getArtists() {
        return artists;
    }

    public void addArtists(Artist[] artists) {
        Artist[] arrartists;
        if (artists == null) {
            artists = new Artist[0];
        }
        if (this.artists == null) {
            this.artists = new Artist[0];
            arrartists = artists;
        } else {
            arrartists = new Artist[this.artists.length + artists.length];
            System.arraycopy(this.artists, 0, arrartists, 0, this.artists.length);
            for (int i = artists.length - 1; i >= 0; i--) {
                arrartists[arrartists.length - 1 - i] = artists[i];
            }
        }
        int num = 0;
        for (int i = 0; i < arrartists.length; i++) {
            for (int j = i + 1; j < arrartists.length; j++) {
                if (arrartists[i] != null && arrartists[j] != null) {
                    if (arrartists[j].isEqual(arrartists[i])) {
                        arrartists[j] = null;
                        num++;
                    }
                }
            }
        }
        this.artists = new Artist[arrartists.length - num];
        for (int i = 0, j = 0; i < arrartists.length; i++) {
            if (arrartists[i] != null) {
                this.artists[j] = arrartists[i];
                j++;
            }
        }
    }

    public String[] getTopTrendingArtist() {
        if (artists == null) return null;
        int likes = 0;
        String lastName = "", firstName = "";
        for (Artist artist : this.artists) {
            if (likes < artist.totalLikes()) {
                likes = artist.totalLikes();
                firstName = artist.getFirstName();
                lastName = artist.getLastName();
            }
        }
        return new String[]{firstName, lastName};
    }

    public String getTopTrendingAlbum() {
        if (artists == null) {
            return null;
        }
        Album mostLiked = null;
        int lastTotalLikes = 0;
        for (Artist artist : this.artists) {
            for (Album album : artist.getAlbums()) {
                int totalLikes = 0;
                for (Song song : album.getSongs()) {
                    totalLikes += song.getLikes();
                }
                if (lastTotalLikes < totalLikes) {
                    lastTotalLikes = totalLikes;
                    mostLiked = album;
                }
            }
        }
        if (mostLiked == null) {
            return "there is not";
        } else {
            return mostLiked.getTitle();
        }
    }

    public String getTopTrendingSong() {
        Song mostLiked = null;
        int lastMostLiked = 0;
        for (Artist artist : this.artists) {
            if (lastMostLiked < artist.mostLikedSong().getLikes()) {
                lastMostLiked = artist.mostLikedSong().getLikes();
                mostLiked = artist.mostLikedSong();
            }
        }
        if (mostLiked == null) {
            return "there is not";
        } else {
            return mostLiked.getTitle();
        }
    }
}