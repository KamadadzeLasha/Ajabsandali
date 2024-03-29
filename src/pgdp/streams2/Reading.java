package pgdp.streams2;

import pgdp.streams.PenguinData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reading {
    public static Stream<PenguinData> generate(){
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("/home/lasha/Documents/KIU/FOP/Java/Ajabsandali/src/data/OC_LPhillips_LittlePenguin_GPS_tracks_DATA.csv"));
            System.out.println(in.lines().collect(Collectors.toSet()));
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        generate();
    }
}
