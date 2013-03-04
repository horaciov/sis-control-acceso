/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.reportes;

import py.gov.itaipu.controlacceso.view.visita.*;
import java.awt.Dialog;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.observablecollections.ObservableCollections;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.action.visita.VisitaAction;
import py.gov.itaipu.controlacceso.model.Motivo;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;
import py.gov.itaipu.controlacceso.view.JDialogBuscador;
import py.gov.itaipu.controlacceso.view.TimeRenderer;
import py.gov.itaipu.controlacceso.view.administracion.organizacion.JDialogOrganigrama;
import py.gov.itaipu.controlacceso.view.administracion.organizacion.JInternalFrameOrganizacionExterna;
import py.gov.itaipu.controlacceso.view.persona.JDialogPersonaPrincipal;
import py.gov.itaipu.controlacceso.view.persona.JInternalFramePersona;

/**
 *
 * @author fboy
 */
public class JDialogRepVisitasGeneral extends javax.swing.JDialog {

    private VisitaAction visitaAction;
    private TableCellRenderer rendererTime;
    private CRUDAction<Motivo> motivoAction;
    private CRUDAction<Organizacion> organizacionAction;
    private Organizacion organizacionExterna;
    private Persona persona;
    private Persona personaVisitada;
    private Organizacion organizacionVisitada;

    /**
     * Creates new form JDialogConsultaVisitas
     */
    public JDialogRepVisitasGeneral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        motivoAction = new CRUDAction<Motivo>(new Motivo());
        organizacionAction = new CRUDAction<Organizacion>(new Organizacion());
        rendererTime = new TimeRenderer();
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        List<Visita> l=new ArrayList<Visita>();
        listVisitas = ObservableCollections.observableList(l);
        try{
            listMotivos = ObservableCollections.observableList(motivoAction.findAll());
            Motivo m=new Motivo();
            m.setNombre("TODOS");
            listMotivos.add(0,m);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        try{
            listOrganizacionInternas = ObservableCollections.observableList(organizacionAction.findByNamedQuery("Organizacion.findAllInterna"));
            Organizacion o=new Organizacion();
            o.setNombre("TODAS");
            listOrganizacionInternas.add(0, o);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldAreaPersonaVisitada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxMotivo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldOrganizacionExterna = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButtonBuscarOrganizacionExterna = new javax.swing.JButton();
        jFormattedTextFieldDesde = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHasta = new javax.swing.JFormattedTextField();
        jButtonLimpiarPersonaVisitada = new javax.swing.JButton();
        jButtonLimpiarOrganizacion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPersona = new javax.swing.JTextField();
        jButtonBuscarPersona = new javax.swing.JButton();
        jButtonLimpiarPersona = new javax.swing.JButton();
        jButtonBuscarAreaPersona = new javax.swing.JButton();
        jButtonImprimirListado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REPORTE DE VISITAS POR PARAMETROS GENERALES");
        setBounds(new java.awt.Rectangle(30, 30, 0, 0));
        setResizable(false);

        jTextFieldAreaPersonaVisitada.setEditable(false);

        jLabel5.setText("Area/Persona Visitada");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listMotivos, jComboBoxMotivo);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel6.setText("Motivo:");

        jTextAreaObservacion.setColumns(20);
        jTextAreaObservacion.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacion);

        jLabel4.setText("Observación:");

        jLabel3.setText("Desde:");

        jLabel7.setText("Hasta:");

        jTextFieldOrganizacionExterna.setEditable(false);

        jLabel9.setText("Organización:");

