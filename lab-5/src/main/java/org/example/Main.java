package org.example;
import dao.*;
import entity.Keyword;
import utils.DatabaseConnection;
import lombok.extern.log4j.Log4j2;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class Main {
    static void main() {
        log.info("Starting the work...");

        DatabaseConnection dbConnectionPool = DatabaseConnection.getInstance();
        Connection conn = null;

        try {
            conn = dbConnectionPool.getConnection();
            PersonDao personDao = new PersonDao(conn);
            MaterialDao materialDao = new MaterialDao(conn);
            KeywordDao keywordDao = new KeywordDao(conn);
            AuthorDao authorDao = new AuthorDao(conn);
            CatalogueSectionDao sectionDao = new CatalogueSectionDao(conn);

            log.info("Total Users registered: {}", personDao.getAllPersons().size());
            log.info("Total Authors registered: {}", authorDao.getAllAuthors().size());
            log.info("Total Materials available: {}", materialDao.getAllMaterials().size());
            log.info("Total Catalogue Sections: {}", sectionDao.getAllSections().size());
            log.info("Total Keywords in system: {}", keywordDao.getAllKeywords().size());

            log.info("We've found:\n");
            personDao.searchPersonsWithMetadata("thekidslover");
            personDao.searchPersonsWithMetadata("Стівен");

            log.info("CRUD");

            try {
                conn.setAutoCommit(false);

                log.info("1. CREATE: Adding a new tag...");
                Keyword testKeyword = new Keyword();
                testKeyword.setWord("ADA");
                keywordDao.createKeyword(testKeyword);

                log.info("2. READ: Finding the tag...");
                List<Keyword> allKeywords = keywordDao.getAllKeywords();
                Keyword foundKeyword = null;
                for (Keyword k : allKeywords) {
                    if ("ADA".equals(k.getWord())) {
                        foundKeyword = k;
                        log.info("Tag found in DB! Assigned ID: {}", k.getIdKeyword());
                        break;
                    }
                }

                if (foundKeyword != null) {
                    log.info("3. UPDATE: Changing the tag name...");
                    foundKeyword.setWord("Ada");
                    keywordDao.updateKeyword(foundKeyword);
                    log.info("Tag updated to: {}", foundKeyword.getWord());

                    log.info("4. DELETE: Cleaning up the database...");
                    //keywordDao.deleteKeyword(foundKeyword.getIdKeyword());
                    log.info("Temporary tag deleted successfully.");
                }

                conn.commit();
                log.info("Transaction committed successfully!");

            } catch (SQLException e) {
                conn.rollback();
                log.error("SQL Error occurred, transaction rolled back.", e);
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (Exception e) {
            log.error("Unexpected error in main execution loop.", e);
        } finally {
            if (conn != null) {
                dbConnectionPool.releaseConnection(conn);
                log.info("Connection released back to the pool.");
            }
        }
    }
}