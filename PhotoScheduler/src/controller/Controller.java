/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datechooser.model.multiple.Period;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.Agendamento;
import model.Connector;
import model.Restricoes;
import model.Servidores;
import model.Usuarios;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import userinterface.AcompanharUI;
import userinterface.AddServersUI;
import userinterface.AtendimentosRealizadosUI;
import userinterface.CadastrarUI;
import userinterface.ConfDbUI;
import userinterface.ConfigurarUI;
import userinterface.MenuPrincipalUI;
import userinterface.ServersStatisticsUI;
import userinterface.TimeRestrictionUI;
import userinterface.UserStatisticsUI;
import userinterface.UsuariosUI;

/**
 *
 * @author Diego Dias
 */
public class Controller {

    private static Controller instance = null;
    private static Connector con;
    private static int id;
    private static ActionEvent parentEvt;
    private static String dbUsername = "";
    private static String dbPassword = "";
    private static String dbUrl = "";
    private static int dto;
    private static Image icon;

    /**
     * @return the con
     */
    public static Connector getCon() {
        return con;
    }

    /**
     * @param aCon the con to set
     */
    public static void setCon(Connector aCon) {
        con = aCon;
    }

    /**
     * @return the id
     */
    public static int getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(int aId) {
        id = aId;
    }

    /**
     * @return the parentEvt
     */
    public static ActionEvent getParentEvt() {
        return parentEvt;
    }

    /**
     * @param aParentEvt the parentEvt to set
     */
    public static void setParentEvt(ActionEvent aParentEvt) {
        parentEvt = aParentEvt;
    }

    /**
     * @return the dbUsername
     */
    public static String getDbUsername() {
        return dbUsername;
    }

    /**
     * @param aDbUsername the dbUsername to set
     */
    public static void setDbUsername(String aDbUsername) {
        dbUsername = aDbUsername;
    }

    /**
     * @return the dbPassword
     */
    public static String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param aDbPassword the dbPassword to set
     */
    public static void setDbPassword(String aDbPassword) {
        dbPassword = aDbPassword;
    }

    /**
     * @return the dbUrl
     */
    public static String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param aDbUrl the dbUrl to set
     */
    public static void setDbUrl(String aDbUrl) {
        dbUrl = aDbUrl;
    }

    /**
     * @return the dto
     */
    public static int getDto() {
        return dto;
    }

    /**
     * @param aDto the dto to set
     */
    public static void setDto(int aDto) {
        dto = aDto;
    }

    private Controller() {
    }

