package Assignment;

import java.io.*;
import java.util.Scanner;

public class LinkedList<T> {

    Flight head;

    LinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Flight newFlight) {
        if (this.isEmpty()) {
            newFlight.next = head;
            head = newFlight;
        } else {
            Flight previous = head;
            Flight current = head;

            while (current != null) {
                previous = current;
                current = current.next;
            }

            Flight temp = current;
            previous.next = newFlight;
            current = newFlight;
            current.next = temp;
        }
    }

    public Flight search(String string) {
        Flight current = head;

        while (current != null) {
            if (string.toUpperCase().compareTo(current.getFlightNo()) == 0 || 
                    string.toUpperCase().compareTo(current.getDateOfFlight()) == 0) {
                return current;
            } else {
                current = current.next;
            }
        }

        System.out.println("Flight not found.");
        return null;
    }

    public void display() {
        if (!this.isEmpty()) {
            Flight current = head;

            while (current != null) {
                current.display();
                current = current.next;
            }
        }
    }

    public void save() {
        try {
            File file01 = new File("flight.txt");
            PrintWriter output01 = new PrintWriter(new FileOutputStream(file01.getName()), true);

            if (!this.isEmpty()) {
                Flight current = head;

                while (current != null) {
                    output01.write(current.getFlightNo() + "," + current.getDateOfFlight() + "\n");
                    current = current.next;
                }

            }

            output01.close();

            File file02 = new File("ticket.txt");

            PrintWriter output02 = new PrintWriter(new FileOutputStream(file02.getName()), true);

            if (!this.isEmpty()) {
                Flight current = head;

                while (current != null) {

                    if (!current.isEmpty()) {
                        for (int i = 0; i < current.getConfirmedList().length; i++) {
                            if (current.getConfirmedList()[i] != null) {
                                Ticket t = current.getConfirmedList()[i];
                                output02.write(current.getFlightNo() + "," + t.getCustomerName() + "," + t.getIdentity() 
                                        + "," + t.getEmailAddress() + "," + t.getSeatNo() + "\n");
                            }
                        }
                    }

                    if (!current.getWaitingList().isEmpty()) {
                        for (Ticket t : current.getWaitingList().getArray()) {
                            output02.write(current.getFlightNo() + "," + t.getCustomerName() + "," + t.getIdentity() 
                                    + "," + t.getEmailAddress() + "\n");
                        }
                    }

                    current = current.next;
                }
            }

            output02.close();

        } catch (FileNotFoundException e) {

        }
    }

}
