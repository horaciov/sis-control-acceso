/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.reportes;

import java.awt.Dialog;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.view.persona.JInternalFramePersona;

/**
 *
 * @author fboy
 */
public class JDialogRepVisitasFecha extends javax.swing.JDialog {

    /**
     * Creates new form JDialogRepVisitasFecha
     */
    public JDialogRepVisitasFecha(java.awt.Frame parent, boolean modal) {
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

        jSeparator2 = new javax.swing.JSeparator();
        jFormattedTextFieldDesde = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldHasta = new javax.swing.JFormattedTextField();
        jButtonImprimirListado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REPORTE DE VISITAS POR RANGO DE FECHAS");

        jFormattedTextFieldDesde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel3.setText("Desde:");

        jLabel7.setText("Hasta:");

        jFormattedTextFieldHasta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jButtonImprimirListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/printer.png"))); // NOI18N
        jButtonImprimirListado.setText("LISTADO");
        jButtonImprimirListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirListadoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("LISTADO DE VISITAS POR RANGO DE FECHAS.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jFormattedTextFieldHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButtonImprimirListado)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jButtonImprimirListado)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextFieldDesde, jFormattedTextFieldHasta});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirListadoActionPerformed
        // TODO add your handling code here:
        try {

            Class.forName("org.postgresql.Driver");
            Connection conexion = EntityManagerCA.getConexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("reports/reporteListadoVisitas.jasper");
            //Parametros
            Map<String, Object> parametros = new HashMap<String, Object> ();
            ///Parametros
           
            if (jFormattedTextFieldDesde!=null && !jFormattedTextFieldDesde.getText().equals("")) {
                String vFecha = jFormattedTextFieldDesde.getText().substring(6,10)+jFormattedTextFieldDesde.getText().substring(3,5)+jFormattedTextFieldDesde.getText().substring(0,2);
                parametros.put("pFechaDesde",(Object)vFecha.toUpperCase());
                vFecha = vFecha.substring(6,8)+"/"+vFecha.substring(4,6)+"/"+vFecha.substring(0,4);
                parametros.put("pFechaDesdeText",(Object)vFecha.toUpperCase());
            }
            if (jFormattedTextFieldHasta!=null && !jFormattedTextFieldHasta.getText().equals("")) {
                String vFechaH = jFormattedTextFieldHasta.getText().substring(6,10)+jFormattedTextFieldHasta.getText().substring(3,5)+jFormattedTextFieldHasta.getText().substring(0,2);
                parametros.put("pFechaHasta",(Object)vFechaH.toUpperCase());
                vFechaH = vFechaH.substring(6,8)+"/"+vFechaH.substring(4,6)+"/"+vFechaH.substring(0,4);
                parametros.put("pFechaHastaText",(Object)vFechaH.toUpperCase());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

            //            Muestra el Reporte en Pantalla
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
            jviewer.viewReport(jasperPrint,false);

        } catch (JRException ex) {
            Logger.getLogger(JDialogRepVisitasFecha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDialogRepVisitasFecha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    
    }//GEN-LAST:event_jButtonImprimirListadoActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogRepVisitasFecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogRepVisitasFecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogRepVisitasFecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogRepVisitasFecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogRepVisitasFecha dialog = new JDialogRepVisitasFecha(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonImprimirListado;
    private javax.swing.JFormattedTextField jFormattedTextFieldDesde;
    private javax.swing.JFormattedTextField jFormattedTextFieldHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
