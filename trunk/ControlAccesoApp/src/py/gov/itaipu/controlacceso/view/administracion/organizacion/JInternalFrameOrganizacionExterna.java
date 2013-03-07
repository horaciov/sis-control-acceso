/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.administracion.organizacion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;

/**
 *
 * @author vimartih
 */
public class JInternalFrameOrganizacionExterna extends javax.swing.JInternalFrame {

    private CRUDAction<Organizacion> organizacionAction;
    private Organizacion organizacionSeleccionada;
    private Boolean modoBuscador;

    /**
     * Creates new form JInternalFrameOrganizacion
     */
    public JInternalFrameOrganizacionExterna() {
        setClosable(true);
        modoBuscador = false;
        organizacionAction = new CRUDAction<Organizacion>();
        organizacionAction.setEntity(new Organizacion());
        initComponents();

    }

    public Boolean getModoBuscador() {
        return modoBuscador;
    }

    public void setModoBuscador(Boolean modoBuscador) {
        this.modoBuscador = modoBuscador;
    }

    public Organizacion getOrganizacionSeleccionada() {
        return organizacionSeleccionada;
    }

    public void setOrganizacionSeleccionada(Organizacion organizacionSeleccionada) {
        this.organizacionSeleccionada = organizacionSeleccionada;
    }

