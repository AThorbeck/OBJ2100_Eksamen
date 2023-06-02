package group7.obj2100;
// import com.sun.tools.javac.Main;

// Worked on by student: 7127

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainApplication {


    public Connection databaseConnection;


    public static void main(String[] args) {

        new MainApplication();

    }



    public MainApplication() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                try {

                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());

                    javax.swing.JFrame frame = new javax.swing.JFrame("Testing");

                    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

                    frame.setJMenuBar(createMenuBar()); // Add this line

                    frame.add(new framePane());

                    frame.pack();

                    frame.setLocationRelativeTo(null);

                    frame.setVisible(true);

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {

                    ex.printStackTrace();

                }

            }

        });

    }



    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();



        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem selectFolderMenuItem = new JMenuItem("Select Folder"); // Select folder menu button
        selectFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==selectFolderMenuItem) {
                    // dispose(); // gets rid of main page and goes to list change folder page
                    FolderSelection myWindow = new FolderSelection(); // connects to class FolderSelection.java
                    myWindow.loadFolder();
                }

                selectFolder();
            }
        });
        fileMenu.add(selectFolderMenuItem);

        JMenuItem writeCustomersMenuItem = new JMenuItem("Write Customers to File");

        writeCustomersMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==writeCustomersMenuItem) {

                    CustomerWriter myWindow = new CustomerWriter();
                    myWindow.runCustomerWriter();
                }

                writeCustomersToFile();
            }

        });
        fileMenu.add(writeCustomersMenuItem);


        JMenuItem bulkImportMenuItem = new JMenuItem("Import bulk to Database");
        bulkImportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bulkImportOrders();
            }
        });


        fileMenu.add(bulkImportMenuItem);


        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);



        fileMenu.add(selectFolderMenuItem);

        fileMenu.add(writeCustomersMenuItem);

        fileMenu.add(bulkImportMenuItem);

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);



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



        JMenuItem addModifyEmployeeMenuItem = new JMenuItem("Add / modify Employee");
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



        databaseMenu.add(testConnectionMenuItem);
        JButton databaseButton = new JButton("Test database connection");



        databaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database();
            }
        });



        databaseMenu.add(executeQueryMenuItem);
        executeQueryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SQLQueryExecutor myWindow = new SQLQueryExecutor();
                myWindow.runSqlQueryExecutor();

            }
        });
        databaseMenu.add(executeQueryMenuItem);

        JButton databaseButton = new JButton("Test database connection");



        databaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database();
            }
        });
        add(databaseButton);

        databaseMenu.add(addModifyEmployeeMenuItem);
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


        databaseMenu.add(listAllProductsMenuItem);
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


        databaseMenu.add(filterOfficeCountryMenuItem);
        filterOfficeCountryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterOfficeCountry();
            }
        });
        databaseMenu.add(filterOfficeCountryMenuItem);


        menuBar.add(databaseMenu);





        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);


        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAboutDialog();
            }
        });

        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);



        return menuBar;

    }



    private void database() {

    }

    private void filterOfficeCountry() {
    }

    private void listAllProducts() {
    }

    private void addModifyEmployee() {

    }

    private void testDatabaseConnection() {

    }

    private void bulkImportOrders() {

    }

    private void writeCustomersToFile() {
    }

    private void selectFolder() {
    }

}

class framePane extends JPanel {



    private FileAccessSettingsPane fileAccessSettingsPane;

    private ListCustomersPane ListCustomersPane;

    private ReportsAndUpdatesPane reportsAndUpdatesPane;

    private ActionPane actionPane;



    public framePane() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.weightx = 1;

        gbc.weighty = 0.25;

        gbc.anchor = GridBagConstraints.WEST;

        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(4, 4, 4, 4);



        add((fileAccessSettingsPane = new FileAccessSettingsPane()), gbc);

        gbc.gridy++;

        add((ListCustomersPane = new ListCustomersPane()), gbc);

        gbc.gridy++;

        add((reportsAndUpdatesPane = new ReportsAndUpdatesPane()), gbc);



        gbc.gridy = 0;

        gbc.gridx++;

