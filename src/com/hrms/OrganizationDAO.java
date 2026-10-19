package com.hrms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrganizationDAO {

    public static boolean createOrganization(
            String name,
            String address,
            String pan,
            String gst,
            String department,
            String email,
            String passwordHash,
            int employeeCount) {

        String sql = "INSERT INTO organizations "
                + "(organization_name, address, pan, gst, "
                + "department, email, password_hash, employee_count) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                return false;
            }

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, pan);
            ps.setString(4, gst);
            ps.setString(5, department);
            ps.setString(6, email);
            ps.setString(7, passwordHash);
            ps.setInt(8, employeeCount);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Organization creation failed.");
            e.printStackTrace();
            return false;
        }
    }
    public static String loginOrganization(
            String email, String password) {

        String sql = "SELECT organization_name FROM organizations "
                   + "WHERE email = ? AND password_hash = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                return null;
            }

            ps.setString(1, email);
            ps.setString(2, password);

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("organization_name");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static boolean resetPassword(String email, String newPassword) {
        String sql = "UPDATE organizations SET password_hash = ? WHERE email = ?";

        try (java.sql.Connection con = DBConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
}