package com.hrms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

public class AttendanceDAO {

    public static boolean addAttendance(
            int employeeId, String date, String status) {

        String sql = "INSERT INTO attendance "
                   + "(employee_id, attendance_date, status) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3, status);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error adding attendance: "
                    + e.getMessage());
            return false;
        }
    }

    public static void viewAttendance() {

        String sql = "SELECT * FROM attendance ORDER BY attendance_date";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== ATTENDANCE DETAILS =====");

            while (rs.next()) {
                found = true;

                System.out.println("------------------------");
                System.out.println("Attendance ID: "
                        + rs.getInt("attendance_id"));
                System.out.println("Employee ID: "
                        + rs.getInt("employee_id"));
                System.out.println("Date: "
                        + rs.getDate("attendance_date"));
                System.out.println("Status: "
                        + rs.getString("status"));
            }

            if (!found) {
                System.out.println("No attendance records found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing attendance: "
                    + e.getMessage());
        }
    }

    public static boolean updateAttendance(
            int attendanceId, String date, String status) {

        String sql = "UPDATE attendance SET attendance_date = ?, "
                   + "status = ? WHERE attendance_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));
            ps.setString(2, status);
            ps.setInt(3, attendanceId);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error updating attendance: "
                    + e.getMessage());
            return false;
        }
    }

    public static boolean deleteAttendance(int attendanceId) {

        String sql = "DELETE FROM attendance WHERE attendance_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendanceId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting attendance: "
                    + e.getMessage());
            return false;
        }
    }
}