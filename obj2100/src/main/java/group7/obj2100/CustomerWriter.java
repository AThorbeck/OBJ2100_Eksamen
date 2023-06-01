import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerWriter {

    // JDBC connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/classicmodels";
    private static final String DB_USERNAME = "student";
    private static final String DB_PASSWORD = "student";

    private static final String[] CRITERIA_OPTIONS = {"State", "City"};

    private JFrame mainFrame;
    private JComboBox<String> criteriaComboBox;
    private JTextField inputField;
    private JButton searchButton;

    public static void main(String[] args) {
        CustomerWriter customerWriter = new CustomerWriter();
        customerWriter.createUI();
    }

    public void createUI() {
        mainFrame = new JFrame("Customer Search");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 150);
        mainFrame.setLayout(new FlowLayout());

        criteriaComboBox = new JComboBox<>(CRITERIA_OPTIONS);
        mainFrame.add(criteriaComboBox);

        inputField = new JTextField(15);
        mainFrame.add(inputField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        mainFrame.add(searchButton);

        mainFrame.setVisible(true);
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String criteria = criteriaComboBox.getSelectedItem().toString();
            String input = inputField.getText();

            List<Customer> selectedCustomers = searchCustomers(criteria, input);

            if (selectedCustomers.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "No customers found for the selected criteria.");
                return;
            }

            // Save the selected customers to a file
            String fileName = "customers" + input + ".txt";
            writeCustomersToFile(selectedCustomers, fileName);

            // Display the results in a new window
            displayResults(selectedCustomers);
        }
    }

    private List<Customer> searchCustomers(String criteria, String input) {
        List<Customer> selectedCustomers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Prepare and execute the SQL query based on the selected criteria
            String sql = "";
            if (criteria.equals("State")) {
                sql = "SELECT * FROM customers WHERE state = ?";
            } else if (criteria.equals("City")) {
                sql = "SELECT * FROM customers WHERE city = ?";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, input);
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create Customer objects
            while (resultSet.next()) {
                String customerName = resultSet.getString("customerName");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                Customer customer = new Customer(customerName, city, state);
                selectedCustomers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while querying the database: " + e.getMessage());
        }

        return selectedCustomers;
    }
    
    private void writeCustomersToFile(List<Customer> customers, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(mainFrame, "Customers written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private void displayResults(List<Customer> customers) {
        JFrame frame = new JFrame();
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Selected Customers");
        frame.pack();
        frame.setVisible(true);

        for (Customer customer : customers) {
            textArea.append(customer.toString());
            textArea.append("\n");
        }
    }
    
    // Dummy Customer for the system to work.
    private static class Customer {
        private String name;
        private String city;
        private String state;

        public Customer(String name, String city, String state) {
            this.name = name;
            this.city = city;
            this.state = state;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", City: " + city + ", State: " + state;
        }
    }
}
