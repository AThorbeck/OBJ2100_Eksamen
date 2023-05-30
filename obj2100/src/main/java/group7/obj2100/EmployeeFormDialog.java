import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@author Andreas

public class EmployeeFormDialog extends JDialog {
	private JTextField employeeNumberField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField officeCodeField;
    private JTextField reportsToField;
    private JTextField jobTitleField;
    
    
    public EmployeeFormDialog(JFrame parent) {
        super(parent, "Employee Form", true);
        
        // Create the form components
        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        employeeNumberField = new JTextField(20);
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel officeCodeLabel = new JLabel("Office Code:");
        officeCodeField = new JTextField(20);

        JLabel reportsToLabel = new JLabel("Reports To:");
        reportsToField = new JTextField(20);

        JLabel jobTitleLabel = new JLabel("Job Title:");
        jobTitleField = new JTextField(20);

        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Save the employee data or perform the modification
            saveEmployeeData();
            dispose(); // Close the dialog after saving or modifying the record
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Close the dialog without saving or modifying
        
        // Create a panel and add the form components
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(employeeNumberLabel);
        panel.add(employeeNumberField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(officeCodeLabel);
        panel.add(officeCodeField);
        panel.add(reportsToLabel);
        panel.add(reportsToField);
        panel.add(jobTitleLabel);
        panel.add(jobTitleField);
        panel.add(saveButton);
        panel.add(cancelButton);
        
        // Add the panel to the dialog
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent); // Center the dialog on the parent frame
    }
 
    private void saveEmployeeData() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String employeeNumber = employeeNumberField.getText();
        String email = emailField.getText();
        String officeCode = officeCodeField.getText();
        String reportsTo = reportsToField.getText();
        String jobTitle = jobTitleField.getText();
        
        
        System.out.println("Employee Data: " + firstName + " " + lastName + " (" + jobTitle + ")");
    }
    
	 // Database connection parameters
	    String url = "jdbc:mysql://localhost:3306/classicmodels";
	    String username = "student"; 
	    String password = "student";
        {
	    
	    // Establish the database connection
	    try {
	        Connection connection = DriverManager.getConnection(url, username, password);
	        // Perform database operations here (e.g., saving or modifying the employee data)
	        // ...
	
	        // Close the database connection
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton openDialogButton = new JButton("Add/Modify Employee");
        openDialogButton.addActionListener(e -> {
            EmployeeFormDialog dialog = new EmployeeFormDialog(frame);
            dialog.setVisible(true);
        });
        
        frame.getContentPane().add(openDialogButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
