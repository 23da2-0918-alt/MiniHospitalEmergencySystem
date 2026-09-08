import java.util.Stack;

public class TreatmentStack {

    private Stack<String> treatmentStack = new Stack<>();

    // Add treatment record
    public void push(String treatmentRecord) {
        treatmentStack.push(treatmentRecord);
        System.out.println("Treatment record added successfully.");
    }

    // Remove latest treatment record
    public void pop() {

        if (treatmentStack.isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }

        String record = treatmentStack.pop();

        System.out.println("Removed treatment record: " + record);
    }

    // Display treatment history
    public void displayStack() {

        if (treatmentStack.isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }

        System.out.println("\nTreatment History:");

        for (int i = treatmentStack.size() - 1; i >= 0; i--) {
            System.out.println(treatmentStack.get(i));
        }
    }
}