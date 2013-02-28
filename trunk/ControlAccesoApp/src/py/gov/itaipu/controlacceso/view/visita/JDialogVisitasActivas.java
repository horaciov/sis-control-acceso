/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.visita;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.utils.tree.CustomIconRenderer;
import py.gov.itaipu.controlacceso.utils.tree.UtilesArbol;
import py.gov.itaipu.controlacceso.view.administracion.organizacion.JDialogOrganigrama;

/**
 *
 * @author fboy
 */
public class JDialogVisitasActivas extends javax.swing.JDialog {

    private CRUDAction<Organizacion> organizacionAction;
    private Persona persona;
    private Organizacion area;
    private Organizacion areaPadre;
    private Boolean modoBuscador;
    private Object seleccionado;
   
    /**
     * Creates new form JDialogOrganigrama
     */
    public JDialogVisitasActivas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        modoBuscador = false;
        organizacionAction = new CRUDAction<Organizacion>();
        organizacionAction.setEntity(new Organizacion());
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

        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneOrganigrama = new javax.swing.JScrollPane();
        try{
            jTreeOrganigrama = new javax.swing.JTree();
            jTextFieldEmpleadoApellido = new javax.swing.JTextField();
            jTextFieldEmpleadoNombre = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jTextFieldEmpleadoNroDoc = new javax.swing.JTextField();
            jLabel8 = new javax.swing.JLabel();
            jTextFieldAreaNombre = new javax.swing.JTextField();
            jTextFieldEmpleadoOrganizacion = new javax.swing.JTextField();
            jTextFieldAreaPadreNombre = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jTextFieldFiltroArbol = new javax.swing.JTextField();
            jButtonBuscar = new javax.swing.JButton();
            jButtonLimpiar = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("VISITAS DEL DIA");
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowActivated(java.awt.event.WindowEvent evt) {
                    formWindowActivated(evt);
                }
            });
            getContentPane().setLayout(null);

            jLabel6.setText("Apellido:");
            getContentPane().add(jLabel6);
            jLabel6.setBounds(510, 260, 78, 14);

            jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel4.setText("Empleado");
            getContentPane().add(jLabel4);
            jLabel4.setBounds(510, 210, 78, 15);

            jLabel5.setText("Nombre:");
            getContentPane().add(jLabel5);
            jLabel5.setBounds(510, 230, 78, 14);

            DefaultMutableTreeNode root = UtilesArbol.crearArbol("ORGANIGRAMA", true,true,true);
            jTreeOrganigrama = new JTree(root);
            jTreeOrganigrama.setCellRenderer(new CustomIconRenderer());
            jTreeOrganigrama.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    jTreeOrganigramaMousePressed(evt);
                }
            });
            jTreeOrganigrama.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jTreeOrganigramaValueChanged(evt);
                }
            });
            jScrollPaneOrganigrama.setViewportView(jTreeOrganigrama);
        } catch (py.gov.itaipu.controlacceso.model.exception.ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        getContentPane().add(jScrollPaneOrganigrama);
        jScrollPaneOrganigrama.setBounds(20, 36, 480, 470);

        jTextFieldEmpleadoApellido.setEditable(false);
        getContentPane().add(jTextFieldEmpleadoApellido);
        jTextFieldEmpleadoApellido.setBounds(610, 260, 230, 20);

        jTextFieldEmpleadoNombre.setEditable(false);
        getContentPane().add(jTextFieldEmpleadoNombre);
        jTextFieldEmpleadoNombre.setBounds(610, 230, 230, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Area Interna:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(510, 110, 91, 15);

        jLabel7.setText("Nro Documento:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(510, 290, 91, 14);

        jTextFieldEmpleadoNroDoc.setEditable(false);
        getContentPane().add(jTextFieldEmpleadoNroDoc);
        jTextFieldEmpleadoNroDoc.setBounds(610, 290, 230, 20);

        jLabel8.setText("Area:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(510, 320, 78, 14);

        jTextFieldAreaNombre.setEditable(false);
        jTextFieldAreaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAreaNombreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldAreaNombre);
        jTextFieldAreaNombre.setBounds(610, 130, 230, 20);

        jTextFieldEmpleadoOrganizacion.setEditable(false);
        getContentPane().add(jTextFieldEmpleadoOrganizacion);
        jTextFieldEmpleadoOrganizacion.setBounds(610, 320, 230, 20);

        jTextFieldAreaPadreNombre.setEditable(false);
        getContentPane().add(jTextFieldAreaPadreNombre);
        jTextFieldAreaPadreNombre.setBounds(610, 160, 230, 20);

        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(510, 130, 78, 20);

        jLabel3.setText("Area Superior:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(510, 160, 91, 14);

        jLabel9.setText("Buscar");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(510, 40, 60, 20);
        getContentPane().add(jTextFieldFiltroArbol);
        jTextFieldFiltroArbol.setBounds(610, 40, 230, 20);

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscar.setToolTipText("Buscar Persona");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBuscar);
        jButtonBuscar.setBounds(800, 60, 20, 20);

        jButtonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiar.setToolTipText("Buscar Persona");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLimpiar);
        jButtonLimpiar.setBounds(820, 60, 20, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTreeOrganigramaValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeOrganigramaValueChanged
        // TODO add your handling code here:
        if (jTreeOrganigrama.getLastSelectedPathComponent() != null) {
            DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTreeOrganigrama.getLastSelectedPathComponent();
            Object objSel = nodoSeleccionado.getUserObject();
            if (objSel.getClass().getSimpleName().equals("Persona")) {
                persona = (Persona) objSel;
                areaPadre = persona.getOrganizacion();

                jTextFieldEmpleadoApellido.setText(persona.getApellido());
                jTextFieldEmpleadoNombre.setText(persona.getNombre());
                if (persona.getOrganizacion()!=null && !persona.getOrganizacion().getNombre().equals("")) {
                    jTextFieldEmpleadoOrganizacion.setText(persona.getOrganizacion().getNombre());
                }
                jTextFieldEmpleadoNroDoc.setText(persona.getNumeroDocumento());
                jTextFieldAreaPadreNombre.setText("");
                jTextFieldAreaNombre.setText("");

            } else if (objSel.getClass().getSimpleName().equals("Organizacion")) {
                persona = null;
                area = (Organizacion) objSel;
                areaPadre = (Organizacion) objSel;

                jTextFieldAreaNombre.setText(areaPadre.getNombre());
                if (area.getOrganizacionPadre() != null) {
                    jTextFieldAreaPadreNombre.setText(areaPadre.getOrganizacionPadre().getNombre());
                }

                jTextFieldEmpleadoApellido.setText("");
                jTextFieldEmpleadoNombre.setText("");
                jTextFieldEmpleadoOrganizacion.setText("");
                jTextFieldEmpleadoNroDoc.setText("");

            } else {
                persona = null;
                area = null;
                areaPadre = null;
                jTextFieldAreaPadreNombre.setText("");
                jTextFieldAreaNombre.setText("");
                jTextFieldEmpleadoApellido.setText("");
                jTextFieldEmpleadoNombre.setText("");
                jTextFieldEmpleadoOrganizacion.setText("");
                jTextFieldEmpleadoNroDoc.setText("");
            }
        }
    }//GEN-LAST:event_jTreeOrganigramaValueChanged
      
    private void jTextFieldAreaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAreaNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAreaNombreActionPerformed
       
    private void jTreeOrganigramaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeOrganigramaMousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTreeOrganigramaMousePressed

    public Object getSeleccionado() {
        return seleccionado;
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if (modoBuscador) {
        }
    }//GEN-LAST:event_formWindowActivated
    
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        if (jTextFieldFiltroArbol.getText() != null && !jTextFieldFiltroArbol.equals("")) {
            try {
                DefaultMutableTreeNode root = UtilesArbol.crearArbolFiltrado("ORGANIGRAMA", jTextFieldFiltroArbol.getText().toUpperCase(), true);
                jTreeOrganigrama = new JTree(root);
                jTreeOrganigrama.setCellRenderer(new CustomIconRenderer());
                jTreeOrganigrama.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                    public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                        jTreeOrganigramaValueChanged(evt);
                    }
                });
                jScrollPaneOrganigrama.setViewportView(jTreeOrganigrama);
            } catch (ErrorInesperado ei) {
                JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        // TODO add your handling code here:
        try {
            jTextFieldFiltroArbol.setText("");
            DefaultMutableTreeNode root = UtilesArbol.crearArbol("ORGANIGRAMA", true, true, true);
            jTreeOrganigrama = new JTree(root);
            jTreeOrganigrama.setCellRenderer(new CustomIconRenderer());
            jTreeOrganigrama.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jTreeOrganigramaValueChanged(evt);
                }
            });
            jScrollPaneOrganigrama.setViewportView(jTreeOrganigrama);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    
    

    public Boolean getModoBuscador() {
        return modoBuscador;
    }

    public void setModoBuscador(Boolean modoBuscador) {
        this.modoBuscador = modoBuscador;
    }

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
            java.util.logging.Logger.getLogger(JDialogOrganigrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogOrganigrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogOrganigrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogOrganigrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogOrganigrama dialog = new JDialogOrganigrama(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPaneOrganigrama;
    private javax.swing.JTextField jTextFieldAreaNombre;
    private javax.swing.JTextField jTextFieldAreaPadreNombre;
    private javax.swing.JTextField jTextFieldEmpleadoApellido;
    private javax.swing.JTextField jTextFieldEmpleadoNombre;
    private javax.swing.JTextField jTextFieldEmpleadoNroDoc;
    private javax.swing.JTextField jTextFieldEmpleadoOrganizacion;
    private javax.swing.JTextField jTextFieldFiltroArbol;
    private javax.swing.JTree jTreeOrganigrama;
    // End of variables declaration//GEN-END:variables
}
