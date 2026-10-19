package com.hrms;

import java.util.Scanner;

public class AttendanceMenu {

    public static void showMenu(Scanner sc) {

        int choice = 0;

        do {
            System.out.println("\n===== ATTENDANCE MANAGEMENT =====");
            System.out.println("1. Create Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. Update Attendance");
            System.out.println("4. Delete Attendance");
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

                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();

                        System.out.print("Enter Status (Present/Absent/Leave): ");
                        String status = sc.nextLine();

                        if (!status.equalsIgnoreCase("Present")
                                && !status.equalsIgnoreCase("Absent")
                                && !status.equalsIgnoreCase("Leave")) {
                            System.out.println("Invalid status.");
                            break;
                        }

                        boolean added = AttendanceDAO.addAttendance(
                                empId, date, status);

                        System.out.println(added
                                ? "Attendance created successfully!"
                                : "Attendance not created.");

                    } catch (NumberFormatException e) {
                        System.out.println("Employee ID must be a number.");
                    }
                    break;

                case 2:
                    AttendanceDAO.viewAttendance();
                    break;

                case 3:
                    try {
                        System.out.print("Enter Attendance ID to update: ");
                        int attId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter New Date (YYYY-MM-DD): ");
                        String newDate = sc.nextLine();

                        System.out.print("Enter New Status (Present/Absent/Leave): ");
                        String newStatus = sc.nextLine();

                        if (!newStatus.equalsIgnoreCase("Present")
                                && !newStatus.equalsIgnoreCase("Absent")
                                && !newStatus.equalsIgnoreCase("Leave")) {
                            System.out.println("Invalid status.");
                            break;
                        }

                        boolean updated = AttendanceDAO.updateAttendance(
                                attId, newDate, newStatus);

                        System.out.println(updated
                                ? "Attendance updated successfully!"
                                : "Attendance ID not found.");

                    } catch (NumberFormatException e) {
                        System.out.println("Enter valid numeric values.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Attendance ID to delete: ");
                        int attId = Integer.parseInt(sc.nextLine());

                        System.out.print("Confirm delete? (yes/no): ");
                        String confirm = sc.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            boolean deleted =
                                    AttendanceDAO.deleteAttendance(attId);

                            System.out.println(deleted
                                    ? "Attendance deleted successfully!"
                                    : "Attendance ID not found.");
                        } else {
                            System.out.println("Delete cancelled.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid attendance ID.");
                    }
                    break;

                case 5:
                    System.out.println("Returning to Organization Menu...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}