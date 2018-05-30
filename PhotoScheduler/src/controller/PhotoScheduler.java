/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import model.Connector;
import userinterface.MenuPrincipalUI;

/**
 *
 * @author Diego Dias
 */
public class PhotoScheduler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {


        Util resource = new Util();

        resource.loadParams();
        Connector dbConnector = new Connector(resource.getDbHostname(),
                resource.getDbName(),
                resource.getDbUsername(),
                resource.getDbPassword());
        
        // Definicao de propriedades para CtrlEntityManagerFactory
        Controller.setDbUsername(resource.getDbUsername());
        Controller.setDbPassword(resource.getDbPassword());
        Controller.setDbUrl("jdbc:postgresql://" + resource.getDbHostname()
                + ":5432/" + resource.getDbName());

        Controller.setCon(dbConnector);
        MenuPrincipalUI.CreateAndShowInitUI(args);
    }
}
