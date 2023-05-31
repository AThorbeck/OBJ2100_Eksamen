import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

//@author Andreas

public class EmployeeFormDialog extends JDialog {
    private JTextField employeeNumberField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField officeCodeField;
    private JTextField reportsToField;
    private JTextField jobTitleField;

    // Database connection parameters
    private static String url = "jdbc:mysql://localhost:3306/classicmodels";
    private static String username = "student";
    private static String password = "student";

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

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        formPanel.add(firstNameLabel, gbc);
        gbc.gridy++;
        formPanel.add(lastNameLabel, gbc);
        gbc.gridy++;
        formPanel.add(employeeNumberLabel, gbc);
        gbc.gridy++;
        formPanel.add(emailLabel, gbc);
        gbc.gridy++;
        formPanel.add(officeCodeLabel, gbc);
        gbc.gridy++;
        formPanel.add(reportsToLabel, gbc);
        gbc.gridy++;
        formPanel.add(jobTitleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        formPanel.add(firstNameField, gbc);
        gbc.gridy++;
        formPanel.add(lastNameField, gbc);
        gbc.gridy++;
        formPanel.add(employeeNumberField, gbc);
        gbc.gridy++;
        formPanel.add(emailField, gbc);
        gbc.gridy++;
        formPanel.add(officeCodeField, gbc);
        gbc.gridy++;
        formPanel.add(reportsToField, gbc);
        gbc.gridy++;
        formPanel.add(jobTitleField, gbc);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Save the employee data or perform the modification
            saveEmployeeData();
            dispose(); // Close the dialog after saving or modifying the record
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Close the dialog without saving or modifying
        buttonPanel.add(cancelButton);

        // Create the main panel and add the form panel and button panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton displayAllButton = new JButton("Display All Employees");
        displayAllButton.addActionListener(e -> {
            JDialog displayDialog = new JDialog(this, "All Employees", true);

            JTextArea employeeTextArea = new JTextArea(10, 30);
            employeeTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(employeeTextArea);

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

                StringBuilder employeesData = new StringBuilder();
                while (resultSet.next()) {
                    String empFirstName = resultSet.getString("firstName");
                    String empLastName = resultSet.getString("lastName");
                    String empJobTitle = resultSet.getString("jobTitle");
                    employeesData.append("Employee Data: ").append(empFirstName).append(" ").append(empLastName)
                            .append(" (").append(empJobTitle).append(")\n");
                }
                employeeTextArea.setText(employeesData.toString());

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            displayDialog.getContentPane().add(scrollPane);
            displayDialog.pack();
            displayDialog.setLocationRelativeTo(this);
            displayDialog.setVisible(true);
        });

        buttonPanel.add(displayAllButton);

        // Add the main panel to the dialog
        getContentPane().add(mainPanel);

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
<<<<<<< HEAD
    
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
    
=======

>>>>>>> e1f7682093e9c6e1d26587eba40de385557dae47
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openDialogButton = new JButton("Add/Modify Employee");
        openDialogButton.addActionListener(e -> {
            EmployeeFormDialog dialog = new EmployeeFormDialog(frame);
            dialog.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openDialogButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
