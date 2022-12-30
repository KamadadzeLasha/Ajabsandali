package pgdp.streams;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        return stream.collect(Collectors.groupingBy(PenguinData::getTrackID)).entrySet().stream().map(element ->new Penguin(element.getValue().stream().map(data -> data.geom).toList(), element.getKey())).toList().stream();

    }

    public static void outputPenguinStream() {
        System.out.println(getDataByTrackId(CSVReading.processInputFile()).sorted(Comparator.comparing(Penguin::getTrackID)).toList().size());
        getDataByTrackId(CSVReading.processInputFile()).sorted(Comparator.comparing(Penguin::getTrackID)).
                toList().forEach(p -> System.out.println(p.toStringUsingStreams()));
    }


    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        StringBuilder sb = new StringBuilder();
        getDataByTrackId(stream).forEach(p  -> sb.append("\n").append(p.getTrackID()).append("\nMin Longitude: ").append(p.getLocations().stream().mapToDouble(Geo::getLongitude).min().getAsDouble()).append(" Max Longitude: ").append(p.getLocations().stream().mapToDouble(Geo::getLongitude).max().getAsDouble()).append(" Avg Longitude: ").append(p.getLocations().stream().mapToDouble(Geo::getLongitude).average().getAsDouble()).append(" Min Latitude: ").append(p.getLocations().stream().mapToDouble(Geo::getLatitude).min().getAsDouble()).append(" Max Latitude: ").append(p.getLocations().stream().mapToDouble(Geo::getLatitude).max().getAsDouble()).append(" Avg Latitude: ").append(p.getLocations().stream().mapToDouble(Geo::getLatitude).average().getAsDouble()));
        System.out.println(sb);
    }


    public static void main(String[] args) {
       outputPenguinStream();

    }
}