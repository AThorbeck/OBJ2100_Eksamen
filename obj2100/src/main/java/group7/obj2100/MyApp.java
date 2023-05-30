import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyApp implements ActionListener {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    // Add more menu items as needed

    public MyApp() {
        // Create the main frame
        frame = new JFrame("EXAM Group 7");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the menu bar
        menuBar = new JMenuBar();

        // create menus for the menu bar
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("help");
        JMenu databaseMenu = new JMenu("database");
        
        
        // Create menu items for File
        menuItem1 = new JMenuItem("Change folder");
        menuItem1.addActionListener(this);
        fileMenu.add(menuItem1);

        menuItem2 = new JMenuItem("Write customers into file");
        menuItem2.addActionListener(this);
        fileMenu.add(menuItem2);
        
        JMenuItem menuItem3 = new JMenuItem("Bulk import of orders");
        menuItem3.addActionListener(this);
        fileMenu.add(menuItem3);
        
        JMenuItem menuItem4 = new JMenuItem("Exit application");
        menuItem2.addActionListener(this);
        fileMenu.add(menuItem4);
        
        //Create menu items for help
        menuItem1 = new JMenuItem("About");
        menuItem1.addActionListener(this);
        helpMenu.add(menuItem1);
        
        //Create menu items for database
        menuItem1 = new JMenuItem("Test database connection");
        menuItem1.addActionListener(this);
        databaseMenu.add(menuItem1);

        menuItem2 = new JMenuItem("Execute SQL query");
        menuItem2.addActionListener(this);
        databaseMenu.add(menuItem2);
        
        menuItem3 = new JMenuItem("Add or modify employee");
        menuItem3.addActionListener(this);
        databaseMenu.add(menuItem3);
        
        menuItem4 = new JMenuItem("List all products");
        menuItem4.addActionListener(this);
        databaseMenu.add(menuItem4);
        
        JMenuItem menuItem5 = new JMenuItem("Filter and present offices from a country");
        menuItem5.addActionListener(this);
        databaseMenu.add(menuItem5);
        
        // Add the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(databaseMenu);
        menuBar.add(helpMenu);
        
        
        

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create buttons
        JButton button1 = new JButton("Button 1");
        button1.addActionListener(this);
        buttonPanel.add(button1);

        JButton button2 = new JButton("Button 2");
        button2.addActionListener(this);
        buttonPanel.add(button2);

        // Add the button panel to the frame
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle menu item or button clicks here
        if (e.getSource() == menuItem1) {
            // Perform action for Feature 1
            System.out.println("Feature 1 selected");
        } else if (e.getSource() == menuItem2) {
            // Perform action for Feature 2
            System.out.println("Feature 2 selected");
        } else if (e.getActionCommand().equals("Button 1")) {
            // Perform action for Button 1
            System.out.println("Button 1 clicked");
        } else if (e.getActionCommand().equals("Button 2")) {
            // Perform action for Button 2
            System.out.println("Button 2 clicked");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyApp());
    }
}
