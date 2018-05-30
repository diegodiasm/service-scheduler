/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.Timer;
import model.Confguiche;
import model.Senha;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 *
 * @author Diego Dias
 */
public class PainelUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private DisplayMode dispModeOld;
    private boolean fullscreen;
    private ArrayDeque<Senha> exibicao = new ArrayDeque<Senha>();
    private String aviso;
    private int inicio = 0;
    private String avisomq;
    private int elapsedTicketShowTime = 0;
    private boolean bannerIsActive = false;
    private boolean showUltimas = false;
    private ArrayDeque<Senha> ultimas = new ArrayDeque<Senha>();
    private Confguiche confguiche;

    /**
     * Creates new form PainelUI
     */
    public PainelUI() {

        loadParams();
        EntityManagerFactory emf = ctrlCreateEntityManager();
        EntityManager em = emf.createEntityManager();
        confguiche = (Confguiche) em.createNamedQuery("Confguiche.findAll").getSingleResult();
        em.refresh(confguiche);

        initComponents();
        Timer tExibicaoSenha;
        Timer tConsultaSenha;
        Timer tConsultaAviso;
        Timer tMarqueeAviso;




        tExibicaoSenha = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                EntityManagerFactory emf = ctrlCreateEntityManager();
                EntityManager em = emf.createEntityManager();
                Confguiche confguiche =
                        (Confguiche) em.createNamedQuery("Confguiche.findAll").getSingleResult();
                em.refresh(confguiche);
                if ((elapsedTicketShowTime++ >= confguiche.getTexibicao())
                        || (lbSenha.getText().equals("---"))) {
                    if (!exibicao.isEmpty()) {
                        Senha current = exibicao.removeFirst();
                        lbSenha.setText(String.valueOf(formatSenha(current.getNumeracao())));
                        lbGuiche.setText(current.getGuiche());
                        if (ultimas.size() < confguiche.getNultimas()) {
                            if (!ultimas.contains(current)) {
                                ultimas.addFirst(current);
                            }
                        } else {
                            if (!ultimas.contains(current)) {
                                ultimas.removeLast();
                                ultimas.addFirst(current);
                            }
                        }
                        AudioClip clip = Applet.newAudioClip(PainelUI.class.getResource(confguiche.getSound()));
                        clip.play();
                    } else {
                        lbSenha.setText("---");
                        lbGuiche.setText("---");
                    }
                    elapsedTicketShowTime = 0;
                    lbForSenha.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
                    lbSenha.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
                    lbForGuiche.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
                    lbGuiche.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
                }

                em.close();
                emf.close();
            }
        });

        tConsultaSenha = new Timer(confguiche.getTfreqconsultasenha() * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                EntityManagerFactory emf = ctrlCreateEntityManager();
                EntityManager em = emf.createEntityManager();
                try {
                    Senha current = (Senha) em.createNamedQuery("Senha.findByStatusAndPainel").
                            setParameter("status", 'W').
                            setParameter("painel", true).
                            setMaxResults(1).
                            getSingleResult();
                    em.refresh(current);
                    current.setPainel(false);
                    exibicao.addLast(current);
                    em.getTransaction().begin();
                    em.merge(current);
                    em.getTransaction().commit();
                } catch (Exception e) {
                } finally {
                    em.close();
                    emf.close();
                }
            }
        });

        tConsultaAviso = new Timer(confguiche.getTfreqconsultaaviso() * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!bannerIsActive) {
                    EntityManagerFactory emf = ctrlCreateEntityManager();
                    EntityManager em = emf.createEntityManager();
                    Confguiche confguiche = (Confguiche) em.createNamedQuery("Confguiche.findAll").
                            getSingleResult();
                    em.refresh(confguiche);
                    if (showUltimas) {
                        String avisot = "";
                        for (int i = 0;
                                i < Math.min(confguiche.getNultimas(), ultimas.size());
                                i++) {
                            Senha s = ultimas.removeFirst();
                            avisot += avisot.contains(
                                    " [Senha:" + formatSenha(s.getNumeracao())
                                    + " Guiche: " + s.getGuiche() + "]") ? ""
                                    : " [Senha:" + formatSenha(s.getNumeracao())
                                    + " Guichê: " + s.getGuiche() + "]";
                            ultimas.addLast(s);
                        }
                        aviso = avisot.length() > 0 ? "    Últimas chamadas: " + avisot + "    " : "";
                        lbAnuncio.setForeground(new Color(255, 255, 102));
                    } else {
                        aviso = "    " + confguiche.getAviso() + "       ";
                        lbAnuncio.setForeground(new Color(102, 255, 102));
                    }
                    inicio = 0;
                    avisomq = aviso;
                    showUltimas = !showUltimas;
                    bannerIsActive = true;
                    lbAnuncio.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontaviso()));
                    em.close();
                    emf.close();
                }
            }
        });


        tMarqueeAviso = new Timer(1000 / confguiche.getVelocidadeaviso(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (bannerIsActive) {
                    lbAnuncio.setText(avisomq);
                    inicio = inicio < aviso.length() ? inicio + 1 : 0;
                    avisomq = aviso.substring(inicio, aviso.length());
                    if (avisomq.isEmpty()) {
                        bannerIsActive = false;
                    }
                }
            }
        });

        tConsultaSenha.start();
        tExibicaoSenha.start();
        tConsultaAviso.start();
        tMarqueeAviso.start();

        em.close();
        emf.close();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpChamada = new javax.swing.JPanel();
        lbForGuiche = new javax.swing.JLabel();
        lbForSenha = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        lbGuiche = new javax.swing.JLabel();
        jpAnuncio = new javax.swing.JPanel();
        lbAnuncio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jpChamada.setBackground(new java.awt.Color(0, 0, 0));

        lbForGuiche.setBackground(new java.awt.Color(0, 0, 0));
        lbForGuiche.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
        lbForGuiche.setForeground(new java.awt.Color(255, 255, 255));
        lbForGuiche.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbForGuiche.setText(" Guichê:");
        lbForGuiche.setMaximumSize(new java.awt.Dimension(700, 207));
        lbForGuiche.setMinimumSize(new java.awt.Dimension(700, 207));
        lbForGuiche.setPreferredSize(new java.awt.Dimension(700, 207));

        lbForSenha.setBackground(new java.awt.Color(0, 0, 0));
        lbForSenha.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
        lbForSenha.setForeground(new java.awt.Color(255, 255, 255));
        lbForSenha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbForSenha.setText(" Senha:");
        lbForSenha.setMaximumSize(new java.awt.Dimension(700, 207));
        lbForSenha.setMinimumSize(new java.awt.Dimension(700, 207));
        lbForSenha.setPreferredSize(new java.awt.Dimension(700, 207));

        lbSenha.setBackground(new java.awt.Color(0, 0, 0));
        lbSenha.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
        lbSenha.setForeground(new java.awt.Color(255, 255, 255));
        lbSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSenha.setText("---");
        lbSenha.setMaximumSize(new java.awt.Dimension(400, 207));
        lbSenha.setMinimumSize(new java.awt.Dimension(400, 207));
        lbSenha.setPreferredSize(new java.awt.Dimension(400, 207));

        lbGuiche.setBackground(new java.awt.Color(0, 0, 0));
        lbGuiche.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontsenha()));
        lbGuiche.setForeground(new java.awt.Color(255, 255, 255));
        lbGuiche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGuiche.setText("---");
        lbGuiche.setMaximumSize(new java.awt.Dimension(400, 207));
        lbGuiche.setMinimumSize(new java.awt.Dimension(400, 207));
        lbGuiche.setPreferredSize(new java.awt.Dimension(400, 207));

        javax.swing.GroupLayout jpChamadaLayout = new javax.swing.GroupLayout(jpChamada);
        jpChamada.setLayout(jpChamadaLayout);
        jpChamadaLayout.setHorizontalGroup(
            jpChamadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChamadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpChamadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbForSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbForGuiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpChamadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGuiche, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpChamadaLayout.setVerticalGroup(
            jpChamadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChamadaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpChamadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbForSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpChamadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbForGuiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGuiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpAnuncio.setBackground(new java.awt.Color(102, 102, 102));

        lbAnuncio.setBackground(new java.awt.Color(255, 255, 255));
        lbAnuncio.setFont(new java.awt.Font("Tahoma", 0, confguiche.getFontaviso()));
        lbAnuncio.setForeground(new java.awt.Color(102, 255, 102));
        lbAnuncio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAnuncio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbAnuncio.setMaximumSize(new java.awt.Dimension(249, 109));
        lbAnuncio.setMinimumSize(new java.awt.Dimension(249, 109));
        lbAnuncio.setPreferredSize(new java.awt.Dimension(249, 109));
        lbAnuncio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnuncioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpAnuncioLayout = new javax.swing.GroupLayout(jpAnuncio);
        jpAnuncio.setLayout(jpAnuncioLayout);
        jpAnuncioLayout.setHorizontalGroup(
            jpAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAnuncioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAnuncio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpAnuncioLayout.setVerticalGroup(
            jpAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAnuncioLayout.createSequentialGroup()
                .addComponent(lbAnuncio, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpAnuncio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpChamada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpChamada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jpAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbAnuncioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnuncioMouseClicked
        if (fullscreen) {
            setFullscreen(false);
        } else {
            setFullscreen(true);
        }
    }//GEN-LAST:event_lbAnuncioMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;






                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PainelUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PainelUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PainelUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PainelUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PainelUI().setVisible(true);
            }
        });
    }

    /**
     * Method allows changing whether this window is displayed in fullscreen or
     * windowed mode.
     *
     * @param fullscreen true = change to fullscreen, false = change to windowed
     */
    public void setFullscreen(boolean fullscreen) {
        //get a reference to the device.
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dispMode = device.getDisplayMode();
        //save the old display mode before changing it.
        dispModeOld = device.getDisplayMode();

        if (this.fullscreen != fullscreen) { //are we actually changing modes.
            //change modes.
            this.fullscreen = fullscreen;
            // toggle fullscreen mode
            if (!fullscreen) {
                //change to windowed mode.
                //set the display mode back to the what it was when
                //the program was launched.
                device.setDisplayMode(dispModeOld);
                //hide the frame so we can change it.
                setVisible(false);
                //remove the frame from being displayable.
                dispose();
                //put the borders back on the frame.
                setUndecorated(false);
                //needed to unset this window as the fullscreen window.
                device.setFullScreenWindow(null);
                //recenter window
                setLocationRelativeTo(null);
                setResizable(true);

                //reset the display mode to what it was before
                //we changed it.
                setVisible(true);

            } else { //change to fullscreen.
                //hide everything
                setVisible(false);
                //remove the frame from being displayable.
                dispose();
                //remove borders around the frame
                setUndecorated(true);
                //make the window fullscreen.
                device.setFullScreenWindow(this);
                //attempt to change the screen resolution.
                device.setDisplayMode(dispMode);
                setResizable(false);
                setAlwaysOnTop(false);
                //show the frame
                setVisible(true);
            }
            //make sure that the screen is refreshed.
            repaint();
        }
    }

    private String formatTime(int h, int m) {

        String hPrint = (h >= 10) ? "" + h : "0" + h;
        String mPrint = (m >= 10) ? "" + m : "0" + m;

        return hPrint + ":" + mPrint;
    }

    private String formatSenha(Integer senha) {
        return ((senha < 10) ? "00" + senha
                : (senha < 100) ? "0" + senha
                : String.valueOf(senha));
    }

    public EntityManagerFactory ctrlCreateEntityManager() {

        Map<String, String> props = new HashMap<String, String>();

        props.put(PersistenceUnitProperties.JDBC_USER, getDbUsername());
        props.put(PersistenceUnitProperties.JDBC_PASSWORD, getDbPassword());
        props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://" + getDbHostname()
                + ":5432/" + getDbName());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PainelSenhaPU", props);

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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpAnuncio;
    private javax.swing.JPanel jpChamada;
    private javax.swing.JLabel lbAnuncio;
    private javax.swing.JLabel lbForGuiche;
    private javax.swing.JLabel lbForSenha;
    private javax.swing.JLabel lbGuiche;
    private javax.swing.JLabel lbSenha;
    // End of variables declaration//GEN-END:variables
    private static String dbHostname = "";
    private static String dbUsername = "";
    private static String dbPassword = "";
    private static String dbName = "";
    private static String propertiesFile = "PhotoScheduler.ini";
}
