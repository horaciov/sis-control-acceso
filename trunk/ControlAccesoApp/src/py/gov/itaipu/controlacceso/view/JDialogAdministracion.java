/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import py.gov.itaipu.controlacceso.action.seguridad.AutenticadorAction;
import py.gov.itaipu.controlacceso.model.Rol;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;
import py.gov.itaipu.controlacceso.view.administracion.organizacion.JDialogOrganigrama;
import py.gov.itaipu.controlacceso.view.persona.JDialogPersonaPrincipal;
import py.gov.itaipu.controlacceso.view.seguridad.JDialogSeguridadMant;
import py.gov.itaipu.controlacceso.view.visita.JDialogConsultaVisitas;

/**
 *
 * @author vimartih
 */
public class JDialogAdministracion extends javax.swing.JDialog {

    /**
     * Creates new form JDialogAdministracion
     */
    public JDialogAdministracion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/resource/img/bandera-paraguay.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReportes = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonVisitas = new javax.swing.JButton();
        jButtonOrganigrama = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonVisitantes = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabelPersonaVisitada5 = new javax.swing.JLabel();
        jButtonSeguridad = new javax.swing.JButton();
        jLabelSeguridad = new javax.swing.JLabel();
        jButtonAbout = new javax.swing.JButton();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administración del sistema");
        getContentPane().setLayout(null);

        jButtonReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/logo_itaipu.png"))); // NOI18N
        jButtonReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportesActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReportes);
        jButtonReportes.setBounds(370, 60, 66, 57);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Acerca de");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(370, 120, 65, 14);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Visitas");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(190, 120, 65, 14);

        jButtonVisitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/personas.jpeg"))); // NOI18N
        jButtonVisitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitasActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVisitas);
        jButtonVisitas.setBounds(190, 60, 65, 57);

        jButtonOrganigrama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/organigrama.png"))); // NOI18N
        jButtonOrganigrama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrganigramaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonOrganigrama);
        jButtonOrganigrama.setBounds(100, 60, 70, 57);

        jLabel2.setText("Organigrama");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 120, 80, 14);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Visitantes");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(19, 120, 60, 14);

        jButtonVisitantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/ficha.png"))); // NOI18N
        jButtonVisitantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitantesActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVisitantes);
        jButtonVisitantes.setBounds(20, 60, 63, 57);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(10, 43, 430, 10);

        jLabelPersonaVisitada5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPersonaVisitada5.setText("Administración del sistema.");
        getContentPane().add(jLabelPersonaVisitada5);
        jLabelPersonaVisitada5.setBounds(10, 11, 236, 26);

        jButtonSeguridad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/security.jpeg"))); // NOI18N
        jButtonSeguridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeguridadActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSeguridad);
        jButtonSeguridad.setBounds(280, 60, 65, 57);

        jLabelSeguridad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSeguridad.setText("Seguridad");
        getContentPane().add(jLabelSeguridad);
        jLabelSeguridad.setBounds(280, 120, 65, 14);

        jButtonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon_about.png"))); // NOI18N
        jButtonAbout.setToolTipText("Acerca de...");
        jButtonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAboutActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAbout);
        jButtonAbout.setBounds(400, 0, 20, 20);

        jButtonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/help.jpeg"))); // NOI18N
        jButtonAyuda.setToolTipText("Ayuda");
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAyuda);
        jButtonAyuda.setBounds(420, 0, 20, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportesActionPerformed
        // TODO add your handling code here:
        JDialogAbout about = new JDialogAbout(null, true);
        WindowUtil.centerWindow(about);
        about.setVisible(true);
    }//GEN-LAST:event_jButtonReportesActionPerformed

    private void jButtonVisitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitasActionPerformed
        // TODO add your handling code here:
        JDialogConsultaVisitas visitas = new JDialogConsultaVisitas(null, false);
        visitas.setSize(1290,660);
        WindowUtil.centerWindow(visitas);
        visitas.setVisible(true);
    }//GEN-LAST:event_jButtonVisitasActionPerformed

    private void jButtonOrganigramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrganigramaActionPerformed
        // TODO add your handling code here:
        JDialogOrganigrama dialogOrganigrama = new JDialogOrganigrama(null, true);
        dialogOrganigrama.setSize(860,560);
        WindowUtil.centerWindow(dialogOrganigrama);
        dialogOrganigrama.setVisible(true);
    }//GEN-LAST:event_jButtonOrganigramaActionPerformed

    private void jButtonVisitantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitantesActionPerformed
        // TODO add your handling code here:
        JDialogPersonaPrincipal dialogPersona = new JDialogPersonaPrincipal(null, false);
        dialogPersona.setSize(1200, 665);
        WindowUtil.centerWindow(dialogPersona);
        dialogPersona.setVisible(true);
    }//GEN-LAST:event_jButtonVisitantesActionPerformed

    private void jButtonSeguridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeguridadActionPerformed
        // TODO add your handling code here:
        Rol rolSeguridad = new Rol();
        rolSeguridad.setNombre("SEGURIDAD SISTEMA");
        if (AutenticadorAction.getUsuarioConectado().getRoles().indexOf(rolSeguridad) > -1) {
             JDialogSeguridadMant jdsm = new JDialogSeguridadMant(null, rootPaneCheckingEnabled);
             jdsm.setSize(900, 320);
             WindowUtil.centerWindow(jdsm);
             jdsm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No cuenta con el rol de seguridad", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_jButtonSeguridadActionPerformed

    private void jButtonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAboutActionPerformed
        // TODO add your handling code here:
        JDialogAbout about = new JDialogAbout(null, true);
        WindowUtil.centerWindow(about);
        about.setVisible(true);
    }//GEN-LAST:event_jButtonAboutActionPerformed

    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        // TODO add your handling code here:
        if (Desktop.isDesktopSupported()) {
            try {
                java.io.File file = new java.io.File("");   //Dummy file
                String abspath = file.getAbsolutePath();
                File myFile = new File(abspath + "/manuales/SCAModuloAdministrativo.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
                JOptionPane.showMessageDialog(null, "El manual no se encuentra, verifique con el administrador del sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonAyudaActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogAdministracion dialog = new JDialogAdministracion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonAbout;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonOrganigrama;
    private javax.swing.JButton jButtonReportes;
    private javax.swing.JButton jButtonSeguridad;
    private javax.swing.JButton jButtonVisitantes;
    private javax.swing.JButton jButtonVisitas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelPersonaVisitada5;
    private javax.swing.JLabel jLabelSeguridad;
    private javax.swing.JSeparator jSeparator6;
    // End of variables declaration//GEN-END:variables
}
