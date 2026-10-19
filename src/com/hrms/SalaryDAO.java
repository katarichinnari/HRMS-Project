package com.hrms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryDAO {

    public static boolean createSalary(
            int employeeId, double amount, String month) {

        String sql = "INSERT INTO salaries "
                   + "(employee_id, salary_amount, salary_month) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setDouble(2, amount);
            ps.setString(3, month);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error creating salary: "
                    + e.getMessage());
            return false;
        }
    }

    public static void viewSalaries() {

        String sql = "SELECT * FROM salaries ORDER BY employee_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== SALARY DETAILS =====");

            while (rs.next()) {
                found = true;

                System.out.println("----------------------");
                System.out.println("Salary ID: "
                        + rs.getInt("salary_id"));
                System.out.println("Employee ID: "
                        + rs.getInt("employee_id"));
                System.out.println("Salary Amount: "
                        + rs.getBigDecimal("salary_amount"));
                System.out.println("Salary Month: "
                        + rs.getString("salary_month"));
            }

            if (!found) {
                System.out.println("No salary records found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing salaries: "
                    + e.getMessage());
        }
    }

    public static boolean updateSalary(
            int salaryId, double amount, String month) {

        String sql = "UPDATE salaries SET salary_amount = ?, "
                   + "salary_month = ? WHERE salary_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setString(2, month);
            ps.setInt(3, salaryId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating salary: "
                    + e.getMessage());
            return false;
        }
    }

    public static boolean deleteSalary(int salaryId) {

        String sql = "DELETE FROM salaries WHERE salary_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, salaryId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting salary: "
                    + e.getMessage());
            return false;
        }
    }
}
