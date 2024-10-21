package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

public class SubscriptionDAO {
    public void addSubscription(String email) {
        String sql = "INSERT INTO Subscriptions (email) VALUES (?)";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllSubscribers() {
        List<String> subscribers = new ArrayList<>();
        String sql = "SELECT email FROM Subscriptions";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                subscribers.add(rs.getString("email"));
            }
        } catch (SQLException | UnknownHostException e) {
            e.printStackTrace();
        }
        return subscribers;
    }
}