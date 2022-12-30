  package pgdp.streams;

  import java.io.BufferedReader;
  import java.io.FileNotFoundException;
  import java.io.FileReader;
  import java.io.IOException;
  import java.time.LocalDateTime;
  import java.time.format.DateTimeFormatter;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.function.Function;
  import java.util.stream.Stream;

  public class CSVReading {

    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static List<PenguinData> instance = null;
    public static Stream<PenguinData> processInputFile() {
      if (instance != null){
        return instance.stream();
      }
      instance = new ArrayList<>();
      String filesPath = "data/OC_LPhillips_LittlePenguin_GPS_tracks_DATA.csv";
      try {
        BufferedReader reader = new BufferedReader(new FileReader(filesPath));
        reader.readLine();
        reader.lines().map(mapToPenguinData).forEach(data -> {if (data != null) instance.add(data);});
        reader.close();
      } catch (FileNotFoundException e){
        System.out.println("File not found");
      } catch (IOException e) {
        System.out.println("Data Path seems to be wrong");
      }
      return instance.stream();
    }

    private static final Function<String, PenguinData> mapToPenguinData = (line) -> {
      String[] p = line.split(","); // a CSV has comma separated lines
      LocalDateTime dateTime = LocalDateTime.parse(p[2], formatter);
      return new PenguinData(p[0], Integer.parseInt(p[1]), dateTime, Double.parseDouble(p[3]),
              Double.parseDouble(p[4]), p[5], p[6], p[7], new Geo(p[8]));
    };

  }
