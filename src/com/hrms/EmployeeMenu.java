package com.hrms;

import java.util.Scanner;

public class EmployeeMenu {

    public static void showMenu(Scanner sc) {

        int choice = 0;

        do {
            System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Back to Organization Menu");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
            case 1:
                System.out.print("Enter Employee Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Department: ");
                String department = sc.nextLine();

                System.out.print("Enter Salary: ");
                double salary;

                try {
                    salary = Double.parseDouble(sc.nextLine());

                    boolean added = EmployeeDAO.addEmployee(
                            name, email, department, salary);

                    if (added) {
                        System.out.println("Employee added successfully!");
                    } else {
                        System.out.println("Employee not added.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid salary.");
                }
                break;

            case 2:
                EmployeeDAO.viewEmployees();
                break;

            case 3:
                System.out.print("Enter Employee ID to update: ");
                int id;

                try {
                    id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter New Employee Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter New Department: ");
                    String newDepartment = sc.nextLine();

                    System.out.print("Enter New Salary: ");
                    double newSalary = Double.parseDouble(sc.nextLine());

                    boolean updated = EmployeeDAO.updateEmployee(
                            id, newName, newEmail, newDepartment, newSalary);

                    if (updated) {
                        System.out.println("Employee updated successfully!");
                    } else {
                        System.out.println("Employee ID not found.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid numeric values.");
                }

                break;

            case 4:
                System.out.print("Enter Employee ID to delete: ");

                try {
                    int deleteId = Integer.parseInt(sc.nextLine());

                    System.out.print(
                        "Are you sure you want to delete this employee? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {
                        boolean deleted = EmployeeDAO.deleteEmployee(deleteId);

                        if (deleted) {
                            System.out.println("Employee deleted successfully!");
                        } else {
                            System.out.println("Employee ID not found.");
                        }
                    } else {
                        System.out.println("Delete cancelled.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid employee ID.");
                }

                break;
                case 5:
                    System.out.println("Returning to Organization Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }
}
