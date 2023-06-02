package group7.obj2100;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;


class products{
    private String productCode;
    private String productName;
    private String productLine;
    private String productVendor;

    private String productScale;
    private String productDescription;
    private int quantityInStock;
    private int buyPrice;
    private int MSRP;

    public products(String productCode, String productName, String productLine, String productVendor, String productScale,
    String productDescription, int quantityInStock, int buyPrice, int MSRP){
        this.productCode = productCode;
        this.productName = productName;
        this.productLine = productLine;
        this.productVendor = productVendor;
        this.productScale = productScale;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.MSRP = MSRP;
    }
    public String getProductCode(){
        return this.productCode;
    }

    public String getProductName(){
        return this.productName;
    }

    public String getProductLine(){return this.productLine; }

    public String getProductVendor(){
        return this.productVendor;
    }

    public String getProductScale() {return this.productScale; }

    public String getProductDescription(){
        return this.productDescription;
    }

    public int getQuantityInStock(){
        return this.quantityInStock;
    }

    public int getBuyPrice(){
        return this.buyPrice;
    }

    public int getMSRP(){
        return this.MSRP;
    }
}


public class ListAllProd extends JFrame {

    public ListAllProd(){
        super("Products from database");
        setLocationRelativeTo(null);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    static Connection  getConnection(){

        String url = "jdbc:mysql://localhost:3306/classicmodels";
        String username = "student";
        String password = "student";

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





static ArrayList<products> getProducts(){
    ArrayList<products> products = new ArrayList<products>();
    Connection connection = getConnection();
    Statement myStmt;
    ResultSet myRs;
    products p;

    try {
        myStmt = connection.createStatement();
        myRs = myStmt.executeQuery("SELECT * FROM products");

        while (myRs.next()){
        p = new products(
         myRs.getString("productCode"),
         myRs.getString("productName"),
         myRs.getString("productLine"),
         myRs.getString("productScale"),
         myRs.getString("productVendor"),
         myRs.getString("productDescription"),
         myRs.getInt("quantityInStock"),
         myRs.getInt("buyPrice"),
         myRs.getInt("MSRP"));

            products.add(p);
        }
    }
    catch(SQLException ex) {
        Logger.getLogger(ListAllProd.class.getName()).log(Level.SEVERE, null, ex);
    }


    return products;
}

    public void start() {
        JTable table = new JTable();
        ArrayList<products> products = getProducts();

        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[9];
        columnsName[0] = "productCode";
        columnsName[1] = "productName";
        columnsName[2] = "productLine";
        columnsName[3] = "productVendor";
        columnsName[4] = "productScale";
        columnsName[5] = "productDescription";
        columnsName[6] = "quantityInStock";
        columnsName[7] = "buyPrice";
        columnsName[8] = "MSRP";

        model.setColumnIdentifiers(columnsName);

        Object[] rowdata = new Object[9];

        for(int i = 0; i < products.size(); i++){
            rowdata[0] = products.get(i).getProductCode();
            rowdata[1] = products.get(i).getProductName();
            rowdata[2] = products.get(i).getProductLine();
            rowdata[3] = products.get(i).getProductVendor();
            rowdata[4] = products.get(i).getProductScale();
            rowdata[5] = products.get(i).getProductDescription();
            rowdata[6] = products.get(i).getQuantityInStock();
            rowdata[7] = products.get(i).getBuyPrice();
            rowdata[8] = products.get(i).getMSRP();

            model.addRow(rowdata);
        }

        table.setModel(model);
        System.out.println(products.size());
        ListAllProd window = new ListAllProd();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane,BorderLayout.CENTER);
        window.setContentPane(panel);


}
}