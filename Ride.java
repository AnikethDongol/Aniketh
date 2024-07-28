import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ride implements RideInterface {
    private String rideName;
    private Employee operator;
    private Queue<Visitor> visitorQueue;
    private LinkedList<Visitor> rideHistory;
    private int maxRiders;
    private int numOfCycles;
    private Lock lock;

    public Ride(String rideName, Employee operator) {
        this.rideName = rideName;
        this.operator = operator;
        this.visitorQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRiders = 5; // example value
        this.numOfCycles = 0;
        this.lock = new ReentrantLock();
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        lock.lock();
        try {
            visitorQueue.add(visitor);
            System.out.println("Visitor added to queue: " + visitor.getName());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void removeVisitorFromQueue(Visitor visitor) {
        lock.lock();
        try {
            if (visitorQueue.remove(visitor)) {
                System.out.println("Visitor removed from queue: " + visitor.getName());
            } else {
                System.out.println("Visitor not found in queue: " + visitor.getName());
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void printQueue() {
        lock.lock();
        try {
            System.out.println("Current Queue:");
            for (Visitor visitor : visitorQueue) {
                System.out.println(visitor);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void runOneCycle() {
        lock.lock();
        try {
            if (operator == null) {
                System.out.println("No operator assigned. Cannot run the ride.");
                return;
            }
            if (visitorQueue.isEmpty()) {
                System.out.println("No visitors in the queue. Cannot run the ride.");
                return;
            }
            int riders = Math.min(maxRiders, visitorQueue.size());
            for (int i = 0; i < riders; i++) {
                Visitor visitor = visitorQueue.poll();
                if (visitor != null) {
                    rideHistory.add(visitor);
                    System.out.println("Visitor took the ride: " + visitor.getName());
                }
            }
            numOfCycles++;
            System.out.println("Ride cycle completed. Total cycles: " + numOfCycles);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void printRideHistory() {
        lock.lock();
        try {
            System.out.println("Ride History:");
            Iterator<Visitor> iterator = rideHistory.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } finally {
            lock.unlock();
        }
    }

    public void addVisitorToHistory(Visitor visitor) {
        lock.lock();
        try {
            rideHistory.add(visitor);
            System.out.println("Visitor added to history: " + visitor.getName());
        } finally {
            lock.unlock();
        }
    }

    public boolean isVisitorInHistory(Visitor visitor) {
        lock.lock();
        try {
            return rideHistory.contains(visitor);
        } finally {
            lock.unlock();
        }
    }

    public int getRideHistoryCount() {
        lock.lock();
        try {
            return rideHistory.size();
        } finally {
            lock.unlock();
        }
    }

    public void writeHistoryToFile(String filename) {
        lock.lock();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Visitor visitor : rideHistory) {
                writer.write(visitor.toCsv());
                writer.newLine();
            }
            System.out.println("Ride history saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void readHistoryFromFile(String filename) {
        lock.lock();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Visitor visitor = Visitor.fromCsv(line);
                rideHistory.add(visitor);
            }
            System.out.println("Ride history loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public Queue<Visitor> getVisitorQueue() {
        return visitorQueue;
    }

    public List<Visitor> getRideHistory() {
        return rideHistory;
    }
}
