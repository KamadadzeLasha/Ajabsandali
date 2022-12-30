package pgdp.songs;

public class SpotiJy {
    private Artist[] artists;

    public Artist[] getArtists() {
        return artists;
    }

    public void addArtists(Artist[] artists) {
        Artist[] arr;
        if (artists == null) {
            artists = new Artist[0];
        }
        if (this.artists == null) {
            this.artists = new Artist[0];
            arr = artists;
        } else {
            arr = new Artist[this.artists.length + artists.length];
            System.arraycopy(this.artists, 0, arr, 0, this.artists.length);
            for (int i = artists.length - 1; i >= 0; i--) {
                arr[arr.length - 1 - i] = artists[i];
            }
        }
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != null && arr[j] != null) {
                    if (arr[j].isEqual(arr[i])) {
                        arr[j] = null;
                        num++;
                    }
                }
            }
        }
        this.artists = new Artist[arr.length - num];
        for (int i = 0, x = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                this.artists[x] = arr[i];
                x++;
            }
        }
    }

    public String[] getTopTrendingArtist() {
        if (artists == null) return null;
        int likes = 0;
        String lastName = "", firstName = "";
        for (Artist artist : this.artists) {
            if (artist.totalLikes() > likes) {
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
        int previousTotalLikes = 0;
        for (Artist artist : this.artists) {
            for (Album album : artist.getAlbums()) {
                int totalLikes = 0;
                for (Song song : album.getSongs()) {
                    totalLikes += song.getLikes();
                }
                if (totalLikes > previousTotalLikes) {
                    mostLiked = album;
                    previousTotalLikes = totalLikes;
                }
            }
        }
        if (mostLiked == null) {
            return "none";
        } else {
            return mostLiked.getTitle();
        }
    }

    public String getTopTrendingSong() {
        Song mostLiked = null;
        int previousMostLiked = 0;
        for (Artist artist : this.artists) {
            if (artist.mostLikedSong().getLikes() > previousMostLiked) {
                previousMostLiked = artist.mostLikedSong().getLikes();
                mostLiked = artist.mostLikedSong();
            }
        }
        if (mostLiked == null) {
            return "none";
        } else {
            return mostLiked.getTitle();
        }
    }
}