/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Confguiche;
import model.Guiches;
import model.Senha;
import model.Usuarios;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import userinterface.GuicheUI;

/**
 *
 * @author Diego Dias
 */
public class Controller {

    private static Controller instance = null;
    private int userId = 0;
    private int currentId = -1;
    Senha current;
    private static String dbHostname = "";
    private static String dbUsername = "";
    private static String dbPassword = "";
    private static String dbName = "";
    private static String propertiesFile = "PhotoScheduler.ini";

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
            loadParams();
            
        }
        return instance;
    }

    public void frGuicheUIBtLogar(GuicheUI panel) {

        String username = panel.getTfUsername().getText();
        String password = String.valueOf(panel.getTfPassword().getPassword());
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();

        Usuarios usuario = null;

        try {
            usuario = (Usuarios) em.createNamedQuery("Usuarios.findByUsernameAndPassword").
                    setParameter("username", username).
                    setParameter("password", password).
                    getSingleResult();
        } catch (Exception e) {
        }

        if (usuario == null) {
            panel.getLbFailure().setText("Usuário/Senha incorreto");
        } else {
            setUserId(usuario.getId());
            panel.getJpLogin().setVisible(false);
            panel.getJpGuiche().setVisible(true);
            if (usuario.getPermissao().contains("F")) {
                panel.getBtConfigurar().setVisible(true);
                panel.getBtEstatisticas().setVisible(true);
            }
            frGuicheUIUpdateNumAtendidas(panel);
        }
        em.close();
        emf.close();
    }

    public EntityManagerFactory ctrlCreateEntityManager() {

        Map<String, String> props = new HashMap<String, String>();

        props.put(PersistenceUnitProperties.JDBC_USER, getDbUsername());
        props.put(PersistenceUnitProperties.JDBC_PASSWORD, getDbPassword());
        props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://" + getDbHostname()
                    + ":5432/" + getDbName());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GuichesUnificadosAppletPU", props);

        return emf;

    }

    public static void loadParams() {
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

    public void frGuicheUIBtChamar(GuicheUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();
        Confguiche configuracao = (Confguiche) em.createNamedQuery("Confguiche.findAll").getSingleResult();

        if (currentId >= 0) {
            current = em.find(Senha.class, getCurrentId());
            em.refresh(current);
            if (current.getStatus().equals('W')) {
                em.getTransaction().begin();
                if (current.getNchamadas() >= configuracao.getNtentativas()) {
                    current.setStatus('L');
                    setCurrentId(-1);
                } else {
                    current.setHchamada(Calendar.getInstance().getTime());
                    current.setNchamadas(current.getNchamadas() + 1);
                    current.setPainel(true);
                }
                em.merge(current);
                em.getTransaction().commit();
                frame.getLbNumeroSenha().setText(formatSenha(current.getNumeracao()));
            } else if (current.getStatus() == 'L') {
                Date hcriacao = Calendar.getInstance().getTime();
                hcriacao.setMinutes(hcriacao.getMinutes() - configuracao.getTolerancia());
                Date hchamada = Calendar.getInstance().getTime();
                hchamada.setMinutes(hchamada.getMinutes() - configuracao.getIntervalo());
                Iterator<Senha> sList = em.createNamedQuery("Senha.findByStatusAndHchamadaAndHcriacao").
                        setParameter("hcriacao", hcriacao).
                        setParameter("hchamada", hchamada).
                        setParameter("status", 'L').getResultList().iterator();
                if (sList.hasNext()) {
                    current = sList.next();
                    em.refresh(current);
                    setCurrentId(current.getId());
                    em.getTransaction().begin();
                    current.setStatus('W');
                    current.setGuiche(frame.getCbGuiche().getSelectedItem().toString());
                    current.setHchamada(Calendar.getInstance().getTime());
                    current.setNchamadas(current.getNchamadas() + 1);
                    current.setPainel(true);
                    em.merge(current);
                    em.getTransaction().commit();
                    frame.getLbNumeroSenha().setText(formatSenha(current.getNumeracao()));
                }
            }
        }
        if (currentId < 0) {
            frGuicheUICriarSenha(frame);
        }
        em.close();
        emf.close();
    }

    private void frGuicheUICriarSenha(GuicheUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();
        Senha current = new Senha();

        Integer numeracao = (Integer) em.createNamedQuery("Senha.findMaxNumeracaoByDtcriacao").
                setParameter("dtcriacao", Calendar.getInstance().getTime()).
                getSingleResult();

        current.setDtcriacao(Calendar.getInstance().getTime());
        current.setHcriacao(Calendar.getInstance().getTime());
        current.setHchamada(Calendar.getInstance().getTime());
        current.setGuiche(frame.getCbGuiche().getSelectedItem().toString());
        current.setNchamadas(1);
        current.setAtendente(userId);
        current.setPainel(true);
        current.setNumeracao(numeracao != null ? numeracao + 1 : 1);
        current.setStatus('W');

        em.getTransaction().begin();
        em.persist(current);
        em.getTransaction().commit();
        em.close();
        emf.close();
        setCurrentId(current.getId());
        frame.getLbNumeroSenha().setText(formatSenha(current.getNumeracao()));
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the currentId
     */
    public int getCurrentId() {
        return currentId;
    }

    /**
     * @param currentId the currentId to set
     */
    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void frGuicheUIBtAtender(GuicheUI frame) {
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();

        if (currentId > 0) {
            Senha current = em.find(Senha.class, getCurrentId());
            em.refresh(current);
            current.setStatus('P');
            current.setAtendente(getUserId());
            current.setGuiche(frame.getCbGuiche().getSelectedItem().toString());
            current.setPainel(false);
            em.getTransaction().begin();
            em.merge(current);
            em.getTransaction().commit();
            em.close();
            emf.close();
            setCurrentId(-1);
            frame.getLbNumeroSenha().setText("");
        }
    }

    private String formatSenha(Integer senha) {
        return (senha < 10) ? "00" + senha
                : (senha < 100) ? "0" + senha
                : String.valueOf(senha);
    }

    public void frGuicheUIBtSalvar(GuicheUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();

        Confguiche confguiche =
                (Confguiche) em.createNamedQuery("Confguiche.findAll").getSingleResult();

        confguiche.setAviso(frame.getTfAviso().getText());
        confguiche.setFontaviso((Integer) frame.getJsTamanhoFonteAviso().getValue());
        confguiche.setFontsenha((Integer) frame.getJsTamanhoFonteSenha().getValue());
        confguiche.setTfreqconsultaaviso((Integer) frame.getJsPeriodicidadeConsultaAviso().getValue());
        confguiche.setTfreqconsultasenha((Integer) frame.getJsPeriodicidadeConsultaSenha().getValue());
        confguiche.setTexibicao((Integer) frame.getJsTempoPermanenciaSenha().getValue());
        confguiche.setNultimas((Integer) frame.getJsUQtdeltimasChamadas().getValue());
        confguiche.setTolerancia((Integer) frame.getJsToleranciaSenha().getValue());
        confguiche.setIntervalo((Integer) frame.getJsIntervaloRechamadas().getValue());
        confguiche.setNtentativas((Integer) frame.getJsNtentativasSenha().getValue());
        confguiche.setVelocidadeaviso((Integer) frame.getJsVelocidade().getValue());
        confguiche.setSound(frame.getRbAirp().isSelected() ? "Airport.wav" : "Indirimli.wav");

        em.getTransaction().begin();
        em.merge(confguiche);
        em.getTransaction().commit();

        em.close();
        emf.close();

    }

    public void frGuicheUIBtNovo(GuicheUI frame) {

        frame.getdNovo().pack();
        frame.getdNovo().setVisible(true);

    }

    public void frGuicheUIBtSalvarNovoGuiche(GuicheUI frame) {
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();
        Guiches guiche = new Guiches();

        guiche.setNome(frame.getTfNomeGuiche().getText());
        em.getTransaction().begin();
        em.persist(guiche);
        em.getTransaction().commit();
        em.close();
        emf.close();
        frame.getdNovo().dispose();

        frame.getTfNomeGuiche().setText("");
        frGuicheUIUpdateTable(frame);

    }

    public void frGuicheUICreateTable(GuicheUI frame) {
        frame.getGuicheTableModel().addColumn("id");
        frame.getGuicheTableModel().addColumn("nome");
        frame.getTbGuiche().getColumnModel().removeColumn(
                frame.getTbGuiche().getColumnModel().getColumn(0));

    }

    public void frGuicheUIUpdateTable(GuicheUI frame) {

        List<Guiches> resultList;
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();


        int size = frame.getGuicheTableModel().getRowCount();
        for (int i = size - 1; (size > 0) && (i >= 0); i--) {
            frame.getGuicheTableModel().removeRow(0);
        }
        resultList = em.createNamedQuery("Guiches.findAll").getResultList();
        ArrayList<String> row = new ArrayList<String>();

        for (Guiches g : resultList) {
            row.add(g.getId().toString());
            row.add(g.getNome().toString());
            frame.getGuicheTableModel().addRow(row.toArray());
            row.clear();
        }

        em.close();
        emf.close();
    }

    public void frGuicheUIBtApagarGuiche(GuicheUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();
        Guiches guiche;

        guiche = em.find(Guiches.class, Integer.parseInt(frame.getGuicheTableModel().
                getValueAt(frame.getTbGuiche().getSelectedRow(), 0).toString()));

        em.getTransaction().begin();
        em.remove(guiche);
        em.getTransaction().commit();
        em.close();
        emf.close();

        frGuicheUIUpdateTable(frame);
    }

    public void frGuicheUICreateTableAtendimento(GuicheUI frame) {
        frame.getAtendimentoTableModel().addColumn("id");
        frame.getAtendimentoTableModel().addColumn("Guichê");
        frame.getAtendimentoTableModel().addColumn("Total Atendidos");
        frame.getTbAtendimento().getColumnModel().removeColumn(
                frame.getTbAtendimento().getColumnModel().getColumn(0));


    }

    public void frGuicheUIUpdateTableAtendimento(GuicheUI frame) {
        ArrayList<String> row = new ArrayList<String>();
        List<Guiches> gList;
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();

        int size = frame.getAtendimentoTableModel().getRowCount();
        for (int i = size - 1; (size > 0) && (i >= 0); i--) {
            frame.getAtendimentoTableModel().removeRow(0);
        }
        gList = (List<Guiches>) em.createNamedQuery("Guiches.findAll").getResultList();
        for (Guiches g : gList) {
            int total = Integer.parseInt(em.createNamedQuery("Senha.findCountByGuicheAndDtCriacaoAndStatus").
                    setParameter("status", 'P').
                    setParameter("dtcriacao", frame.getDcbDtAtendimento().getSelectedDate().getTime()).
                    setParameter("guiche", g.getNome()).getSingleResult().toString());
            row.add(g.getId().toString());
            row.add(g.getNome().toString());
            row.add("" + total);
            frame.getAtendimentoTableModel().addRow(row.toArray());
            row.clear();
        }

        em.close();
        emf.close();
    }

    public void frGuicheUIUpdateNumAtendidas(GuicheUI frame) {
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();

        frame.getLbTotalAtendidas().
                setText(em.createNamedQuery("Senha.findCountByGuicheAndDtCriacaoAndStatusAndAtendente").
                setParameter("dtcriacao", Calendar.getInstance().getTime()).
                setParameter("guiche", frame.getCbGuiche().getSelectedItem()).
                setParameter("status", 'P').
                setParameter("atendente", getUserId()).
                getSingleResult().toString());
    }

    /**
     * @return the dbHostname
     */
    public static String getDbHostname() {
        return dbHostname;
    }

    /**
     * @param dbHostname the dbHostname to set
     */
    public static void setDbHostname(String adbHostname) {
        dbHostname = adbHostname;
    }

    /**
     * @return the dbUsername
     */
    public static String getDbUsername() {
        return dbUsername;
    }

    /**
     * @param dbUsername the dbUsername to set
     */
    public static void setDbUsername(String adbUsername) {
        dbUsername = adbUsername;
    }

    /**
     * @return the dbPassword
     */
    public static String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param dbPassword the dbPassword to set
     */
    public static void setDbPassword(String adbPassword) {
        dbPassword = adbPassword;
    }

    /**
     * @return the dbName
     */
    public static String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public static void setDbName(String adbName) {
        dbName = adbName;
    }
}
