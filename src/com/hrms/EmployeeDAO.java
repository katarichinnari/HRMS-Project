package com.hrms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {

    public static boolean addEmployee(
            String name, String email,
            String department, double salary) {

        String sql = "INSERT INTO employees "
                   + "(employee_name, email, department, salary) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, department);
            ps.setDouble(4, salary);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding employee: "
                    + e.getMessage());
            return false;
        }
    }
    public static void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== EMPLOYEE DETAILS =====");

            while (rs.next()) {
                found = true;

                System.out.println("---------------------------");
                System.out.println("Employee ID: "
                        + rs.getInt("employee_id"));
                System.out.println("Name: "
                        + rs.getString("employee_name"));
                System.out.println("Email: "
                        + rs.getString("email"));
                System.out.println("Department: "
                        + rs.getString("department"));
                System.out.println("Salary: "
                        + rs.getDouble("salary"));
            }

            if (!found) {
                System.out.println("No employees found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing employees: "
                    + e.getMessage());
        }
    }
    public static boolean updateEmployee(
            int id, String name, String email,
            String department, double salary) {

        String sql = "UPDATE employees SET employee_name = ?, "
                   + "email = ?, department = ?, salary = ? "
                   + "WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, department);
            ps.setDouble(4, salary);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating employee: "
                    + e.getMessage());
            return false;
        }
    }
    public static boolean deleteEmployee(int id) {

        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting employee: "
                    + e.getMessage());
            return false;
        }
    }
}