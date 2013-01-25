/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.visita;

import java.awt.Dialog;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import py.gov.itaipu.controlacceso.view.administracion.parametrogeneral.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.action.visita.VisitaAction;
import py.gov.itaipu.controlacceso.model.Motivo;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.view.TimeRenderer;
import py.gov.itaipu.controlacceso.view.persona.JInternalFramePersona;


/**
 *
 * @author vimartih
 */
public class JInternalFrameRegistroVisita extends javax.swing.JInternalFrame {
   
    
    private VisitaAction visitaAction;
    private TableCellRenderer rendererTime;
    
    
    /**
     * Creates new form JInternalFrameMotivo
     */
    public JInternalFrameRegistroVisita() {
        setClosable(true);
        visitaAction=new VisitaAction();
        visitaAction.setVisita(new Visita());       
        rendererTime=new TimeRenderer();
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

        listVisitas = ObservableCollections.observableList(visitaAction.findVisitasPendientes());
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVisitas = new javax.swing.JTable();
        jButtonVer = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();
        jButtonAnular = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButtonRegistroSalida = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimirVisita = new javax.swing.JButton();

        setTitle("Gestión de visitas");

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listVisitas, jTableVisitas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${persona.nombre} ${persona.apellido}"));
        columnBinding.setColumnName("Persona");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${persona.organizacion.nombre}"));
        columnBinding.setColumnName("Organización");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechaIngreso}"));
        columnBinding.setColumnName("Fecha Ingreso");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${motivo.nombre}"));
        columnBinding.setColumnName("Motivo");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${personaVisitada.nombre} ${personaVisitada.apellido}"));
        columnBinding.setColumnName("Persona Visitada");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${organizacionInterna.nombre}"));
        columnBinding.setColumnName("Area Visitada");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${observacion}"));
        columnBinding.setColumnName("Observacion");
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableVisitas);
        jTableVisitas.getColumnModel().getColumn(0).setMinWidth(200);
        jTableVisitas.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableVisitas.getColumnModel().getColumn(0).setMaxWidth(200);
        jTableVisitas.getColumnModel().getColumn(2).setCellRenderer(rendererTime);

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

        jButtonAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/delete.png"))); // NOI18N
        jButtonAnular.setText("Anular");
        jButtonAnular.setPreferredSize(new java.awt.Dimension(95, 25));
        jButtonAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnularActionPerformed(evt);
            }
        });

        jLabel1.setText("Visitas Pendientes");

        jButtonRegistroSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/salida.jpeg"))); // NOI18N
        jButtonRegistroSalida.setToolTipText("Registro de salida");
        jButtonRegistroSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroSalidaActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRegistroSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRegistroSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButtonImprimirVisita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/ficha.png"))); // NOI18N
        jButtonImprimirVisita.setText("IMPRIMIR");
        jButtonImprimirVisita.setPreferredSize(new java.awt.Dimension(110, 25));
        jButtonImprimirVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirVisitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonVer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonImprimirVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImprimirVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        // TODO add your handling code here:    
        JDialogVisita dialogMotivo = new JDialogVisita(null, closable);
        dialogMotivo.setVisita(new Visita());
        dialogMotivo.setVisible(true);
        listVisitas.clear();
        listVisitas.addAll(visitaAction.findVisitasPendientes());

    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        // TODO add your handling code here:
        if (jTableVisitas.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una visita", "Error", 0);
            return;
        }
        Visita v=(Visita) listVisitas.get(jTableVisitas.getSelectedRow());
        JDialogVisita dialogVisita = new JDialogVisita(null, closable);
        dialogVisita.setVisita(v);
        dialogVisita.cargarDatosVisita();
        dialogVisita.setReadOnly(true);
        dialogVisita.setVisible(true);
       
    }//GEN-LAST:event_jButtonVerActionPerformed

    private void jButtonAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnularActionPerformed
        // TODO add your handling code here:
        if (jTableVisitas.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una visita", "Error", 0);
            return;
        }
        
        if(JOptionPane.showConfirmDialog(this,"Está seguro que desea Anular?","Eliminar Motivo",0)!=0)
            return;       
        Visita v = (Visita) listVisitas.get(jTableVisitas.getSelectedRow());
        visitaAction.setVisita(v);
        visitaAction.anular();
        //        visitaAction.eliminar();
        listVisitas.clear();
        listVisitas.addAll(visitaAction.findVisitasPendientes());
        JOptionPane.showMessageDialog(this, "Se ha Anulado correctamente","Info",1);
    }//GEN-LAST:event_jButtonAnularActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonRegistroSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroSalidaActionPerformed
        // TODO add your handling code here:
        if(jTableVisitas.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una visita","Error",0);
            return;
        }
        Calendar c= Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        Visita v=(Visita) listVisitas.get(jTableVisitas.getSelectedRow());
        if(JOptionPane.showConfirmDialog(this,"Está seguro que desea marcar la salida de " 
                +v.getPersona().getNombre()+
                " "+v.getPersona().getApellido()+
                ", a las: "+sdf.format(c.getTime())
                +
                "?","Registro de salida",0)!=0)
            return;   
        
        v.setFechaSalida(c.getTime());
        visitaAction.setVisita(v);
        visitaAction.guardar();
        listVisitas.clear();
        listVisitas.addAll(visitaAction.findVisitasPendientes());
        
        JOptionPane.showMessageDialog(this, "Se ha registrado la salida correctamente","Info",1);
    }//GEN-LAST:event_jButtonRegistroSalidaActionPerformed

    private void jButtonImprimirVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirVisitaActionPerformed
        // TODO add your handling code here:
       if (jTableVisitas.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una visita", "Error", 0);
            return;
        }
        Visita v = (Visita) listVisitas.get(jTableVisitas.getSelectedRow());
       try {

            Class.forName("org.postgresql.Driver");
            Connection conexion = EntityManagerCA.getConexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("reports/reporteTicketVisitas.jasper");
            //Parametros
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("idVis", (Object) v.getId());
//                //Fotografia
//                ByteArrayInputStream bis = new ByteArrayInputStream(visita.getPersona().getFotografia());
//                InputStream iS = bis;
//                parametros.put("fotografia",(Object)iS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

//            Muestra el Reporte en Pantalla
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
            jviewer.viewReport(jasperPrint, false);

            //     Genera el Reporte en PDF            
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint); 
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("reportePDF.pdf"));
//            exporter.exportReport();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonImprimirVisitaActionPerformed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnular;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonImprimirVisita;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonRegistroSalida;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableVisitas;
    private java.util.List listVisitas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
