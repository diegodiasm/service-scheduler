/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Dias
 */
public class Connector {

    private static Connection con;
    private static String dbaddress;
    private static String dbname;
    private static String dbuser;
    private static String dbpass;

    /**
     *
     * @param dbaddress // Endereco IP do banco de dados
     * @param dbname // Nome do banco de dados
     * @param dbuser // Nome do usuario do banco de dados
     * @param dbpass // Senha do usuario do banco de dados
     */
    public Connector(String dbaddress, String dbname, String dbuser, String dbpass) throws SQLException {

        Connector.dbaddress = dbaddress;
        Connector.dbname = dbname;
        Connector.dbuser = dbuser;
        Connector.dbpass = dbpass;
        try {

            Class.forName("org.postgresql.Driver");
            // If you are using any other database then load the right driver here.
            //Create the connection using the static getConnection method
            setCon(DriverManager.getConnection("jdbc:postgresql://" + dbaddress + ":5432/" + dbname,
                    dbuser, dbpass));

            System.out.println("Conectado a PSQL");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doQuery(String query) {
        try {
            //Create a Statement class to execute the SQL statement
            Statement stmt = getCon().createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public void doInsert(String table, ArrayList<Object> array) {

     String request = "";
     while (!array.isEmpty()) {
     request = request + "'" + array.remove(0) + "'";
     if (!array.isEmpty()) {
     request = request + ",";
     }
     }

     doQuery("INSERT INTO " + table + " VALUES (" + request + ")");
     } 
     */
    public ResultSet doSelect(String query) {

        /* Create a PreparedStatement class to execute the SQL statement
         * Execute the SQL statement and get the results in a Resultset
         */

        ResultSet rs = null;

        try {
            Statement stmt = getCon().createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public void close() {

        try {
            getCon().close();
            System.out.println("Desconectado a PSQL");
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    public void showStatus() {
        System.err.println("[" + Connector.dbaddress + "]:["
                + Connector.dbname + "]:["
                + Connector.dbuser + "]:["
                + Connector.dbpass + "]");
    }
}
