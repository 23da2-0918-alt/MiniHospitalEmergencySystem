import java.util.LinkedList;

public class EmergencyQueue {

    private LinkedList<Patient> queue = new LinkedList<>();

    // Add patient to queue
    public void enqueue(Patient patient) {
        queue.addLast(patient);
        System.out.println("Patient added to emergency queue.");
    }

    // Remove patient from queue
    public Patient dequeue() {

        if (queue.isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return null;
        }

        Patient patient = queue.removeFirst();

        System.out.println("Next patient: " + patient.getPatientName());

        return patient;
    }

    // Display waiting patients
    public void displayQueue() {

        if (queue.isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        System.out.println("\nWaiting Patients:");

        for (Patient patient : queue) {
            System.out.println(patient);
        }
    }

    // Check whether queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}