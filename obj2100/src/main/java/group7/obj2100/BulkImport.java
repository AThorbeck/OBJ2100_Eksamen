package group7.obj2100;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BulkImport {
    public static void importFileToDatabase(Connection connection, File file, String tableName) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 13) {
                    String query = "";

                    if (tableName.equals("customers")) {
                        query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    } else if (tableName.equals("employees")) {
                        query = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    } else if (tableName.equals("offices")) {
                        query = "INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    } else if (tableName.equals("orderdetails")) {
                        query = "INSERT INTO orderdetails (orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber) VALUES (?, ?, ?, ?, ?)";
                    } else if (tableName.equals("orders")) {
                        query = "INSERT INTO orders (orderNumber, orderDate, requiredDate, status, comments, customerNumber) VALUES (?, ?, ?, ?, ?, ?)";
                    } else if (tableName.equals("payments")) {
                        query = "INSERT INTO payments (customerNumber, checkNumber, paymentDate, amount) VALUES (?, ?, ?, ?)";
                    } else if (tableName.equals("productlines")) {
                        query = "INSERT INTO productlines (productLine, textDescription, htmlDescription, image) VALUES (?, ?, ?, ?)";
                    } else if (tableName.equals("products")) {
                        query = "INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    }

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        for (int i = 0; i < data.length; i++) {
                            statement.setString(i + 1, data[i].trim());
                        }

                        statement.executeUpdate();
                    }
                }
            }
        }
    }



    public static void importBulk() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            String[] tableOptions = {"customers", "employees", "offices", "orderdetails", "orders", "payments", "productlines", "products"};
            String selectedTable = (String) JOptionPane.showInputDialog(null, "Select the table to import the data into:", "Table Selection", JOptionPane.QUESTION_MESSAGE, null, tableOptions, tableOptions[0]);

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "")) {
                importFileToDatabase(connection, file, selectedTable);
                JOptionPane.showMessageDialog(null, "File imported successfully");
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error occurred during import: " + e.getMessage());
            }
        }
    }

    public static void runBulk() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                importBulk();
            }
        });
    }

    public void importFileToDatabase() {
    }
}
