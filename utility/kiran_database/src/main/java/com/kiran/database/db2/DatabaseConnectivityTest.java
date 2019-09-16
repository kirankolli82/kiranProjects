package com.kiran.database.db2;

import com.ibm.db2.jcc.DB2SimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectivityTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*DB2ConnectionPoolDataSource dataSource = new DB2ConnectionPoolDataSource();
        dataSource.setDatabaseName("fxplorer");
        dataSource.setUser("db2Admin");
        dataSource.setPassword("0penSesame!");

        dataSource.getPooledConnection().getConnection();*/

        Class.forName("com.ibm.db2.jcc.DB2Driver");
        System.out.println("**** Loaded the JDBC driver");

        // Create the connection using the IBM Data Server Driver for JDBC and SQLJ
        Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/fxplorer", "db2Admin", "********");

        DB2SimpleDataSource dataSource = new DB2SimpleDataSource();
        dataSource.setDatabaseName("fxplorer");
        dataSource.setUser("db2Admin");
        dataSource.setPassword("********");

        con = dataSource.getConnection();
        System.out.println("---connected---");

    }
}
