/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.administracion.parametrogeneral;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.model.Estado;
import py.gov.itaipu.controlacceso.model.Motivo;
import py.gov.itaipu.controlacceso.model.TipoDocumento;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class JInternalFrameTipoDocumento extends javax.swing.JInternalFrame {

    private CRUDAction<TipoDocumento> tipoDocumentoAction;

    /**
     * Creates new form JInternalFrameMotivo
     */
    public JInternalFrameTipoDocumento() {
        setClosable(true);
        tipoDocumentoAction = new CRUDAction<TipoDocumento>();
        tipoDocumentoAction.setEntity(new TipoDocumento());
        initComponents();

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

        try{
            listTipos = ObservableCollections.observableList(tipoDocumentoAction.findAll());
            jScrollPane1 = new javax.swing.JScrollPane();
            jTableTipos = new javax.swing.JTable();
            jButtonNuevo = new javax.swing.JButton();
            jButtonEditar = new javax.swing.JButton();
            jButtonCerrar = new javax.swing.JButton();
            jButtonEliminar = new javax.swing.JButton();
            jSeparator1 = new javax.swing.JSeparator();
            jLabel1 = new javax.swing.JLabel();
            jButtonVer = new javax.swing.JButton();

        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        setTitle("Gestión de tipos de documentos");

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listTipos, jTableTipos);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Tipo de Documento");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descripcion}"));
        columnBinding.setColumnName("Descripcion");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableTipos);
        jTableTipos.getColumnModel().getColumn(0).setMinWidth(200);
        jTableTipos.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableTipos.getColumnModel().getColumn(0).setMaxWidth(200);
        jTableTipos.getColumnModel().getColumn(1).setMinWidth(400);
        jTableTipos.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTableTipos.getColumnModel().getColumn(1).setMaxWidth(400);

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/new.jpg"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/edit.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/exit.png"))); // NOI18N
        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/delete.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipos de Documentos");

        jButtonVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonVer.setText("Ver");
        jButtonVer.setMaximumSize(new java.awt.Dimension(89, 25));
        jButtonVer.setMinimumSize(new java.awt.Dimension(89, 25));
        jButtonVer.setPreferredSize(new java.awt.Dimension(89, 25));
        jButtonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonVer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevo)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonCerrar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Gestión de estados");

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        // TODO add your handling code here:    
        try {
            JDialogTipoDocumento dialogTipoDocumento = new JDialogTipoDocumento(null, closable);
            dialogTipoDocumento.setTipoDocumento(new TipoDocumento());
            dialogTipoDocumento.setVisible(true);
            listTipos.clear();
            listTipos.addAll(tipoDocumentoAction.findAll());
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
        try {
            if (jTableTipos.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo", "Error", 0);
                return;
            }
            TipoDocumento t = (TipoDocumento) listTipos.get(jTableTipos.getSelectedRow());
            JDialogTipoDocumento dialogTipoDocumento = new JDialogTipoDocumento(null, closable);
            dialogTipoDocumento.setTipoDocumento(t);
            dialogTipoDocumento.setVisible(true);
            listTipos.clear();
            listTipos.addAll(tipoDocumentoAction.findAll());
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        try {
            if (jTableTipos.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo", "Error", 0);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Está seguro que desea eliminar?", "Eliminar Tipo de Documento", 0) != 0) {
                return;
            }
            TipoDocumento t = (TipoDocumento) listTipos.get(jTableTipos.getSelectedRow());
            tipoDocumentoAction.setEntity(t);
            tipoDocumentoAction.eliminar();
            listTipos.clear();
            listTipos.addAll(tipoDocumentoAction.findAll());
            JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente", "Info", 1);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        // TODO add your handling code here:
        if (jTableTipos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo", "Error", 0);
            return;
        }
        TipoDocumento t = (TipoDocumento) listTipos.get(jTableTipos.getSelectedRow());
        JDialogTipoDocumento dialogTipoDocumento = new JDialogTipoDocumento(null, closable);
        dialogTipoDocumento.setTipoDocumento(t);
        dialogTipoDocumento.setReadOnly(true);
        dialogTipoDocumento.setVisible(true);
    }//GEN-LAST:event_jButtonVerActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTipos;
    private java.util.List listTipos;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
