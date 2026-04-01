package dao;
import entity.Material;
import lombok.extern.log4j.Log4j2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MaterialDao {
    private Connection conn;

    public MaterialDao(Connection conn) {
        this.conn = conn;
    }

    public void createMaterial(Material material) {
        String sql = "INSERT INTO Material (title, annotation, body_text, cost, id_section) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, material.getTitle());
            pstmt.setString(2, material.getAnnotation());
            pstmt.setString(3, material.getBodyText());
            pstmt.setDouble(4, material.getCost());
            pstmt.setInt(5, material.getIdSection());

            pstmt.executeUpdate();
            log.info("Material '{}' successfully added.", material.getTitle());
        } catch (SQLException e) {
            log.error("Something went wrong, failed to add material!", e);
        }
    }

    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM Material";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Material m = new Material(
                        rs.getInt("id_material"),
                        rs.getString("title"),
                        rs.getString("annotation"),
                        rs.getString("body_text"),
                        rs.getDouble("cost"),
                        rs.getInt("id_section")
                );
                materials.add(m);
            }
        } catch (SQLException e) {
            log.error("Failed to load the materials!", e);
        }
        return materials;
    }

    public void updateMaterial(Material material) {
        String sql = "UPDATE Material SET title = ?, annotation = ?, body_text = ?, cost = ?, id_section = ? WHERE id_material = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, material.getTitle());
            pstmt.setString(2, material.getAnnotation());
            pstmt.setString(3, material.getBodyText());
            pstmt.setDouble(4, material.getCost());
            pstmt.setInt(5, material.getIdSection());
            pstmt.setInt(6, material.getIdMaterial());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Material with ID {} was successfully updated.", material.getIdMaterial());
            } else {
                log.warn("Failed to find material with ID {}.", material.getIdMaterial());
            }
        } catch (SQLException e) {
            log.error("Failed to update the material", e);
        }
    }

    public void deleteMaterial(int idMaterial) {
        String sql = "DELETE FROM Material WHERE id_material = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMaterial);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Material with ID {} was deleted.", idMaterial);
            } else {
                log.warn("No material with ID {} was found.", idMaterial);
            }
        } catch (SQLException e) {
            log.error("Failed to delete material with ID {}!", idMaterial, e);
        }
    }
}