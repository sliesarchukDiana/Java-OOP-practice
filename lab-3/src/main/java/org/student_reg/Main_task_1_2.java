package org.student_reg;
import java.util.*;

public class Main_task_1_2 {

    public static <T> Set<T> getUniqueElements (List<T> list){
        return new HashSet<>(list);
    }

    public static <T> Map<T, Integer> countElements(List<T> list) {
        Map<T, Integer> countMap = new HashMap<>();
        for (T item : list){
            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
        }
        return countMap;
    }

        static void main() {
            Student_register registry = new Student_register();

            Student s1 = new Student("Bob", "Smith", 20, 4.5f);
            Student s2 = new Student("Bella", "Swan", 17, 4.9f);
            Student s3 = new Student("Edward", "Cullen", 17, 3.2f);

            registry.addStudent(s1);
            registry.addStudent(s2);
            registry.addStudent(s3);
            registry.displayAllStudents();
            System.out.println("Seeking student by id 2: " + registry.findStudent(2));
            registry.deleteStudent(1);
            registry.displayAllStudents();

            Student s4 = new Student("Bob", "Smith", 20, 4.5f);
            Student s5 = new Student("Bella", "Swan", 17, 4.9f);

            List<Student> studentList = Arrays.asList(s1, s2, s3, s4, s5);
            System.out.println("Student count with duplicates: " + studentList.size());
            Set<Student> uniqueStudents = getUniqueElements(studentList);
            System.out.println ("Unique student count: " + uniqueStudents.size());
            System.out.println("Unique students (Set): " + uniqueStudents + "\n");

            System.out.println("Number of occurrences (Map): ");
            Map<Student, Integer> studentCount = countElements(studentList);
            for (Map.Entry<Student, Integer> entry : studentCount.entrySet()){
                System.out.println(entry.getKey() + " -> " + entry.getValue() + " times");
            }
        }
    }
