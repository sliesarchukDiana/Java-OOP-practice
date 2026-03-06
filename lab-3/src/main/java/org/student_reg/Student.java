package org.student_reg;

public class Student {
    String firstName, lastName;
    int age;
    float averageGrade;

     public Student (String fname, String lname, int age, float avgGrade){
         this.firstName = fname;
         this.lastName = lname;
         this.age = age;
         this.averageGrade = avgGrade;
     }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