    public JTextField getjTextFieldOrganizacion() {
        return jTextFieldOrganizacion;
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
            listOrganizaciones = ObservableCollections.observableList(organizacionAction.findByNamedQuery("Organizacion.findAllExterna"));
            jScrollPane1 = new javax.swing.JScrollPane();
            jTableOrganizaciones = new javax.swing.JTable();
            jButtonNuevo = new javax.swing.JButton();
            jButtonEditar = new javax.swing.JButton();
            jButtonCerrar = new javax.swing.JButton();
            jButtonEliminar = new javax.swing.JButton();
            jSeparator1 = new javax.swing.JSeparator();
            jLabel1 = new javax.swing.JLabel();
            jButtonVer = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            jTextFieldOrganizacion = new javax.swing.JTextField();

        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        setTitle("Gestión de organizaciones externas");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(null);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listOrganizaciones, jTableOrganizaciones);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Organización");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descripcion}"));
        columnBinding.setColumnName("Descripcion");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTableOrganizaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOrganizacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableOrganizaciones);
        jTableOrganizaciones.getColumnModel().getColumn(0).setMinWidth(300);
        jTableOrganizaciones.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTableOrganizaciones.getColumnModel().getColumn(0).setMaxWidth(400);
        jTableOrganizaciones.getColumnModel().getColumn(1).setMinWidth(400);
        jTableOrganizaciones.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTableOrganizaciones.getColumnModel().getColumn(1).setMaxWidth(400);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(6, 129, 595, 310);

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/new.jpg"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setMaximumSize(new java.awt.Dimension(89, 25));
        jButtonNuevo.setMinimumSize(new java.awt.Dimension(89, 25));
        jButtonNuevo.setPreferredSize(new java.awt.Dimension(89, 25));
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonNuevo);
        jButtonNuevo.setBounds(330, 90, 100, 25);

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/edit.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setMaximumSize(new java.awt.Dimension(89, 25));
        jButtonEditar.setMinimumSize(new java.awt.Dimension(89, 25));
        jButtonEditar.setPreferredSize(new java.awt.Dimension(89, 25));
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEditar);
        jButtonEditar.setBounds(230, 90, 100, 25);

        jButtonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/exit.png"))); // NOI18N
        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.setMaximumSize(new java.awt.Dimension(89, 25));
        jButtonCerrar.setMinimumSize(new java.awt.Dimension(89, 25));
        jButtonCerrar.setPreferredSize(new java.awt.Dimension(95, 25));
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCerrar);
        jButtonCerrar.setBounds(430, 90, 100, 25);

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/delete.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setPreferredSize(new java.awt.Dimension(95, 25));
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEliminar);
        jButtonEliminar.setBounds(130, 90, 100, 25);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(6, 27, 575, 12);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Organizaciones");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 92, 15);

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
        getContentPane().add(jButtonVer);
        jButtonVer.setBounds(30, 90, 100, 25);

        jLabel2.setText("Organización:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 50, 120, 16);

        jTextFieldOrganizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldOrganicacionKeyRealeased(evt);
            }
        });
        getContentPane().add(jTextFieldOrganizacion);
        jTextFieldOrganizacion.setBounds(110, 50, 420, 28);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        // TODO add your handling code here:    
            JDialogOrganizacionExterna dialogOrganizacion = new JDialogOrganizacionExterna(null, true);
            Organizacion o=new Organizacion();
            o.setNombre(jTextFieldOrganizacion.getText());
            dialogOrganizacion.setOrganizacion(o);
            WindowUtil.centerWindow(dialogOrganizacion);
            dialogOrganizacion.setVisible(true);
            listOrganizaciones.clear();
            listOrganizaciones.add(o);
        

    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
        try {
            if (jTableOrganizaciones.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una organización", "Error", 0);
                return;
            }
            Organizacion m = (Organizacion) listOrganizaciones.get(jTableOrganizaciones.getSelectedRow());
            JDialogOrganizacionExterna dialogOrganizacion = new JDialogOrganizacionExterna(null, closable);
            dialogOrganizacion.setOrganizacion(m);
            dialogOrganizacion.setVisible(true);
            listOrganizaciones.clear();
            listOrganizaciones.addAll(organizacionAction.findByNamedQuery("Organizacion.findAllExterna"));
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        try {
            if (jTableOrganizaciones.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una organización", "Error", 0);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Está seguro que desea eliminar?", "Eliminar Organización", 0) != 0) {
                return;
            }
            Organizacion m = (Organizacion) listOrganizaciones.get(jTableOrganizaciones.getSelectedRow());
            organizacionAction.setEntity(m);
            organizacionAction.eliminar();
            listOrganizaciones.clear();
            listOrganizaciones.addAll(organizacionAction.findByNamedQuery("Organizacion.findAllExterna"));
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
        if (jTableOrganizaciones.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una organización", "Error", 0);
            return;
        }
        Organizacion m = (Organizacion) listOrganizaciones.get(jTableOrganizaciones.getSelectedRow());
        JDialogOrganizacionExterna dialogOrganizacion = new JDialogOrganizacionExterna(null, closable);
        dialogOrganizacion.setOrganizacion(m);
        dialogOrganizacion.setReadOnly(true);
        dialogOrganizacion.setVisible(true);
    }//GEN-LAST:event_jButtonVerActionPerformed

    private void jTableOrganizacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrganizacionesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && modoBuscador) {
            int row = jTableOrganizaciones.getSelectedRow();
            if (row > -1) {
                organizacionSeleccionada = (Organizacion) listOrganizaciones.get(row);
            }
            this.dispose();
        }

    }//GEN-LAST:event_jTableOrganizacionesMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        if (modoBuscador) {
            jButtonEditar.setVisible(false);
            jButtonEliminar.setVisible(false);
            jButtonNuevo.setVisible(true);
            jButtonVer.setVisible(false);
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void jTextFieldOrganicacionKeyRealeased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldOrganicacionKeyRealeased
        try {
            // TODO add your handling code here:
            listOrganizaciones.clear();
            listOrganizaciones.addAll(organizacionAction.findAllByNameWithCondition(jTextFieldOrganizacion.getText(),"tipoOrganizacion='EXTERNA'"));
        } catch (ErrorInesperado ex) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jTextFieldOrganicacionKeyRealeased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableOrganizaciones;
    private javax.swing.JTextField jTextFieldOrganizacion;
    private java.util.List listOrganizaciones;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
