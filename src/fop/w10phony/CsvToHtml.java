package fop.w10phony;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;

public class CsvToHtml {
    private Function<String[] , CsvEntry> cellsToEntry;

    public CsvToHtml(Function<String[], CsvEntry> cellsToEntry) {
        this.cellsToEntry = cellsToEntry;
    }

    private static String[] splitLine(String line){
        return line.split(",");
    }
    public void convert(Path in , Path out){
        if (Files.exists(out)){
            System.out.println("File already exists.");
            return;
        }
        try {
            Files.createFile(out);
            StringBuilder sb = new StringBuilder();
            sb.append("""
                    <html>
                    <body>
                    <table>
                    """);
            BufferedReader bf = new BufferedReader(new FileReader(in.toString()));
            bf.lines().filter(Objects::nonNull).map(CsvToHtml::splitLine).map(cellsToEntry).filter(Objects::nonNull).map(CsvEntry::toHtml).filter(Objects::nonNull).forEach(ele -> sb.append(ele).append("\n"));
            sb.append("""
                    </table>
                    </body>
                    </html>""");
            Files.write(out, Collections.singleton(sb));
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch (IOException e) {
            System.out.println("File can't be created!");
        }
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: CsvToHtml in out "+ args.length);
            return;
        }
        CsvToHtml cth = new CsvToHtml(PhoneBookEntry::cellsToEntry);
        cth.convert(Paths.get(args[0]), Paths.get(args[1]));
    }
}
