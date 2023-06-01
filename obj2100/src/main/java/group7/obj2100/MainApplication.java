package group7.obj2100;
// import com.sun.tools.javac.Main;

// Worked on by student: 7127

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        super("OBJ2100 Exam 2023 - Group 7");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 500);


        super.getContentPane().setBackground(new Color(234, 234, 234)); // background color of frame (gray color)


        // setMaximizedBounds(new Rectangle(900, 500)); // sets max size
        // Border border = BorderFactory.createLineBorder(Color.black, 3); // adds black border

        // Create main menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(new Color(255, 255, 255)); // sets color of Menu bar (white color)


        // File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);


        JMenuItem selectFolderMenuItem = new JMenuItem("Select Folder");  // Select folder meny button
        selectFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolder();
            }
        });

        fileMenu.add(selectFolderMenuItem);

        JMenuItem writeCustomersMenuItem = new JMenuItem("Write Customers to the file"); // Select write customer meny button
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
        JMenuItem BulkImportMenuItem = new JMenuItem("Import Bulk to database"); // bulkInport meny button

        bulkImportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==bulkImportMenuItem) {
                   BulkImport myWindow = new BulkImport();
                    myWindow.runBulk();
                }

                bulkImportOrders();
            }
        });
        fileMenu.add(bulkImportMenuItem);

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

       // EXECUTE
        
        executeQueryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SQLQueryExecutor myWindow = new SQLQueryExecutor();
                myWindow.runSqlQueryExecutor();

            }
        });
        databaseMenu.add(executeQueryMenuItem);

       
        
        
        // Add modify employee
        
        JMenuItem addModifyEmployeeMenuItem = new JMenuItem("Add/Modify Employee");
        addModifyEmployeeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==addModifyEmployeeMenuItem) {

                    EmployeeFormDialog myWindow = new EmployeeFormDialog(); // connects to class ListAllProd.java
                    myWindow.runEmployeeDialog();
                }
                addModifyEmployee();
            }
        });
        databaseMenu.add(addModifyEmployeeMenuItem);
        
        
        
        // add modify employee

       
        
        // NEW11111111111111111

        JMenuItem listAllProductsMenuItem = new JMenuItem("List all products");
        listAllProductsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==listAllProductsMenuItem) {
                   // dispose(); // gets rid of main page and goes to list all products page
                    ListAllProd myWindow = new ListAllProd();
                    myWindow.start(); // connects to class ListAllProd.java
                }
                listAllProducts();
            }
        });
        databaseMenu.add(listAllProductsMenuItem);



        

        JMenuItem filterOfficeCountryMenuItem = new JMenuItem("Filter and present offices from a country");
        filterOfficeCountryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterOfficeCountry();
            }
        });
        databaseMenu.add(filterOfficeCountryMenuItem);


        // NEW11111111111111111111111
        
        
        
        
        
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
// selectFolderButton.setBounds(200,100,100,50);
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
                dispose(); // gets rid of main page and goes to SQLExecutor
                SQLQueryExecutor myWindow = new SQLQueryExecutor();
                myWindow.runSqlQueryExecutor();
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

// list all products
      //  setLayout(new FlowLayout());

        // Add buttons or other components to the main view
        JButton listAllProductsButton = new JButton("List all products");

        listAllProductsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                if(e.getSource()==listAllProductsButton) {
                    dispose(); // gets rid of main page and goes to list all products page
                    ListAllProd myWindow = new ListAllProd();
                    myWindow.start();// connects to class AllProducts.java
                }

                listAllProducts();
            }

        });

        add(listAllProductsButton);

        // list all products


        // List all offices

      //  setLayout(new FlowLayout());

        JButton listAllOfficesButton = new JButton("List all offices");

        listAllOfficesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==listAllOfficesButton) {
                    dispose(); // gets rid of main page and goes to list all offices page
                    ListAllOffices myWindow = new ListAllOffices(); // connects to class AllOffices.java
                    myWindow.start();
                }

                listAllOffices();
            }

        });

        add(listAllOfficesButton);


        // list all offices







        // addEmployee


        JButton EmployeeButton = new JButton("Add or modify Employee");

        EmployeeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==EmployeeButton) {

                    JFrame frame = new JFrame();

                    dispose(); // gets rid of main page and goes to list add employee page
                    EmployeeFormDialog myWindow = new EmployeeFormDialog();
                     // connects to class Employee.java
                        myWindow.runEmployeeDialog();
        


                }
                addEmployee();
            }

        });

        add(EmployeeButton);



        // addEmployee









        // Bulk import of orders


        JButton bulkImportOrdersButton = new JButton("Bulk import of orders");

        bulkImportOrdersButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==bulkImportOrdersButton) {
                    dispose(); // gets rid of main page and goes to list add Bulk import of order page
                    BulkImport myWindow = new BulkImport(); // connects to class BulkImport.java
                    myWindow.runBulk();
                }


            }

        });





        // Bulk import of orders
        add(bulkImportOrdersButton);
        
        
        
       



// change folder

        JButton changeFolderButton = new JButton("Change folder");

        changeFolderButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==changeFolderButton) {
                   // dispose(); // gets rid of main page and goes to list all products page
                    FolderSelection myWindow = new FolderSelection(); // connects to class ListAllProd.java
                    myWindow.loadFolder();
                }

                selectFolder();
            }

        });

        add(changeFolderButton);


        // change folder
        
        
        
        //write customer to file


        JButton writeCustomerToFileButton = new JButton("Write customer list that match selected criteria into file...");

        writeCustomerToFileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==writeCustomerToFileButton) {
                    // dispose(); // gets rid of main page and goes to list all products page
                    CustomerWriter myWindow = new CustomerWriter();// connects to class CustomerWriter.java
                    myWindow.runCustomerWriter();
                }

                writeCustomersToFile();
            }

        });

        add(writeCustomerToFileButton);


        // write customer to file






        JButton exitButton = new JButton("Exit");  // exit button
// HERE



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

    private void bulkImport() {

    }


    private void selectFolder() {
        // Legg til funksjon her
    }
    
    // Database
    private void database() {
        

        Object TestDatabaseCon = null;
        if (TestDatabaseCon != null) {

            JOptionPane.showMessageDialog(null, "Database connection unsuccessful");


        } else {
            JOptionPane.showMessageDialog(null, "Database connection successful");
        }
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
        Object TestDatabaseCon = null;
        if (TestDatabaseCon != null) {


            JOptionPane.showMessageDialog(null, "Database connection unsuccessful");
            

        } else {
            JOptionPane.showMessageDialog(null, "Database connection successful");
        }

    }

    private void executeSQLQuery() {
        // Legg til funksjon her
    }

    private void addModifyEmployee() {
        // Legg til funksjon her
    }
    
    
    private void listAllProducts() {  /// 222222222222222
        // Legg til funksjon her
    }

    private void filterOfficeCountry() {  ///22222222222222
        // Legg til funksjon her
    }

    private void showAboutDialog() {
        // Dialog when clicking on help.
        JOptionPane.showMessageDialog(this, "This is a GUI application made by group 7.");
    }


    private void listAllOffices() {




    }

    private void addEmployee() {
    
    }




    public static void main(String[] args) {

        // Connect to the MySQL database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }






















        // Initialize the application
        MainApplication application = new MainApplication();
        application.databaseConnection = connection;



    }
}

