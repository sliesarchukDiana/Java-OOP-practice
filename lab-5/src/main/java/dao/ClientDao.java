package dao;
import entity.Client;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ClientDao {
    public void createClient(Client client) {
        String sql = "INSERT INTO Client (id_person, reg_date) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, client.getIdPerson());
                pstmt.setDate(2, Date.valueOf(client.getRegDate()));

                pstmt.executeUpdate();
                log.info("Client with ID {} successfully added.", client.getIdPerson());
            }
        } catch (SQLException e) {
            log.error("Something went wrong, failed to add client!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Client c = new Client(
                            rs.getInt("id_person"),
                            rs.getDate("reg_date").toLocalDate()
                    );
                    clients.add(c);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to load the clients!", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
        return clients;
    }

    public void updateClient(Client client) {
        String sql = "UPDATE Client SET reg_date = ? WHERE id_person = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, Date.valueOf(client.getRegDate()));
                pstmt.setInt(2, client.getIdPerson());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Client with ID {} was successfully updated.", client.getIdPerson());
                } else {
                    log.warn("Failed to find client with ID {}.", client.getIdPerson());
                }
            }
        } catch (SQLException e) {
            log.error("Failed to update the client", e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }

    public void deleteClient(int idPerson) {
        String sql = "DELETE FROM Client WHERE id_person = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPerson);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Client with ID {} was deleted.", idPerson);
                } else {
                    log.warn("No client with ID {} was found.", idPerson);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to delete client with ID {}!", idPerson, e);
        } finally {
            if (conn != null) {
                DatabaseConnection.getInstance().releaseConnection(conn);
            }
        }
    }
}