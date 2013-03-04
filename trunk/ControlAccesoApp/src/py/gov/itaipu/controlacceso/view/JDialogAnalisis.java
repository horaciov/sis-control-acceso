/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;
import py.gov.itaipu.controlacceso.view.reportes.JDialogRepPersonasNoGratas;
import py.gov.itaipu.controlacceso.view.reportes.JDialogRepVisitasActivas;
import py.gov.itaipu.controlacceso.view.reportes.JDialogRepVisitasAreaPersona;
import py.gov.itaipu.controlacceso.view.reportes.JDialogRepVisitasFecha;
import py.gov.itaipu.controlacceso.view.reportes.JDialogRepVisitasGeneral;
import py.gov.itaipu.controlacceso.view.reportes.JDialogRepVisitasVisitante;

/**
 *
 * @author vimartih
 */
public class JDialogAnalisis extends javax.swing.JDialog {

    /**
     * Creates new form JDialogAnalisis
     */
    public JDialogAnalisis(java.awt.Frame parent, boolean modal) {
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

        jLabelPersonaVisitada4 = new javax.swing.JLabel();
        jButtonReporteGeneral = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonReporteVisitasPorPersona2 = new javax.swing.JButton();
        jButtonReporteVisitasPorPersona1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButtonReporteVisitasPorVisitante = new javax.swing.JButton();
        jButtonReporteVisitasPorArea = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButtonReporteVisitasPorPersona3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButtonAbout = new javax.swing.JButton();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabelPersonaVisitada4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPersonaVisitada4.setText("Informes");
        getContentPane().add(jLabelPersonaVisitada4);
        jLabelPersonaVisitada4.setBounds(10, 11, 271, 26);

        jButtonReporteGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-doc.gif"))); // NOI18N
        jButtonReporteGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteGeneralActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReporteGeneral);
        jButtonReporteGeneral.setBounds(10, 59, 31, 25);

        jLabel5.setText("General");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(45, 70, 128, 14);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(10, 38, 414, 10);

        jLabel8.setText("Visitas por Rango Fecha");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(248, 70, 176, 14);

        jLabel9.setText("Personas no Gratas");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(248, 101, 128, 14);

        jButtonReporteVisitasPorPersona2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-doc.gif"))); // NOI18N
        jButtonReporteVisitasPorPersona2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteVisitasPorPersona2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReporteVisitasPorPersona2);
        jButtonReporteVisitasPorPersona2.setBounds(213, 90, 31, 25);

        jButtonReporteVisitasPorPersona1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-doc.gif"))); // NOI18N
        jButtonReporteVisitasPorPersona1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteVisitasPorPersona1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReporteVisitasPorPersona1);
        jButtonReporteVisitasPorPersona1.setBounds(213, 59, 31, 25);

        jLabel6.setText("Visitas por Visitante");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(45, 101, 128, 14);

        jButtonReporteVisitasPorVisitante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-doc.gif"))); // NOI18N
        jButtonReporteVisitasPorVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteVisitasPorVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReporteVisitasPorVisitante);
        jButtonReporteVisitasPorVisitante.setBounds(10, 90, 31, 25);

        jButtonReporteVisitasPorArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-doc.gif"))); // NOI18N
        jButtonReporteVisitasPorArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteVisitasPorAreaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReporteVisitasPorArea);
        jButtonReporteVisitasPorArea.setBounds(10, 121, 31, 25);

        jLabel7.setText("Visitas por Area/Persona");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(45, 132, 177, 14);

        jButtonReporteVisitasPorPersona3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-doc.gif"))); // NOI18N
        jButtonReporteVisitasPorPersona3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteVisitasPorPersona3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReporteVisitasPorPersona3);
        jButtonReporteVisitasPorPersona3.setBounds(213, 121, 31, 25);

        jLabel10.setText("Visitas Activas");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(250, 130, 128, 14);

        jButtonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon_about.png"))); // NOI18N
        jButtonAbout.setToolTipText("Acerca de...");
        jButtonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAboutActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAbout);
        jButtonAbout.setBounds(380, 10, 20, 20);

        jButtonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/help.jpeg"))); // NOI18N
        jButtonAyuda.setToolTipText("Ayuda");
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAyuda);
        jButtonAyuda.setBounds(400, 10, 20, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReporteGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteGeneralActionPerformed
        // TODO add your handling code here:
        JDialogRepVisitasGeneral dialogRepVisGen = new JDialogRepVisitasGeneral(null, false);
        WindowUtil.centerWindow(dialogRepVisGen);
        dialogRepVisGen.setVisible(true);
    }//GEN-LAST:event_jButtonReporteGeneralActionPerformed

    private void jButtonReporteVisitasPorPersona2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteVisitasPorPersona2ActionPerformed
        // TODO add your handling code here:
        JDialogRepPersonasNoGratas dialogRepPersNoGratas = new JDialogRepPersonasNoGratas(null, false);
        WindowUtil.centerWindow(dialogRepPersNoGratas);
        dialogRepPersNoGratas.setVisible(true);
    }//GEN-LAST:event_jButtonReporteVisitasPorPersona2ActionPerformed

    private void jButtonReporteVisitasPorPersona1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteVisitasPorPersona1ActionPerformed
        // TODO add your handling code here:
        JDialogRepVisitasFecha dialogRepVisFec = new JDialogRepVisitasFecha(null, false);
        WindowUtil.centerWindow(dialogRepVisFec);
        dialogRepVisFec.setVisible(true);
    }//GEN-LAST:event_jButtonReporteVisitasPorPersona1ActionPerformed

    private void jButtonReporteVisitasPorVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteVisitasPorVisitanteActionPerformed
        // TODO add your handling code here:
        JDialogRepVisitasVisitante dialogRepVisVis = new JDialogRepVisitasVisitante(null, false);
        WindowUtil.centerWindow(dialogRepVisVis);
        dialogRepVisVis.setVisible(true);
    }//GEN-LAST:event_jButtonReporteVisitasPorVisitanteActionPerformed

    private void jButtonReporteVisitasPorAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteVisitasPorAreaActionPerformed
        // TODO add your handling code here:
        JDialogRepVisitasAreaPersona dialogRepVisAreaPers = new JDialogRepVisitasAreaPersona(null, false);
        WindowUtil.centerWindow(dialogRepVisAreaPers);
        dialogRepVisAreaPers.setVisible(true);
    }//GEN-LAST:event_jButtonReporteVisitasPorAreaActionPerformed

    private void jButtonReporteVisitasPorPersona3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteVisitasPorPersona3ActionPerformed
        // TODO add your handling code here:
        JDialogRepVisitasActivas dialogRepVisAc = new JDialogRepVisitasActivas(null, false);
        WindowUtil.centerWindow(dialogRepVisAc);
        dialogRepVisAc.setVisible(true);
    }//GEN-LAST:event_jButtonReporteVisitasPorPersona3ActionPerformed

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
                File myFile = new File(abspath + "/manuales/SCAModuloAnalisis.pdf");
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
            java.util.logging.Logger.getLogger(JDialogAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogAnalisis dialog = new JDialogAnalisis(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonReporteGeneral;
    private javax.swing.JButton jButtonReporteVisitasPorArea;
    private javax.swing.JButton jButtonReporteVisitasPorPersona1;
    private javax.swing.JButton jButtonReporteVisitasPorPersona2;
    private javax.swing.JButton jButtonReporteVisitasPorPersona3;
    private javax.swing.JButton jButtonReporteVisitasPorVisitante;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPersonaVisitada4;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}