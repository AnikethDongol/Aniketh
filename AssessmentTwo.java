import java.util.Scanner;

public class AssessmentTwo {
    private Ride ride;
    private Scanner scanner;

    public static void main(String[] args) {
        AssessmentTwo assessment = new AssessmentTwo();
        assessment.run();
    }

    public AssessmentTwo() {
        ride = new Ride("Roller Coaster", new Employee("John", 30, "Male", "E001", "Operator"));
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    addVisitorToQueue();
                    break;
                case 2:
                    removeVisitorFromQueue();
                    break;
                case 3:
                    printQueue();
                    break;
                case 4:
                    runOneCycle();
                    break;
                case 5:
                    printRideHistory();
                    break;
                case 6:
                    addVisitorToHistory();
                    break;
                case 7:
                    checkVisitorInHistory();
                    break;
                case 8:
                    countRideHistory();
                    break;
                case 9:
                    saveRideHistoryToFile();
                    break;
                case 10:
                    loadRideHistoryFromFile();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }

    public void displayMenu() {
        System.out.println("\nChoose an operation:");
        System.out.println("1. Add visitor to queue");
        System.out.println("2. Remove visitor from queue");
        System.out.println("3. Print queue");
        System.out.println("4. Run one cycle");
        System.out.println("5. Print ride history");
        System.out.println("6. Add visitor to history");
        System.out.println("7. Check if visitor is in history");
        System.out.println("8. Count visitors in history");
        System.out.println("9. Save ride history to file");
        System.out.println("10. Load ride history from file");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addVisitorToQueue() {
        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter visitor age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter visitor gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter visitor ID: ");
        String visitorId = scanner.nextLine();
        System.out.print("Does the visitor have a ticket (true/false): ");
        boolean hasTicket = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline character

        Visitor visitor = new Visitor(name, age, gender, visitorId, hasTicket);
        ride.addVisitorToQueue(visitor);
    }

    public void removeVisitorFromQueue() {
        System.out.print("Enter visitor ID to remove: ");
        String visitorId = scanner.nextLine();
        Visitor visitor = findVisitorById(visitorId);
        if (visitor != null) {
            ride.removeVisitorFromQueue(visitor);
        } else {
            System.out.println("Visitor not found.");
        }
    }

    public void printQueue() {
        ride.printQueue();
    }

    public void runOneCycle() {
        ride.runOneCycle();
    }

    public void printRideHistory() {
        ride.printRideHistory();
    }

    public void addVisitorToHistory() {
        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter visitor age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter visitor gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter visitor ID: ");
        String visitorId = scanner.nextLine();
        System.out.print("Does the visitor have a ticket (true/false): ");
        boolean hasTicket = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline character

        Visitor visitor = new Visitor(name, age, gender, visitorId, hasTicket);
        ride.addVisitorToHistory(visitor);
    }

    public void checkVisitorInHistory() {
        System.out.print("Enter visitor ID to check: ");
        String visitorId = scanner.nextLine();
        Visitor visitor = findVisitorById(visitorId);
        if (visitor != null) {
            System.out.println("Is visitor in history: " + ride.isVisitorInHistory(visitor));
        } else {
            System.out.println("Visitor not found.");
        }
    }

    public void countRideHistory() {
        System.out.println("Number of visitors in history: " + ride.getRideHistoryCount());
    }

    public void saveRideHistoryToFile() {
        ride.writeHistoryToFile("history.csv");
    }

    public void loadRideHistoryFromFile() {
        ride.readHistoryFromFile("history.csv");
    }

    private Visitor findVisitorById(String visitorId) {
        for (Visitor visitor : ride.getVisitorQueue()) {
            if (visitor.getVisitorId().equals(visitorId)) {
                return visitor;
            }
        }
        for (Visitor visitor : ride.getRideHistory()) {
            if (visitor.getVisitorId().equals(visitorId)) {
                return visitor;
            }
        }
        return null;
    }
}
