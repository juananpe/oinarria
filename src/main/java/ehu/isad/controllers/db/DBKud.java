package ehu.isad.controllers.db;

import ehu.isad.utils.Utils;

import java.sql.*;
import java.util.Properties;


public class DBKud {
    Connection conn = null;

    private void conOpen(String path) {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        try {
            //conn = DriverManager.getConnection("jdbc:sqlite:"+path);
            DriverManager.registerDriver(new org.sqlite.JDBC());
            conn = DriverManager.getConnection("jdbc:sqlite:"+path);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private void conClose() {

        if (conn != null)
            try {
                conn.close();
                System.out.println("Connection to SQLite has been terminated.\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public ResultSet execSQL(String query) {
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                // select
                rs = s.executeQuery(query);
            } else {
                // update, delete, create, insert...
                int count = s.executeUpdate(query);
                System.out.println(count + " rows affected");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return rs;
    }

    // singleton patroia
    private static DBKud instantzia = new DBKud();

    private DBKud() {
        Properties properties = Utils.lortuEzarpenak();
        this.conOpen(properties.getProperty("dbname"));
    }

    public static DBKud getInstantzia() {
        return instantzia;
    }


}
