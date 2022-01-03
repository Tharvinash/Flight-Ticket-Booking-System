package Assignment;

import java.io.*;

public class Ticket {

    private String customerName = null;
    private String identity = null;
    private String emailAddress = null;
    private String seatNo;

    Ticket(String name, String id, String email) {
        this(name, id, email, null);
    }

    Ticket(String name, String id, String email, String seat) {
        this.customerName = name;
        this.identity = id;
        this.emailAddress = email;
        this.seatNo = seat;
    }
    
    public boolean isNull() {
        return (this.customerName == null && this.identity == null && this.emailAddress == null);
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSeatNo() {
        return seatNo;
    }
    
    public String getSeatNo(int n) {
        int first = (n / 6) + 1;
        String firstChar = null;

        switch (first) {
            case 1:
                firstChar = "1";
                break;

            case 2:
                firstChar = "2";
                break;

            case 3:
                firstChar = "3";
                break;

            case 4:
                firstChar = "4";
                break;

            case 5:
                firstChar = "5";
                break;            
        }

        int second = n % 6;
        String secondChar = null;

        switch (second) {
            case 0:
                secondChar = "A";
                break;

            case 1:
                secondChar = "B";
                break;

            case 2:
                secondChar = "C";
                break;

            case 3:
                secondChar = "D";
                break;

            case 4:
                secondChar = "E";
                break;

            case 5:
                secondChar = "F";
                break;
        }

        String seat = firstChar + secondChar;
        
        return seat;
    }
    
    public int getSeatNo(String string) {
        char first = string.charAt(0);
        int firstNum = 0;

        switch (first) {
            case '1':
                firstNum = 0;
                break;

            case '2':
                firstNum = 6;
                break;

            case '3':
                firstNum = 12;
                break;

            case '4':
                firstNum = 18;
                break;

            case '5':
                firstNum = 24;
                break;

            
        }

        char second = string.charAt(1);
        int secondNum = 0;

        switch (second) {
            case 'A':
                secondNum = 0;
                break;

            case 'B':
                secondNum = 1;
                break;

            case 'C':
                secondNum = 2;
                break;

            case 'D':
                secondNum = 3;
                break;

            case 'E':
                secondNum = 4;
                break;

            case 'F':
                secondNum = 5;
                break;
        }

        int seat = firstNum + secondNum;
        
        return seat;
    }
    
    

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void display() {
        String out = "Name: " + this.getCustomerName() + "\n";
        out += "Passport number / Identity card number: " + this.getIdentity() + "\n";
        out += "Email address: " + this.getEmailAddress() + "\n";
        out += "Seat: " + this.getSeatNo();
        System.out.println(out);
    }
}
