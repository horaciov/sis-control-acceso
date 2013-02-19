/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.visita;

import com.lowagie.text.pdf.ArabicLigaturizer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.rtp.event.ByeEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import py.gov.itaipu.controlacceso.view.administracion.parametrogeneral.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.codehaus.groovy.control.messages.Message;
import org.jdesktop.observablecollections.ObservableCollections;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.action.persona.PersonaAction;
import py.gov.itaipu.controlacceso.action.visita.VisitaAction;
import py.gov.itaipu.controlacceso.model.Motivo;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.TipoDocumento;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.utils.tree.CustomIconRenderer;
import py.gov.itaipu.controlacceso.utils.tree.UtilesArbol;
import py.gov.itaipu.controlacceso.view.FileFilterExtension;
import py.gov.itaipu.controlacceso.view.JDialogBuscador;
import py.gov.itaipu.controlacceso.view.TimeRenderer;
import py.gov.itaipu.controlacceso.view.persona.JDialogPersona;
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
    private CRUDAction<TipoDocumento> tipoDocAction;
    private PersonaAction personaAction;
    private Persona persona;
    private Persona personaVisitada;
    private Organizacion areaVisitada;
    private VisitaAction action;
    private TableCellRenderer rendererTime;

    /**
     * Creates new form JDialogMotivo
     */
    public JDialogVisita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        persona = new Persona();
        rendererTime = new TimeRenderer();
        readOnly = false;
        motivoAction = new CRUDAction<Motivo>(new Motivo());
        organizacionAction = new CRUDAction<Organizacion>(new Organizacion());
        tipoDocAction = new CRUDAction(new TipoDocumento());
        personaAction = new PersonaAction(persona);
        areaVisitada = new Organizacion();
        action = new VisitaAction(new Visita());
        initComponents();
        jComboBoxTipoDoc.setVisible(false);
        jLabelTipoDeDoc.setVisible(false);
        jComboBoxTipoDoc.setSelectedItem(tipoDocAction.findByNamedQuery("TipoDocumento.findCI"));
        jButtonActualizarFotografia.setVisible(false);
        jButtonRegistrarSalida.setVisible(false);

    }

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
        listUltimasVisitas = ObservableCollections.observableList(action.findByPersona(persona));
        buttonGroupExtranjero = new javax.swing.ButtonGroup();
        listTipoDocumento = tipoDocAction.findAll();
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
        jButtonBuscarPersona = new javax.swing.JButton();
        jButtonBuscarPersonaVisitada = new javax.swing.JButton();
        jButtonLimpiarPersonaVisitada = new javax.swing.JButton();
        jButtonLimpiarPersona = new javax.swing.JButton();
        jTextFieldOrganizacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonActualizarFotografia = new javax.swing.JButton();
        jScrollPanePersonasVisitadas = new javax.swing.JScrollPane();
        jTreePersonaVisitada = new javax.swing.JTree();
        jLabelPersonaVisitada = new javax.swing.JLabel();
        jLabelDocNro = new javax.swing.JLabel();
        jTextFieldDocumentoPersona = new javax.swing.JTextField();
        jLabelFotografia = new javax.swing.JLabel();
        jTextFieldAreaVisitada = new javax.swing.JTextField();
        jLabelPersonaVisitada1 = new javax.swing.JLabel();
        jButtonTomarFotografia = new javax.swing.JButton();
        jLabelSemaforo = new javax.swing.JLabel();
        jLabelPersonaEstado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVisitas = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabelTipoDeDoc = new javax.swing.JLabel();
        jComboBoxTipoDoc = new javax.swing.JComboBox();
        jRadioButtonExtranjero = new javax.swing.JRadioButton();
        jRadioButtonNacional = new javax.swing.JRadioButton();
        jButtonCambiarEstadoPersona = new javax.swing.JButton();
        jButtonRegistrarSalida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de visita");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTextFieldPersona.setEditable(false);

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Nueva Visita");

        jTextFieldPersonaVisitada.setEditable(false);

        jLabel5.setText("Persona Visitada:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listMotivos, jComboBoxMotivo);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel6.setText("Motivo:");

        jLabel8.setText("Area Visitada:");

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

        jButtonActualizarFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/CAMARA.png"))); // NOI18N
        jButtonActualizarFotografia.setToolTipText("Actualizar Fotografia");
        jButtonActualizarFotografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarFotografiaActionPerformed(evt);
            }
        });

        DefaultMutableTreeNode root = UtilesArbol.crearArbol("ORGANIGRAMA", true);
        jTreePersonaVisitada = new JTree(root);
        jTreePersonaVisitada.setCellRenderer(new CustomIconRenderer());
        jTreePersonaVisitada.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreePersonaVisitadaValueChanged(evt);
            }
        });
        jScrollPanePersonasVisitadas.setViewportView(jTreePersonaVisitada);

        jLabelPersonaVisitada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPersonaVisitada.setForeground(new java.awt.Color(0, 0, 153));
        jLabelPersonaVisitada.setText("Visitante");

        jLabelDocNro.setFont(new java.awt.Font("Bookman Old Style", 0, 48)); // NOI18N
        jLabelDocNro.setForeground(new java.awt.Color(0, 0, 153));
        jLabelDocNro.setText("Nro Doc:");

        jTextFieldDocumentoPersona.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jTextFieldDocumentoPersona.setForeground(new java.awt.Color(204, 0, 0));
        jTextFieldDocumentoPersona.setToolTipText("Nro de Documento");
        jTextFieldDocumentoPersona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDocumentoPersonaFocusLost(evt);
            }
        });
        jTextFieldDocumentoPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDocumentoPersonaKeyPressed(evt);
            }
        });

        jLabelFotografia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg"))); // NOI18N
        jLabelFotografia.setToolTipText("Fotografia");
        jLabelFotografia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelFotografia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFotografiaMouseClicked(evt);
            }
        });

        jTextFieldAreaVisitada.setEditable(false);

        jLabelPersonaVisitada1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPersonaVisitada1.setForeground(new java.awt.Color(0, 0, 153));
        jLabelPersonaVisitada1.setText("Persona Visitada:");

        jButtonTomarFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/CAMARA.png"))); // NOI18N
        jButtonTomarFotografia.setToolTipText("Actualizar Fotografia");
        jButtonTomarFotografia.setEnabled(false);
        jButtonTomarFotografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTomarFotografiaActionPerformed(evt);
            }
        });

        jLabelSemaforo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSemaforo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/Semaforo_verde.png"))); // NOI18N

        jLabelPersonaEstado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPersonaEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPersonaEstado.setText("Persona Habilitada");

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listUltimasVisitas, jTableVisitas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechaIngreso}"));
        columnBinding.setColumnName("Fecha Visita");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${organizacionInterna.nombre}"));
        columnBinding.setColumnName("Area Visitada");
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${personaVisitada.nombre} ${personaVisitada.apellido}"));
        columnBinding.setColumnName("Persona Visitada");
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTableVisitas);
        jTableVisitas.getColumnModel().getColumn(0).setResizable(false);
        jTableVisitas.getColumnModel().getColumn(0).setCellRenderer(rendererTime);

        jLabel10.setText("Ultimas visitas");

        jLabelTipoDeDoc.setText("Tipo De doc:");

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listTipoDocumento, jComboBoxTipoDoc);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBoxTipoDoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTipoDocItemStateChanged(evt);
            }
        });
        jComboBoxTipoDoc.setSelectedItem(null);

        buttonGroupExtranjero.add(jRadioButtonExtranjero);
        jRadioButtonExtranjero.setText("Extranjero");
        jRadioButtonExtranjero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonExtranjeroItemStateChanged(evt);
            }
        });

        buttonGroupExtranjero.add(jRadioButtonNacional);
        jRadioButtonNacional.setSelected(true);
        jRadioButtonNacional.setText("Paraguayo");
        jRadioButtonNacional.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonNacionalItemStateChanged(evt);
            }
        });

        jButtonCambiarEstadoPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/check.gif"))); // NOI18N
        jButtonCambiarEstadoPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCambiarEstadoPersonaActionPerformed(evt);
            }
        });

        jButtonRegistrarSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon-clock.png"))); // NOI18N
        jButtonRegistrarSalida.setText("Salida");
        jButtonRegistrarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarSalidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButtonBuscarPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpiarPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPanePersonasVisitadas, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRegistrarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel2)
                                            .addComponent(jRadioButtonNacional, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextFieldOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextFieldPersonaVisitada)
                                                            .addComponent(jTextFieldAreaVisitada)
                                                            .addComponent(jComboBoxMotivo, 0, 413, Short.MAX_VALUE))
                                                        .addGap(148, 148, 148))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTextFieldPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButtonBuscarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButtonLimpiarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButtonTomarFotografia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButtonActualizarFotografia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jScrollPane1)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButtonExtranjero)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelTipoDeDoc)
                                                .addGap(10, 10, 10)
                                                .addComponent(jComboBoxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(32, 32, 32)
                                        .addComponent(jScrollPane2)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelPersonaEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelSemaforo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelFotografia, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButtonCambiarEstadoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelPersonaVisitada1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabelDocNro)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldDocumentoPersona)
                        .addGap(233, 233, 233))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabelPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonActualizarFotografia, jButtonBuscarPersona, jButtonLimpiarPersona, jButtonTomarFotografia});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelFotografia, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabelSemaforo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelPersonaEstado))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(108, 108, 108)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButtonExtranjero)
                                            .addComponent(jLabelTipoDeDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxTipoDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jRadioButtonNacional)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonLimpiarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonBuscarPersona))
                                        .addComponent(jButtonTomarFotografia)
                                        .addComponent(jButtonActualizarFotografia))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextFieldPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextFieldOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(jLabel10)
                                .addGap(45, 45, 45)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCambiarEstadoPersona)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPersonaVisitada1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDocNro)
                                    .addComponent(jTextFieldDocumentoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(123, 123, 123)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextFieldPersonaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextFieldAreaVisitada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jComboBoxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(107, 107, 107)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonGuardar)
                                    .addComponent(jButtonCancelar)
                                    .addComponent(jButtonRegistrarSalida)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPanePersonasVisitadas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBuscarPersonaVisitada)
                            .addComponent(jButtonLimpiarPersonaVisitada))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextFieldAreaVisitada, jTextFieldOrganizacion, jTextFieldPersona, jTextFieldPersonaVisitada});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelPersonaVisitada, jLabelPersonaVisitada1});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonActualizarFotografia, jButtonTomarFotografia});

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private boolean validar() {
        boolean resultado = true;
        if (persona == null || persona.getId() == null) {
            JOptionPane.showMessageDialog(this, "La persona es obligatorio", "Error", 0);
            resultado = false;
        }
        Organizacion o = areaVisitada;
        if ((personaVisitada == null || persona.getId() == null) && o.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe visitar una persona o area", "Error", 0);
            resultado = false;
        }
        return resultado;
    }

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        if (visita == null) {
            return;
        }

        //Validamos que la persona no se encuentre con una visita pendiente.
        if (action.findPendienteByPersona(persona)!=null) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "ATENCIÓN!. No es posible su ingreso hasta que se registre su salida", "Error", 0);
            return;
        }

        if (persona.getEstado() != null && persona.getEstado().getNombre().equals("INHABILITADO")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "ATENCIÓN!, la persona está inhabilitada. No es posible su ingreso", "Error", 0);
            return;
        }

        action = new VisitaAction(visita);

        if (!validar()) {
            return;//Implementar el método con las validaciones
        }

        visita.setPersona(persona);
        visita.setPersonaVisitada(personaVisitada);

        visita.setFechaIngreso(Calendar.getInstance().getTime());

        Organizacion o = areaVisitada;
        if (o.getId() != null) {
            visita.setOrganizacionInterna(o);
        } else {
            visita.setOrganizacionInterna(null);
        }

        visita.setMotivo((Motivo) jComboBoxMotivo.getSelectedItem());
        visita.setObservacion(jTextAreaObservacion.getText());
        visita.setAnulado("N");

        if (visita.getId() == null) {
            action.crear();
            JOptionPane.showMessageDialog(this, "Se ha creado con éxito", "Info", 1);
            imprimirTicket();
        } else {
            action.guardar();
            JOptionPane.showMessageDialog(this, "Se ha actualizado correctamente", "Info", 1);
        }

        this.dispose();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    public void cargarDatosVisita() {
        jTextFieldPersona.setText(visita.getPersona().getNombre() + ", " + visita.getPersona().getApellido());
        if (visita.getPersonaVisitada() != null && visita.getPersonaVisitada().getId() != null) {
            jTextFieldPersonaVisitada.setText(visita.getPersonaVisitada().getNombre() + ", " + visita.getPersonaVisitada().getApellido());
        }
        jTextFieldOrganizacion.setText(visita.getPersona().getOrganizacion().getNombre());
        jTextFieldAreaVisitada.setText(visita.getOrganizacionInterna().getNombre());
        jComboBoxMotivo.setSelectedItem(visita.getMotivo());
        jTextAreaObservacion.setText(visita.getObservacion());
    }

    private void imprimirTicket() {
        try {
            InputStream bis = generaBarCode(visita.getId().toString());
            Class.forName("org.postgresql.Driver");
            Connection conexion = EntityManagerCA.getConexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("reports/reporteTicketVisitas.jasper");
            //Parametros
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("idVis", (Object) visita.getId());
            //Paso el full path del proyecto
            java.io.File file = new java.io.File("");   //Dummy file
            String abspath = file.getAbsolutePath() + "/";
            parametros.put("pathImagen", (Object) abspath);
            parametros.put("barCode", bis);



//                //Fotografia
//                ByteArrayInputStream bis = new ByteArrayInputStream(visita.getPersona().getFotografia());
//                InputStream iS = bis;
//                parametros.put("fotografia",(Object)iS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

//            Muestra el Reporte en Pantalla
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
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
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
//        jTextFieldPersona.setText(motivo.getNombre());
//        jTextAreaObservacion.setText(motivo.getDescripcion());

        ///OCULTAMOS LOS BOTONES DE BUSQUEDA DE PERSONA VISITADA Y BORRADO DE PERSONA VISITADA. REEMPLAZADOS POR EL ARBOL
        jButtonBuscarPersonaVisitada.setVisible(false);
        jButtonLimpiarPersonaVisitada.setVisible(false);

        if (readOnly) {
            jButtonBuscarPersona.setVisible(false);
            jButtonBuscarPersonaVisitada.setVisible(false);
            jButtonLimpiarPersona.setVisible(false);
            jButtonLimpiarPersonaVisitada.setVisible(false);
            jComboBoxMotivo.setEnabled(false);
            jTextFieldPersona.setEditable(false);
            jTextFieldPersonaVisitada.setEditable(false);
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
        JDialogBuscador buscador = new JDialogBuscador(null, true);
        buscador.setSize(jFramePersona.getSize());
        //jFramePersona.setClosable(false);
        jFramePersona.setResizable(false);
        buscador.getjDesktopPaneBuscador().add(jFramePersona);
        buscador.setVisible(true);
        if (jFramePersona.getPersonaSeleccionada() != null) {
            persona = jFramePersona.getPersonaSeleccionada();
            if (persona != null) {
//                jButtonTomarFotografia.setEnabled(true);
//                //Cargar Fotografia
//                if (persona.getFotografiaPath() != null && !persona.getFotografiaPath().equals("")) {
//                    mostrarFotoPersona();
//                } else {
//                    jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg")));
//                }
                //  jTextFieldPersona.setText(persona.getNombre() + ", " + persona.getApellido());
                jTextFieldDocumentoPersona.setText(persona.getNumeroDocumento());
                actualizarDatos();
            }
            //jTextFieldOrganizacion.setText(persona.getOrganizacion().getNombre());

        }

    }//GEN-LAST:event_jButtonBuscarPersonaActionPerformed

    private void jButtonLimpiarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        personaVisitada = null;
        jTextFieldPersonaVisitada.setText(null);
        jTextFieldAreaVisitada.setText("");
    }//GEN-LAST:event_jButtonLimpiarPersonaVisitadaActionPerformed

    private void jButtonLimpiarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaActionPerformed
        // TODO add your handling code here:
        persona = null;
        jTextFieldPersona.setText(null);
        jTextFieldOrganizacion.setText("");
        jTextFieldDocumentoPersona.setText("");
        listUltimasVisitas.clear();
        jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg")));
    }//GEN-LAST:event_jButtonLimpiarPersonaActionPerformed

    private void jButtonActualizarFotografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarFotografiaActionPerformed
        // TODO add your handling code here:

        if (persona == null || persona.getId() == null) {
            JOptionPane.showMessageDialog(this, "Debe Elegir una Persona", "Error", 0);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        //Filtro De Extensiones
        FileFilterExtension filtroExtension = new FileFilterExtension("JPG, JPEG, PNG", new String[]{"JPG", "JPEG", "PNG"});
        chooser.setFileFilter(filtroExtension);
        //Desaparece la opcion TODOS LOS ARCHIVOS
        chooser.setAcceptAllFileFilterUsed(false);

        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            RandomAccessFile f;
            byte[] fotografiaByte = null;

            try {
                if (JOptionPane.showConfirmDialog(this, "Desea Actualizar la Fotografia?", "Actualizar Fotografia", 0) != 0) {
                    return;
                }
                f = new RandomAccessFile(chooser.getSelectedFile(), "r");
                fotografiaByte = new byte[(int) f.length()];
                f.read(fotografiaByte);
                persona.setFotografia(fotografiaByte);
                PersonaAction pAction = new PersonaAction();
                pAction.setPersona(persona);
                pAction.guardar();
                JOptionPane.showMessageDialog(this, "Se ha guardado con exito los nuevos Datos de Persona", "Info", 1);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(JDialogPersona.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JDialogPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonActualizarFotografiaActionPerformed

    private void jTreePersonaVisitadaValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreePersonaVisitadaValueChanged
        // TODO add your handling code here:
        if (jTreePersonaVisitada.getLastSelectedPathComponent() != null) {
            DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTreePersonaVisitada.getLastSelectedPathComponent();
            Object objSel = nodoSeleccionado.getUserObject();
            if (objSel.getClass().getSimpleName().equals("Persona")) {
                personaVisitada = (Persona) objSel;
                areaVisitada = personaVisitada.getOrganizacion();
                jTextFieldAreaVisitada.setText(areaVisitada.getNombre());
                jTextFieldPersonaVisitada.setText(jTreePersonaVisitada.getLastSelectedPathComponent().toString());
            } else if (objSel.getClass().getSimpleName().equals("Organizacion")) {
                personaVisitada = null;
                areaVisitada = (Organizacion) objSel;
                jTextFieldAreaVisitada.setText(areaVisitada.getNombre());
                jTextFieldPersonaVisitada.setText("");
            } else {
                personaVisitada = null;
                areaVisitada = null;
                jTextFieldPersonaVisitada.setText("");
                jTextFieldAreaVisitada.setText("");
            }

        }



    }//GEN-LAST:event_jTreePersonaVisitadaValueChanged

    private void jTextFieldDocumentoPersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDocumentoPersonaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            actualizarDatos();
        }
    }//GEN-LAST:event_jTextFieldDocumentoPersonaKeyPressed

    public void actualizarDatos() {

        if (jTextFieldDocumentoPersona.getText() != null && !jTextFieldDocumentoPersona.getText().equals("")) {
            //BUSCAR PERSONA EN BD LOCAL Y POLICIA
            persona = buscarPersona();

            if (persona != null && persona.getId() != null) {

                //Validamos que la persona no se encuentre con una visita pendiente.
                if (action.findPendienteByPersona(persona)!=null) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "ATENCIÓN!. No es posible su ingreso hasta que se registre su salida", "Error", 0); 
                    jButtonRegistrarSalida.setVisible(true);
                }else{
                    jButtonRegistrarSalida.setVisible(false);
                }

                //CARGAR DATOS DE LA PERSONA ENCONTRADA                                
                jTextFieldPersona.setText(persona.getNombre() + ", " + persona.getApellido());

                if (persona.getOrganizacion() != null) {
                    jTextFieldOrganizacion.setText(persona.getOrganizacion().getNombre());
                }

                jButtonTomarFotografia.setEnabled(true);

                if (persona.getEstado() != null && persona.getEstado().getNombre().equals("INHABILITADO")) {
                    jLabelSemaforo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/Semaforo_rojo.png")));
                    jLabelPersonaEstado.setText("PERSONA NO HABILITADA");
                    jButtonCambiarEstadoPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/check.gif")));
                    jButtonCambiarEstadoPersona.setToolTipText("HABILITAR");
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "ATENCIÓN!, la persona está inhabilitada. No es posible su ingreso", "Error", 0);
                } else {
                    jLabelSemaforo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/Semaforo_verde.png")));
                    jLabelPersonaEstado.setText("PERSONA HABILITADA");
                    jButtonCambiarEstadoPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/stop_sign.jpeg")));
                    jButtonCambiarEstadoPersona.setToolTipText("INHABILITAR");
                }

                //Ultimas Visitas
                listUltimasVisitas.clear();
                listUltimasVisitas.addAll(action.findByPersona(persona));

                //Cargar Fotografia
                if (persona.getFotografiaPath() != null && !persona.getFotografiaPath().equals("")) {
                    mostrarFotoPersona();
                }else{
                    if (JOptionPane.showConfirmDialog(this, "La persona no cuenta con Fotografia Actual, Desea cargar la Fotografia?", "Fotografia", 0) == 0) {
                    
                        JDialogFotografia dialogFotografia = new JDialogFotografia(null, rootPaneCheckingEnabled, "CAPTURAR");
                        dialogFotografia.setPersona(persona);
                        dialogFotografia.setVisible(true);        // TODO add your handling code here:
                        if (dialogFotografia.isCapturado()) {
                            mostrarFotoPersona();
                        }
                    }
                }

            } else if (registroDesdeBaseDeDatosExterna()) {
                //hacer el registro desde la base de datos externa
            } else {
                jTextFieldPersona.setText("");
                jTextFieldOrganizacion.setText("");
                jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg")));
                jButtonTomarFotografia.setEnabled(false);
                listUltimasVisitas.clear();
                if (JOptionPane.showConfirmDialog(this, "La persona no existe, desea registrarla?", "Persona no registrada", 0) != 0) {
                    this.setVisible(false);
                    return;
                }
                JDialogPersona dialogPersona = new JDialogPersona(null, rootPaneCheckingEnabled);
                dialogPersona.getjTextFieldNroDoc().setText(jTextFieldDocumentoPersona.getText());
                dialogPersona.getjTextFieldNroDoc().setEditable(false);
                dialogPersona.getjComboBoxTipoDocumento().setSelectedItem(jComboBoxTipoDoc.getSelectedItem());
                dialogPersona.getjComboBoxTipoDocumento().setEnabled(false);
                dialogPersona.setVisible(true);
                actualizarDatos();
                
                
            }
        } else {
            JOptionPane.showMessageDialog(this, "INGRESE NRO DE DOCUMENTO.", "Error", 0);
            jTextFieldPersona.setText("");
            jTextFieldOrganizacion.setText("");
            jButtonTomarFotografia.setEnabled(false);
            jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg")));
            listUltimasVisitas.clear();

        }

    }

    public JTextField getjTextFieldDocumentoPersona() {
        return jTextFieldDocumentoPersona;
    }

    public JComboBox getjComboBoxTipoDoc() {
        return jComboBoxTipoDoc;
    }

    private Persona buscarPersona() {
        Persona personaBuscada = new Persona();
        /// Se busca en la tabla de personas local
        List<Persona> listaPersonas = personaAction.findByNumeroDocumento(jTextFieldDocumentoPersona.getText().toUpperCase(), (TipoDocumento) jComboBoxTipoDoc.getSelectedItem());
        if (listaPersonas != null && listaPersonas.size() > 0) {
            personaBuscada = listaPersonas.get(0);
        }

        return personaBuscada;
    }
    private void jTextFieldDocumentoPersonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDocumentoPersonaFocusLost
        // TODO add your handling code here:
        if (jTextFieldDocumentoPersona.getText() != null && !jTextFieldDocumentoPersona.getText().equals(persona.getNumeroDocumento()) ) {
            actualizarDatos();     
        }


    }//GEN-LAST:event_jTextFieldDocumentoPersonaFocusLost
    private void mostrarFotoPersona() {
        BufferedImage image;
        try {
            String rutaImagen = persona.getFotografiaPath();
            File fileImagen = new File(rutaImagen);
            image = ImageIO.read(fileImagen);
            //REGULAR TAMAÑO    
            Image imageScale = image.getScaledInstance(150, 100, image.SCALE_FAST);
            ImageIcon iconoFoto = new javax.swing.ImageIcon(imageScale);
            jLabelFotografia.setIcon(iconoFoto);
        } catch (IOException ex) {
            Logger.getLogger(JDialogPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///CARGAR FOTO DESDE LA BASE DE DATOS SUPLANTADO POR LECTURA DESDE ARCHIVO DIRECTO
//                            ByteArrayInputStream bis = new ByteArrayInputStream(persona.getFotografia());
//                            BufferedImage image;
//                            
//                            try {
//                                image = ImageIO.read(bis);
//                                //REGULAR TAMAÑO    
//                                Image imageScale =  image.getScaledInstance(150, 100, image.SCALE_FAST);
//                                ImageIcon iconoFoto = new javax.swing.ImageIcon(imageScale);
//                                
//                                jLabelFotografia.setIcon(iconoFoto);
//                            } catch (IOException ex) {
//                                Logger.getLogger(JDialogPersona.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//             
    }

    private void jButtonBuscarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        JInternalFramePersona jFramePersona = new JInternalFramePersona();
        jFramePersona.setModoBuscador(true);
        jFramePersona.setTitle("Buscador de Personas Internas");
        jFramePersona.setTipoOrganizacionPersona("INTERNA");
        jFramePersona.setVisible(true);
        JDialogBuscador buscador = new JDialogBuscador(null, true);
        buscador.setSize(jFramePersona.getSize());
        //jFramePersona.setClosable(false);
        jFramePersona.setResizable(false);
        buscador.getjDesktopPaneBuscador().add(jFramePersona);
        buscador.setVisible(true);
        if (jFramePersona.getPersonaSeleccionada() != null) {
            personaVisitada = jFramePersona.getPersonaSeleccionada();
            if (personaVisitada != null) {
                jTextFieldPersonaVisitada.setText(personaVisitada.getNombre() + ", " + personaVisitada.getApellido());
            }
            jTextFieldAreaVisitada.setText(personaVisitada.getOrganizacion().getNombre());
        }
    }//GEN-LAST:event_jButtonBuscarPersonaVisitadaActionPerformed

    private void jButtonTomarFotografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTomarFotografiaActionPerformed
        JDialogFotografia dialogFotografia = new JDialogFotografia(null, rootPaneCheckingEnabled, "CAPTURAR");
        dialogFotografia.setPersona(persona);
        dialogFotografia.setVisible(true);        // TODO add your handling code here:
        if (dialogFotografia.isCapturado()) {
            mostrarFotoPersona();
        }
    }//GEN-LAST:event_jButtonTomarFotografiaActionPerformed

    private void jLabelFotografiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFotografiaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && persona != null && !persona.getFotografiaPath().equals("")) {
            JDialogFotografia dialogFotografia = new JDialogFotografia(null, rootPaneCheckingEnabled, "VER");
            dialogFotografia.setPersona(persona);
            dialogFotografia.setVisible(true);
        }
    }//GEN-LAST:event_jLabelFotografiaMouseClicked

    private boolean registroDesdeBaseDeDatosExterna() {
        return false;
    }

    private void jRadioButtonExtranjeroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonExtranjeroItemStateChanged
        // TODO add your handling code here:
        jComboBoxTipoDoc.setVisible(true);
        jLabelTipoDeDoc.setVisible(true);
    }//GEN-LAST:event_jRadioButtonExtranjeroItemStateChanged

    private void jRadioButtonNacionalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonNacionalItemStateChanged
        // TODO add your handling code here:
        jComboBoxTipoDoc.setVisible(false);
        jLabelTipoDeDoc.setVisible(false);
        jComboBoxTipoDoc.setSelectedItem(tipoDocAction.findByNamedQuery("TipoDocumento.findCI").get(0));
    }//GEN-LAST:event_jRadioButtonNacionalItemStateChanged

    private void jComboBoxTipoDocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoDocItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && jTextFieldDocumentoPersona.getText() != null && !jTextFieldDocumentoPersona.getText().equals("")) {
            actualizarDatos();
        }
    }//GEN-LAST:event_jComboBoxTipoDocItemStateChanged

    private void jButtonCambiarEstadoPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCambiarEstadoPersonaActionPerformed
        // TODO add your handling code here:
        personaAction.setPersona(persona);
        if (persona.getEstado() != null && persona.getEstado().getNombre().equals("INHABILITADO")) {
            personaAction.habilitar();
            jLabelSemaforo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/Semaforo_verde.png")));
            jLabelPersonaEstado.setText("PERSONA HABILITADA");
            jButtonCambiarEstadoPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/stop_sign.jpeg")));
            jButtonCambiarEstadoPersona.setToolTipText("INHABILITAR");

        } else {
            personaAction.inhabilitar();
            jLabelSemaforo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/Semaforo_rojo.png")));
            jLabelPersonaEstado.setText("PERSONA NO HABILITADA");
            jButtonCambiarEstadoPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/check.gif")));
            jButtonCambiarEstadoPersona.setToolTipText("HABILITAR");
        }
    }//GEN-LAST:event_jButtonCambiarEstadoPersonaActionPerformed

    private void jButtonRegistrarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarSalidaActionPerformed
        // TODO add your handling code here:
                Visita v = action.findPendienteByPersona(persona);
    
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                if (JOptionPane.showConfirmDialog(this, "Está seguro que desea marcar la salida de "
                        + v.getPersona().getNombre()
                        + " " + v.getPersona().getApellido()
                        + ", a las: " + sdf.format(c.getTime())
                        + "?", "Registro de salida", 0) != 0) {
                    return;
                }

                v.setFechaSalida(c.getTime());
                action.setVisita(v);
                action.guardar();
                listUltimasVisitas.clear();
                listUltimasVisitas.addAll(action.findByPersona(persona));
                JOptionPane.showMessageDialog(this, "Se ha registrado la salida correctamente", "Info", 1);
                jButtonRegistrarSalida.setVisible(false);
    }//GEN-LAST:event_jButtonRegistrarSalidaActionPerformed

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
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
    private javax.swing.ButtonGroup buttonGroupExtranjero;
    private javax.swing.JButton jButtonActualizarFotografia;
    private javax.swing.JButton jButtonBuscarPersona;
    private javax.swing.JButton jButtonBuscarPersonaVisitada;
    private javax.swing.JButton jButtonCambiarEstadoPersona;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiarPersona;
    private javax.swing.JButton jButtonLimpiarPersonaVisitada;
    private javax.swing.JButton jButtonRegistrarSalida;
    private javax.swing.JButton jButtonTomarFotografia;
    private javax.swing.JComboBox jComboBoxMotivo;
    private javax.swing.JComboBox jComboBoxTipoDoc;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelDocNro;
    private javax.swing.JLabel jLabelFotografia;
    private javax.swing.JLabel jLabelPersonaEstado;
    private javax.swing.JLabel jLabelPersonaVisitada;
    private javax.swing.JLabel jLabelPersonaVisitada1;
    private javax.swing.JLabel jLabelSemaforo;
    private javax.swing.JLabel jLabelTipoDeDoc;
    private javax.swing.JRadioButton jRadioButtonExtranjero;
    private javax.swing.JRadioButton jRadioButtonNacional;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPanePersonasVisitadas;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableVisitas;
    private javax.swing.JTextArea jTextAreaObservacion;
    private javax.swing.JTextField jTextFieldAreaVisitada;
    private javax.swing.JTextField jTextFieldDocumentoPersona;
    private javax.swing.JTextField jTextFieldOrganizacion;
    private javax.swing.JTextField jTextFieldPersona;
    private javax.swing.JTextField jTextFieldPersonaVisitada;
    private javax.swing.JTree jTreePersonaVisitada;
    private java.util.List listMotivos;
    private java.util.List listOrganizacionInterna;
    private java.util.List listTipoDocumento;
    private java.util.List listUltimasVisitas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
