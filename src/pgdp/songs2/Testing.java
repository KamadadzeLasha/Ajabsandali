package pgdp.songs2;


import pgdp.songs.Album;
import pgdp.songs.Artist;
import pgdp.songs.Song;
import pgdp.songs.SpotiJy;

import java.util.Arrays;

public class Testing {
    public static void main(String[] args) {
        pgdp.songs.Song snakeJazz = new pgdp.songs.Song("Snake Jazz", 1989);
        pgdp.songs.Song majorSong = new pgdp.songs.Song("Space Oddity", 1969, 315);
        pgdp.songs.Song queenSong1 = new pgdp.songs.Song("Teo Torriatte", 1977, 355, 132178);
        pgdp.songs.Song queenSong2 = new pgdp.songs.Song("Teo Torriadatte", 124, 355, 13214454);
        pgdp.songs.Song BeatAndTheBeast = new pgdp.songs.Song("BeatAndTheBeast",1977,180+36);
        pgdp.songs.Song DavidBowie = new pgdp.songs.Song("The next day",1977,15102);
        pgdp.songs.Song zabla = new pgdp.songs.Song("zabla" , 2022,254);
        pgdp.songs.Song lela1 = new pgdp.songs.Song("lela",2004,204);
        pgdp.songs.Song lela2 = new pgdp.songs.Song("gamissicumegafante", 2022,30);
        pgdp.songs.Song lela3 = new pgdp.songs.Song("gelas miyvarxar", 2022,501,10000000);
        pgdp.songs.Song zublu = new pgdp.songs.Song("zublu",2022,65,1546);
        pgdp.songs.Song SnakeJazz = new pgdp.songs.Song("Snake Jazz",1989,30);
        pgdp.songs.Album greenSide = new pgdp.songs.Album("Green side",1976);
        pgdp.songs.Album lelako = new pgdp.songs.Album("lelako",1042);
        lelako.addSongs(new pgdp.songs.Song[]{lela1,lela2,queenSong2});
        pgdp.songs.Album bowie = new pgdp.songs.Album("bowie",1977);
        bowie.addSongs(new pgdp.songs.Song[]{DavidBowie,BeatAndTheBeast});
        System.out.println(greenSide.addSongs(new pgdp.songs.Song[]{}));
        System.out.println(greenSide.addSongs(new pgdp.songs.Song[]{snakeJazz}));
        System.out.println(greenSide.addSongs(new pgdp.songs.Song[]{snakeJazz, majorSong,queenSong1}));
        pgdp.songs.Artist davita = new pgdp.songs.Artist("datita","bowie",1947, new pgdp.songs.Album[]{bowie,greenSide}, new pgdp.songs.Song[]{zabla,zublu});
        pgdp.songs.Artist lela = new pgdp.songs.Artist("lela", "wurwa",1969,new Album[]{lelako}, new Song[]{lela3,SnakeJazz});
        System.out.println(davita.totalLikes());
        System.out.println(lela.totalLikes());
        System.out.println(lela);
        pgdp.songs.SpotiJy spotiJy = new SpotiJy();
        spotiJy.addArtists(new Artist[]{lela,davita});
        System.out.println(spotiJy.getTopTrendingSong());
        System.out.println(spotiJy.getTopTrendingAlbum());
        System.out.println(Arrays.toString(spotiJy.getArtists()));
        System.out.println(Arrays.toString(spotiJy.getTopTrendingArtist()));
    }
}
