package org.example;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasicStreamTasks {
    public static Optional<String> findStringWithX(List<String> strings) {
        return strings.stream().filter(s -> s.startsWith("X") && s.length() > 5).findFirst().or(() -> Optional.of("Default"));
    }

    public static List<Integer> unwrapOptionals(List<Optional<Integer>> optionals) {
        return optionals.stream().flatMap(Optional::stream).collect(Collectors.toList());
    }


    public static Optional<String> findLongestName(List<String> names) {
        return names.stream().max(Comparator.comparingInt(String::length));
    }

    static void main() {
        List<String> words1 = Arrays.asList("Hello", "World", "Java", "JavaScript");
        List<String> words2 = Arrays.asList("Xenomorph", "Parrot", "Hamster");
        System.out.println("First list result: " + findStringWithX(words1).orElse("No value"));
        System.out.println("Second list result: " + findStringWithX(words2).orElse("No value"));

        List<Optional<Integer>> optionals = Arrays.asList(Optional.of(4), Optional.empty(), Optional.of(67), Optional.empty(), Optional.of(169));
        System.out.println("\nList: " + optionals);
        System.out.println("Integer list: " + unwrapOptionals(optionals));

        List<String> names = Arrays.asList("Bob", "Jamal", "Edward", "Bella");
        List<String> emptyNames = List.of();
        System.out.println("\nLongest name: " + findLongestName(names).orElse("Empty list"));
        System.out.println("Empty list: " + findLongestName(emptyNames).isPresent());
    }
}