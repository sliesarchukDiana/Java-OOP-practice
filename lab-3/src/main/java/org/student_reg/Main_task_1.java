package org.student_reg;

public class Main_task_1 {
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
        }
    }
