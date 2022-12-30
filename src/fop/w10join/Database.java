package fop.w10join;

// TODO Import the stuff you need

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {
    private static Path baseDataDirectory = Paths.get("data");

    public Database() {
        // TODO
    }

    public static void  setBaseDataDirectory(Path baseDataDirectory) {
        Database.baseDataDirectory = baseDataDirectory;
    }

    public static Stream<Customer> processInputFileCustomer() {
        List<Customer> list;
        try (Stream<String> lines = Files.lines(Path.of(baseDataDirectory + "/customer.tbl"))) {
            list = lines.map(line -> {
                String[] parameters = line.split("\\|");
                return new Customer(Integer.parseInt(parameters[0]), parameters[2].toCharArray(), Integer.parseInt(parameters[3]), parameters[4].toCharArray(), Float.parseFloat(parameters[5]), parameters[6], parameters[7].toCharArray());
            }).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.stream();
    }

    public static Stream<LineItem> processInputFileLineItem() {
        List<LineItem> list;
        try (Stream<String> lines = Files.lines(Path.of(baseDataDirectory + "/lineitem.tbl"))) {
            list = lines.map(line -> {
                String[] parameters = line.split("\\|");
                return new LineItem(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]) * 100, Float.parseFloat(parameters[5]), Float.parseFloat(parameters[6]), Float.parseFloat(parameters[7]), parameters[8].charAt(0), parameters[9].charAt(0), LocalDate.parse(parameters[10]), LocalDate.parse(parameters[11]), LocalDate.parse(parameters[12]), parameters[13].toCharArray(), parameters[14].toCharArray(), parameters[15].toCharArray());
            }).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.stream();
        // For quantity of LineItems please use Integer.parseInt(str) * 100.
    }

    public static Stream<Order> processInputFileOrders() {
        List<Order> list;
        try (Stream<String> lines = Files.lines(Path.of(baseDataDirectory + "/orders.tbl"))) {
            list = lines.map(line -> {
                String[] parameters = line.split("\\|");
                return new Order(Integer.parseInt(parameters[0]),
                        Integer.parseInt(parameters[1]),
                        parameters[2].charAt(0),
                        Float.parseFloat(parameters[3]),
                        LocalDate.parse(parameters[4]),
                        parameters[5].toCharArray(),
                        parameters[6].toCharArray(),
                        Integer.parseInt(parameters[7]),
                        parameters[8].toCharArray());
            }).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.stream();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"AUTOMOBILE","FURNITURE","HOUSEHOLD", "BUILDING", "FURNITURE"}) {
            System.out.println(getAverageQuantityPerMarketSegment(s));
        }
    }


    public static long getAverageQuantityPerMarketSegment(String marketsegment) {
        //მაპავს ქასთქიებს მაკეტსეგმენტებზე.
        Map<Integer, String> custKeyToMarket = processInputFileCustomer().filter(key -> key.mktsegment.equals(marketsegment)).collect(Collectors.toMap(a -> a.custKey, a -> marketsegment, (v1, v2) -> v1, HashMap::new));
        Map<Integer, String> orderKeyToMarket = processInputFileOrders().filter(a -> custKeyToMarket.containsKey(a.custKey)).collect(Collectors.toMap(a -> a.orderKey, key -> marketsegment, (String v1, String v2) -> v1, HashMap::new));
        return processInputFileLineItem().filter(a -> orderKeyToMarket.containsKey(a.orderKey)).collect(Collectors.averagingLong(a -> a.quantity)).longValue();
    }
}
