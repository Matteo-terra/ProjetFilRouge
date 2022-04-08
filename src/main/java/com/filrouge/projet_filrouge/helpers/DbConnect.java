
package com.filrouge.projet_filrouge.helpers;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnect {
    private Statement st;
    private ResultSet rs;
    
    private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "fil_rouge";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
        private static Connection connection ;
        
        
        public static Connection getConnect (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return  connection;
        }


    public ResultSet searchQuery (String sql){
        try {
            getConnect();
            st=connection.createStatement();
            rs=st.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println(e);

        }
        return rs;
    }
}
