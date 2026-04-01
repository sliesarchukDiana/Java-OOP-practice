package dao;
import entity.CatalogueSection;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CatalogueSectionDao {
    public void createSection(CatalogueSection section) {
        String sql = "INSERT INTO Catalogue_section (name, parent_id) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, section.getName());
                pstmt.setObject(2, section.getParentId(), Types.INTEGER);

                pstmt.executeUpdate();
                log.info("Section '{}' successfully added.", section.getName());
            }
        } catch (SQLException e) {
            log.error("Something went wrong, failed to add section!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public List<CatalogueSection> getAllSections() {
        List<CatalogueSection> sections = new ArrayList<>();
        String sql = "SELECT * FROM Catalogue_section";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Integer parentId = (Integer) rs.getObject("parent_id");

                    CatalogueSection cs = new CatalogueSection(
                            rs.getInt("id_section"),
                            rs.getString("name"),
                            parentId
                    );
                    sections.add(cs);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to load the sections!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
        return sections;
    }

    public void updateSection(CatalogueSection section) {
        String sql = "UPDATE Catalogue_section SET name = ?, parent_id = ? WHERE id_section = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, section.getName());
                pstmt.setObject(2, section.getParentId(), Types.INTEGER);
                pstmt.setInt(3, section.getIdSection());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Section with ID {} was successfully updated.", section.getIdSection());
                } else {
                    log.warn("Failed to find section with ID {}.", section.getIdSection());
                }
            }
        } catch (SQLException e) {
            log.error("Failed to update the section", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public void deleteSection(int idSection) {
        String sql = "DELETE FROM Catalogue_section WHERE id_section = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idSection);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Section with ID {} was deleted.", idSection);
                } else {
                    log.warn("No section with ID {} was found.", idSection);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to delete section with ID {}!", idSection, e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }
}