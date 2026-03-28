package dao;
import entity.MaterialKeyword;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MaterialKeywordDao {
    public void linkKeywordToMaterial(MaterialKeyword mk) {
        String sql = "INSERT INTO `Material-Keyword` (id_material, id_keyword) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mk.getIdMaterial());
            pstmt.setInt(2, mk.getIdKeyword());

            pstmt.executeUpdate();
            log.info("Keyword {} successfully linked to Material {}.", mk.getIdKeyword(), mk.getIdMaterial());

        } catch (SQLException e) {
            log.error("Something went wrong, failed to attach keyword!", e);
        }
    }

    public List<MaterialKeyword> getAllLinks() {
        List<MaterialKeyword> links = new ArrayList<>();
        String sql = "SELECT * FROM `Material-Keyword`";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MaterialKeyword mk = new MaterialKeyword(
                        rs.getInt("id_material"),
                        rs.getInt("id_keyword")
                );
                links.add(mk);
            }
        } catch (SQLException e) {
            log.error("Failed to load the material-keyword links!", e);
        }
        return links;
    }

    public void unlinkKeywordFromMaterial(int idMaterial, int idKeyword) {
        String sql = "DELETE FROM `Material-Keyword` WHERE id_material = ? AND id_keyword = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idMaterial);
            pstmt.setInt(2, idKeyword);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Link between Material {} and Keyword {} was deleted.", idMaterial, idKeyword);
            } else {
                log.warn("No link found to delete.");
            }

        } catch (SQLException e) {
            log.error("Failed to delete the link!", e);
        }
    }
}