        jButtonBuscarOrganizacionExterna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarOrganizacionExterna.setToolTipText("Buscar Persona");
        jButtonBuscarOrganizacionExterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarOrganizacionExternaActionPerformed(evt);
            }
        });

        jFormattedTextFieldDesde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jFormattedTextFieldHasta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jButtonLimpiarPersonaVisitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiarPersonaVisitada.setToolTipText("Buscar Persona");
        jButtonLimpiarPersonaVisitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPersonaVisitadaActionPerformed(evt);
            }
        });

        jButtonLimpiarOrganizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiarOrganizacion.setToolTipText("Buscar Persona");
        jButtonLimpiarOrganizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarOrganizacionActionPerformed(evt);
            }
        });

        jLabel2.setText("Visitante");

        jTextFieldPersona.setEditable(false);

        jButtonBuscarPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarPersona.setToolTipText("Buscar Persona");
        jButtonBuscarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPersonaActionPerformed(evt);
            }
        });

        jButtonLimpiarPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiarPersona.setToolTipText("Buscar Persona");
        jButtonLimpiarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPersonaActionPerformed(evt);
            }
        });

        jButtonBuscarAreaPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarAreaPersona.setToolTipText("Buscar Persona");
        jButtonBuscarAreaPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarAreaPersonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(42, 42, 42))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(40, 40, 40)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldOrganizacionExterna, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonBuscarOrganizacionExterna, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLimpiarOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxMotivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAreaPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(166, 166, 166)
                                    .addComponent(jTextFieldPersona))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(455, 455, 455))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBuscarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscarAreaPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonLimpiarPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimpiarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane2)))
                .addGap(7, 7, 7))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonLimpiarPersona, jButtonLimpiarPersonaVisitada});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jFormattedTextFieldDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jFormattedTextFieldHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldAreaPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jButtonLimpiarPersonaVisitada)
                    .addComponent(jButtonBuscarAreaPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldOrganizacionExterna, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonBuscarOrganizacionExterna)
                            .addComponent(jButtonLimpiarOrganizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jTextFieldPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonLimpiarPersona)
                            .addComponent(jButtonBuscarPersona))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonLimpiarPersona, jButtonLimpiarPersonaVisitada});

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
        jLabel1.setText("LISTADO DE VISITAS POR PARAMETROS GENERALES.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(425, 425, 425)
                                .addComponent(jButtonImprimirListado)
                                .addGap(68, 68, 68)))
                        .addGap(37, 37, 37)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImprimirListado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarOrganizacionExternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarOrganizacionExternaActionPerformed

        // TODO add your handling code here:
        JInternalFrameOrganizacionExterna jFrameOrganizacionExterna = new JInternalFrameOrganizacionExterna();
        jFrameOrganizacionExterna.setModoBuscador(true);
        jFrameOrganizacionExterna.setVisible(true);
        JDialogBuscador buscador = new JDialogBuscador(null, true);
        buscador.setSize(jFrameOrganizacionExterna.getSize());
        jFrameOrganizacionExterna.setClosable(false);
        jFrameOrganizacionExterna.setResizable(false);
        jFrameOrganizacionExterna.setTitle("Buscador de organización externa");
        WindowUtil.centerWindow(buscador);
