/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.visita;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.gov.itaipu.controlacceso.view.administracion.parametrogeneral.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.observablecollections.ObservableCollections;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.action.persona.PersonaAction;
import py.gov.itaipu.controlacceso.action.visita.VisitaAction;
import py.gov.itaipu.controlacceso.model.Motivo;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.view.JDialogBuscador;
import py.gov.itaipu.controlacceso.view.persona.JInternalFramePersona;

/**
 *
 * @author vimartih
 */
public class JDialogVisita extends javax.swing.JDialog {

    private Visita visita;
    private Boolean readOnly;
    private CRUDAction<Motivo> motivoAction;
    private CRUDAction<Organizacion> organizacionAction;
    private Persona persona;
    private Persona personaVisitada;
    private PersonaAction personaAction;
    
    /**
     * Creates new form JDialogMotivo
     */
    public JDialogVisita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        readOnly=false;
        motivoAction=new CRUDAction<Motivo>(new Motivo());        
        organizacionAction=new CRUDAction<Organizacion>(new Organizacion());
        personaAction=new PersonaAction();
        initComponents();        
       
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersonaVisitada() {
        return personaVisitada;
    }

    public void setPersonaVisitada(Persona personaVisitada) {
        this.personaVisitada = personaVisitada;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
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

        listMotivos = ObservableCollections.observableList(motivoAction.findAll());
        listOrganizacionInterna = ObservableCollections.observableList(organizacionAction.findByNamedQuery("Organizacion.findAllInterna"));
        Organizacion o=new Organizacion();
        o.setNombre("NINGUNA");
        listOrganizacionInterna.add(0, o);
        jTextFieldPersona = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPersonaVisitada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxMotivo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxOrganizacionesInternas = new javax.swing.JComboBox();
        jButtonBuscarPersona = new javax.swing.JButton();
        jButtonBuscarPersonaVisitada = new javax.swing.JButton();
        jButtonLimpiarPersonaVisitada = new javax.swing.JButton();
        jButtonLimpiarPersona = new javax.swing.JButton();
        jTextFieldOrganizacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de visita");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTextAreaObservacion.setColumns(20);
        jTextAreaObservacion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacion);

        jLabel2.setText("Persona:");

        jLabel3.setText("Observación:");

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/exit.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("Visita");

        jLabel5.setText("Persona Visitada:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listMotivos, jComboBoxMotivo);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel6.setText("Motivo:");

        jLabel8.setText("Area Visitada:");

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listOrganizacionInterna, jComboBoxOrganizacionesInternas);
        bindingGroup.addBinding(jComboBoxBinding);

        jButtonBuscarPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarPersona.setToolTipText("Buscar Persona");
        jButtonBuscarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPersonaActionPerformed(evt);
            }
        });

        jButtonBuscarPersonaVisitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarPersonaVisitada.setToolTipText("Buscar Persona");
        jButtonBuscarPersonaVisitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPersonaVisitadaActionPerformed(evt);
            }
        });

        jButtonLimpiarPersonaVisitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiarPersonaVisitada.setToolTipText("Buscar Persona");
        jButtonLimpiarPersonaVisitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPersonaVisitadaActionPerformed(evt);
            }
        });

        jButtonLimpiarPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiarPersona.setToolTipText("Buscar Persona");
        jButtonLimpiarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPersonaActionPerformed(evt);
            }
        });

        jTextFieldOrganizacion.setEditable(false);

        jLabel7.setText("Organizacion:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxMotivo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldOrganizacion)
                                            .addComponent(jTextFieldPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonLimpiarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxOrganizacionesInternas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextFieldPersonaVisitada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscarPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonLimpiarPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonBuscarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLimpiarPersona, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPersona, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonBuscarPersonaVisitada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextFieldPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonLimpiarPersonaVisitada, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxOrganizacionesInternas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCancelar)
                            .addComponent(jButtonGuardar))))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    
    private boolean validar() {
        boolean resultado=true;
        if(persona==null || persona.getId()==null){
            JOptionPane.showMessageDialog(this, "La persona es obligatorio","Error",0);
            resultado=false;
        }
        Organizacion o=(Organizacion)jComboBoxOrganizacionesInternas.getSelectedItem();
        if((personaVisitada==null || persona.getId()==null) && o.getId()==null){
            JOptionPane.showMessageDialog(this, "Debe visitar una persona o area","Error",0);
            resultado=false;
        }
        return resultado;
    }
    
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        if(visita==null)
            return;
        VisitaAction action=new VisitaAction(visita);
        
        if(!validar()){
            return;//Implementar el método con las validaciones
        }
        
        visita.setPersona(persona);
        visita.setPersonaVisitada(personaVisitada);
        
        visita.setFechaIngreso(Calendar.getInstance().getTime());
        
        Organizacion o=(Organizacion)jComboBoxOrganizacionesInternas.getSelectedItem();
        if(o.getId()!=null){
            visita.setOrganizacionInterna(o);
        }else{
            visita.setOrganizacionInterna(null);
        }
        
        visita.setMotivo((Motivo)jComboBoxMotivo.getSelectedItem());
        visita.setObservacion(jTextAreaObservacion.getText());
        
        if(visita.getId()==null) {
            action.crear();
            JOptionPane.showMessageDialog(this, "Se ha creado con éxito","Info",1);
            imprimirTicket();
        }
        else {
            action.guardar();
            JOptionPane.showMessageDialog(this, "Se ha actualizado correctamente","Info",1);
        }              
        
        this.dispose();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

     private void imprimirTicket(){
         try {
            
            Class.forName("org.postgresql.Driver");
            Connection conexion = EntityManagerCA.getConexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("reports/reporteTicketVisitas.jasper");
            //Parametros
                Map<String, Object> parametros = new HashMap<String, Object> ();
                parametros.put("idVis",(Object)visita.getId());
//                //Fotografia
//                ByteArrayInputStream bis = new ByteArrayInputStream(visita.getPersona().getFotografia());
//                InputStream iS = bis;
//                parametros.put("fotografia",(Object)iS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

//            Muestra el Reporte en Pantalla
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
            jviewer.viewReport(jasperPrint,false);
        
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
       }
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
//        jTextFieldPersona.setText(motivo.getNombre());
//        jTextAreaObservacion.setText(motivo.getDescripcion());
        
        if(readOnly){
            jTextFieldPersona.setEditable(false);
            jTextAreaObservacion.setEditable(false);
            jButtonGuardar.setVisible(false);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jButtonBuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPersonaActionPerformed
        // TODO add your handling code here:
        JInternalFramePersona jFramePersona = new JInternalFramePersona();
            jFramePersona.setModoBuscador(true);
            jFramePersona.setTipoOrganizacionPersona("EXTERNA");
            jFramePersona.setTitle("Buscador de Personas Externas");
            jFramePersona.setVisible(true);            
            JDialogBuscador buscador=new JDialogBuscador(null, true);        
            buscador.setSize(jFramePersona.getSize());
            //jFramePersona.setClosable(false);
            jFramePersona.setResizable(false);
            buscador.getjDesktopPaneBuscador().add(jFramePersona);
            buscador.setVisible(true);        
            persona=jFramePersona.getPersonaSeleccionada();
            if(persona!=null)
                jTextFieldPersona.setText(persona.getNombre()+", "+persona.getApellido());
                jTextFieldOrganizacion.setText(persona.getOrganizacion().getNombre());
    }//GEN-LAST:event_jButtonBuscarPersonaActionPerformed

    private void jButtonBuscarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        JInternalFramePersona jFramePersona = new JInternalFramePersona();
            jFramePersona.setModoBuscador(true);
            jFramePersona.setTitle("Buscador de Personas Internas");
            jFramePersona.setTipoOrganizacionPersona("INTERNA");
            jFramePersona.setVisible(true);            
            JDialogBuscador buscador=new JDialogBuscador(null, true);        
            buscador.setSize(jFramePersona.getSize());
            //jFramePersona.setClosable(false);
            jFramePersona.setResizable(false);
            buscador.getjDesktopPaneBuscador().add(jFramePersona);
            buscador.setVisible(true);        
            personaVisitada=jFramePersona.getPersonaSeleccionada();
            if(personaVisitada!=null)
                jTextFieldPersonaVisitada.setText(personaVisitada.getNombre()+", "+personaVisitada.getApellido());
                jComboBoxOrganizacionesInternas.setSelectedItem(personaVisitada.getOrganizacion());
    }//GEN-LAST:event_jButtonBuscarPersonaVisitadaActionPerformed

    private void jButtonLimpiarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        personaVisitada=null;
        jTextFieldPersonaVisitada.setText(null);
        jComboBoxOrganizacionesInternas.setSelectedIndex(0);
    }//GEN-LAST:event_jButtonLimpiarPersonaVisitadaActionPerformed

    private void jButtonLimpiarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaActionPerformed
        // TODO add your handling code here:
        persona=null;
        jTextFieldPersona.setText(null);
        jTextFieldOrganizacion.setText("");
        
        
    }//GEN-LAST:event_jButtonLimpiarPersonaActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogMotivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogMotivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogMotivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogMotivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogMotivo dialog = new JDialogMotivo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonBuscarPersona;
    private javax.swing.JButton jButtonBuscarPersonaVisitada;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiarPersona;
    private javax.swing.JButton jButtonLimpiarPersonaVisitada;
    private javax.swing.JComboBox jComboBoxMotivo;
    private javax.swing.JComboBox jComboBoxOrganizacionesInternas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaObservacion;
    private javax.swing.JTextField jTextFieldOrganizacion;
    private javax.swing.JTextField jTextFieldPersona;
    private javax.swing.JTextField jTextFieldPersonaVisitada;
    private java.util.List listMotivos;
    private java.util.List listOrganizacionInterna;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
