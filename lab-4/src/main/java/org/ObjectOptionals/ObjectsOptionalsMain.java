package org.ObjectOptionals;
import java.util.*;
import static org.ObjectOptionals.ObjectsOptional.StreamTasks.*;

public class ObjectsOptionalsMain {
    static void main() {
        var employees = List.of(new Employee("Diddy", 1000), new Employee("Jamal", 2000), new Employee("Malik", 4000), new Employee("Jeffrey", 6000));
        var groupedEmployees = topEarnerBySalaryRange(employees);
        System.out.println("The most well payed workers in range:");
        groupedEmployees.forEach((range, empOpt) -> empOpt.ifPresent(emp -> System.out.printf("  Range %s: %s (Salary: %.0f)%n", range, emp.name(), emp.salary())));

        System.out.println("Multiplication: " + productOfOdds(List.of(1, 2, 3, 4, 5)).orElse(0));

        var friend1 = new Person("Bill", List.of());
        var friend2 = new Person("Stephen", List.of());
        var people = List.of(new Person("Jeffrey", List.of(friend1, friend2)), new Person("Elon", List.of(friend1)));
        System.out.println("Unique friends: " + uniqueFriendsNames(people));

        var transactions = List.of(new Transaction(100, "Restaurants"), new Transaction(67, "Restaurants"), new Transaction(129, "Taxi"));
        System.out.println("Filtered transactions: " + sumTransactionsByCategory(transactions));

        var products = List.of(new Product("Bread", 40), new Product("Cheese", 80), new Product("Meat", 210), new Product ("Eggs", 89));
        System.out.println("Second the most expensive product is " + secondMostExpensiveProduct(products).orElse(""));

        var productMap = Map.of("1", Optional.of("Juice"), "2", Optional.<String>empty(), "3", Optional.of("Water"), "4", Optional.of("Coffee"));
        System.out.println("Завдання 9: " + getPresentProductNames(productMap));

        var cityTemps = Map.of("Kyiv", List.of(20, 25), "Dnipro", List.of(15, 18), "Chernivtsi", List.of(10, 14));
        System.out.println("Highest average temperature in the city " + cityWithHighestAvgTemp(cityTemps).orElse(""));
    }
}