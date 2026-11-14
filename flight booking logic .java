import java.util.*;

// Exception for invalid user
class InvalidUserException extends Exception {
    InvalidUserException(String msg) {
        super(msg);
    }
}

// Exception for seat issue
class NoSeatsException extends Exception {
    NoSeatsException(String msg) {
        super(msg);
    }
}

// Flight class
class Flight {

    private String flightName = "Indigo";
    private int seats = 10;

    // login
    public void login(String name) throws InvalidUserException {
        if (!name.equals("user")) {
            throw new InvalidUserException("Invalid Username!");
        }
        System.out.println("Login successful. Welcome " + name);
    }

    // show seats
    public void showSeats() {
        System.out.println("Available seats: " + seats);
    }

    // book ticket
    public void bookTicket(int n) throws NoSeatsException {
        if (n <= 0) {
            System.out.println("Enter valid number of seats");
            return;
        }
        if (n > seats) {
            throw new NoSeatsException("Not enough seats available!");
        }

        seats -= n;
        System.out.println("Booking successful!");
        System.out.println("Seats left: " + seats);
    }

    // cancel ticket
    public void cancelTicket(int n) {
        if (n <= 0) {
            System.out.println("Enter valid number");
            return;
        }

        seats += n;
        System.out.println("Cancellation successful!");
        System.out.println("Seats now: " + seats);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Flight f = new Flight();

        try {
            // login once
            System.out.println("Enter Username:");
            String name = sc.next();
            f.login(name);

            int choice;

            // loop system (important fix)
            do {
                System.out.println("\n===== FLIGHT MENU =====");
                System.out.println("1. Check Seats");
                System.out.println("2. Book Ticket");
                System.out.println("3. Cancel Ticket");
                System.out.println("4. Exit");
                System.out.println("Enter choice:");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        f.showSeats();
                        break;

                    case 2:
                        System.out.println("Enter seats to book:");
                        int b = sc.nextInt();
                        try {
                            f.bookTicket(b);
                        } catch (NoSeatsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("Enter seats to cancel:");
                        int c = sc.nextInt();
                        f.cancelTicket(c);
                        break;

                    case 4:
                        System.out.println("Thank you for using system ✈️");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 4);

        } catch (InvalidUserException e) {
            System.out.println(e.getMessage());
        }
    }
}