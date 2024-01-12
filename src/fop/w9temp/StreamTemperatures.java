package fop.w9temp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTemperatures extends Temperatures {
    public StreamTemperatures(String filepath) {
        super(filepath);
    }

    public long size() {
        return 0;
    }

    public List<LocalDate> dates() {
        return null;
    }

    public Set<String> cities() {
        return null;
    }

    public Set<String> countries() {
        return null;
    }

    public Map<String, List<Double>> temperaturesByCountry() {
        return null;
    }

    @Override
    public String coldestCountryAbs() {
        return null;
    }

    @Override
    public String hottestCountryAbs() {
        return null;
    }

    @Override
    public Map<String, Double> countriesAvgTemperature() {
        return null;
    }

    public Map<String, Double> avgTemperatureDeltaPerYearPerCountry() {
        return null;
    }

    public static void main(final String[] args) {
        String filepath = "temperaturesEurope1963Till2013ByCity.csv";
        StreamTemperatures temperatures = new StreamTemperatures(filepath);

        temperatures.printSummary();
        final Map<String, Double> values = temperatures
                .avgTemperatureDeltaPerYearPerCountry();

        print("Averaged yearly temperature delta per country:",
                Arrays.toString(values.entrySet().toArray()));
    }

}
