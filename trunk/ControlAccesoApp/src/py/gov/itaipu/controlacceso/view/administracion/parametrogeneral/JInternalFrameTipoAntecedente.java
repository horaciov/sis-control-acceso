/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.administracion.parametrogeneral;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.model.TipoAntecedente;

/**
 *
 * @author fboy
 */
public class JInternalFrameTipoAntecedente extends javax.swing.JInternalFrame {

     private CRUDAction<TipoAntecedente> tipoAntecedenteAction;
     private TipoAntecedente tipoAntecedente;
    
    
    /**
     * Creates new form JInternalFrameTipoAntecedente
     */
    public JInternalFrameTipoAntecedente() {
        setClosable(true);
        tipoAntecedenteAction = new CRUDAction<TipoAntecedente>();
        tipoAntecedenteAction.setEntity(new TipoAntecedente());
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

        listaTipoAntecedente = ObservableCollections.observableList(tipoAntecedenteAction.findAll());
        jDialogNuevoTipoAntecedente = new javax.swing.JDialog();
        jButtonGuardar = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelNombreDescripcion = new javax.swing.JLabel();
        jScrollPaneDescripcion = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTiposAntecedentes = new javax.swing.JTable();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonEliminar1 = new javax.swing.JButton();
        jButtonVer = new javax.swing.JButton();

        jDialogNuevoTipoAntecedente.setTitle("Nuevo Tipo Antecedente");
        jDialogNuevoTipoAntecedente.setAlwaysOnTop(true);
        jDialogNuevoTipoAntecedente.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        jDialogNuevoTipoAntecedente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialogNuevoTipoAntecedente.setResizable(false);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jLabelNombre.setText("Nombre");

        jLabelNombreDescripcion.setText("Descripcion");

        jScrollPaneDescripcion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jTextAreaDescripcion.setMargin(new java.awt.Insets(2, 2, 0, 0));
        jScrollPaneDescripcion.setViewportView(jTextAreaDescripcion);

        javax.swing.GroupLayout jDialogNuevoTipoAntecedenteLayout = new javax.swing.GroupLayout(jDialogNuevoTipoAntecedente.getContentPane());
        jDialogNuevoTipoAntecedente.getContentPane().setLayout(jDialogNuevoTipoAntecedenteLayout);
        jDialogNuevoTipoAntecedenteLayout.setHorizontalGroup(
            jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNuevoTipoAntecedenteLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardar)
                    .addGroup(jDialogNuevoTipoAntecedenteLayout.createSequentialGroup()
                        .addGroup(jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelNombreDescripcion))
                        .addGap(59, 59, 59)
                        .addGroup(jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNombre)
                            .addComponent(jScrollPaneDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jDialogNuevoTipoAntecedenteLayout.setVerticalGroup(
            jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNuevoTipoAntecedenteLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre))
                .addGap(18, 18, 18)
                .addGroup(jDialogNuevoTipoAntecedenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogNuevoTipoAntecedenteLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabelNombreDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPaneDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonGuardar)
                .addContainerGap())
        );

        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Tipo Antecedente");

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaTipoAntecedente, jTableTiposAntecedentes);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descripcion}"));
        columnBinding.setColumnName("Descripcion");
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableTiposAntecedentes);

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

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/edit.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setActionCommand("editar");
        jButtonEditar.setMaximumSize(new java.awt.Dimension(89, 25));
        jButtonEditar.setMinimumSize(new java.awt.Dimension(89, 25));
        jButtonEditar.setPreferredSize(new java.awt.Dimension(89, 25));
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/delete.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setPreferredSize(new java.awt.Dimension(95, 25));
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/delete.png"))); // NOI18N
        jButtonEliminar1.setText("Eliminar");
        jButtonEliminar1.setPreferredSize(new java.awt.Dimension(95, 25));
        jButtonEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar1ActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(214, 214, 214)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(214, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(159, 159, 159)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(142, Short.MAX_VALUE)))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        // TODO add your handling code here:
         tipoAntecedente = new TipoAntecedente();   
         JDialogoTipoAntecedente dialogoNuevo = new JDialogoTipoAntecedente(null, closable);
         dialogoNuevo.setTipoAntecedente(tipoAntecedente);
         dialogoNuevo.setVisible(true);
         listaTipoAntecedente.clear();
         listaTipoAntecedente.addAll(tipoAntecedenteAction.findAll());
        
         
         
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
            tipoAntecedente.setNombre(jTextFieldNombre.getText());
            tipoAntecedente.setDescripcion(jTextAreaDescripcion.getText());
            tipoAntecedenteAction.setEntity(tipoAntecedente);
            tipoAntecedenteAction.crear();
            jDialogNuevoTipoAntecedente.setVisible(false);
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
         if(jTableTiposAntecedentes.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Tipo de Antecedente","Error",0);
            return;
        } 
        tipoAntecedente = (TipoAntecedente) listaTipoAntecedente.get(jTableTiposAntecedentes.getSelectedRow());
         JDialogoTipoAntecedente dialogoNuevo = new JDialogoTipoAntecedente(null, closable);
         dialogoNuevo.setTipoAntecedente(tipoAntecedente);
         dialogoNuevo.setVisible(true);
         listaTipoAntecedente.clear();
         listaTipoAntecedente.addAll(tipoAntecedenteAction.findAll());
        
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar1ActionPerformed
        // TODO add your handling code here:
         if(jTableTiposAntecedentes.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Tipo de Antecedente","Error",0);
            return;
        }
        if(JOptionPane.showConfirmDialog(this,"Está seguro que desea eliminar?","Eliminar Tipo Antecedente",0)!=0)
            return;       
        TipoAntecedente ta=(TipoAntecedente) listaTipoAntecedente.get(jTableTiposAntecedentes.getSelectedRow());
        tipoAntecedenteAction.setEntity(ta);
        tipoAntecedenteAction.eliminar();
        listaTipoAntecedente.clear();
        listaTipoAntecedente.addAll(tipoAntecedenteAction.findAll());
        JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente","Info",1);
    }//GEN-LAST:event_jButtonEliminar1ActionPerformed

    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        if(jTableTiposAntecedentes.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Tipo de Antecedente","Error",0);
            return;
        }
         tipoAntecedente = (TipoAntecedente) listaTipoAntecedente.get(jTableTiposAntecedentes.getSelectedRow());
         JDialogoTipoAntecedente dialogoNuevo = new JDialogoTipoAntecedente(null, closable);
         dialogoNuevo.setTipoAntecedente(tipoAntecedente);
         dialogoNuevo.setReadOnly(true);
         dialogoNuevo.setVisible(true);
                
      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminar1;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JDialog jDialogNuevoTipoAntecedente;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreDescripcion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDescripcion;
    private javax.swing.JTable jTableTiposAntecedentes;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextField jTextFieldNombre;
    private java.util.List listaTipoAntecedente;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public TipoAntecedente getTipoAntecedente() {
        return tipoAntecedente;
    }

    public void setTipoAntecedente(TipoAntecedente tipoAntecedente) {
        this.tipoAntecedente = tipoAntecedente;
    }


}