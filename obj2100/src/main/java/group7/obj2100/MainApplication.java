// NEW
package gui;

import javax.swing.*;
import javax.swing.border.Border;
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
        super("OBJ2100 Exam 2023 - Group XX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);


        super.getContentPane().setBackground(new Color(234, 234, 234)); // background color of frame (gray color)





        setMaximizedBounds(new Rectangle(900, 500)); // sets max size
        // Border border = BorderFactory.createLineBorder(Color.black, 3); // adds black border

        // Create main menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(new Color(255, 255, 255)); // sets color of Menu bar (white color)



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

        JButton databaseButton = new JButton("Test database connection"); // test database connection button
        databaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database();
            }
        });
        add(databaseButton);



        JButton sqlButton = new JButton("Execute SQL query"); // test database connection button
        sqlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sql();
            }
        });
        add(sqlButton);

        JButton aboutButton = new JButton("About the application"); // test database connection button
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                about();

            }
        });
        add(aboutButton);




        JButton exitButton = new JButton("Exit");  // exit button
// HERE
        exitButton.setBounds(20,10,10,5);
        exitButton.setFont(new Font("Arial", Font.BOLD, 28));


        this.add(exitButton);
// HERE
        exitButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {


                exit();
            }
        });

        add(exitButton);

        setVisible(true);
    }

    private void selectFolder() {
        // Legg til funksjon her
    }

    private void database() {
        // Legg til funksjon her
    }

    private void sql() {

    }

    private void about() {
        JOptionPane.showMessageDialog(this, "This is a GUI application made by group 7.");
    }


    private void exit() {
        System.exit(0);  // button to exit the user off.


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
        JOptionPane.showMessageDialog(this, "This is a GUI application made by group 7.");
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


// NEW ^^















// OLD

/* import javax.swing.*;
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

/* public class MainApplication extends JFrame {

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
} /*
