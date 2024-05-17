package ex2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserAnalyzer {
    private final List<User> users;

    public UserAnalyzer(List<User> users) {
        this.users = users;
    }

    public double calculateAverageAge() {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0.0);
    }


    public List<String> findCountriesWithMostUsers() {
        Map<String, Long> countryUserCount = users.stream()
                .collect(Collectors.groupingBy(User::getCountry, Collectors.counting()));

        Long maxUserCount = countryUserCount.values().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
        return countryUserCount.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxUserCount))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }



    public String findMostCommonFirstName() {
        return users.stream()
                .collect(Collectors.groupingBy(User::getFirstName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public String findMostCommonLastName() {
        return users.stream()
                .collect(Collectors.groupingBy(User::getLastName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public User findOldestUser() {
        return users.stream()
                .max(Comparator.comparing(User::getAge))
                .orElse(null);
    }

    public User findYoungestUser() {
        return users.stream()
                .min(Comparator.comparing(User::getAge))
                .orElse(null);
    }

    public List<String> findUniqueCountries() {
        return users.stream()
                .map(User::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }
}