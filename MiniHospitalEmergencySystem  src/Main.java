
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static PatientBST patientBST = new PatientBST();
    static EmergencyQueue emergencyQueue = new EmergencyQueue();
    static TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {

        int choice;

        do {
            displayMenu();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    deletePatient();
                    break;

                case 4:
                    patientBST.displayInOrder();
                    break;

                case 5:
                    addEmergencyPatient();
                    break;

                case 6:
                    emergencyQueue.dequeue();
                    break;

                case 7:
                    emergencyQueue.displayQueue();
                    break;

                case 8:
                    completeTreatment();
                    break;

                case 9:
                    treatmentStack.pop();
                    break;

                case 10:
                    treatmentStack.displayStack();
                    break;

                case 11:
                    addVisit();
                    break;

                case 12:
                    removeVisit();
                    break;

                case 13:
                    searchVisit();
                    break;

                case 14:
                    displayVisits();
                    break;

                case 0:
                    System.out.println("System closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }

    static void displayMenu() {

        System.out.println();
        System.out.println("======================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT");
        System.out.println("======================================");

        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display Patients");

        System.out.println();
        System.out.println("--- Emergency Queue ---");
        System.out.println("5. Add Patient to Emergency Queue");
        System.out.println("6. Treat Next Patient");
        System.out.println("7. Display Waiting Patients");

        System.out.println();
        System.out.println("--- Treatment Stack ---");
        System.out.println("8. Complete Treatment");
        System.out.println("9. Remove Latest Treatment");
        System.out.println("10. Display Treatment History");

        System.out.println();
        System.out.println("--- Patient Visit History ---");
        System.out.println("11. Add Patient Visit");
        System.out.println("12. Remove Patient Visit");
        System.out.println("13. Search Patient Visit");
        System.out.println("14. Display Patient Visits");

        System.out.println();
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    static void registerPatient() {

        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (patientBST.search(id) != null) {
            System.out.println("Patient ID already exists.");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(
                id, name, age, contact, condition
        );

        patientBST.insert(patient);

        System.out.println("Patient registered successfully.");
    }

   
static void searchPatient() {

    if (patientBST.isEmpty()) {
        System.out.println("Warning: No patients available to search.");
        return;
    }

    System.out.print("Enter Patient ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Patient patient = patientBST.search(id);

    if (patient != null) {
        System.out.println("Patient found:");
        System.out.println(patient);
    } else {
        System.out.println("Patient not found.");
    }
}

    static void deletePatient() {

        System.out.print("Enter Patient ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            patientBST.delete(id);
            System.out.println("Patient deleted successfully.");
        }
    }

    static void addEmergencyPatient() {

        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not registered.");
            return;
        }

        emergencyQueue.enqueue(patient);
    }

    static void completeTreatment() {

        Patient patient = emergencyQueue.dequeue();

        if (patient == null) {
            return;
        }

        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();

        System.out.print("Enter Treatment Provided: ");
        String treatment = scanner.nextLine();

        String record =
                "Patient ID: " + patient.getPatientId()
                + " | Patient: " + patient.getPatientName()
                + " | Doctor: " + doctor
                + " | Treatment: " + treatment;

        treatmentStack.push(record);

        System.out.println("Treatment completed.");
    }

    static void addVisit() {

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Visit ID: ");
        int visitId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Visit Date: ");
        String date = scanner.nextLine();

        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();

        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        Visit visit = new Visit(
                visitId,
                date,
                doctor,
                diagnosis,
                treatment
        );

        patient.getVisitHistory().addVisit(visit);

        System.out.println("Visit added successfully.");
    }

    static void removeVisit() {

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Visit ID to remove: ");
        int visitId = scanner.nextInt();
        scanner.nextLine();

        patient.getVisitHistory().removeVisit(visitId);
    }

    static void searchVisit() {

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Visit ID to search: ");
        int visitId = scanner.nextInt();
        scanner.nextLine();

        patient.getVisitHistory().searchVisit(visitId);
    }

    static void displayVisits() {

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println();
        System.out.println("Visit history for " + patient.getPatientName());

        patient.getVisitHistory().displayHistory();
    }
}

