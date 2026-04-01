package org.example;
import dao.*;
import entity.Keyword;
import java.util.List;

public class Main {
    static void main() {
        System.out.println("Starting the work...\n");
        PersonDao personDao = new PersonDao();
        MaterialDao materialDao = new MaterialDao();
        KeywordDao keywordDao = new KeywordDao();
        AuthorDao authorDao = new AuthorDao();
        CatalogueSectionDao sectionDao = new CatalogueSectionDao();

        System.out.println("\nTotal Users registered: " + personDao.getAllPersons().size());
        System.out.println("Total Authors registered: " + authorDao.getAllAuthors().size());
        System.out.println("Total Materials available: " + materialDao.getAllMaterials().size());
        System.out.println("Total Catalogue Sections: " + sectionDao.getAllSections().size());
        System.out.println("Total Keywords in system: " + keywordDao.getAllKeywords().size());

        personDao.searchPersonsWithMetadata("thekidslover");
        personDao.searchPersonsWithMetadata("Стівен");

        System.out.println("\nCRUD");
        System.out.println("CREATE: Adding a new tag...");
        Keyword testKeyword = new Keyword();
        testKeyword.setWord("C sharp");
        keywordDao.createKeyword(testKeyword);
        System.out.println("READ: Finding the tag...");
        List<Keyword> allKeywords = keywordDao.getAllKeywords();
        Keyword foundKeyword = null;
        for (Keyword k : allKeywords) {
            if (k.getWord().isEmpty()) {
                foundKeyword = k;
                System.out.println("Tag found in DB! Assigned ID: " + k.getIdKeyword());
                break;
            }
        }
        if (foundKeyword != null) {
            System.out.println("3. UPDATE: Changing the tag name...");
            foundKeyword.setWord("C#");
            keywordDao.updateKeyword(foundKeyword);
            System.out.println("Tag updated to: " + foundKeyword.getWord());

            System.out.println("4. DELETE: Cleaning up the database...");
            keywordDao.deleteKeyword(foundKeyword.getIdKeyword());
            System.out.println("Temporary tag deleted successfully.");
        }
    }
}