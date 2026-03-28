package dao;
import entity.Person;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PersonDao {
    public void createPerson(Person person) {
        String sql = "INSERT INTO Person (first_name, last_name, email, password_hash) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getPasswordHash());

            pstmt.executeUpdate();
            log.info("User {} successfully added.", person.getFirstName());

        } catch (SQLException e) {
            log.error("Something went wrong, failed to add user!", e);
        }
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM Person";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Person p = new Person(
                        rs.getInt("id_Person"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                );
                persons.add(p);
            }
        } catch (SQLException e) {
            log.error("Failed to load the users!", e);
        }
        return persons;
    }

    public void updatePerson(Person person) {
        String sql = "UPDATE Person SET first_name = ?, last_name = ?, email = ?, password_hash = ? WHERE id_Person = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getPasswordHash());
            pstmt.setInt(5, person.getIdPerson()); // ID для умови WHERE

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("User with ID {} was successfully updated.", person.getIdPerson());
            } else {
                log.warn("Failed to find user with ID {}.", person.getIdPerson());
            }

        } catch (SQLException e) {
            log.error("Failed to update the user", e);
        }
    }

    public void deletePerson(int idPerson) {
        String sql = "DELETE FROM Person WHERE id_Person = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPerson);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("User with ID {} was deleted.", idPerson);
            } else {
                log.warn("No user with ID {} was found.", idPerson);
            }

        } catch (SQLException e) {
            log.error("Failed to delete user with ID {}!", idPerson, e);
        }
    }

    public void searchPersonsWithMetadata(String keyword) {
        String sql = "SELECT id_Person, first_name, last_name, email FROM Person WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                System.out.println("\n Search results: '" + keyword + "'");
                System.out.println("-------------------------------------------------------------");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-15s | ", metaData.getColumnName(i).toUpperCase());
                }
                System.out.println("\n-------------------------------------------------------------");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("%-15s | ", rs.getString(i));
                    }
                    System.out.println();
                }

                if (!found) {
                    System.out.println("Oops... We've found nothing!.");
                }
                System.out.println("-------------------------------------------------------------");
            }
        } catch (SQLException e) {
            log.error("Failed to search!", e);
        }
    }
}