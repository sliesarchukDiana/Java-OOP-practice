package dao;
import entity.Keyword;
import lombok.extern.log4j.Log4j2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class KeywordDao {
    private Connection conn;

    public KeywordDao(Connection conn) {
        this.conn = conn;
    }

    public void createKeyword(Keyword keyword) {
        String sql = "INSERT INTO Keyword (word) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, keyword.getWord());

            pstmt.executeUpdate();
            log.info("Keyword '{}' successfully added.", keyword.getWord());
        } catch (SQLException e) {
            log.error("Something went wrong, failed to add keyword!", e);
        }
    }

    public List<Keyword> getAllKeywords() {
        List<Keyword> keywords = new ArrayList<>();
        String sql = "SELECT * FROM Keyword";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Keyword k = new Keyword(
                        rs.getInt("id_keyword"),
                        rs.getString("word")
                );
                keywords.add(k);
            }
        } catch (SQLException e) {
            log.error("Failed to load the keywords!", e);
        }
        return keywords;
    }

    public void updateKeyword(Keyword keyword) {
        String sql = "UPDATE Keyword SET word = ? WHERE id_keyword = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, keyword.getWord());
            pstmt.setInt(2, keyword.getIdKeyword());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Keyword with ID {} was successfully updated.", keyword.getIdKeyword());
            } else {
                log.warn("Failed to find keyword with ID {}.", keyword.getIdKeyword());
            }
        } catch (SQLException e) {
            log.error("Failed to update the keyword", e);
        }
    }

    public void deleteKeyword(int idKeyword) {
        String sql = "DELETE FROM Keyword WHERE id_keyword = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idKeyword);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Keyword with ID {} was deleted.", idKeyword);
            } else {
                log.warn("No keyword with ID {} was found.", idKeyword);
            }
        } catch (SQLException e) {
            log.error("Failed to delete keyword with ID {}!", idKeyword, e);
        }
    }
}