package dao;
import entity.AuthorMaterial;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AuthorMaterialDao {

    public void linkAuthorToMaterial(AuthorMaterial am) {
        String sql = "INSERT INTO `Author-Material` (id_author, id_material) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, am.getIdAuthor());
                pstmt.setInt(2, am.getIdMaterial());

                pstmt.executeUpdate();
                log.info("Author {} successfully linked to Material {}.", am.getIdAuthor(), am.getIdMaterial());
            }
        } catch (SQLException e) {
            log.error("Something went wrong, failed to link author to material!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public List<AuthorMaterial> getAllLinks() {
        List<AuthorMaterial> links = new ArrayList<>();
        String sql = "SELECT * FROM `Author-Material`";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    AuthorMaterial am = new AuthorMaterial(
                            rs.getInt("id_author"),
                            rs.getInt("id_material")
                    );
                    links.add(am);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to load the author-material links!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
        return links;
    }

    public void unlinkAuthorFromMaterial(int idAuthor, int idMaterial) {
        String sql = "DELETE FROM `Author-Material` WHERE id_author = ? AND id_material = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idAuthor);
                pstmt.setInt(2, idMaterial);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Link between Author {} and Material {} was deleted.", idAuthor, idMaterial);
                } else {
                    log.warn("No link found to delete.");
                }
            }
        } catch (SQLException e) {
            log.error("Failed to delete the link!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }
}