    public static Controller getInstance() {

        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static Controller getInstance(Connector con) {

        if (instance == null) {
            instance = new Controller();
            setCon(con);
        }
        return instance;
    }

    public void frCadastrarUIBtSalvarActionPerformed(CadastrarUI frame) {

        Agendamento ag = frame.getFormData();
        Usuarios operador;

        if (!ag.getNome().isEmpty()) {
            ResultSet rs = getCon().doSelect("SELECT name FROM servidores, agendamento"
                    + " WHERE servidores.name='"
                    + frame.getCbMaquina().getSelectedItem()
                    + "' AND agendamento.horario='" + frame.getCbHorario().getSelectedItem().toString()
                    + "' AND agendamento.data='" + frame.getDcDataAgendamento().getSelectedDate().getTime()
                    + "' AND CAST (agendamento.servidor AS integer)=servidores.id");


            try {
                if (rs.next()) {
                    frame.getLbHorarioIndisponivel().setText(frame.getCbHorario().getSelectedItem().
                            toString() + " não está mais disponível");
                } else {
                    EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
                    EntityManager em = emf.createEntityManager();
                    operador = em.find(Usuarios.class, getId());

                    ag.setOperador(operador.getName());
                    em.getTransaction().begin();
                    em.persist(ag);
                    em.getTransaction().commit();
                    em.close();
                    emf.close();
                    frame.getLbHorarioIndisponivel().setText("");
                    frame.setFormData();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            frame.getLbHorarioIndisponivel().setText("O campo nome não pode ser vazio!");
        }
    }

    public void frCadastrarUIBtMenuPrincipalActionPerformed(CadastrarUI frame) {

        MenuPrincipalUI telaMenuPrincipal = new MenuPrincipalUI(getCon());
        frame.dispose();
        telaMenuPrincipal.setVisible(true);

    }

    public void frCadastrarUIupdateCbHorario(CadastrarUI frame) {

        Date data = new Date();
        ResultSet rs;
        int service = 1;
        int inicioExpedienteH = 0;
        int inicioExpedienteM = 0;
        int finalExpedienteH = 0;
        int finalExpedienteM = 0;
        int inicioAlmocoH = 0;
        int inicioAlmocoM = 0;
        int finalAlmocoH = 0;
        int finalAlmocoM = 0;
        int nextTimeSlice = data.getMinutes();
        int hour = data.getHours();
        int minute = nextTimeSlice;
        boolean computouAlmoco = false;
        /* Artificio para comparacao de datas desconsiderando horario */
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        boolean afterToday = frame.getDcDataAgendamento().getSelectedDate().getTime().after(data);
        boolean isToday = formatter.format(frame.getDcDataAgendamento().getSelectedDate().getTime()).
                equals(formatter.format(data));
        /* Fim do artificio */


        rs = getCon().doSelect("SELECT * FROM servidores WHERE name='"
                + frame.getCbMaquina().getSelectedItem() + "'");

        // Carrega configuracao default

        try {
            if (rs.next()) {
                String[] split = rs.getString("inicioexp").split(":");
                inicioExpedienteH = Integer.parseInt(split[0]);
                inicioExpedienteM = Integer.parseInt(split[1]);
                split = rs.getString("finalexp").split(":");
                finalExpedienteH = Integer.parseInt(split[0]);
                finalExpedienteM = Integer.parseInt(split[1]);
                split = rs.getString("inicioalmoco").split(":");
                inicioAlmocoH = Integer.parseInt(split[0]);
                inicioAlmocoM = Integer.parseInt(split[1]);
                split = rs.getString("finalalmoco").split(":");
                finalAlmocoH = Integer.parseInt(split[0]);
                finalAlmocoM = Integer.parseInt(split[1]);
                service = Integer.parseInt(rs.getString("servico"));
                nextTimeSlice = ((nextTimeSlice % service) == 0) ? nextTimeSlice
                        : nextTimeSlice + (service - (nextTimeSlice % service));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Carrega configuracao de data especifica para o servidor selecionado

        rs = getCon().doSelect("SELECT restricoes.inicioexp, restricoes.finalexp,"
                + "restricoes.inicioalmoco, restricoes.finalalmoco FROM "
                + "restricoes,servidores WHERE restricoes.data='"
                + frame.getDcDataAgendamento().getSelectedDate().getTime()
                + "' AND restricoes.serverid=servidores.id AND servidores.name='"
                + frame.getCbMaquina().getSelectedItem() + "'");

        try {
            if (rs.next()) {
                String[] split = rs.getString("inicioexp").split(":");
                inicioExpedienteH = Integer.parseInt(split[0]);
                inicioExpedienteM = Integer.parseInt(split[1]);
                split = rs.getString("finalexp").split(":");
                finalExpedienteH = Integer.parseInt(split[0]);
                finalExpedienteM = Integer.parseInt(split[1]);
                split = rs.getString("inicioalmoco").split(":");
                inicioAlmocoH = Integer.parseInt(split[0]);
                inicioAlmocoM = Integer.parseInt(split[1]);
                split = rs.getString("finalalmoco").split(":");
                finalAlmocoH = Integer.parseInt(split[0]);
                finalAlmocoM = Integer.parseInt(split[1]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Fim do mecanismo para carregar datas especificas

        frame.getCbHorario().removeAllItems();


        if (afterToday) {
            nextTimeSlice = inicioExpedienteM;
        } else {
            nextTimeSlice = (hour < inicioExpedienteH) ? inicioExpedienteM
                    : (hour == inicioExpedienteH) ? nextTimeSlice
                    : (hour < inicioAlmocoH) ? nextTimeSlice
                    : ((hour == inicioAlmocoH) && (nextTimeSlice < inicioAlmocoM)) ? nextTimeSlice
                    : (hour < finalExpedienteH) ? nextTimeSlice
                    : ((hour == finalExpedienteH) && (nextTimeSlice < finalExpedienteM)) ? nextTimeSlice : 0;
            inicioExpedienteH = Math.max(hour, inicioExpedienteH);
        }
        if (afterToday || isToday) {
            /* Para cada hora/minuto valido entre inicioExpedienteH e finalExpedienteH */
            for (int h = inicioExpedienteH, lastMinute;
                    h <= finalExpedienteH;
                    h++) {
                lastMinute = (h == finalExpedienteH ? finalExpedienteM
                        : h == inicioAlmocoH && !computouAlmoco ? inicioAlmocoM : 60);
                for (int m = nextTimeSlice; (m < lastMinute); m = m + service) {
                    nextTimeSlice = (m + service) % 60;
                    /*Pula intervalo do almoco, mas contninua contando nextTimeSlice*/
                    if (!(h > inicioAlmocoH && h < finalAlmocoH)) {
                        rs = getCon().doSelect("SELECT horario FROM agendamento,servidores WHERE "
                                + "agendamento.horario='" + Util.formatTime(h, m)
                                + "' AND servidores.name='" + frame.getCbMaquina().getSelectedItem()
                                + "' AND agendamento.data='"
                                + frame.getDcDataAgendamento().getCurrent().getTime()
                                + "' AND servidores.id = agendamento.servidor");
                        try {
                            if (!rs.next()) {
                                frame.getCbHorario().addItem(Util.formatTime(h, m));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(CadastrarUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                nextTimeSlice = nextTimeSlice % 60;
                if (h == inicioAlmocoH) {
                    if (!computouAlmoco) {
                        h = finalAlmocoH - 1;
                        nextTimeSlice = finalAlmocoM;
                        computouAlmoco = true;
                    }
                }
            }
        }

        /* Atualiza getDcDataAgendamento para exibir proxima data disponivel
         * para agendamento. Atualizacao eh feita atraves de um metodo dedicado. 
         */
        if (!frame.getChbPrioritario().isSelected()) {
            frCadastrarUIupdateDcAgendamento(frame);
        }
    }

    public void frCadastrarUIupdateCbMaquina(CadastrarUI frame) {

        ResultSet rs;

        rs = getCon().doSelect("SELECT * from servidores WHERE status='A' ORDER BY name");
        try {

            frame.getCbMaquina().removeAllItems();
            while (rs.next()) {
                frame.getCbMaquina().addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("empty-statement")
    public void frAcompanharUIUpdateDetranTableModel(AcompanharUI frame) {


        DefaultTableModel dtm = new javax.swing.table.DefaultTableModel();
        ArrayList<String> row = new ArrayList<String>();
        ResultSet rs;
        Date data = frame.getDcDateChooserAcompanharUI().getSelectedDate().getTime();
        String maquina = frame.getCbMaquina().getSelectedItem().toString();
        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        frame.setDetranTableModel(dtm);
        frame.getDetranTableModel().addColumn("Registro");
        frame.getDetranTableModel().addColumn("Horário");
        frame.getDetranTableModel().addColumn("Nome");
        frame.getDetranTableModel().addColumn("CPF");
        frame.getDetranTableModel().addColumn("Telefone");



        rs = getCon().doSelect("SELECT id FROM servidores WHERE name='" + maquina + "'");
        try {
            if (rs.next()) {
                int id = rs.getInt("id");
                rs = getCon().doSelect("SELECT * FROM agendamento WHERE servidor='"
                        + id + "' AND data='"
                        + data.toString()
                        + "' AND status='P' ORDER BY horario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            while (rs.next()) {
                row.add(rs.getString("id"));
                row.add(rs.getString("horario"));
                row.add(rs.getString("nome"));
                row.add(rs.getString("cpf"));
                row.add(rs.getString("telefone"));
                frame.getDetranTableModel().addRow(row.toArray());
                row.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcompanharUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.getTbMarcacoes().setModel(frame.getDetranTableModel());
        frame.getTbMarcacoes().getColumnModel().removeColumn(frame.getTbMarcacoes().
                getColumnModel().getColumn(0));


        frame.getLbAtendidos().setText(em.createNamedQuery("Agendamento.countFindByDataAndStatusAndName").
                setParameter("data", frame.getDcDateChooserAcompanharUI().
                getSelectedDate().getTime()).
                setParameter("status", 'A').
                setParameter("name", frame.getCbMaquina().getSelectedItem()).getSingleResult().toString());
        frame.getLbPendentes().setText(em.createNamedQuery("Agendamento.countFindByDataAndStatusAndName").
                setParameter("data", frame.getDcDateChooserAcompanharUI().
                getSelectedDate().getTime()).
                setParameter("status", 'P').
                setParameter("name", frame.getCbMaquina().getSelectedItem()).getSingleResult().toString());
        frame.getLbTotal().setText(em.createNamedQuery("Agendamento.countFindByDataAndName").
                setParameter("data", frame.getDcDateChooserAcompanharUI().
                getSelectedDate().getTime()).
                setParameter("name", frame.getCbMaquina().getSelectedItem()).getSingleResult().toString());
        frame.getLbDesistentes().setText(em.createNamedQuery("Agendamento.countFindByDataAndStatusAndName").
                setParameter("data", frame.getDcDateChooserAcompanharUI().
                getSelectedDate().getTime()).
                setParameter("status", 'D').
                setParameter("name", frame.getCbMaquina().getSelectedItem()).getSingleResult().toString());


    }

    public void frAcompanharUIBtAtenderMarcadosActionPerformed(AcompanharUI frame) {

        if ((frame.getTbMarcacoes().getModel().getRowCount() > 0)
                && (frame.getTbMarcacoes().getSelectedRow() >= 0)) {

            EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
            EntityManager em = emf.createEntityManager();
            int line = Math.max(frame.getTbMarcacoes().getSelectedRow(), 1);

            setDto(frame.getAgendamentoId());
            Agendamento agendamento = em.find(Agendamento.class, getDto());

            frAcompanharUIRegistrarTempoAtendimento(frame);

            agendamento.setStatus('A');
            em.getTransaction().begin();
            em.merge(agendamento);
            em.getTransaction().commit();
            em.close();
            emf.close();


            frAcompanharUIUpdateDetranTableModel(frame);

            if (frame.getTbMarcacoes().getModel().getRowCount() > 0) {
                frame.getTbMarcacoes().requestFocus();
                frame.getTbMarcacoes().setRowSelectionInterval(line - 1, line - 1);
            }


        }
    }

    public void frAcompanharUIBtMenuPrincipalActionPerformed(AcompanharUI frame) {
        MenuPrincipalUI telaMenuPrincipal = new MenuPrincipalUI(getCon());
        frame.dispose();
        telaMenuPrincipal.setVisible(true);

    }

    public void frConfigurarUILoadParams(ConfDbUI panel) {

        Util resource = new Util();

        resource.loadParams();

        panel.getTfDbHostname().setText(resource.getDbHostname());
        panel.getTfDbUsername().setText(resource.getDbUsername());
        panel.getTfDbName().setText(resource.getDbName());
        panel.getTfDbPassword().setText(resource.getDbPassword());

    }

    public void frConfigurarUISaveParams(ConfDbUI panel) {

        Util resource = new Util();

        resource.setDbHostname(panel.getTfDbHostname().getText());
        resource.setDbUsername(panel.getTfDbUsername().getText());
        resource.setDbName(panel.getTfDbName().getText());
        resource.setDbPassword(String.valueOf(panel.getTfDbPassword().getPassword()));

        try {
            Connector dbConnector = new Connector(resource.getDbHostname(),
                    resource.getDbName(),
                    resource.getDbUsername(),
                    resource.getDbPassword());

            Controller.getCon().close();

            // Definicao dos parametros do CtrlEntityManagerFactory
            Controller.setDbUsername(resource.getDbUsername());
            Controller.setDbPassword(resource.getDbPassword());
            Controller.setDbUrl("jdbc:postgresql://" + resource.getDbHostname()
                    + ":5432/" + resource.getDbName());
            // Fim da secao de definicao dos parametros do
            // CtrlEntityManagerFactory

            setCon(dbConnector);
            ((ConfigurarUI) SwingUtilities.getWindowAncestor(panel)).setCon(dbConnector);

            panel.getLbImpossivelConectar().setText("");
            resource.saveParamChanges();
        } catch (SQLException ex) {
            panel.getLbImpossivelConectar().setText("Dados incorretos: não "
                    + "foi possível estabeler uma conexão.");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("empty-statement")
    public void frAddServersUIRefreshTable(AddServersUI panel) {

        ArrayList<String> row = new ArrayList<String>();
        ResultSet rs = null;

        if (getCon() != null) {
            if (getCon().getCon() != null) {
                try {
                    if (!getCon().getCon().isClosed()) {
                        rs = getCon().doSelect("SELECT * FROM servidores ORDER by name");
                        try {
                            int size = panel.getAtendenteTableModel().getRowCount();
                            for (int i = size - 1; (size > 0) && (i >= 0); i--) {
                                panel.getAtendenteTableModel().removeRow(0);
                            }
                            while (rs.next()) {
                                row.add(rs.getString("id"));
                                row.add(rs.getString("name"));
                                row.add(rs.getString("servico"));
                                row.add(rs.getString("status"));
                                row.add(rs.getString("inicioexp"));
                                row.add(rs.getString("finalexp"));
                                row.add(rs.getString("inicioalmoco"));
                                row.add(rs.getString("finalalmoco"));
                                panel.getAtendenteTableModel().addRow(row.toArray());
                                row.clear();

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void frAddServersUIBtExcluir(AddServersUI panel) {

        if ((panel.getTbAtendentes().getRowCount() > 0)
                && (panel.getTbAtendentes().getSelectedRow() >= 0)) {

            int line = Math.max(panel.getTbAtendentes().getSelectedRow(), 1);


            String id = (String) panel.getAtendenteTableModel().getValueAt(
                    panel.getTbAtendentes().getSelectedRow(), 0);
            getCon().doQuery("DELETE FROM servidores WHERE id='" + id + "'");

            // Adicionar mecanismo para remover linha apenas se houver linha
            panel.getAtendenteTableModel().removeRow(panel.getTbAtendentes().getSelectedRow());
            if (panel.getAtendenteTableModel().getRowCount() > 0) {
                panel.getTbAtendentes().requestFocus();
                panel.getTbAtendentes().setRowSelectionInterval(line - 1, line - 1);
            }
        }

    }

    public void frAcompanharUIUpdateCbMaquinaModel(AcompanharUI frame) {

        ResultSet rs = getCon().doSelect("SELECT name from servidores ORDER by name");

        try {
            while (rs.next()) {
                frame.getCbMaquinaModel().addElement(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int frCadastrarUIgetSelectedServer(CadastrarUI frame) {

        String s = "";
        ResultSet rs = getCon().doSelect("SELECT id FROM servidores WHERE name='"
                + frame.getCbMaquina().getSelectedItem() + "'");
        try {
            if (rs.next()) {
                s = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Integer.parseInt(s);

    }

    public void frConfigurarUIBtMenuPrincipal(ConfigurarUI frame) {

        MenuPrincipalUI telaMenuPrincipal = new MenuPrincipalUI(getCon());
        frame.dispose();
        telaMenuPrincipal.setVisible(true);
    }

    public void frAddServersUICreateTable(AddServersUI panel) {

        panel.getAtendenteTableModel().addColumn("ID");
        panel.getAtendenteTableModel().addColumn("Atendente");
        panel.getAtendenteTableModel().addColumn("Tempo de Atendimento");
        panel.getAtendenteTableModel().addColumn("Status");
        panel.getAtendenteTableModel().addColumn("Início do exp.");
        panel.getAtendenteTableModel().addColumn("Final do exp.");
        panel.getAtendenteTableModel().addColumn("Saída para almoço");
        panel.getAtendenteTableModel().addColumn("Retorno do almoço");
        panel.getTbAtendentes().getColumnModel().removeColumn(panel.getTbAtendentes().
                getColumnModel().getColumn(0));
    }

    public void frAddServersUIBtSalvar(AddServersUI panel) {


        Servidores server = panel.getFormAdicionarData();

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(server);
        em.getTransaction().commit();
        em.close();
        emf.close();

        panel.cleanFields();
        panel.getdAddServer().dispose();

    }

    public void frAddServersUIBtEditar(AddServersUI panel) {

        int idServer = panel.getSelectedServerId();

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Servidores server = em.find(Servidores.class, idServer);

        panel.getTfNomeAtendente1().setText(server.getName());
        panel.getTfInicioExpediente1().setText(server.getInicioexp());
        panel.getTfFinalExpediente1().setText(server.getFinalexp());
        panel.getTfSaidaAlmoco1().setText(server.getInicioalmoco());
        panel.getTfRetornoAlmoco1().setText(server.getFinalalmoco());
        panel.getTfTempoAtendimento1().setText(server.getServico());
        panel.getChbAtivo1().setSelected(server.getStatus().equals('A') ? true : false);

        em.close();
        emf.close();

    }

    public void frAddServersUIBtAtualizar(AddServersUI panel) {

        int serverId = panel.getSelectedServerId();

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Servidores server = em.find(Servidores.class, serverId);

        em.getTransaction().begin();

        server.setName(panel.getTfNomeAtendente1().getText());
        server.setInicioalmoco(panel.getTfSaidaAlmoco1().getText());
        server.setFinalalmoco(panel.getTfRetornoAlmoco1().getText());
        server.setInicioexp(panel.getTfInicioExpediente1().getText());
        server.setFinalexp(panel.getTfFinalExpediente1().getText());
        server.setStatus(panel.getChbAtivo1().isSelected() ? 'A' : 'I');
        server.setServico(panel.getTfTempoAtendimento1().getText());

        em.merge(server);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void frTimeRestrictionUIBtSalvar(TimeRestrictionUI panel) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Iterator<Period> iterator = panel.getDcTimeRestriction().getSelection().iterator();
        int serverId = 0;

        ResultSet rs = getCon().doSelect("SELECT id FROM servidores WHERE "
                + "name='" + panel.getCbAtendente().getSelectedItem() + "'");
        try {
            if (rs.next()) {
                serverId = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (iterator.hasNext()) {
            Period p = iterator.next();
            for (Date tdate = p.getStartDate().getTime();
                    !tdate.after(p.getEndDate().getTime());
                    tdate.setDate(tdate.getDate() + 1)) {
                Restricoes restricoes = new Restricoes();
                restricoes.setData(tdate);
                if (panel.getChbNaoHaveraExpediente().isSelected()) {
                    restricoes.setInicioexp("00:00");
                    restricoes.setFinalexp("00:00");

                } else {
                    restricoes.setInicioexp(panel.getTfInicioExpediente().getText());
                    restricoes.setFinalexp(panel.getTfFinalExpediente().getText());
                }

                restricoes.setInicioalmoco(panel.getTfSaidaAlmoco().getText());
                restricoes.setFinalalmoco(panel.getTfRetornoAlmoco().getText());
                restricoes.setMotivo(panel.getTfMotivo().getText());
                restricoes.setServerid(serverId);

                restricoes.setMotivo(panel.getTfMotivo().getText());
                em.getTransaction().begin();
                em.persist(restricoes);
                em.getTransaction().commit();
            }
        }
        em.close();
        emf.close();

        frTimeRestrictionsUIRefreshTable(panel);
        panel.getdTimeRestriction().dispose();

    }

    public void frTimeRestrictionsUICreateTable(TimeRestrictionUI panel) {

        panel.getRestricoesTableModel().addColumn("ID");
        panel.getRestricoesTableModel().addColumn("Data");
        panel.getRestricoesTableModel().addColumn("Atendente");
        panel.getRestricoesTableModel().addColumn("Início do expediente");
        panel.getRestricoesTableModel().addColumn("Final do expediente");
        panel.getRestricoesTableModel().addColumn("Saída para almoço");
        panel.getRestricoesTableModel().addColumn("Retorno do almoço");
        panel.getRestricoesTableModel().addColumn("Motivo");
        panel.getTbTimeRestrictions().getColumnModel().
                removeColumn(panel.getTbTimeRestrictions().getColumnModel().getColumn(0));

    }

    public void frTimeRestrictionsUIRefreshTable(TimeRestrictionUI panel) {

        ArrayList<String> row = new ArrayList<String>();

        if (getCon() != null) {
            if (getCon().getCon() != null) {
                try {
                    if (!getCon().getCon().isClosed()) {

                        ResultSet rs;
                        rs = getCon().doSelect("SELECT restricoes.inicioexp, restricoes.finalexp,"
                                + "restricoes.motivo,restricoes.data,restricoes.id,servidores.name,"
                                + "restricoes.inicioalmoco, restricoes.finalalmoco "
                                + "FROM restricoes,servidores WHERE restricoes.serverid=servidores.id "
                                + "ORDER by data");
                        try {
                            int size = panel.getRestricoesTableModel().getRowCount();
                            for (int i = size - 1; (size > 0) && (i >= 0); i--) {
                                panel.getRestricoesTableModel().removeRow(0);
                            }
                            while (rs.next()) {

                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                                row.add(rs.getString("id"));
                                row.add(formatter.format(rs.getDate("data")).toString());
                                row.add(rs.getString("name"));
                                row.add(rs.getString("inicioexp"));
                                row.add(rs.getString("finalexp"));
                                row.add(rs.getString("inicioalmoco"));
                                row.add(rs.getString("finalalmoco"));
                                row.add(rs.getString("motivo"));
                                panel.getRestricoesTableModel().addRow(row.toArray());
                                row.clear();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void frMenuPrincipalLogin(MenuPrincipalUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Usuarios usuario = null;
        try {
            usuario = (Usuarios) em.createNamedQuery("Usuarios.findByUsernameAndPassword").
                    setParameter("username", frame.getTfUsuario().getText()).
                    setParameter("password", String.valueOf(frame.getTfSenha().getPassword())).
                    getSingleResult();
        } catch (Exception e) {
        }

        if (usuario != null) {
            setId(usuario.getId());
            frame.getdLogin().dispose();
        } else {
            frame.getLbFalhaLogin().setText("Falha ao efetuar login");
        }

        em.close();
        emf.close();

    }

    public void frMenuPrincipalControleDeAcesso(MenuPrincipalUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Usuarios usuario = em.find(Usuarios.class, getId());

        if (!usuario.getPermissao().contains("A")) {
            frame.getBtAcompanhar().setEnabled(false);
        }
        if (!usuario.getPermissao().contains("C")) {
            frame.getBtCadastrar().setEnabled(false);
        }
        if (!usuario.getPermissao().contains("F")) {
            frame.getBtConfigurar().setEnabled(false);
        }

        em.close();
        emf.close();

    }

    public void frTimeRestrictionUIBtExcluir(TimeRestrictionUI panel) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Restricoes restricao = em.find(Restricoes.class,
                Integer.parseInt(panel.getTbTimeRestrictions().getModel().
                getValueAt(panel.getTbTimeRestrictions().getSelectedRow(), 0).
                toString()));

        em.getTransaction().begin();
        em.remove(restricao);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public void frUsuariosUICreateTable(UsuariosUI panel) {

        panel.getUsuariosTableModel().addColumn("ID");
        panel.getUsuariosTableModel().addColumn("Nome");
        panel.getUsuariosTableModel().addColumn("Login");
        panel.getUsuariosTableModel().addColumn("Permissões");

        panel.getTbUsuarios().getColumnModel().removeColumn(
                panel.getTbUsuarios().getColumnModel().getColumn(0));

    }

    public void frUsuariosUIRefreshTable(UsuariosUI panel) {

        if (getCon() != null) {
            if (getCon().getCon() != null) {
                try {
                    if (!getCon().getCon().isClosed()) {
                        ResultSet rs = getCon().doSelect("SELECT id,name,username,permissao FROM usuarios");
                        ArrayList<String> row = new ArrayList<String>();


                        try {
                            int size = panel.getUsuariosTableModel().getRowCount();
                            for (int i = size - 1; (size > 0) && (i >= 0); i--) {
                                panel.getUsuariosTableModel().removeRow(0);
                            }
                            while (rs.next()) {

                                row.add(rs.getString("id"));
                                row.add(rs.getString("name"));
                                row.add(rs.getString("username"));
                                row.add(rs.getString("permissao"));

                                panel.getUsuariosTableModel().addRow(row.toArray());
                                row.clear();


                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void frUsuariosUIBtSalvar(UsuariosUI panel) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Usuarios usuario = panel.getFormData();

        if (((JButton) Controller.getParentEvt().getSource()).getText().equals("Editar")) {

            Usuarios usuarioTmp = em.find(Usuarios.class, Controller.getDto());
            usuarioTmp.setName(usuario.getName());
            usuarioTmp.setUsername(usuario.getUsername());
            usuarioTmp.setPassword(usuario.getPassword());
            usuarioTmp.setPermissao(usuario.getPermissao());
            usuario = usuarioTmp;
        }

        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
        emf.close();

        frUsuariosUIRefreshTable(panel);
        panel.cleanFields();
        panel.getdAdicionarUsuario().dispose();
    }

    public void frUsuariosUIBtExcluir(UsuariosUI panel) {

        int userId = panel.getSelecedUserId();

        if (userId != getId()) {
            EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
            EntityManager em = emf.createEntityManager();
            Usuarios usuario = em.find(Usuarios.class, userId);

            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
            em.close();
            emf.close();
        } else {
            JOptionPane.showMessageDialog(panel, "Você está logado como "
                    + panel.getUsuariosTableModel().getValueAt(panel.getTbUsuarios().getSelectedRow(), 1)
                    + ". Não é possível remover o usuário usado na autenticação.");
        }
    }

    public void frTimeRestrictionUILoadAtendentes(TimeRestrictionUI panel) {

        if (getCon() != null) {
            if (getCon().getCon() != null) {
                try {
                    if (!getCon().getCon().isClosed()) {

                        ResultSet rs = getCon().doSelect("SELECT name FROM servidores WHERE "
                                + "status='A' ORDER by name");

                        try {
                            panel.getAtendentesDisponiveis().removeAllElements();
                            while (rs.next()) {
                                panel.getAtendentesDisponiveis().addElement(rs.getString("name"));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void frAcompanharUIBtExcluir(AcompanharUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        int agendamentoId = frame.getAgendamentoId();

        Agendamento agendamento = em.find(Agendamento.class, agendamentoId);

        em.getTransaction().begin();
        em.remove(agendamento);
        em.getTransaction().commit();
        em.close();
        emf.close();

        frAcompanharUIUpdateDetranTableModel(frame);
    }

    public void frUsuariosUIBtEditar(UsuariosUI panel) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        int userId = panel.getSelecedUserId();

        Usuarios usuario = em.find(Usuarios.class, userId);

        panel.getChbAcompanhar().setSelected(usuario.getPermissao().contains("A"));
        panel.getChbCadastrar().setSelected(usuario.getPermissao().contains("C"));
        panel.getChbConfigurar().setSelected(usuario.getPermissao().contains("F"));
        panel.getChbAgendamentoPrioritario().setSelected(usuario.getPermissao().contains("P"));
        panel.getChbAtenderExcluir().setSelected(usuario.getPermissao().contains("E"));
        panel.getTfLogin().setText(usuario.getUsername());
        panel.getTfNomeUsuario().setText(usuario.getName());
        panel.getTfSenha().setText(usuario.getPassword());
        setDto(userId);

        panel.getdAdicionarUsuario().pack();
        panel.getdAdicionarUsuario().setVisible(true);
    }

    public void frAtendimentosRealizadosCreateTable(AtendimentosRealizadosUI panel) {

        panel.getAtendimentosRealizadosModel().addColumn("ID");
        panel.getAtendimentosRealizadosModel().addColumn("Data");
        panel.getAtendimentosRealizadosModel().addColumn("Atendente");
        panel.getAtendimentosRealizadosModel().addColumn("Horário");
        panel.getAtendimentosRealizadosModel().addColumn("Nome");
        panel.getAtendimentosRealizadosModel().addColumn("CPF");
        panel.getAtendimentosRealizadosModel().addColumn("Telefone");
        panel.getAtendimentosRealizadosModel().addColumn("Data da Inscrição");
        panel.getAtendimentosRealizadosModel().addColumn("Horário da Inscrição");
        panel.getAtendimentosRealizadosModel().addColumn("Operador");
        panel.getAtendimentosRealizadosModel().addColumn("Inicio do Atendimento");
        panel.getAtendimentosRealizadosModel().addColumn("Duração");
        panel.getTbAtendimentosRealizados().getColumnModel().removeColumn(
                panel.getTbAtendimentosRealizados().getColumnModel().getColumn(0));

    }

    public void frAtendimentosRealizadosRefreshTable(AtendimentosRealizadosUI panel) {


        if (getCon() != null) {
            if (getCon().getCon() != null) {
                try {
                    if (!getCon().getCon().isClosed()) {
                        ArrayList<String> row = new ArrayList<String>();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                        ResultSet rs = getCon().doSelect("SELECT * FROM agendamento, servidores "
                                + "WHERE agendamento.status='A' AND agendamento.servidor=servidores.id "
                                + "ORDER BY data, name, horario");

                        try {

                            int size = panel.getAtendimentosRealizadosModel().getRowCount();
                            for (int i = size - 1; (size > 0) && (i >= 0); i--) {
                                panel.getAtendimentosRealizadosModel().removeRow(0);
                            }
                            while (rs.next()) {

                                row.add(rs.getString("id"));
                                row.add(formatter.format(rs.getDate("data")));
                                row.add(rs.getString("name"));
                                row.add(rs.getString("horario") + ":00");
                                row.add(rs.getString("nome"));
                                row.add(rs.getString("cpf"));
                                row.add(rs.getString("telefone"));
                                row.add(formatter.format(rs.getDate("dtmarcacao")));
                                row.add(rs.getString("hmarcacao"));
                                row.add(rs.getString("operador"));
                                row.add(rs.getString("inicioatendimento"));
                                row.add(Util.milisecondsToString(rs.getInt("duracao")));
                                panel.getAtendimentosRealizadosModel().addRow(row.toArray());
                                row.clear();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AcompanharUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void frAtendimentosRealizadosBtExcluir(AtendimentosRealizadosUI panel) {

        int[] atendimentoId = panel.getSelectedAtendimentoId();
        int deletedValues = 0;
        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 0; i < atendimentoId.length; i++) {
            deletedValues += em.createNamedQuery("Agendamento.deleteById").
                    setParameter("id", atendimentoId[i]).executeUpdate();
        }
        if (!(deletedValues == atendimentoId.length)) {
            JOptionPane.showMessageDialog(panel, "Falha ao excluir registros");
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public EntityManagerFactory ctrlCreateEntityManagerFactory() {

        Map<String, String> props = new HashMap<String, String>();

        props.put(PersistenceUnitProperties.JDBC_USER, getDbUsername());
        props.put(PersistenceUnitProperties.JDBC_PASSWORD, getDbPassword());
        props.put(PersistenceUnitProperties.JDBC_URL, getDbUrl());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhotoSchedulerPU", props);
        return emf;

    }

    public void frTimeRestrictionUILoadTextFields(TimeRestrictionUI panel) {

        ResultSet rs = getCon().doSelect("SELECT * FROM servidores WHERE name='"
                + panel.getCbAtendente().getSelectedItem() + "'");
        try {
            if (rs.next()) {
                panel.getTfInicioExpediente().setText(rs.getString("inicioexp"));
                panel.getTfFinalExpediente().setText(rs.getString("finalexp"));
                panel.getTfSaidaAlmoco().setText(rs.getString("inicioalmoco"));
                panel.getTfRetornoAlmoco().setText(rs.getString("finalalmoco"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void frAcompanharUIRegistrarTempoAtendimento(AcompanharUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Agendamento agendamento = em.find(Agendamento.class, getDto());
        Long inicioAtendimento;
        Long finalAtendimento;

        agendamento.setInicioatendimento(new Date());
        inicioAtendimento = System.currentTimeMillis();
        JOptionPane.showMessageDialog(frame, "Confirme apenas quando finalizar o atendimento");
        finalAtendimento = System.currentTimeMillis();

        agendamento.setDuracao((int) (finalAtendimento - inicioAtendimento));

        em.getTransaction().begin();
        em.persist(agendamento);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    public void frAcompanharUIAddTimer(final AcompanharUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Servidores servidor = (Servidores) em.createNamedQuery("Servidores.findByName").
                setParameter("name", frame.getCbMaquina().getSelectedItem().toString()).
                getSingleResult();

        Timer t = new Timer(1000 * 60 * Integer.parseInt(servidor.getServico()) / 4,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frAcompanharUIUpdateDetranTableModel(frame);
                    }
                });
        t.start();
    }

    public void frCadastrarUICheckPermission(CadastrarUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Usuarios usuario = em.find(Usuarios.class, getId());


        if (usuario.getPermissao().contains("P")) {
            frame.getChbPrioritario().setVisible(true);
        }

        em.close();
        emf.close();

    }

    public void frAcompanharUICheckPermission(AcompanharUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Usuarios usuario = em.find(Usuarios.class, getId());


        if (usuario.getPermissao().contains("E")) {
            frame.getBtExcluir().setEnabled(true);
        }

        em.close();
        emf.close();

    }

    public void frServerStatisticsUICreateTable(ServersStatisticsUI panel) {


        panel.getEstatisticasModel().addColumn("Atendente");
        panel.getEstatisticasModel().addColumn("Atend. no Dia");
        panel.getEstatisticasModel().addColumn("Desist. no Dia");
        panel.getEstatisticasModel().addColumn("Duração Média (Dia)");
        panel.getEstatisticasModel().addColumn("Atend. no Mês");
        panel.getEstatisticasModel().addColumn("Desist. no Mês");
        panel.getEstatisticasModel().addColumn("Duração Media (Mês)");

    }

    public void frServerStatisticsUIRefreshTable(ServersStatisticsUI panel) {

        if (getCon() != null) {
            if (getCon().getCon() != null) {
                try {
                    if (!getCon().getCon().isClosed()) {

                        ArrayList<String> row = new ArrayList<String>();
                        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
                        EntityManager em = emf.createEntityManager();
                        List<Servidores> listServidores =
                                em.createNamedQuery("Servidores.findAllOrderByName").getResultList();
                        Object avg;
                        String[] split;

                        int size = panel.getEstatisticasModel().getRowCount();
                        for (int i = size - 1; (size > 0) && (i >= 0); i--) {
                            panel.getEstatisticasModel().removeRow(0);
                        }
                        //                      
                        for (Iterator<Servidores> it = listServidores.iterator(); it.hasNext();) {
                            Servidores atendente = it.next();

                            ResultSet rs;

                            // Coluna 1: Nome do Atendente
                            row.add(atendente.getName());
                            // Coluna 2: Atendimentos por Data e Servidor
                            row.add(em.createNamedQuery("Agendamento.countFindByDataAndServidorAndStatus").
                                    setParameter("data", panel.getDcDateChooser().getSelectedDate().getTime()).
                                    setParameter("status", 'A').
                                    setParameter("servidor", atendente.getId()).getSingleResult().toString());
                            // Coluna 3: Desistencia por  Data e Servidor
                            row.add(em.createNamedQuery("Agendamento.countFindByDataAndServidorAndStatus").
                                    setParameter("data", panel.getDcDateChooser().getSelectedDate().getTime()).
                                    setParameter("status", 'D').
                                    setParameter("servidor", atendente.getId()).getSingleResult().toString());
                            // Coluna 4: Tempo medio de atendimento por Data e Servidor
                            avg = em.createNamedQuery("Agendamento.avgFindByDataAndServidor").
                                    setParameter("data", panel.getDcDateChooser().getSelectedDate().getTime()).
                                    setParameter("servidor", atendente.getId()).getSingleResult();
                            if (avg != null) {
                                split = avg.toString().split("\\.");
                                row.add(Util.milisecondsToString(Integer.valueOf(split[0])));
                            } else {
                                row.add("");
                            }
                            // Coluna 5: Atendimentos por Mes e Servidor
                            // Neste caso NamedQueries nao funcionam, pois precisa usar EXTRACT (funcao especifica
                            // do PostgreSQL)
                            rs = getCon().doSelect("SELECT COUNT(a) FROM Agendamento a WHERE EXTRACT(month "
                                    + "FROM a.data) = " + (panel.getDcDateChooser().getSelectedDate().getTime().getMonth() + 1)
                                    + " AND a.servidor='" + atendente.getId()
                                    + "' AND a.status='A'");
                            rs.next();
                            row.add(rs.getString(1));
                            rs.close();
                            // Coluna 6: Desistencias por Mes e Servidor
                            rs = getCon().doSelect("SELECT COUNT(a) FROM Agendamento a WHERE EXTRACT(month "
                                    + "FROM a.data) = " + (panel.getDcDateChooser().getSelectedDate().getTime().getMonth() + 1)
                                    + " AND a.servidor='" + atendente.getId()
                                    + "' AND a.status='D'");
                            rs.next();
                            row.add(rs.getString(1));
                            rs.close();
                            // Coluna 7: Tempo medio de atendimento por Mes e Servidor
                            rs = getCon().doSelect("SELECT AVG(a.duracao) FROM Agendamento a WHERE EXTRACT(month "
                                    + "FROM a.data) = " + (panel.getDcDateChooser().getSelectedDate().getTime().getMonth() + 1)
                                    + " AND a.servidor='" + atendente.getId()
                                    + "' AND a.status='A'");
                            rs.next();
                            if (rs.getString(1) != null) {
                                split = rs.getString(1).split("\\.");
                                row.add(Util.milisecondsToString(Integer.valueOf(split[0])));
                            } else {
                                row.add("");
                            }
                            panel.getEstatisticasModel().addRow(row.toArray());
                            row.clear();
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void frUserStatisticsUICreateTable(UserStatisticsUI panel) {

        panel.getEstatisticasModel().addColumn("Operador");
        panel.getEstatisticasModel().addColumn("Mesmo dia");
        panel.getEstatisticasModel().addColumn("Futuro");
        panel.getEstatisticasModel().addColumn("Total");

    }

    public void frUserStatisticsUIRefreshTable(UserStatisticsUI panel) {

        ArrayList<String> row = new ArrayList<String>();
        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        List<Usuarios> listUsuarios =
                em.createNamedQuery("Usuarios.findAll").getResultList();

        int size = panel.getEstatisticasModel().getRowCount();
        for (int i = size - 1; (size > 0) && (i >= 0); i--) {
            panel.getEstatisticasModel().removeRow(0);
        }
        for (Usuarios usuario : listUsuarios) {

            row.add(usuario.getName());
            row.add(em.createNamedQuery("Agendamento.countFindByOperadorAndDtmarcacaoEqualsDataAgendamento").
                    setParameter("operador", usuario.getName()).
                    setParameter("dtmarcacao", panel.getDcDateChooser().getSelectedDate().getTime()).
                    getSingleResult().toString());
            row.add(em.createNamedQuery("Agendamento.countFindByOperadorAndDtmarcacaoLeqDataAgendamento").
                    setParameter("operador", usuario.getName()).
                    setParameter("dtmarcacao", panel.getDcDateChooser().getSelectedDate().getTime()).
                    getSingleResult().toString());
            row.add(em.createNamedQuery("Agendamento.countFindByOperadorAndDtmarcacao").
                    setParameter("operador", usuario.getName()).
                    setParameter("dtmarcacao", panel.getDcDateChooser().getSelectedDate().getTime()).
                    getSingleResult().toString());
            panel.getEstatisticasModel().addRow(row.toArray());
            row.clear();

        }
        row.add("** Todos os Operadores **");
        row.add(em.createNamedQuery("Agendamento.countFindByDtmarcacaoEqualsDataAgendamento").
                setParameter("dtmarcacao", panel.getDcDateChooser().getSelectedDate().getTime()).
                getSingleResult().toString());
        row.add(em.createNamedQuery("Agendamento.countFindDtmarcacaoLeqDataAgendamento").
                setParameter("dtmarcacao", panel.getDcDateChooser().getSelectedDate().getTime()).
                getSingleResult().toString());
        row.add(em.createNamedQuery("Agendamento.countFindByDtmarcacao").
                setParameter("dtmarcacao", panel.getDcDateChooser().getSelectedDate().getTime()).
                getSingleResult().toString());
        panel.getEstatisticasModel().addRow(row.toArray());
        row.clear();

    }

    public void frCadastrarUIgetNextAvailableDate(CadastrarUI frame) {
        frame.getDcDataAgendamento().setSelectedDate(Calendar.getInstance());
    }

    public void frAnyFrameSetIconImage(JFrame frame) {
        try {
            frame.setIconImage(ImageIO.read(getClass().getResource("/userinterface/img/Detran.png")));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void frCadastrarUIupdateDcAgendamento(CadastrarUI frame) {
        /*
         * Calculo da proxima data disponivel para agendamento. Assume-se que
         * sabado e domingo nao ha expediente.
         */

        if (frame.getCbHorario().getModel().getSize() == 0) {

            Calendar c = frame.getDcDataAgendamento().getSelectedDate();
            c.add(Calendar.DAY_OF_MONTH,
                    c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ? 3
                    : c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ? 2 : 1);
            frame.getDcDataAgendamento().setSelectedDate(c);

        }
    }

    public void frAcompanharUIBtNoShow(AcompanharUI frame) {

        EntityManagerFactory emf = ctrlCreateEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Agendamento ag = em.find(Agendamento.class, Integer.parseInt(frame.getTbMarcacoes().
                getModel().getValueAt(frame.getTbMarcacoes().getSelectedRow(), 0).toString()));

        ag.setStatus('D');
        ag.setInicioatendimento(new Date());
        ag.setDuracao(0);

        em.getTransaction().begin();
        em.merge(ag);
        em.getTransaction().commit();

        em.close();
        emf.close();

        frAcompanharUIUpdateDetranTableModel(frame);
    }
}