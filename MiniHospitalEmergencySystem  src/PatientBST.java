public class PatientBST {

    private class Node {
        Patient patient;
        Node left;
        Node right;

        Node(Patient patient) {
            this.patient = patient;
            left = null;
            right = null;
        }
    }

    private Node root;
    
public boolean isEmpty() {
    return root == null;
}


    // Insert patient
    public void insert(Patient patient) {
        root = insertPatient(root, patient);
    }

    private Node insertPatient(Node root, Patient patient) {

        if (root == null) {
            return new Node(patient);
        }

        if (patient.getPatientId() < root.patient.getPatientId()) {
            root.left = insertPatient(root.left, patient);
        }
        else if (patient.getPatientId() > root.patient.getPatientId()) {
            root.right = insertPatient(root.right, patient);
        }
        else {
            System.out.println("Patient ID already exists.");
        }

        return root;
    }

    // Search patient
    public Patient search(int id) {

        Node current = root;

        while (current != null) {

            if (id == current.patient.getPatientId()) {
                return current.patient;
            }

            if (id < current.patient.getPatientId()) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }

        return null;
    }

    // Delete patient
    public void delete(int id) {
        root = deletePatient(root, id);
    }

    private Node deletePatient(Node root, int id) {

        if (root == null) {
            return null;
        }

        if (id < root.patient.getPatientId()) {
            root.left = deletePatient(root.left, id);
        }
        else if (id > root.patient.getPatientId()) {
            root.right = deletePatient(root.right, id);
        }
        else {

            // No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Only right child
            if (root.left == null) {
                return root.right;
            }

            // Only left child
            if (root.right == null) {
                return root.left;
            }

            // Two children
            Node temp = root.right;

            while (temp.left != null) {
                temp = temp.left;
            }

            root.patient = temp.patient;
            root.right = deletePatient(
                    root.right,
                    temp.patient.getPatientId()
            );
        }

        return root;
    }

    // Display patients in ascending order
    public void displayInOrder() {

        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }

        inOrder(root);
    }

    private void inOrder(Node root) {

        if (root != null) {
            inOrder(root.left);
            System.out.println(root.patient);
            inOrder(root.right);
        }
    }
}