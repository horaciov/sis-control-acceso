/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.administracion.parametrogeneral;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import py.gov.itaipu.controlacceso.action.administracion.parametrogeneral.CRUDAction;
import py.gov.itaipu.controlacceso.model.Nacionalidad;

/**
 *
 * @author fboy
 */
public class JInternalFrameNacionalidad extends javax.swing.JInternalFrame {

     private CRUDAction<Nacionalidad> nacionalidadAction;
     private Nacionalidad nacionalidad;
    
    
    /**
     * Creates new form JInternalFrameNacionalidad
     */
    public JInternalFrameNacionalidad() {
        setClosable(true);
        nacionalidadAction = new CRUDAction<Nacionalidad>();
        nacionalidadAction.setEntity(new Nacionalidad());
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

        listaNacionalidad = ObservableCollections.observableList(nacionalidadAction.findAll());
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNacionalidad = new javax.swing.JTable();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar1 = new javax.swing.JButton();
        jButtonVer = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("Nacionalidad");

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaNacionalidad, jTableNacionalidad);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descripcion}"));
        columnBinding.setColumnName("Descripcion");
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableNacionalidad);

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
                .addContainerGap(15, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        // TODO add your handling code here:
         nacionalidad = new Nacionalidad();   
         JDialogoNacionalidad dialogoNuevo = new JDialogoNacionalidad(null, closable);
         dialogoNuevo.setNacionalidad(nacionalidad);
         dialogoNuevo.setTitle("Nueva Nacionalidad");
         dialogoNuevo.setVisible(true);
         listaNacionalidad.clear();
         listaNacionalidad.addAll(nacionalidadAction.findAll());
        
         
         
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
        if(jTableNacionalidad.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Nacionalidad","Error",0);
            return;
        } 
         nacionalidad = (Nacionalidad) listaNacionalidad.get(jTableNacionalidad.getSelectedRow());
         JDialogoNacionalidad dialogoNuevo = new JDialogoNacionalidad(null, closable);
         dialogoNuevo.setNacionalidad(nacionalidad);
         dialogoNuevo.setTitle("Editar Nacionalidad");
         dialogoNuevo.setVisible(true);
         listaNacionalidad.clear();
         listaNacionalidad.addAll(nacionalidadAction.findAll());
        
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar1ActionPerformed
        // TODO add your handling code here:
        if(jTableNacionalidad.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Nacionalidad","Error",0);
            return;
        }
        if(JOptionPane.showConfirmDialog(this,"Está seguro que desea eliminar?","Eliminar Nacionalidad",0)!=0)
            return;       
        Nacionalidad ta=(Nacionalidad) listaNacionalidad.get(jTableNacionalidad.getSelectedRow());
        nacionalidadAction.setEntity(ta);
        nacionalidadAction.eliminar();
        listaNacionalidad.clear();
        listaNacionalidad.addAll(nacionalidadAction.findAll());
        JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente","Info",1);
    }//GEN-LAST:event_jButtonEliminar1ActionPerformed

    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        if(jTableNacionalidad.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Nacionalidad","Error",0);
            return;
        }
         nacionalidad = (Nacionalidad) listaNacionalidad.get(jTableNacionalidad.getSelectedRow());
         JDialogoNacionalidad dialogoNuevo = new JDialogoNacionalidad(null, closable);
         dialogoNuevo.setNacionalidad(nacionalidad);
         dialogoNuevo.setTitle("Ver Nacionalidad");
         dialogoNuevo.setReadOnly(true);
         dialogoNuevo.setVisible(true);
                
      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar1;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNacionalidad;
    private java.util.List listaNacionalidad;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


}
