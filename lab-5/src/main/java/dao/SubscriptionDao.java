package dao;
import entity.Subscription;
import lombok.extern.log4j.Log4j2;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class SubscriptionDao {
    public void createSubscription(Subscription subscription) {
        String sql = "INSERT INTO Subscription (format_type, id_client, id_section, id_keyword) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, subscription.getFormatType());
            pstmt.setInt(2, subscription.getIdClient());
            pstmt.setInt(3, subscription.getIdSection());
            pstmt.setInt(4, subscription.getIdKeyword());

            pstmt.executeUpdate();
            log.info("Subscription for client ID {} successfully added.", subscription.getIdClient());

        } catch (SQLException e) {
            log.error("Something went wrong, failed to add subscription!", e);
        }
    }

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM Subscription";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Subscription s = new Subscription(
                        rs.getInt("id_subscription"),
                        rs.getInt("format_type"),
                        rs.getInt("id_client"),
                        rs.getInt("id_section"),
                        rs.getInt("id_keyword")
                );
                subscriptions.add(s);
            }
        } catch (SQLException e) {
            log.error("Failed to load the subscriptions!", e);
        }
        return subscriptions;
    }

    public void updateSubscription(Subscription subscription) {
        String sql = "UPDATE Subscription SET format_type = ?, id_client = ?, id_section = ?, id_keyword = ? WHERE id_subscription = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, subscription.getFormatType());
            pstmt.setInt(2, subscription.getIdClient());
            pstmt.setInt(3, subscription.getIdSection());
            pstmt.setInt(4, subscription.getIdKeyword());
            pstmt.setInt(5, subscription.getIdSubscription());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Subscription with ID {} was successfully updated.", subscription.getIdSubscription());
            } else {
                log.warn("Failed to find subscription with ID {}.", subscription.getIdSubscription());
            }

        } catch (SQLException e) {
            log.error("Failed to update the subscription", e);
        }
    }

    public void deleteSubscription(int idSubscription) {
        String sql = "DELETE FROM Subscription WHERE id_subscription = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idSubscription);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Subscription with ID {} was deleted.", idSubscription);
            } else {
                log.warn("No subscription with ID {} was found.", idSubscription);
            }

        } catch (SQLException e) {
            log.error("Failed to delete subscription with ID {}!", idSubscription, e);
        }
    }
}