//        WindowUtil.centerWindow(jFrameOrganizacionExterna);
        buscador.getjDesktopPaneBuscador().add(jFrameOrganizacionExterna);
        buscador.setVisible(true);
        organizacionExterna = jFrameOrganizacionExterna.getOrganizacionSeleccionada();
        if (organizacionExterna != null) {
            jTextFieldOrganizacionExterna.setText(organizacionExterna.getNombre());
        }

    }//GEN-LAST:event_jButtonBuscarOrganizacionExternaActionPerformed

    private void jButtonLimpiarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaActionPerformed
        // TODO add your handling code here:
        persona = null;
        jTextFieldPersona.setText(null);
    }//GEN-LAST:event_jButtonLimpiarPersonaActionPerformed

    private void jButtonLimpiarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        personaVisitada = null;
        organizacionVisitada = null;
        jTextFieldAreaPersonaVisitada.setText(null);
    }//GEN-LAST:event_jButtonLimpiarPersonaVisitadaActionPerformed

    private void jButtonLimpiarOrganizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarOrganizacionActionPerformed
        // TODO add your handling code here:
        organizacionExterna = null;
        jTextFieldOrganizacionExterna.setText(null);
    }//GEN-LAST:event_jButtonLimpiarOrganizacionActionPerformed

    private void jButtonImprimirListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirListadoActionPerformed
        // TODO add your handling code here:
        try {

            Class.forName("org.postgresql.Driver");
            Connection conexion = EntityManagerCA.getConexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("reports/reporteListadoVisitas.jasper");
            //Parametros
            Map<String, Object> parametros = new HashMap<String, Object>();
            ///Parametros
            if (persona != null && persona.getId() != null) {
                parametros.put("pPersonaText", (Object) (persona.getApellido() + ", " + persona.getNombre()));
                parametros.put("pPersonaId", (Object) persona.getId().intValue());
            }
            if (personaVisitada != null && personaVisitada.getId() != null) {
                parametros.put("pPersonaVisitadaText", (Object) (personaVisitada.getApellido() + ", " + personaVisitada.getNombre()));
                parametros.put("pPersonaVisitadaId", (Object) personaVisitada.getId().intValue());
            }

            if (organizacionExterna != null && organizacionExterna.getId() != null) {
                parametros.put("pOrganizacionText", (Object) organizacionExterna.getNombre());
                parametros.put("pOrganizacionId", (Object) organizacionExterna.getId().intValue());
            }


            if (organizacionVisitada != null && organizacionVisitada.getId() != null) {
                parametros.put("pOrganizacionVisitadaText", organizacionVisitada.getNombre());
                parametros.put("pOrganizacionVisitadaId", organizacionVisitada.getId().intValue());
            }
            Motivo motivo = (Motivo) jComboBoxMotivo.getSelectedItem();
            if (motivo.getId() != null) {
                parametros.put("pMotivoText", (Object) motivo.getNombre());
                parametros.put("pMotivoId", (Object) motivo.getId().intValue());
            }
            if (jTextAreaObservacion.getText() != null && !jTextAreaObservacion.getText().equals("")) {
                parametros.put("pObservacion", (Object) jTextAreaObservacion.getText().toUpperCase());
            }

            if (jFormattedTextFieldDesde != null && !jFormattedTextFieldDesde.getText().equals("")) {
                String vFecha = jFormattedTextFieldDesde.getText().substring(6, 10) + jFormattedTextFieldDesde.getText().substring(3, 5) + jFormattedTextFieldDesde.getText().substring(0, 2);
                parametros.put("pFechaDesde", (Object) vFecha.toUpperCase());
                vFecha = vFecha.substring(6, 8) + "/" + vFecha.substring(4, 6) + "/" + vFecha.substring(0, 4);
                parametros.put("pFechaDesdeText", (Object) vFecha.toUpperCase());
            }
            if (jFormattedTextFieldHasta != null && !jFormattedTextFieldHasta.getText().equals("")) {
                String vFechaH = jFormattedTextFieldHasta.getText().substring(6, 10) + jFormattedTextFieldHasta.getText().substring(3, 5) + jFormattedTextFieldHasta.getText().substring(0, 2);
                parametros.put("pFechaHasta", (Object) vFechaH.toUpperCase());
                vFechaH = vFechaH.substring(6, 8) + "/" + vFechaH.substring(4, 6) + "/" + vFechaH.substring(0, 4);
                parametros.put("pFechaHastaText", (Object) vFechaH.toUpperCase());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

            //            Muestra el Reporte en Pantalla
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
            jviewer.viewReport(jasperPrint, false);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(JInternalFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();

    }//GEN-LAST:event_jButtonImprimirListadoActionPerformed

    private void jButtonBuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPersonaActionPerformed
        // TODO add your handling code here:
        JDialogPersonaPrincipal jdP = new JDialogPersonaPrincipal(null, true);
        jdP.setModoBuscador(true);
        jdP.setTitle("Buscador de Personas Externas");
        jdP.setSize(1200, 665);
        WindowUtil.centerWindow(jdP);
        jdP.setResizable(false);
        jdP.setVisible(true);
        persona=jdP.getPersonaSeleccionada();
        if(persona!=null)
        jTextFieldPersona.setText(persona.getNombre()+", "+persona.getApellido());
       
    }//GEN-LAST:event_jButtonBuscarPersonaActionPerformed

    private void jButtonBuscarAreaPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarAreaPersonaActionPerformed
        // TODO add your handling code here:
        JDialogOrganigrama jOrganigrama = new JDialogOrganigrama(null, true);
        jOrganigrama.setSize(860,560);
        jOrganigrama.setModoBuscador(true);
        jOrganigrama.setTitle("Seleccionar Area/Persona Visitada");
        jOrganigrama.setVisible(true);
        jOrganigrama.setResizable(false);
        WindowUtil.centerWindow(jOrganigrama);
        if (jOrganigrama.getSeleccionado() != null) {
            Object seleccionado = jOrganigrama.getSeleccionado();

            if (seleccionado.getClass().getSimpleName().equals("Persona")) {
                personaVisitada = (Persona) seleccionado;
                organizacionVisitada = null;
                jTextFieldAreaPersonaVisitada.setText(personaVisitada.getApellido() + ", " + personaVisitada.getNombre());
            } else if (seleccionado.getClass().getSimpleName().equals("Organizacion")) {
                personaVisitada = null;
                organizacionVisitada = (Organizacion) seleccionado;
                jTextFieldAreaPersonaVisitada.setText(organizacionVisitada.getNombre());
            }
        }


    }//GEN-LAST:event_jButtonBuscarAreaPersonaActionPerformed

    private InputStream generaBarCode(String codeDigits) {

        while (codeDigits.length() < 11) {
            codeDigits = "0" + codeDigits;
        }

        ByteArrayInputStream bis;
        UPCABean bean = new UPCABean();
        final int dpi = 150;
        bean.setModuleWidth(UnitConv.in2mm(2.0f / dpi));
        bean.setFontSize(2.0);
        bean.doQuietZone(true);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        bean.generateBarcode(canvas, codeDigits);
        try {
            canvas.finish();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(JDialogVisita.class.getName()).log(Level.SEVERE, null, ex);
        }

        bis = new ByteArrayInputStream(out.toByteArray());
        return bis;
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
            java.util.logging.Logger.getLogger(JDialogConsultaVisitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogConsultaVisitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogConsultaVisitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogConsultaVisitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogConsultaVisitas dialog = new JDialogConsultaVisitas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonBuscarAreaPersona;
    private javax.swing.JButton jButtonBuscarOrganizacionExterna;
    private javax.swing.JButton jButtonBuscarPersona;
    private javax.swing.JButton jButtonImprimirListado;
    private javax.swing.JButton jButtonLimpiarOrganizacion;
    private javax.swing.JButton jButtonLimpiarPersona;
    private javax.swing.JButton jButtonLimpiarPersonaVisitada;
    private javax.swing.JComboBox jComboBoxMotivo;
    private javax.swing.JFormattedTextField jFormattedTextFieldDesde;
    private javax.swing.JFormattedTextField jFormattedTextFieldHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaObservacion;
    private javax.swing.JTextField jTextFieldAreaPersonaVisitada;
    private javax.swing.JTextField jTextFieldOrganizacionExterna;
    private javax.swing.JTextField jTextFieldPersona;
    private java.util.List listMotivos;
    private java.util.List listOrganizacionInternas;
    private java.util.List listVisitas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
