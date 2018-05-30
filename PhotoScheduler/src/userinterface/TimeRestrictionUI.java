/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import controller.Controller;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Dias
 */
public class TimeRestrictionUI extends javax.swing.JPanel {

    /**
     * Creates new form TimeRestrictionUI
     */
    public TimeRestrictionUI() {

        Controller ctrl = Controller.getInstance();

        initComponents();
        ctrl.frTimeRestrictionUILoadAtendentes(this);
        ctrl.frTimeRestrictionsUICreateTable(this);
        ctrl.frTimeRestrictionsUIRefreshTable(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dTimeRestriction = new javax.swing.JDialog();
        dcTimeRestriction = new datechooser.beans.DateChooserPanel();
        chbNaoHaveraExpediente = new javax.swing.JCheckBox();
        tfInicioExpediente = new javax.swing.JTextField();
        lbInicioExpediente = new javax.swing.JLabel();
        lbFinalExpediente = new javax.swing.JLabel();
        tfFinalExpediente = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        lbSaidaAlmoco = new javax.swing.JLabel();
        tfSaidaAlmoco = new javax.swing.JTextField();
        cbAtendente = new javax.swing.JComboBox();
        lbAtendente = new javax.swing.JLabel();
        lbRetornoAlmoco = new javax.swing.JLabel();
        lbMotivo = new javax.swing.JLabel();
        tfRetornoAlmoco = new javax.swing.JTextField();
        tfMotivo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTimeRestrictions = new javax.swing.JTable() {
            private static final long serialVersion = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btAdicionar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();

        dTimeRestriction.setTitle("Adicionar Restrição");
        dTimeRestriction.setModal(true);
        dTimeRestriction.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                dTimeRestrictionWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dTimeRestrictionWindowOpened(evt);
            }
        });

        dcTimeRestriction.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
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
    dcTimeRestriction.setLocale(new java.util.Locale("pt", "BR", ""));
    dcTimeRestriction.setCurrentNavigateIndex(1);

    chbNaoHaveraExpediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    chbNaoHaveraExpediente.setText("Não haverá expediente");
    chbNaoHaveraExpediente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            chbNaoHaveraExpedienteActionPerformed(evt);
        }
    });

    tfInicioExpediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

    lbInicioExpediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbInicioExpediente.setText("Início do Expediente:");

    lbFinalExpediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbFinalExpediente.setText("Final do Expediente:");

    tfFinalExpediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    tfFinalExpediente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            tfFinalExpedienteActionPerformed(evt);
        }
    });

    btSalvar.setText("Salvar");
    btSalvar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btSalvarActionPerformed(evt);
        }
    });

    lbSaidaAlmoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbSaidaAlmoco.setText("Saída para almoço:");

    tfSaidaAlmoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

    cbAtendente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    cbAtendente.setModel(atendentesDisponiveis);
    cbAtendente.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cbAtendenteItemStateChanged(evt);
        }
    });
    cbAtendente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbAtendenteActionPerformed(evt);
        }
    });

    lbAtendente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbAtendente.setText("Atendente:");

    lbRetornoAlmoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbRetornoAlmoco.setText("Retorno do almoço:");

    lbMotivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbMotivo.setText("Descrição (motivo):");

    tfRetornoAlmoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    tfRetornoAlmoco.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            tfRetornoAlmocoActionPerformed(evt);
        }
    });

    tfMotivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

    javax.swing.GroupLayout dTimeRestrictionLayout = new javax.swing.GroupLayout(dTimeRestriction.getContentPane());
    dTimeRestriction.getContentPane().setLayout(dTimeRestrictionLayout);
    dTimeRestrictionLayout.setHorizontalGroup(
        dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dTimeRestrictionLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dTimeRestrictionLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btSalvar)
                    .addGap(113, 113, 113))
                .addGroup(dTimeRestrictionLayout.createSequentialGroup()
                    .addComponent(dcTimeRestriction, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 10, Short.MAX_VALUE))))
        .addGroup(dTimeRestrictionLayout.createSequentialGroup()
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dTimeRestrictionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbInicioExpediente)
                        .addComponent(lbFinalExpediente)
                        .addComponent(lbSaidaAlmoco)
                        .addComponent(lbAtendente)
                        .addComponent(lbRetornoAlmoco)
                        .addComponent(lbMotivo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbAtendente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfSaidaAlmoco)
                        .addComponent(tfFinalExpediente, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addComponent(tfRetornoAlmoco)
                        .addComponent(tfMotivo)
                        .addComponent(tfInicioExpediente)))
                .addGroup(dTimeRestrictionLayout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(chbNaoHaveraExpediente)))
            .addGap(0, 0, Short.MAX_VALUE))
    );
    dTimeRestrictionLayout.setVerticalGroup(
        dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dTimeRestrictionLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dcTimeRestriction, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbAtendente))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfInicioExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbInicioExpediente))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbFinalExpediente)
                .addComponent(tfFinalExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfSaidaAlmoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbSaidaAlmoco))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbRetornoAlmoco)
                .addComponent(tfRetornoAlmoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(dTimeRestrictionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbMotivo)
                .addComponent(tfMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(chbNaoHaveraExpediente)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
            .addComponent(btSalvar)
            .addContainerGap())
    );

    addComponentListener(new java.awt.event.ComponentAdapter() {
        public void componentShown(java.awt.event.ComponentEvent evt) {
            formComponentShown(evt);
        }
    });

    tbTimeRestrictions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    tbTimeRestrictions.setModel(restricoesTableModel);
    tbTimeRestrictions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tbTimeRestrictions.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tbTimeRestrictions);

    btAdicionar.setText("Adicionar");
    btAdicionar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btAdicionarActionPerformed(evt);
        }
    });

    btExcluir.setText("Excluir");
    btExcluir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btExcluirActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btAdicionar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btExcluir)
            .addContainerGap())
        .addComponent(jScrollPane1)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btExcluir)
                .addComponent(btAdicionar))
            .addContainerGap())
    );
    }// </editor-fold>//GEN-END:initComponents

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        if (getCbAtendente().getModel() != null) {
            if (getCbAtendente().getModel().getSize() == 0) {
                cleanFields();
            }
        }
        getdTimeRestriction().pack();
        getdTimeRestriction().setVisible(true);
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void chbNaoHaveraExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbNaoHaveraExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbNaoHaveraExpedienteActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        Controller ctrl = Controller.getInstance();
        ctrl.frTimeRestrictionUIBtSalvar(this);
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfFinalExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFinalExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFinalExpedienteActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if (getTbTimeRestrictions().getSelectedRowCount() > 0) {

            Controller ctrl = Controller.getInstance();
            int line = Math.max(getTbTimeRestrictions().getSelectedRow(), 1);
            ctrl.frTimeRestrictionUIBtExcluir(this);
            ctrl.frTimeRestrictionsUIRefreshTable(this);
            if (getTbTimeRestrictions().getRowCount() > 0) {
                getTbTimeRestrictions().requestFocus();
                getTbTimeRestrictions().setRowSelectionInterval(line - 1, line - 1);
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void tfRetornoAlmocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRetornoAlmocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRetornoAlmocoActionPerformed

    private void cbAtendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAtendenteActionPerformed
        if (getCbAtendente().getModel() != null) {
            if (getCbAtendente().getModel().getSize() > 0) {

                Controller ctrl = Controller.getInstance();
                ctrl.frTimeRestrictionUILoadTextFields(this);
            }
        }
    }//GEN-LAST:event_cbAtendenteActionPerformed

    private void cbAtendenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAtendenteItemStateChanged
    }//GEN-LAST:event_cbAtendenteItemStateChanged

    private void dTimeRestrictionWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dTimeRestrictionWindowOpened
    }//GEN-LAST:event_dTimeRestrictionWindowOpened

    private void dTimeRestrictionWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dTimeRestrictionWindowActivated
        if (getCbAtendente().getModel() != null) {
            if (getCbAtendente().getModel().getSize() > 0) {

                Controller ctrl = Controller.getInstance();
                ctrl.frTimeRestrictionUILoadTextFields(this);
            }
        }
    }//GEN-LAST:event_dTimeRestrictionWindowActivated

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        Controller ctrl = Controller.getInstance();
        
        ctrl.frTimeRestrictionUILoadAtendentes(this);
        ctrl.frTimeRestrictionsUIRefreshTable(this);
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbAtendente;
    private javax.swing.JCheckBox chbNaoHaveraExpediente;
    private javax.swing.JDialog dTimeRestriction;
    private datechooser.beans.DateChooserPanel dcTimeRestriction;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAtendente;
    private javax.swing.JLabel lbFinalExpediente;
    private javax.swing.JLabel lbInicioExpediente;
    private javax.swing.JLabel lbMotivo;
    private javax.swing.JLabel lbRetornoAlmoco;
    private javax.swing.JLabel lbSaidaAlmoco;
    private javax.swing.JTable tbTimeRestrictions;
    private javax.swing.JTextField tfFinalExpediente;
    private javax.swing.JTextField tfInicioExpediente;
    private javax.swing.JTextField tfMotivo;
    private javax.swing.JTextField tfRetornoAlmoco;
    private javax.swing.JTextField tfSaidaAlmoco;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel restricoesTableModel = new DefaultTableModel();
    private DefaultComboBoxModel atendentesDisponiveis = new DefaultComboBoxModel();

    /**
     * @return the chbNaoHaveraExpediente
     */
    public javax.swing.JCheckBox getChbNaoHaveraExpediente() {
        return chbNaoHaveraExpediente;
    }

    /**
     * @return the dateChooserPanel1
     */
    public datechooser.beans.DateChooserPanel getDcTimeRestriction() {
        return dcTimeRestriction;
    }

    /**
     * @return the tfFinalExpediente
     */
    public javax.swing.JTextField getTfFinalExpediente() {
        return tfFinalExpediente;
    }

    /**
     * @return the tfInicioExpediente
     */
    public javax.swing.JTextField getTfInicioExpediente() {
        return tfInicioExpediente;
    }

    /**
     * @return the dTimeRestriction
     */
    public javax.swing.JDialog getdTimeRestriction() {
        return dTimeRestriction;
    }

    /**
     * @return the jTable1
     */
    public javax.swing.JTable getTbTimeRestrictions() {
        return tbTimeRestrictions;
    }

    /**
     * @return the restricoesTableModel
     */
    public DefaultTableModel getRestricoesTableModel() {
        return restricoesTableModel;
    }

    /**
     * @return the tfMotivo
     */
    public javax.swing.JTextField getTfMotivo() {
        return tfMotivo;
    }

    public void cleanFields() {
        tfFinalExpediente.setText("");
        tfInicioExpediente.setText("");
        getTfSaidaAlmoco().setText("");
        getTfRetornoAlmoco().setText("");
        chbNaoHaveraExpediente.setSelected(false);
    }

    /**
     * @return the atendentesDisponiveis
     */
    public DefaultComboBoxModel getAtendentesDisponiveis() {
        return atendentesDisponiveis;
    }

    /**
     * @return the cbAtendente
     */
    public javax.swing.JComboBox getCbAtendente() {
        return cbAtendente;
    }

    /**
     * @return the tfRetornoAlmoco
     */
    public javax.swing.JTextField getTfRetornoAlmoco() {
        return tfRetornoAlmoco;
    }

    /**
     * @return the tfSaidaAlmoco
     */
    public javax.swing.JTextField getTfSaidaAlmoco() {
        return tfSaidaAlmoco;
    }
}