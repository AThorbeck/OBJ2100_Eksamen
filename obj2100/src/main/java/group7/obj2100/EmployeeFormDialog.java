package group7.obj2100;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class EmployeeFormDialog extends JFrame {
    private JTextField employeeNumberField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField extensionField;
    private JTextField emailField;
    private JTextField officeCodeField;
    private JTextField reportsToField;
    private JTextField jobTitleField;

    // Database connection parameters
    private static String url = "jdbc:mysql://localhost:3306/classicmodels";
    private static String username = "root";
    private static String password = "";

    public EmployeeFormDialog(JDialog parent) {
        super("Employee Form");

        // Create the form components
        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        employeeNumberField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        
        JLabel extensionLabel = new JLabel("Extension:");
        extensionField = new JTextField(20);

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
        formPanel.add(extensionLabel, gbc);
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
        formPanel.add(extensionField, gbc);
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

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            // Update the employee data
            updateEmployeeData();
            dispose(); // Close the dialog after updating the record
        });
        buttonPanel.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Close the dialog without saving or modifying
        buttonPanel.add(cancelButton);

        // Create the main panel and add the form panel and button panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton displayAllButton = new JButton("Display All Employees");
        displayAllButton.addActionListener(e -> displayAllEmployees());

        // Add the main panel and the display all button to the dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(displayAllButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }



    public EmployeeFormDialog(JFrame parent) {
    }


    private void saveEmployeeData() {
        // Get the employee data from the text fields
        String employeeNumber = employeeNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String extension = extensionField.getText();
        String email = emailField.getText();
        String officeCode = officeCodeField.getText();
        String reportsTo = reportsToField.getText();
        String jobTitle = jobTitleField.getText();

        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Insert the employee data into the database
            String sql = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) " +
                         "VALUES ('" + employeeNumber + "', '" + lastName + "', '" + firstName + "',  '" + extension + "', '" + email + "', '" +
                         officeCode + "', '" + reportsTo + "', '" + jobTitle + "')";

            statement.executeUpdate(sql);

            // Close the statement and connection
            statement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Employee data saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployeeData() {
        // Get the employee data from the text fields
        String employeeNumber = employeeNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String extension = extensionField.getText();
        String email = emailField.getText();
        String officeCode = officeCodeField.getText();
        String reportsTo = reportsToField.getText();
        String jobTitle = jobTitleField.getText();

        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Update the employee data in the database
            String sql = "UPDATE employees SET lastName = '" + lastName + "', firstName = '" + firstName + "', extension = '" + extension +
                         "', email = '" + email + "', officeCode = '" + officeCode + "', reportsTo = '" + reportsTo +
                         "', jobTitle = '" + jobTitle + "' WHERE employeeNumber = '" + employeeNumber + "'";

            statement.executeUpdate(sql);

            // Close the statement and connection
            statement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Employee data updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayAllEmployees() {
        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to retrieve all employees
            String sql = "SELECT * FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the result set and display the employee data
            StringBuilder output = new StringBuilder();
            while (resultSet.next()) {
                int employeeNumber = resultSet.getInt("employeeNumber");
                String lastName = resultSet.getString("lastName");
                String firstName = resultSet.getString("firstName");
                String extension = resultSet.getString("extension");
                String email = resultSet.getString("email");
                String officeCode = resultSet.getString("officeCode");
                String reportsTo = resultSet.getString("reportsTo");
                String jobTitle = resultSet.getString("jobTitle");

                output.append("Employee Number: ").append(employeeNumber)
                      .append(", Last Name: ").append(lastName)
                      .append(", First Name: ").append(firstName)
                      .append(", Extension: ").append(extension)
                      .append(", Email: ").append(email)
                      .append(", Office Code: ").append(officeCode)
                      .append(", Reports To: ").append(reportsTo)
                      .append(", Job Title: ").append(jobTitle)
                      .append("\n");
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

            // Display the employee data in a dialog
            JOptionPane.showMessageDialog(this, output.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
