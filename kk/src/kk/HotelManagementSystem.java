package kk;

import java.io.*;
import java.util.Scanner;

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String csvFilePath = "HotelRecords.csv";

        while (true) {
            System.out.println("Hotel Management System Menu:");
            System.out.println("1. Add Entry");
            System.out.println("2. Extract and Display Entries by Name");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1/2/3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addEntry(csvFilePath, scanner);
                    break;
                case 2:
                    extractAndDisplayByName(csvFilePath, scanner);
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            }
        }
    }

    private static void addEntry(String csvFilePath, Scanner scanner) {
        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            System.out.println("Enter details for the new entry:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("ID Card: ");
            String idCard = scanner.nextLine();

            int totalDays = getNumericInput("Total Days of Stay: ", scanner);
            int dateOfStay = getNumericInput("Date of Stay (YYYY-MM-DD): ", scanner);

            System.out.print("Room Type: ");
            String roomType = scanner.nextLine();

            int roomNumber = getNumericInput("Room Number: ", scanner);
            double paymentAmount = getDoubleInput("Payment Amount: ", scanner);

            System.out.print("Payment Method: ");
            String paymentMethod = scanner.nextLine();

            String entry = String.join(",", name, phoneNumber, email, idCard, Integer.toString(totalDays),
                     roomType, Integer.toString(roomNumber), Double.toString(paymentAmount), paymentMethod);

            writer.append(entry).append("\n");
            System.out.println("Entry added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Utility method to get integer input and validate it
    private static int getNumericInput(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.matches("^\\d+$")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Only numbers are allowed. Please enter a valid number.");
            }
        }
    }

    // Utility method to get double input and validate it
    private static double getDoubleInput(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.matches("^[0-9]+(\\.\\d+)?$")) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Only numbers are allowed. Please enter a valid number.");
            }
        }
    }

    private static void extractAndDisplayByName(String csvFilePath, Scanner scanner) {
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean found = false;

            System.out.println("Entries for " + searchName + ":");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(searchName)) {
                    displayEntry(parts);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No entries found for " + searchName + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEntry(String[] parts) {
    	System.out.println("--------------------------");
        System.out.println("Name: " + parts[0]);
        System.out.println("Phone Number: " + parts[1]);
        System.out.println("Email: " + parts[2]);
        System.out.println("ID Card: " + parts[3]);
        System.out.println("Total Days of Stay: " + parts[4]);
        System.out.println("Date of Stay: " + parts[5]);
        System.out.println("Room Type: " + parts[6]);
        System.out.println("Room Number: " + parts[7]);
        System.out.println("Payment Amount: " + parts[8]);
        System.out.println("Payment Method: " + parts[9]);
        System.out.println("--------------------------");
    }
}
