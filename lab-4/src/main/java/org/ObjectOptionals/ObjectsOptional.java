package org.ObjectOptionals;
import java.util.*;
import java.util.stream.Collectors;

record Employee(String name, double salary) {}
record Person(String name, List<Person> friends) {}
record Transaction(double amount, String category) {}
record Product(String name, double price) {}

public class ObjectsOptional {
    public static class StreamTasks {
        public static Map<String, Optional<Employee>> topEarnerBySalaryRange(List<Employee> employees) {
            return employees.stream().collect(Collectors.groupingBy(
                            emp -> {
                                if (emp.salary() < 3000) return "< 3000";
                                else if (emp.salary() <= 5000) return "3000-5000";
                                else return "> 5000";
                            },
                            Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
                    ));
        }

        public static Optional<Integer> productOfOdds(List<Integer> numbers) {
            return numbers.stream()
                    .filter(n -> n % 2 != 0)
                    .reduce((a, b) -> a * b);
        }

        public static List<String> uniqueFriendsNames(List<Person> people) {
            return people.stream()
                    .flatMap(person -> person.friends().stream())
                    .map(Person::name)
                    .map(String::toUpperCase)
                    .distinct().collect(Collectors.toList());
        }

        public static Map<String, Double> sumTransactionsByCategory(List<Transaction> transactions) {
            return transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::category, Collectors.summingDouble(Transaction::amount)));
        }

        public static Optional<String> secondMostExpensiveProduct(List<Product> products) {
            return products.stream().sorted(Comparator.comparingDouble(Product::price)
                    .reversed()).skip(1)
                    .map(Product::name).findFirst();
        }

        public static List<String> getPresentProductNames(Map<String, Optional<String>> productMap) {
            return productMap.values().stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        }

        public static Optional<String> cityWithHighestAvgTemp(Map<String, List<Integer>> cityTemps) {
            return cityTemps.entrySet().stream()
                    .max(Comparator.comparingDouble(entry -> entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0.0)))
                    .map(Map.Entry::getKey);
        }
    }
}