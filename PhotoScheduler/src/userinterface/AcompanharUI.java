/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import controller.Controller;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Connector;

/**
 *
 * @author Diego Dias
 */
public class AcompanharUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

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
     * Creates new form AcompanharUI
     */
    public AcompanharUI(Connector con) {

        final Controller ctrl = Controller.getInstance();

        setCon(con);
        initComponents();



        ctrl.frAcompanharUIUpdateCbMaquinaModel(this);
        ctrl.frAcompanharUIUpdateDetranTableModel(this);
        ctrl.frAcompanharUIAddTimer(this);
        getBtExcluir().setEnabled(false);
        ctrl.frAcompanharUICheckPermission(this);
        ctrl.frAnyFrameSetIconImage(this);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pmOpcoes = new javax.swing.JPopupMenu();
        miBtImprimir = new javax.swing.JMenuItem();
        miBtNoShow = new javax.swing.JMenuItem();
        lbSizeDefinition = new javax.swing.JLabel();
        spTbMarcacoes = new javax.swing.JScrollPane();
        tbMarcacoes = new javax.swing.JTable(){

            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jpBotoes = new javax.swing.JPanel();
        jpVisualizar = new javax.swing.JPanel();
        cbMaquina = new javax.swing.JComboBox();
        btAtenderMarcados = new javax.swing.JButton();
        btMenuPrincipal = new javax.swing.JButton();
        dcDateChooserAcompanharUI = new datechooser.beans.DateChooserCombo();
        btExcluir = new javax.swing.JButton();
        jpStatus = new javax.swing.JPanel();
        lbTotalInfo = new javax.swing.JLabel();
        lbAtendidosInfo = new javax.swing.JLabel();
        lbPendentesInfo = new javax.swing.JLabel();
        lbDesistentesInfo = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        lbAtendidos = new javax.swing.JLabel();
        lbPendentes = new javax.swing.JLabel();
        lbDesistentes = new javax.swing.JLabel();

        miBtImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/img/print_24.png"))); // NOI18N
        miBtImprimir.setMnemonic('I');
        miBtImprimir.setText("Imprimir");
        miBtImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBtImprimirActionPerformed(evt);
            }
        });
        pmOpcoes.add(miBtImprimir);

        miBtNoShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/img/noshow.png"))); // NOI18N
        miBtNoShow.setMnemonic('D');
        miBtNoShow.setText("Desistente");
        miBtNoShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBtNoShowActionPerformed(evt);
            }
        });
        pmOpcoes.add(miBtNoShow);

        lbSizeDefinition.setText("000");

        setTitle("PhotoScheduler - Acompanhamento");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tbMarcacoes.setModel(detranTableModel);
        tbMarcacoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbMarcacoes.getTableHeader().setReorderingAllowed(false);
        tbMarcacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMarcacoesMouseClicked(evt);
            }
        });
        tbMarcacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMarcacoesKeyPressed(evt);
            }
        });
        tbMarcacoes.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                tbMarcacoesVetoableChange(evt);
            }
        });
        spTbMarcacoes.setViewportView(tbMarcacoes);

        jpVisualizar.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualizar"));

        cbMaquina.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbMaquina.setModel(cbMaquinaModel);
        cbMaquina.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMaquinaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpVisualizarLayout = new javax.swing.GroupLayout(jpVisualizar);
        jpVisualizar.setLayout(jpVisualizarLayout);
        jpVisualizarLayout.setHorizontalGroup(
            jpVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVisualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbMaquina, 0, 133, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpVisualizarLayout.setVerticalGroup(
            jpVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVisualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btAtenderMarcados.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btAtenderMarcados.setText("Atender");
        btAtenderMarcados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtenderMarcadosActionPerformed(evt);
            }
        });

        btMenuPrincipal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btMenuPrincipal.setText("Menu Principal");
        btMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuPrincipalActionPerformed(evt);
            }
        });

        dcDateChooserAcompanharUI.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dcDateChooserAcompanharUI.setFormat(2);
    dcDateChooserAcompanharUI.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
    dcDateChooserAcompanharUI.setLocale(new java.util.Locale("pt", "BR", ""));
    dcDateChooserAcompanharUI.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
    dcDateChooserAcompanharUI.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dcDateChooserAcompanharUIOnSelectionChange(evt);
        }
    });

    btExcluir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    btExcluir.setText("Excluir");
    btExcluir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btExcluirActionPerformed(evt);
        }
    });

    jpStatus.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

    lbTotalInfo.setText("Total:");

    lbAtendidosInfo.setText("Atendidos:");

    lbPendentesInfo.setText("Pendentes:");

    lbDesistentesInfo.setText("Desistentes:");

    org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${maximumSize}"), lbTotal, org.jdesktop.beansbinding.BeanProperty.create("maximumSize"));
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${minimumSize}"), lbTotal, org.jdesktop.beansbinding.BeanProperty.create("minimumSize"));
    bindingGroup.addBinding(binding);

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${maximumSize}"), lbAtendidos, org.jdesktop.beansbinding.BeanProperty.create("maximumSize"));
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${minimumSize}"), lbAtendidos, org.jdesktop.beansbinding.BeanProperty.create("minimumSize"));
    bindingGroup.addBinding(binding);

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${maximumSize}"), lbPendentes, org.jdesktop.beansbinding.BeanProperty.create("maximumSize"));
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${minimumSize}"), lbPendentes, org.jdesktop.beansbinding.BeanProperty.create("minimumSize"));
    bindingGroup.addBinding(binding);

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${maximumSize}"), lbDesistentes, org.jdesktop.beansbinding.BeanProperty.create("maximumSize"));
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lbSizeDefinition, org.jdesktop.beansbinding.ELProperty.create("${minimumSize}"), lbDesistentes, org.jdesktop.beansbinding.BeanProperty.create("minimumSize"));
    bindingGroup.addBinding(binding);

    javax.swing.GroupLayout jpStatusLayout = new javax.swing.GroupLayout(jpStatus);
    jpStatus.setLayout(jpStatusLayout);
    jpStatusLayout.setHorizontalGroup(
        jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpStatusLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbAtendidosInfo)
                .addComponent(lbTotalInfo))
            .addGap(9, 9, 9)
            .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbTotal)
                .addComponent(lbAtendidos))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbPendentesInfo)
                .addComponent(lbDesistentesInfo))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbDesistentes)
                .addComponent(lbPendentes))
            .addContainerGap())
    );
    jpStatusLayout.setVerticalGroup(
        jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpStatusLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbTotalInfo)
                .addComponent(lbTotal)
                .addComponent(lbPendentesInfo)
                .addComponent(lbPendentes))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbAtendidosInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbAtendidos)
                .addComponent(lbDesistentesInfo)
                .addComponent(lbDesistentes))
            .addContainerGap())
    );

    javax.swing.GroupLayout jpBotoesLayout = new javax.swing.GroupLayout(jpBotoes);
    jpBotoes.setLayout(jpBotoesLayout);
    jpBotoesLayout.setHorizontalGroup(
        jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpBotoesLayout.createSequentialGroup()
            .addComponent(jpStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jpVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btAtenderMarcados, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dcDateChooserAcompanharUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(22, Short.MAX_VALUE))
    );
    jpBotoesLayout.setVerticalGroup(
        jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpBotoesLayout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBotoesLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dcDateChooserAcompanharUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btExcluir))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btAtenderMarcados)
                        .addComponent(btMenuPrincipal))
                    .addGap(20, 20, 20))
                .addGroup(jpBotoesLayout.createSequentialGroup()
                    .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpVisualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(spTbMarcacoes)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(22, Short.MAX_VALUE)
            .addComponent(jpBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(spTbMarcacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jpBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMenuPrincipalActionPerformed
        Controller ctrl = Controller.getInstance();
        ctrl.frAcompanharUIBtMenuPrincipalActionPerformed(this);
    }//GEN-LAST:event_btMenuPrincipalActionPerformed

    private void btAtenderMarcadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtenderMarcadosActionPerformed
        Controller ctrl = Controller.getInstance();
        ctrl.frAcompanharUIBtAtenderMarcadosActionPerformed(this);

    }//GEN-LAST:event_btAtenderMarcadosActionPerformed

    private void tbMarcacoesVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_tbMarcacoesVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMarcacoesVetoableChange

    private void dcDateChooserAcompanharUIOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dcDateChooserAcompanharUIOnSelectionChange

        Controller ctrl = Controller.getInstance();
        ctrl.frAcompanharUIUpdateDetranTableModel(this);
    }//GEN-LAST:event_dcDateChooserAcompanharUIOnSelectionChange

    private void cbMaquinaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMaquinaItemStateChanged
        Controller ctrl = Controller.getInstance();
        ctrl.frAcompanharUIUpdateDetranTableModel(this);
    }//GEN-LAST:event_cbMaquinaItemStateChanged

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed

        int line = Math.max(getTbMarcacoes().getSelectedRow(), 1);
        if (getTbMarcacoes().getSelectedRowCount() > 0) {
            Controller ctrl = Controller.getInstance();
            ctrl.frAcompanharUIBtExcluir(this);

            if (getTbMarcacoes().getRowCount() > 0) {
                getTbMarcacoes().requestFocus();
                getTbMarcacoes().setRowSelectionInterval(line - 1, line - 1);
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void tbMarcacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMarcacoesMouseClicked
        if (evt.getButton() == evt.BUTTON3) {
            pmOpcoes.show(this, this.getMousePosition().x, this.getMousePosition().y);
        }
    }//GEN-LAST:event_tbMarcacoesMouseClicked

    private void miBtImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBtImprimirActionPerformed
        SimpleDateFormat formatterMonth = new SimpleDateFormat("dd 'de' MMMM'/'yyyy");
        MessageFormat header = new MessageFormat("Relação de Agendamentos: "
                + formatterMonth.format(
                getDcDateChooserAcompanharUI().getSelectedDate().getTime()));
        MessageFormat footer = new MessageFormat("[Atendente:" + getCbMaquina().getSelectedItem() + "]     Página {0}");
        try {

            pmOpcoes.setVisible(false);
            getTbMarcacoes().print(JTable.PrintMode.FIT_WIDTH, header, footer,
                    true, null,
                    true, null);
        } catch (PrinterException ex) {
            Logger.getLogger(UserStatisticsUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_miBtImprimirActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    }//GEN-LAST:event_formMouseClicked

    private void tbMarcacoesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMarcacoesKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_CONTEXT_MENU) {
           pmOpcoes.show(this, getTbMarcacoes().getX(), getTbMarcacoes().getY()); 
        }

    }//GEN-LAST:event_tbMarcacoesKeyPressed

    private void miBtNoShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBtNoShowActionPerformed
        
        Controller ctrl = Controller.getInstance();
        ctrl.frAcompanharUIBtNoShow(this);
        
    }//GEN-LAST:event_miBtNoShowActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AcompanharUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcompanharUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcompanharUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcompanharUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AcompanharUI(getCon()).setVisible(true);
            }
        });
    }

    /**
     * @return the tbMarcacoes
     */
    public javax.swing.JTable getTbMarcacoes() {
        return tbMarcacoes;
    }

    /**
     * @return the detranTableModel
     */
    public DefaultTableModel getDetranTableModel() {
        return detranTableModel;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtenderMarcados;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btMenuPrincipal;
    private javax.swing.JComboBox cbMaquina;
    private datechooser.beans.DateChooserCombo dcDateChooserAcompanharUI;
    private javax.swing.JPanel jpBotoes;
    private javax.swing.JPanel jpStatus;
    private javax.swing.JPanel jpVisualizar;
    private javax.swing.JLabel lbAtendidos;
    private javax.swing.JLabel lbAtendidosInfo;
    private javax.swing.JLabel lbDesistentes;
    private javax.swing.JLabel lbDesistentesInfo;
    private javax.swing.JLabel lbPendentes;
    private javax.swing.JLabel lbPendentesInfo;
    private javax.swing.JLabel lbSizeDefinition;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTotalInfo;
    private javax.swing.JMenuItem miBtImprimir;
    private javax.swing.JMenuItem miBtNoShow;
    private javax.swing.JPopupMenu pmOpcoes;
    private javax.swing.JScrollPane spTbMarcacoes;
    private javax.swing.JTable tbMarcacoes;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    private static Connector con;
    private DefaultTableModel detranTableModel = new DefaultTableModel();
    private DefaultComboBoxModel cbMaquinaModel = new DefaultComboBoxModel();

    /**
     * @param tbMarcacoes the tbMarcacoes to set
     */
    public void setTbMarcacoes(javax.swing.JTable tbMarcacoes) {
        this.tbMarcacoes = tbMarcacoes;
    }

    /**
     * @param detranTableModel the detranTableModel to set
     */
    public void setDetranTableModel(DefaultTableModel detranTableModel) {
        this.detranTableModel = detranTableModel;
    }

    /**
     * @return the dcDateChooserAcompanharUI
     */
    public datechooser.beans.DateChooserCombo getDcDateChooserAcompanharUI() {
        return dcDateChooserAcompanharUI;
    }

    /**
     * @return the cbMaquina
     */
    public javax.swing.JComboBox getCbMaquina() {
        return cbMaquina;
    }

    /**
     * @param cbMaquina the cbMaquina to set
     */
    public void setCbMaquina(javax.swing.JComboBox cbMaquina) {
        this.cbMaquina = cbMaquina;
    }

    /**
     * @return the cbMaquinaModel
     */
    public DefaultComboBoxModel getCbMaquinaModel() {
        return cbMaquinaModel;
    }

    /**
     * @param cbMaquinaModel the cbMaquinaModel to set
     */
    public void setCbMaquinaModel(DefaultComboBoxModel cbMaquinaModel) {
        this.cbMaquinaModel = cbMaquinaModel;
    }

    public int getAgendamentoId() {
        return Integer.parseInt(getTbMarcacoes().getModel().
                getValueAt(getTbMarcacoes().getSelectedRow(), 0).toString());
    }

    /**
     * @return the lbAtendidos
     */
    public javax.swing.JLabel getLbAtendidos() {
        return lbAtendidos;
    }

    /**
     * @return the lbPendentes
     */
    public javax.swing.JLabel getLbPendentes() {
        return lbPendentes;
    }

    /**
     * @return the lbTotal
     */
    public javax.swing.JLabel getLbTotal() {
        return lbTotal;
    }

    /**
     * @return the btExcluir
     */
    public javax.swing.JButton getBtExcluir() {
        return btExcluir;
    }

    /**
     * @return the btAtenderMarcados
     */
    public javax.swing.JButton getBtAtenderMarcados() {
        return btAtenderMarcados;
    }

    /**
     * @return the lbDesistentes
     */
    public javax.swing.JLabel getLbDesistentes() {
        return lbDesistentes;
    }
}
