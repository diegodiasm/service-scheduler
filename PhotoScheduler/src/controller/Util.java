
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Diego Dias
 */
public class Util {

    private String dbHostname = "";
    private String dbUsername = "";
    private String dbPassword = "";
    private String dbName = "";
    private String propertiesFile = "PhotoScheduler.ini";

    public static String formatTime(int h, int m) {

        String hPrint = (h >= 10) ? "" + h : "0" + h;
        String mPrint = (m >= 10) ? "" + m : "0" + m;

        return hPrint + ":" + mPrint;


    }

    public static String milisecondsToString(int timeInMilisecods) {

        return formatTime(
                ((timeInMilisecods) / (60 * 1000)),
                ((timeInMilisecods % (60 * 1000)) / 1000));        
    }

    public void loadParams() {
        Properties props = new Properties();
        InputStream is = null;

        // First try loading from the current directory
        try {
            File f = new File(propertiesFile);

            is = new FileInputStream(f);
        } catch (Exception e) {
            is = null;
        }

        try {

            // Try loading properties from the file (if found)
            props.load(is);
            setDbHostname(props.getProperty("dbHostname"));
            setDbUsername(props.getProperty("dbUsername"));
            setDbPassword(props.getProperty("dbPassword"));
            setDbName(props.getProperty("dbName"));
        } catch (Exception e) {
            System.err.println("Falha ao carregar PhotoScheduler.ini");
        }


    }

    public void saveParamChanges() {
        try {
            Properties props = new Properties();

            props.setProperty("dbHostname", getDbHostname());
            props.setProperty("dbName", getDbName());
            props.setProperty("dbUsername", getDbUsername());
            props.setProperty("dbPassword", getDbPassword());



            File f = new File(propertiesFile);
            OutputStream out = new FileOutputStream(f);

            props.store(out, "Properties of PhotoScheduler (diegodias.m@gmail.com)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the dbHostname
     */
    public String getDbHostname() {
        return dbHostname;
    }

    /**
     * @param dbHostname the dbHostname to set
     */
    public void setDbHostname(String dbHostname) {
        this.dbHostname = dbHostname;
    }

    /**
     * @return the dbUsername
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * @param dbUsername the dbUsername to set
     */
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    /**
     * @return the dbPassword
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param dbPassword the dbPassword to set
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
