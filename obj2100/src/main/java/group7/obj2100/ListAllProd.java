package group7.obj2100;
import java.sql.*;
import java.util.*;

public class ListAllProd {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/classicmodels";
        String user = "root";
        String pass = "";
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pass);



            // 2. Create a statement
            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("SELECT * FROM products ");

            while (myRs.next()) {
                System.out.println( "Product: " + myRs.getString("productName") + "") ;
            }
        }

        finally{
            if(myRs != null){
                myRs.close();}

            if (myStmt != null) {
                myStmt.close();}

            if (myConn != null){
                myConn.close();}
        }
    }
}

