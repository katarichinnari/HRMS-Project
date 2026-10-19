package com.hrms;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== HRMS SYSTEM =====");
            System.out.println("1. Create New Organization");
            System.out.println("2. Login to Organization");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                sc.next();
                System.out.print("Enter your choice: ");
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:
                System.out.println("Organization Registration");

                System.out.print("Enter Organization Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Address: ");
                String address = sc.nextLine();

                System.out.print("Enter PAN: ");
                String pan = sc.nextLine();

                System.out.print("Enter GST: ");
                String gst = sc.nextLine();

                System.out.print("Enter Department: ");
                String department = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                System.out.print("Enter Employee Count: ");
                int employeeCount = Integer.parseInt(sc.nextLine());

                boolean saved = OrganizationDAO.createOrganization(
                	    name,
                	    address,
                	    pan,
                	    gst,
                	    department,
                	    email,
                	    password,
                	    employeeCount
                	);

                	if (saved) {
                	    System.out.println("Organization registered successfully!");
                	} else {
                	    System.out.println("Registration failed. Please try again.");
                	}
                break;

            case 2:
                int loginChoice;

                do {
                    System.out.println("\n=== Organization Login Menu ===");
                    System.out.println("1. Login");
                    System.out.println("2. Forgot Password");
                    System.out.println("3. Back to Main Menu");
                    System.out.print("Enter your choice: ");

                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter 1, 2, or 3.");
                        sc.next();
                        System.out.print("Enter your choice: ");
                    }

                    loginChoice = sc.nextInt();
                    sc.nextLine();

                    switch (loginChoice) {

                    case 1:
                        System.out.print("Enter Email: ");
                        String loginEmail = sc.nextLine();

                        System.out.print("Enter Password: ");
                        String loginPassword = sc.nextLine();

                        String orgName = OrganizationDAO.loginOrganization(
                            loginEmail, loginPassword
                        );

                        if (orgName != null) {
                            System.out.println("Welcome to " + orgName);
                            OrganizationMenu.showMenu(orgName, sc);
                            loginChoice = 3;
                        } else {
                            System.out.println("Invalid email or password.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter registered email: ");
                        String resetEmail = sc.nextLine();

                        System.out.print("Enter new password: ");
                        String newPassword = sc.nextLine();

                        boolean updated = OrganizationDAO.resetPassword(
                            resetEmail, newPassword
                        );

                        if (updated) {
                            System.out.println("Password reset successful!");
                        } else {
                            System.out.println("Email not found or reset failed.");
                        }
                        break;

                    case 3:
                        System.out.println("Returning to Main Menu...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                    }

                } while (loginChoice != 3);

                break;

                case 3:
                    System.out.println("Thank you for using HRMS!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}


