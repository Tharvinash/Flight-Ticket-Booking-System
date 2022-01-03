package Assignment;

import java.io.*;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        run();

    }

    public static void run() {

        Scanner scanner = new Scanner(System.in);
        int i = 0;

        LinkedList flightList = load();
        do {
            flightList.display();
            System.out.print("Enter Flight No / Date Of Flight preferred: ");
            String choice = scanner.next();

            while (flightList.search(choice) == null) {
                System.out.println("Invalid flight.");
                System.out.print("Enter Flight No / Date Of Flight preferred: ");
                choice = scanner.next();
            }

            run(flightList.search(choice));
            System.out.print("Would you like to continue? (0 - No, 1 - Yes) : ");
            i = scanner.nextInt();

        } while (i == 1);

        flightList.save();
    }

    public static void run(Flight flight) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Book ticket \n2 - Edit personal information \n3 - View ticket status \n4 - Cancel ticket");
        System.out.print("Select a number from the list above for the operation you would like to continue with(-1 to quit) :");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("Booking ticket for " + flight.getFlightNo() + " " + flight.getDateOfFlight());
                System.out.print("Enter passenger name: ");
                String name = scanner.nextLine();

                System.out.print("Enter passenger passport no / identity card no:");
                String identity = scanner.nextLine();

                System.out.print("Enter passenger email address: ");
                String email = scanner.nextLine();

                flight.book(new Ticket(name, identity, email));

                break;

            case 2:
                flight.editInformation(askInformation());
                break;

            case 3:
                flight.viewStatus(askInformation());
                break;

            case 4:
                flight.cancel(askInformation());
                break;

            default:
                System.out.println("Invalid number.");
                break;
        }
    }

    public static String askInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter either passenger name or passport no / identity card no or email address: ");
        String string = scanner.nextLine();

        return string;
    }

    public static LinkedList load() {
        LinkedList flightList = new LinkedList();

        try {
            File flight = new File("flight.txt");

            if (flight.exists()) {
                Scanner input01 = new Scanner(new FileInputStream(flight.getName()));

                while (input01.hasNextLine()) {
                    String[] flightDetail = input01.nextLine().split(",");
                    flightList.add(new Flight(flightDetail[0], flightDetail[1]));
                }

                input01.close();
            }

            File ticket = new File("ticket.txt");

            if (ticket.exists()) {
                Scanner input02 = new Scanner(new FileInputStream(ticket.getName()));

                while (input02.hasNext()) {
                    String[] ticketDetail = input02.nextLine().split(",");

                    if (ticketDetail.length == 5) {
                        Ticket t = new Ticket(ticketDetail[1], ticketDetail[2], ticketDetail[3], ticketDetail[4]);
                        flightList.search(ticketDetail[0]).add(t, t.getSeatNo(ticketDetail[4]));
                    }

                    if (ticketDetail.length == 4) {
                        Ticket t = new Ticket(ticketDetail[1], ticketDetail[2], ticketDetail[3]);
                        flightList.search(ticketDetail[0]).add(t);
                    }
                }

                input02.close();
            }

        } catch (FileNotFoundException e) {

        }

        return flightList;
    }
}
