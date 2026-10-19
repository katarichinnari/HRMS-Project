package com.hrms;

import java.util.Scanner;

public class OrganizationMenu {

    public static void showMenu(String orgName, Scanner sc) {

        int choice;

        do {
            System.out.println("\n===== " + orgName + " =====");
            System.out.println("1. Employee Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Salary Management");
            System.out.println("4. Notice / Holiday Management");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
            case 1:
                EmployeeMenu.showMenu(sc);
                break;
            case 2:
                AttendanceMenu.showMenu(sc);
                break;

            case 3:
                SalaryMenu.showMenu(sc);
                break;

            case 4:
                NoticeMenu.showMenu(sc);
                break;

                case 5:
                    System.out.println("Logged out successfully!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }
}