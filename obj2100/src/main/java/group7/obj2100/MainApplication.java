import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Exam Group 7
 * Purpose: The purpose of this app is to create a simple GUI window with different functions that is connected to MySQL
 * Date: 30.05.2023 - 02.06.2023
 */
public class MainApplication extends JFrame {

    private Connection databaseConnection;

    public MainApplication() {
        super("Application Name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create main menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem selectFolderMenuItem = new JMenuItem("Select Folder");
        selectFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolder();
            }
        });
        fileMenu.add(selectFolderMenuItem);

        JMenuItem writeCustomersMenuItem = new JMenuItem("Write Customers to the file");
        writeCustomersMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeCustomersToFile();
            }
        });
        fileMenu.add(writeCustomersMenuItem);

        JMenuItem bulkImportMenuItem = new JMenuItem("Import Bulk to database");
        bulkImportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bulkImportOrders();
            }
        });
        fileMenu.add(bulkImportMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit app");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        // Database menu
        JMenu databaseMenu = new JMenu("Database");
        menuBar.add(databaseMenu);

        JMenuItem testConnectionMenuItem = new JMenuItem("Test Database Connection");
        testConnectionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testDatabaseConnection();
            }
        });
        databaseMenu.add(testConnectionMenuItem);

        JMenuItem executeQueryMenuItem = new JMenuItem("Execute SQL Query");
        executeQueryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeSQLQuery();
            }
        });
        databaseMenu.add(executeQueryMenuItem);

        JMenuItem addModifyEmployeeMenuItem = new JMenuItem("Add/Modify Employee");
        addModifyEmployeeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addModifyEmployee();
            }
        });
        databaseMenu.add(addModifyEmployeeMenuItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAboutDialog();
            }
        });
        helpMenu.add(aboutMenuItem);

        // Set layout
        setLayout(new FlowLayout());

        // Add buttons or other components to the main view
        JButton selectFolderButton = new JButton("Select Folder");
        selectFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolder();
            }
        });
        add(selectFolderButton);

        // Add more buttons or components as needed

        setVisible(true);
    }

    private void selectFolder() {
        // Legg til funksjon her
    }

    private void writeCustomersToFile() {
        // Legg til funksjon her
    }

    private void bulkImportOrders() {
        // Legg til funksjon her
    }

    private void testDatabaseConnection() {
        // Legg til funksjon her
    }

    private void executeSQLQuery() {
        // Legg til funksjon her
    }

    private void addModifyEmployee() {
        // Legg til funksjon her
    }

    private void showAboutDialog() {
        // Dialog when clicking on help.
        JOptionPane.showMessageDialog(this, "Test, test this is the help button.");
    }

    public static void main(String[] args) {
        // Connect to the MySQL database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Initialize the application
        MainApplication application = new MainApplication();
        application.databaseConnection = connection;
    }
}
