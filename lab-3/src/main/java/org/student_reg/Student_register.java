package org.student_reg;
import java.util.HashMap;
import java.util.Map;

public class Student_register {
        Map<Integer, Student> students = new HashMap<>();
        int idCounter = 1;

        void addStudent(Student student){
            students.put(idCounter++, student);
            System.out.println("Student " + student.firstName + " was added by ID: " + (idCounter-1));
            }

        void deleteStudent (int id){
            if (students.containsKey(id)){
                students.remove(id);
                System.out.println("Student with ID " + id + " was removed");
            }
            else{
                System.out.println("Student not found");
            }
        }

        Student findStudent(int id){
            return students.get(id);
        }

        void displayAllStudents(){
            if (students.isEmpty()){
                System.out.println("No records found");
            }
            System.out.println("\n");
            for(Map.Entry<Integer, Student> entry : students.entrySet()){
                Student currentStudent = entry.getValue();
                System.out.println("ID: " + entry.getKey() + "|" + currentStudent.firstName
                        + " " + currentStudent.lastName + " " + currentStudent.age + " years old, average grade: "
                            + currentStudent.averageGrade);
            }
            System.out.println("\n");
        }
}

