package org.example;

import dao.PersonDao;

public class Main {
    static void main() {
        System.out.println("Let's get started...");

        PersonDao personDao = new PersonDao();
        personDao.searchPersonsWithMetadata("Олег");
        personDao.searchPersonsWithMetadata("thekidslover");
        personDao.searchPersonsWithMetadata("Абракадабра");
    }
}