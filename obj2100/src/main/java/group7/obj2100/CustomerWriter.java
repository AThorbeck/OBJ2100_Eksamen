package group7.obj2100;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerWriter {

    // Database connection. Through jdbc driver we are able to connect to the database.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/classicmodels";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    // The different criterias that can be selected from the dropdown list. 
    private static final String[] CRITERIA_OPTIONS = {
        "State",
        "City"
    };

    // main frame of class
    private JFrame mainFrame;
    private JComboBox < String > criteriaComboBox;
    private JComboBox < String > inputComboBox;
    private JButton searchButton;

    public static void runCustomerWriter() {
        CustomerWriter customerWriter = new CustomerWriter();
        customerWriter.createUI();
    }
    
    // UI of the class
    public void createUI() {
        mainFrame = new JFrame("Customer Search"); // Creates the 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.setLayout(new FlowLayout());

        criteriaComboBox = new JComboBox < > (CRITERIA_OPTIONS);
        criteriaComboBox.setSelectedItem("State"); // Select "State" option by default
        criteriaComboBox.addActionListener(new CriteriaComboBoxListener());
        mainFrame.add(criteriaComboBox);

        inputComboBox = new JComboBox < > ();
        updateInputComboBox("State"); // Populate the inputComboBox with states
        mainFrame.add(inputComboBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        mainFrame.add(searchButton);

        mainFrame.setVisible(true);
    }
    
    // This class is used to update the ComboBox when the user selects a new criteria
    private void updateInputComboBox(String criteria) {
        inputComboBox.removeAllItems();
        if (criteria.equals("City")) {
            // Cities get loaded from database and populate the input dropdown list
            String[] cities = getCities();
            for (String city: cities) {
                inputComboBox.addItem(city);
            }
        } else if (criteria.equals("State")) {
            // states get loaded from database and populate the input dropdown list
            String[] states = getStates();
            for (String state: states) {
                inputComboBox.addItem(state);
            }
        }
    }
    
    // Here is the string for cities, it will create the variable so that the user can get the cities from the DB listed in the dropdown menu
    private String[] getCities() {
        List < String > cities = new ArrayList < > ();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT DISTINCT city FROM customers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String city = resultSet.getString("city");
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while querying the database: " + e.getMessage());
        }

        return cities.toArray(new String[0]);
    }

    // same as cities string, it will create a variable so that the user can get states from the DB listed in the dropdown menu
    private String[] getStates() {
        List < String > states = new ArrayList < > ();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT DISTINCT state FROM customers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String state = resultSet.getString("state");
                states.add(state);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while querying the database: " + e.getMessage());
        }

        return states.toArray(new String[0]);
    }

    private class CriteriaComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String criteria = criteriaComboBox.getSelectedItem().toString();
            updateInputComboBox(criteria);
        }
    }

    // This is a listener class, which listens to the software, and registers if the button is pressed.
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String criteria = criteriaComboBox.getSelectedItem().toString();
            String input = inputComboBox.getSelectedItem().toString();

            List < Customer > selectedCustomers = searchCustomers(criteria, input);

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

    // This allows us to list the different customers on search.
    private List < Customer > searchCustomers(String criteria, String input) {
        List < Customer > selectedCustomers = new ArrayList < > ();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "";
            if (criteria.equals("State")) {
                sql = "SELECT * FROM customers WHERE state = ?";
            } else if (criteria.equals("City")) {
                sql = "SELECT * FROM customers WHERE city = ?";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, input);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String customerName = resultSet.getString("customerName");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                Customer customer = new Customer(customerName, city, state);
                selectedCustomers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while querying the database: " + e.getMessage()); // displays error message if there are any erros in the system
        }

        return selectedCustomers;
    }

    
    private void writeCustomersToFile(List < Customer > customers, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Customer customer: customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(mainFrame, "Customers written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private void displayResults(List < Customer > customers) {
        JFrame frame = new JFrame();
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Selected Customers");

        JButton printButton = new JButton("Print");
        printButton.addActionListener(new PrintButtonListener(customers));
        frame.getContentPane().add(printButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        for (Customer customer: customers) {
            textArea.append(customer.toString());
            textArea.append("\n");
        }
    }

    // when user gets result, they are able to print out the results as a file of their choosing.
    private class PrintButtonListener implements ActionListener {
        private List < Customer > customers;

        public PrintButtonListener(List < Customer > customers) {
            this.customers = customers;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(mainFrame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                writeCustomersToFile(customers, file.getAbsolutePath());
            }
        }
    }


    //Dummy data for the method to work. Without it it doesn't function.

    private static class Customer {
        private String name;
        private String city;
        private String state;

        public Customer(String name, String city, String state) {
            this.name = name;
            this.city = city;
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", City: " + city + ", State: " + state;
        }
    }
}
