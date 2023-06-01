package group7.obj2100;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;


class offices{
    private String officeCode;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;

    public offices(String officeCode, String city, String phone, String addressLine1, String addressLine2,
    String state, String country, String postalCode, String territory){
        this.officeCode = officeCode;
        this.city = city;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.territory = territory;
    }
    public String getOfficeCode(){
        return this.officeCode;
    }

    public String getCity(){
        return this.city;
    }

    public String getPhone(){
    	return this.phone; 
    }

    public String getAddressLine1(){
        return this.addressLine1;
    }

    public String getAddressLine2() {
    	return this.addressLine2; 
    }

    public String getState(){
        return this.state;
    }

    public String getCountry(){
        return this.country;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public String getTerritory(){
        return this.territory;
    }
}


public class ListAllOffices extends JFrame {

    public ListAllOffices(){
        super("Offices from database");
        setLocationRelativeTo(null);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    static Connection  getConnection(){

        String url = "jdbc:mysql://localhost:3306/classicmodels";
        String username = "root";
        String password = "";

        Connection connection = null;

        // Establish the database connection
        try {
            connection = DriverManager.getConnection(url, username, password);
            // Perform database operations here (e.g., saving or modifying the employee data)
            // ...

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }





static ArrayList<offices> getOffices(){
    ArrayList<offices> offices = new ArrayList<offices>();
    Connection connection = getConnection();
    Statement myStmt;
    ResultSet myRs;
    offices p;

    
    try {
        myStmt = connection.createStatement();
        myRs = myStmt.executeQuery("SELECT * FROM offices");

        while (myRs.next()){
        p = new offices(
         myRs.getString("officeCode"),
         myRs.getString("city"),
         myRs.getString("phone"),
         myRs.getString("addressLine1"),
         myRs.getString("addressLine2"),
         myRs.getString("state"),
         myRs.getString("country"),
         myRs.getString("postalCode"),
         myRs.getString("territory"));

        offices.add(p);
        }
    }
    catch(SQLException ex) {
        Logger.getLogger(ListAllOffices.class.getName()).log(Level.SEVERE, null, ex);
    }


    return offices;
}

    public static void main(String[] args) {
        JTable table = new JTable();
        ArrayList<offices> offices = getOffices();

        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[9];
        columnsName[0] = "officeCode";
        columnsName[1] = "city";
        columnsName[2] = "phone";
        columnsName[3] = "addressLine1";
        columnsName[4] = "addressLine2";
        columnsName[5] = "state";
        columnsName[6] = "country";
        columnsName[7] = "postalCode";
        columnsName[8] = "territory";

        
        model.setColumnIdentifiers(columnsName);

        Object[] rowdata = new Object[9];

        for(int i = 0; i < offices.size(); i++){
            rowdata[0] = offices.get(i).getOfficeCode();
            rowdata[1] = offices.get(i).getCity();
            rowdata[2] = offices.get(i).getPhone();
            rowdata[3] = offices.get(i).getAddressLine1();
            rowdata[4] = offices.get(i).getAddressLine2();
            rowdata[5] = offices.get(i).getState();
            rowdata[6] = offices.get(i).getCountry();
            rowdata[7] = offices.get(i).getPostalCode();
            rowdata[8] = offices.get(i).getTerritory();
            
            model.addRow(rowdata);
        }

        table.setModel(model);
        System.out.println(offices.size());
        ListAllOffices window = new ListAllOffices();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane,BorderLayout.CENTER);
        window.setContentPane(panel);


}
}
