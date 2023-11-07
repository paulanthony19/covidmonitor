import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class CovidMonitor extends JFrame {
    private static Queue<Patient> queue = new LinkedList<>();
    private JTextArea textArea;
    private JButton addButton;
    private JButton processButton;

    public CovidMonitor() {
        // GUI setup
        setTitle("COVID-19 Monitor");
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("COVID-19 Patient Monitoring");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        add(titleLabel, BorderLayout.NORTH);

        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the text area for displaying the patients
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create the buttons for adding and processing patients
        addButton = new JButton("Add Patient");
        addButton.addActionListener(new AddButtonListener());

        processButton = new JButton("Process Patient");
        processButton.addActionListener(new ProcessButtonListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(processButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the GUI
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add a patient to the queue
            String firstName = JOptionPane.showInputDialog(CovidMonitor.this, "Enter the patient's first name:");
            String lastName = JOptionPane.showInputDialog(CovidMonitor.this, "Enter the patient's last name:");
            String symptoms = JOptionPane.showInputDialog(CovidMonitor.this, "Enter the patient's symptoms:");

            queue.add(new Patient(firstName, lastName, symptoms));
            textArea.append("Added patient: " + firstName + " " + lastName + " (" + symptoms + ")\n");
        }
    }

    private class ProcessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Processing the next patient in the queue
            if (!queue.isEmpty()) {
                Patient patient = queue.poll();
                textArea.append("Processing patient: " + patient.getFirstName() + " " +
                        patient.getLastName() + " (" + patient.getSymptoms() + ")\n");
            } else {
                textArea.append("No patients to process\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the GUI
            new CovidMonitor();
        });
    }
}

class Patient {
    private String firstName;
    private String lastName;
    private String symptoms;

    public Patient(String firstName, String lastName, String symptoms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.symptoms = symptoms;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSymptoms() {
        return symptoms;
    }
}