        gbc.gridheight = GridBagConstraints.REMAINDER;

        gbc.fill = GridBagConstraints.VERTICAL;

        gbc.weighty = 1;

        gbc.weightx = 0;

        add((actionPane = new ActionPane()), gbc);

    }

}


class FileAccessSettingsPane extends JPanel {

    private JButton changeFolderButton;



    public FileAccessSettingsPane() {

        setLayout(new GridBagLayout());

        setBorder(new CompoundBorder(new TitledBorder("File Access Settings"), new EmptyBorder(12, 0, 0, 0)));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.insets = new Insets(0, 0, 0, 0);



        add((changeFolderButton = new JButton("Change Folder")), gbc);

    }

}

class ListCustomersPane extends JPanel {



    private JButton writeCustomerListButton;



    public ListCustomersPane() {

        setLayout(new GridBagLayout());

        setBorder(new CompoundBorder(new TitledBorder("List Customers"), new EmptyBorder(12, 0, 0, 0)));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.insets = new Insets(0, 0, 0, 0);



        add((writeCustomerToFileButton = new JButton("Write customer list that match selected criteria into file...")), gbc);

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

    }

    private void writeCustomersToFile() {

    }

}

class ReportsAndUpdatesPane extends JPanel {



    private JButton AddorModifyEmployeeButton;

    private JButton ListAllProductsButton;

    private JButton ListAllOfficesButton;

    private JButton BulkImportButton;



    public ReportsAndUpdatesPane() {

        setLayout(new GridBagLayout());

        setBorder(new CompoundBorder(new TitledBorder("Reports and Updates"), new EmptyBorder(12, 0, 0, 0)));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.insets = new Insets(0, 0, 0, 0);



        add((AddorModifyEmployeeButton = new JButton("Add or modify employee")), gbc);

        gbc.gridx++;

        AddorModifyEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==addModifyEmployeeMenuItem) {

                    EmployeeFormDialog myWindow = new EmployeeFormDialog(); // connects to class ListAllProd.java
                    myWindow.runEmployeeDialog();
                }
                addModifyEmployee();
            }
        });
        databaseMenu.add(addModifyEmployeeMenuItem);

        add((ListAllProductsButton = new JButton("List all products")), gbc);

        gbc.gridx++;

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


        add((ListAllOfficesButton = new JButton("List all offices")), gbc);

        gbc.gridx++;

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


        add((BulkImportButton = new JButton("Bulk import of orders")), gbc);

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

    }

    private void listAllOffices() {

    }

    private void listAllProducts() {

    }

}

class ActionPane extends JPanel {

    private JButton testDatabaseConnection;

    private JButton executeSQLQuery;

    private JButton aboutTheApp;

    private JButton exitButton;



    public ActionPane() {

        setLayout(new GridBagLayout());

        setBorder(new CompoundBorder(new TitledBorder("Menu"), new EmptyBorder(12, 12, 12, 12)));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        gbc.insets = new Insets(4, 4, 4, 4);



        add((testDatabaseConnection = new JButton("Test database connection")), gbc);

        gbc.gridy++;

        databaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database();
            }
        });
        add(databaseButton);

        add((executeSQLQuery = new JButton("Execute SQL Query")), gbc);

        gbc.gridy++;

        sqlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // gets rid of main page and goes to SQLExecutor
                SQLQueryExecutor myWindow = new SQLQueryExecutor();
                myWindow.runSqlQueryExecutor();
            }
        });
        add(sqlButton);


        add((aboutTheApp = new JButton("About the app")), gbc);

        gbc.gridy++;

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                about();

            }
        });
        add(aboutButton);

        add((exitButton = new JButton("Exit")), gbc);

        exitButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {


                exit();
            }
        });

        add(exitButton);

        setVisible(true);
    }

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




    

    private void about() {
        JOptionPane.showMessageDialog(this, "This is a GUI application made by group 7."); // pop up button of about
    }


    private void exit() {
        System.exit(0);  // button to exit the user off.


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

   

    


    

    private void showAboutDialog() {
        // Dialog when clicking on help.
        JOptionPane.showMessageDialog(this, "This is a GUI application made by group 7.");
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



