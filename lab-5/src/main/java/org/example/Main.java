package org.example;

import dao.PersonDao;
import entity.Person;
import java.util.List;

public class Main {
    static void main() {
        System.out.println("🚀 Запуск програми...");
        PersonDao personDao = new PersonDao();
        List<Person> people = personDao.getAllPersons();
        System.out.println("✅ Знайдено користувачів у базі: " + people.size());
        System.out.println("--------------------------------------------------");

        for (Person p : people) {
            System.out.println("ID: " + p.getIdPerson() +
                    " | Ім'я: " + p.getFirstName() + " " + p.getLastName() +
                    " | Email: " + p.getEmail());
        }

        System.out.println("--------------------------------------------------");
    }
}