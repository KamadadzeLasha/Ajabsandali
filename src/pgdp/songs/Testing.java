package pgdp.songs;


import java.util.Arrays;

public class Testing  {
    public static void main(String[] args) {
        Song snakeJazz = new Song("Snake Jazz", 1989);
        Song majorSong = new Song("Space Oddity", 1969, 315);
        Song queenSong1 = new Song("Teo Torriatte", 1977, 355, 132178);
        Song queenSong2 = new Song("Teo Torriadatte", 124, 355, 13214454);
        Song BeatAndTheBeast = new Song("BeatAndTheBeast",1977,180+36);
        Song DavidBowie = new Song("The next day",1977,15102);
        Song zabla = new Song("zabla" , 2022,254);
        Song lela1 = new Song("lela",2004,204);
        Song lela2 = new Song("gamissicumegafante", 2022,30);
        Song lela3 = new Song("gelas miyvarxar", 2022,501,10000000);
        Song zublu = new Song("zublu",2022,65,1546);
        Song SnakeJazz = new Song("Snake Jazz",1989,30);
        Album greenSide = new Album("Green side",1976);
        Album lelako = new Album("lelako",1042);
        lelako.addSongs(new Song[]{lela1,lela2,queenSong2});
        Album bowie = new Album("bowie",1977);
        bowie.addSongs(new Song[]{DavidBowie,BeatAndTheBeast});
        System.out.println(greenSide.addSongs(new Song[]{}));
        System.out.println(greenSide.addSongs(new Song[]{snakeJazz}));
        System.out.println(greenSide.addSongs(new Song[]{snakeJazz, majorSong,queenSong1}));
        Artist davita = new Artist("datita","bowie",1947, new Album[]{bowie,greenSide}, new Song[]{zabla,zublu});
        Artist lela = new Artist("lela", "wurwa",1969,new Album[]{lelako}, new Song[]{lela3,SnakeJazz});
        System.out.println(davita.totalLikes());
        System.out.println(lela.totalLikes());
        System.out.println(lela);
        SpotiJy spotiJy = new SpotiJy();
        spotiJy.addArtists(new Artist[]{lela,davita});
        System.out.println(spotiJy.getTopTrendingSong());
        System.out.println(spotiJy.getTopTrendingAlbum());
        System.out.println(Arrays.toString(spotiJy.getArtists()));
        System.out.println(Arrays.toString(spotiJy.getTopTrendingArtist()));
    }
}
