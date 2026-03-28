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
            log.info("Користувач {} успішно доданий.", person.getFirstName());

        } catch (SQLException e) {
            log.error("Помилка при додаванні користувача!", e);
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
            log.error("Помилка при отриманні списку користувачів!", e);
        }
        return persons;
    }

}