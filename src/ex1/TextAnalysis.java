package ex1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextAnalysis {

    public static long getWordCount(String text) {
        return Arrays.stream(text.split("\\s+")).count();
    }

    public static long getUniqueWordCount(String text) {
        return Arrays.stream(text.split("\\s+"))
                .distinct()
                .count();
    }

    public static double getAverageWordLength(String text) {
        return Arrays.stream(text.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
    }

    public static String getLongestWord(String text) {
        return Arrays.stream(text.split("\\s+"))
                .max(Comparator.comparingInt(String::length).thenComparing(String::compareToIgnoreCase))
                .orElse("");
    }


    public static String getMostFrequentWord(String text) {
        return Arrays.stream(text.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public static Map<String, Long> getWordFrequency(String text) {
        return Arrays.stream(text.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

}