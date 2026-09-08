public class VisitHistory {

    private class Node {
        Visit visit;
        Node next;

        Node(Visit visit) {
            this.visit = visit;
            this.next = null;
        }
    }

    private Node head;

    // Add a new visit
    public void addVisit(Visit visit) {

        Node newNode = new Node(visit);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Remove a visit
    public void removeVisit(int visitId) {

        if (head == null) {
            System.out.println("Visit history is empty.");
            return;
        }

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            System.out.println("Visit removed successfully.");
            return;
        }

        Node current = head;

        while (current.next != null &&
               current.next.visit.getVisitId() != visitId) {

            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Visit not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Visit removed successfully.");
        }
    }

    // Search for a visit
    public void searchVisit(int visitId) {

        Node current = head;

        while (current != null) {

            if (current.visit.getVisitId() == visitId) {
                System.out.println("Visit found:");
                System.out.println(current.visit);
                return;
            }

            current = current.next;
        }

        System.out.println("Visit not found.");
    }

    // Display all visits
    public void displayHistory() {

        if (head == null) {
            System.out.println("No previous visits.");
            return;
        }

        Node current = head;

        System.out.println("\nPatient Visit History:");

        while (current != null) {
            System.out.println(current.visit);
            current = current.next;
        }
    }
}