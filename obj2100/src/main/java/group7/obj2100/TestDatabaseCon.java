
package group7.obj2100;

//@author www.luv2code.com

import java.sql.*;

public class TestDatabaseCon {
public static void main(String[] args) throws SQLException {

    Connection connection = null;


    String dbUrl = "jdbc:mysql://localhost:3306/classicmodels";
    String user = "root";
    String pass = "";
    try {
        // 1. Get a connection to database
        connection = DriverManager.getConnection(dbUrl, user, pass);
        System.out.println("Database connection successful");

    }

        finally{


        if (connection != null){
        connection.close();}
    }
}
}

