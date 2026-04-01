package dao;
import entity.Author;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AuthorDao {
    public void createAuthor(Author author) {
        String sql = "INSERT INTO Author (id_person, bio) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, author.getIdPerson());
                pstmt.setString(2, author.getBio());

                pstmt.executeUpdate();
                log.info("Author with ID {} successfully added.", author.getIdPerson());
            }
        } catch (SQLException e) {
            log.error("Something went wrong, failed to add author!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM Author";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Author a = new Author(
                            rs.getInt("id_person"),
                            rs.getString("bio")
                    );
                    authors.add(a);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to load the authors!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
        return authors;
    }

    public void updateAuthor(Author author) {
        String sql = "UPDATE Author SET bio = ? WHERE id_person = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, author.getBio());
                pstmt.setInt(2, author.getIdPerson());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Author with ID {} was successfully updated.", author.getIdPerson());
                } else {
                    log.warn("Failed to find author with ID {}.", author.getIdPerson());
                }
            }
        } catch (SQLException e) {
            log.error("Failed to update the author", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public void deleteAuthor(int idPerson) {
        String sql = "DELETE FROM Author WHERE id_person = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPerson);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Author with ID {} was deleted.", idPerson);
                } else {
                    log.warn("No author with ID {} was found.", idPerson);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to delete author with ID {}!", idPerson, e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }
}