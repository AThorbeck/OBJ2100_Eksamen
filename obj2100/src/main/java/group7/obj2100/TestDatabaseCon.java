
package group7.obj2100;

//@author www.luv2code.com

import java.sql.*;

public class TestDatabaseCon {
public static void main(String[] args) throws SQLException {

    Connection myConn = null;


    String dbUrl = "jdbc:mysql://localhost:3306/classicmodels";
    String user = "root";
    String pass = "";
    try {
        // 1. Get a connection to database
        myConn = DriverManager.getConnection(dbUrl, user, pass);
        System.out.println("Database connection successful");

    }

        finally{


        if (myConn != null){
       System.out.println("Database connection unsuccessful");
        myConn.close();}
    }
}
}

