package org.student_reg;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o){
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Student student = (Student) o;
         return age == student.age && Float.compare(student.averageGrade, averageGrade) == 0 &&
        Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode (){
         return Objects.hash(firstName, lastName, age, averageGrade);
    }
}

