package com.hrms;

import java.util.Scanner;

public class SalaryMenu {

    public static void showMenu(Scanner sc) {

        int choice = 0;

        do {
            System.out.println("\n===== SALARY MANAGEMENT =====");
            System.out.println("1. Create Salary");
            System.out.println("2. View Salaries");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Salary");
            System.out.println("5. Back to Organization Menu");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Employee ID: ");
                        int empId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Salary Amount: ");
                        double amount = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Salary Month (e.g. October 2026): ");
                        String month = sc.nextLine();

                        if (amount <= 0 || month.trim().isEmpty()) {
                            System.out.println(
                                "Enter a valid salary amount and month.");
                            break;
                        }

                        boolean created = SalaryDAO.createSalary(
                                empId, amount, month);

                        System.out.println(created
                                ? "Salary created successfully!"
                                : "Salary not created.");

                    } catch (NumberFormatException e) {
                        System.out.println(
                            "Employee ID and salary must be numeric.");
                    }
                    break;

                case 2:
                    SalaryDAO.viewSalaries();
                    break;

                case 3:
                    try {
                        System.out.print("Enter Salary ID to update: ");
                        int salaryId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter New Salary Amount: ");
                        double newAmount = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter New Salary Month: ");
                        String newMonth = sc.nextLine();

                        if (newAmount <= 0 || newMonth.trim().isEmpty()) {
                            System.out.println(
                                "Enter a valid salary amount and month.");
                            break;
                        }

                        boolean updated = SalaryDAO.updateSalary(
                                salaryId, newAmount, newMonth);

                        System.out.println(updated
                                ? "Salary updated successfully!"
                                : "Salary ID not found.");

                    } catch (NumberFormatException e) {
                        System.out.println(
                            "Enter valid numeric values.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Salary ID to delete: ");
                        int salaryId = Integer.parseInt(sc.nextLine());

                        System.out.print("Confirm delete? (yes/no): ");
                        String confirm = sc.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            boolean deleted =
                                    SalaryDAO.deleteSalary(salaryId);

                            System.out.println(deleted
                                    ? "Salary deleted successfully!"
                                    : "Salary ID not found.");
                        } else {
                            System.out.println("Delete cancelled.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(
                            "Enter a valid salary ID.");
                    }
                    break;

                case 5:
                    System.out.println(
                        "Returning to Organization Menu...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}