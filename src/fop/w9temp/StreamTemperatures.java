package fop.w9temp;


import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTemperatures extends Temperatures {
    public StreamTemperatures(String filepath) {
        super(filepath);
    }

    public static void main(final String[] args) {
        String filepath = "../temperaturesEurope1963Till2013ByCity.csv";
        StreamTemperatures temperatures = new StreamTemperatures(filepath);

        temperatures.printSummary();
        final Map<String, Double> values = temperatures.avgTemperatureDeltaPerYearPerCountry();

        print("Averaged yearly temperature delta per country:", Arrays.toString(values.entrySet().toArray()));
    }

    public long size() {
        return stream().count();
    }

    public List<LocalDate> dates() {
        return stream().map(Temperature::getDate).distinct().sorted().collect(Collectors.toList());
    }

    public Set<String> cities() {
        return stream().map(Temperature::getCity).collect(Collectors.toSet());
    }

    public Set<String> countries() {
        return stream().map(Temperature::getCountry).collect(Collectors.toSet());
    }

    public Map<String, List<Double>> temperaturesByCountry() {
        Map<String, List<Double>> map = new HashMap<>();
        countries().forEach(country -> map.put(country, new ArrayList<>()));
        stream().forEach(ele -> map.get(ele.getCountry()).add(ele.getTemperature()));
        return map;
    }

    @Override
    public String coldestCountryAbs() {
        return stream().min(Comparator.comparing(Temperature::getTemperature)).get().getCountry();
    }

    @Override
    public String hottestCountryAbs() {
        return stream().max(Comparator.comparing(Temperature::getTemperature)).get().getCountry();
    }

    @Override
    public Map<String, Double> countriesAvgTemperature() {
        Map<String, Double> map = new HashMap<>();
        temperaturesByCountry().entrySet().forEach(entry -> map.put(entry.getKey(), entry.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
        return map;
    }

    public Map<String, Double> avgTemperatureDeltaPerYearPerCountry() {
        Map<String, List<Temperature>> tempsPerCountry = stream()
                .collect(Collectors.groupingBy(Temperature::getCountry));
        Map<String, Double> avgTempDeltaPerCountry = tempsPerCountry.entrySet()
                .stream().collect(Collectors.toMap(getKey(), entry -> {
                    Map<Integer, List<Temperature>> tempsPerYear = entry
                            .getValue().stream()
                            .collect(Collectors
                                    .groupingBy(temperature -> temperature
                                            .getDate().getYear()));
                    double[] avgTempsPerYear = tempsPerYear.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .mapToDouble(entry2 -> entry2.getValue().stream()
                                    .mapToDouble(Temperature::getTemperature)
                                    .average().getAsDouble())
                            .toArray();
                    return IntStream.range(0, avgTempsPerYear.length - 1)
                            .mapToDouble(i -> avgTempsPerYear[i + 1]
                                    - avgTempsPerYear[i])
                            .average().getAsDouble();
                }));
        avgTempDeltaPerCountry.put("Globally", avgTempDeltaPerCountry.values()
                .stream().mapToDouble(it -> it).average().getAsDouble());
        return avgTempDeltaPerCountry;
    }

    private static Function<Map.Entry<String, List<Temperature>>, String> getKey() {
        return Map.Entry::getKey;
    }
}
