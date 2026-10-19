package com.hrms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class NoticeDAO {

    // 1. Create Notice or Holiday
    public static boolean createNotice(
            String title, String description,
            String date, String type) {

        String sql = "INSERT INTO notices "
                   + "(title, description, notice_date, notice_type) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, description);

            if (date.trim().isEmpty()) {
                ps.setNull(3, java.sql.Types.DATE);
            } else {
                ps.setDate(3, Date.valueOf(date));
            }

            ps.setString(4, type);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error creating notice: " + e.getMessage());
            return false;
        }
    }

    // 2. View Notices and Holidays
    public static void viewNotices() {

        String sql = "SELECT * FROM notices ORDER BY notice_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== NOTICES AND HOLIDAYS =====");

            while (rs.next()) {
                found = true;

                System.out.println("-------------------------");
                System.out.println("Notice ID: "
                        + rs.getInt("notice_id"));
                System.out.println("Title: "
                        + rs.getString("title"));
                System.out.println("Description: "
                        + rs.getString("description"));
                System.out.println("Date: "
                        + rs.getDate("notice_date"));
                System.out.println("Type: "
                        + rs.getString("notice_type"));
            }

            if (!found) {
                System.out.println("No notices or holidays found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing notices: " + e.getMessage());
        }
    }

    // 3. Update Notice or Holiday
    public static boolean updateNotice(
            int id, String title, String description,
            String date, String type) {

        String sql = "UPDATE notices SET title = ?, description = ?, "
                   + "notice_date = ?, notice_type = ? "
                   + "WHERE notice_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, description);

            if (date.trim().isEmpty()) {
                ps.setNull(3, java.sql.Types.DATE);
            } else {
                ps.setDate(3, Date.valueOf(date));
            }

            ps.setString(4, type);
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error updating notice: " + e.getMessage());
            return false;
        }
    }

    // 4. Delete Notice or Holiday
    public static boolean deleteNotice(int id) {

        String sql = "DELETE FROM notices WHERE notice_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting notice: " + e.getMessage());
            return false;
        }
    }
}