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


        JMenuItem selectFolderMenuItem = new JMenuItem("Select Folder");  // Select folder menu button
        selectFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolder();
            }
        });

        fileMenu.add(selectFolderMenuItem);

        JMenuItem writeCustomersMenuItem = new JMenuItem("Write Customers to the file"); // Select write customer menu button
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
        JMenuItem BulkImportMenuItem = new JMenuItem("Import Bulk to database"); // bulkInport menu button

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

        // Database menu, sets test database connection button
        JMenu databaseMenu = new JMenu("Database");
        menuBar.add(databaseMenu);

        JMenuItem testConnectionMenuItem = new JMenuItem("Test Database Connection");  
        testConnectionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testDatabaseConnection();
            }
        });
        databaseMenu.add(testConnectionMenuItem);
        
        // EXECUTE sql query menu button

        JMenuItem executeQueryMenuItem = new JMenuItem("Execute SQL Query");

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
        
        
        
       

       
        
        // list all products

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


// test database connection 
        JButton databaseButton = new JButton("Test database connection"); 

        databaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database();
            }
        });
        add(databaseButton);

        // test execute sql query 

        JButton sqlButton = new JButton("Execute SQL query"); 

        sqlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // gets rid of main page and goes to SQLExecutor
                SQLQueryExecutor myWindow = new SQLQueryExecutor();
                myWindow.runSqlQueryExecutor();
            }
        });
        add(sqlButton);

        // about the app
        JButton aboutButton = new JButton("About the application"); // test database connection button
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                about();

            }
        });
        add(aboutButton);


      //  setLayout(new FlowLayout());

        // Add buttons or other components to the main view
        
        // list all products
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

       


       

      //  setLayout(new FlowLayout());
        
         // List all offices

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

        add(listAllOfficesButton); // adds button


       







        // addEmployee


        JButton EmployeeButton = new JButton("Add or modify Employee");

        EmployeeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==EmployeeButton) {

                    JFrame frame = new JFrame();

                    dispose(); // gets rid of main page and goes to list add employee page
                    EmployeeFormDialog myWindow = new EmployeeFormDialog(); // connects to class EmployeeFormDialog.java
                        myWindow.runEmployeeDialog();
        


                }
                addEmployee();
            }

        });

        add(EmployeeButton);



        









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
                   // dispose(); // gets rid of main page and goes to list change folder page
                    FolderSelection myWindow = new FolderSelection(); // connects to class FolderSelection.java
                    myWindow.loadFolder();
                }

                selectFolder();
            }

        });

        add(changeFolderButton);


        
        
        
        
        //write customer to file


        JButton writeCustomerToFileButton = new JButton("Write customer list that match selected criteria into file...");

        writeCustomerToFileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==writeCustomerToFileButton) {
                    // dispose(); // gets rid of main page and goes to list write customer into file page
                    CustomerWriter myWindow = new CustomerWriter();// connects to class CustomerWriter.java
                    myWindow.runCustomerWriter();
                }

                writeCustomersToFile();
            }

        });

        add(writeCustomerToFileButton);


        




// exit button, to exit page

        JButton exitButton = new JButton("Exit");  // exit button


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
        // add function here
    }
    
    // Database
    private void database() {
        // function here

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
        JOptionPane.showMessageDialog(this, "This is a GUI application made by group 7."); // pop up button of about
    }


    private void exit() {
        System.exit(0);  // button to exit the user off.


    }

    private void writeCustomersToFile() {
        // write function here
    }

    private void bulkImportOrders() {
        
    }

    // test database connection, shows messages based on if youre connected or not
  private void testDatabaseConnection() {
        Object TestDatabaseCon = null;
        if (TestDatabaseCon != null) {


            JOptionPane.showMessageDialog(null, "Database connection unsuccessful");
            

        } else {
            JOptionPane.showMessageDialog(null, "Database connection successful");
        }

    }

    private void executeSQLQuery() {
        
    }

    private void addModifyEmployee() {
       
    }
    
    
    private void listAllProducts() {  
         
    }

    private void filterOfficeCountry() {  
        
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

