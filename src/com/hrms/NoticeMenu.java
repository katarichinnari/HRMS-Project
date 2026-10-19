package com.hrms;

import java.util.Scanner;

public class NoticeMenu {

    public static void showMenu(Scanner sc) {

        int choice = 0;

        do {
            System.out.println("\n===== NOTICE / HOLIDAY MANAGEMENT =====");
            System.out.println("1. Create Notice / Holiday");
            System.out.println("2. View Notices / Holidays");
            System.out.println("3. Update Notice / Holiday");
            System.out.println("4. Delete Notice / Holiday");
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
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter Date (YYYY-MM-DD, or blank): ");
                    String date = sc.nextLine();

                    System.out.print("Enter Type (Notice/Holiday): ");
                    String type = sc.nextLine();

                    if (title.trim().isEmpty()
                            || (!type.equalsIgnoreCase("Notice")
                            && !type.equalsIgnoreCase("Holiday"))) {
                        System.out.println("Enter a valid title and type.");
                        break;
                    }

                    boolean created = NoticeDAO.createNotice(
                            title, description, date, type);

                    System.out.println(created
                            ? "Notice / Holiday created successfully!"
                            : "Notice / Holiday not created.");
                    break;

                case 2:
                    NoticeDAO.viewNotices();
                    break;

                case 3:
                    try {
                        System.out.print("Enter Notice ID to update: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter New Title: ");
                        String newTitle = sc.nextLine();

                        System.out.print("Enter New Description: ");
                        String newDescription = sc.nextLine();

                        System.out.print("Enter New Date (YYYY-MM-DD, or blank): ");
                        String newDate = sc.nextLine();

                        System.out.print("Enter Type (Notice/Holiday): ");
                        String newType = sc.nextLine();

                        if (newTitle.trim().isEmpty()
                                || (!newType.equalsIgnoreCase("Notice")
                                && !newType.equalsIgnoreCase("Holiday"))) {
                            System.out.println("Enter a valid title and type.");
                            break;
                        }

                        boolean updated = NoticeDAO.updateNotice(
                                id, newTitle, newDescription, newDate, newType);

                        System.out.println(updated
                                ? "Notice / Holiday updated successfully!"
                                : "Notice ID not found.");

                    } catch (NumberFormatException e) {
                        System.out.println("Notice ID must be a number.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Notice ID to delete: ");
                        int deleteId = Integer.parseInt(sc.nextLine());

                        System.out.print("Confirm delete? (yes/no): ");
                        String confirm = sc.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            boolean deleted = NoticeDAO.deleteNotice(deleteId);

                            System.out.println(deleted
                                    ? "Notice / Holiday deleted successfully!"
                                    : "Notice ID not found.");
                        } else {
                            System.out.println("Delete cancelled.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Notice ID must be a number.");
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