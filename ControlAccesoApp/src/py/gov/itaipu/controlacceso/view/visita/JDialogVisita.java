/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.visita;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
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
import py.gov.itaipu.controlacceso.action.persona.PersonaAction;
import py.gov.itaipu.controlacceso.action.visita.VisitaAction;
import py.gov.itaipu.controlacceso.model.Motivo;
import py.gov.itaipu.controlacceso.model.Nacionalidad;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.TipoDocumento;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.model.exception.EntidadExiste;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.utils.tree.CustomIconRenderer;
import py.gov.itaipu.controlacceso.utils.tree.UtilesArbol;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;
import py.gov.itaipu.controlacceso.view.AdminCamera;
import py.gov.itaipu.controlacceso.view.JDialogAbout;
import py.gov.itaipu.controlacceso.view.JDialogBuscador;
import py.gov.itaipu.controlacceso.view.TimeRenderer;
import py.gov.itaipu.controlacceso.view.administracion.organizacion.JDialogOrganizacionExterna;
import py.gov.itaipu.controlacceso.view.administracion.organizacion.JInternalFrameOrganizacion;
import py.gov.itaipu.controlacceso.view.administracion.parametrogeneral.JDialogMotivo;
import py.gov.itaipu.controlacceso.view.administracion.parametrogeneral.JDialogTipoDocumento;
import py.gov.itaipu.controlacceso.view.administracion.parametrogeneral.JDialogoNacionalidad;
import py.gov.itaipu.controlacceso.view.persona.JInternalFramePersona;

/**
 *
 * @author vimartih
 */
public class JDialogVisita extends javax.swing.JDialog {

    private VisitaAction visitaAction;
    private CRUDAction<Nacionalidad> nacionalidadAction;
    private CRUDAction<TipoDocumento> tipoDocAction;
    private CRUDAction<Motivo> motivoAction;
    private PersonaAction personaAction;
    private Persona persona;
    private TableCellRenderer rendererTime;
    private Persona personaVisitada;
    private Organizacion areaVisitada;

    /**
     * Creates new form JDialogVisita
     */
    public JDialogVisita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            nacionalidadAction = new CRUDAction(new Nacionalidad());
            tipoDocAction = new CRUDAction(new TipoDocumento());
            motivoAction = new CRUDAction(new Motivo());
            persona = new Persona();
            personaAction = new PersonaAction(persona);
            visitaAction = new VisitaAction(new Visita());
            rendererTime = new TimeRenderer();
            personaVisitada = new Persona();
            areaVisitada = new Organizacion();
            initComponents();
            jButtonNuevoTipoDoc.setVisible(false);
            jButtonEditTipoDoc.setVisible(false);
            jComboBoxTipoDoc.setVisible(false);
            jComboBoxTipoDoc.setSelectedItem(tipoDocAction.findByNamedQuery("TipoDocumento.findCI").get(0));
            inhabilitarEdicionPersona();
            jButtonNuevaVisita.setVisible(false);
            jTextFieldDocumentoPersona.requestFocusInWindow();
            this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/resource/img/bandera-paraguay.png")).getImage());
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    private void fillPersona(Persona p) {
        p.setApellido(jTextFieldApellido.getText());
        p.setNombre(jTextFieldNombre.getText());
        p.setNumeroDocumento(jTextFieldDocumentoPersona.getText());
        p.setFechaNacimiento((Date) jFormattedTextFieldFechaNac.getValue());
        p.setEstadoCivil(jComboBoxEstadoCivil.getSelectedItem().toString());
        p.setSexo(jComboBoxSexo.getSelectedItem().toString());
        p.setNacionalidad((Nacionalidad) listNacionalidades.get(jComboBoxNacionalidad.getSelectedIndex()));
        p.setTipoDocumento((TipoDocumento) listTipoDocumento.get(jComboBoxTipoDoc.getSelectedIndex()));
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

        try{
            listNacionalidades = nacionalidadAction.findAll();
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        try{
            listTipoDocumento = ObservableCollections.observableList(tipoDocAction.findAll());
            buttonGroupExtranjero = new javax.swing.ButtonGroup();
            try{
                listMotivos = ObservableCollections.observableList(motivoAction.findAll());
                try{
                    listUltimasVisitas = ObservableCollections.observableList(visitaAction.findByPersona(persona));
                    jPanelArbolVisita = new javax.swing.JPanel();
                    jLabel1 = new javax.swing.JLabel();
                    jSeparator2 = new javax.swing.JSeparator();
                    jRadioButtonNacional = new javax.swing.JRadioButton();
                    jRadioButtonExtranjero = new javax.swing.JRadioButton();
                    jComboBoxTipoDoc = new javax.swing.JComboBox();
                    jTextFieldDocumentoPersona = new javax.swing.JTextField();
                    jButtonNuevoTipoDoc = new javax.swing.JButton();
                    jButtonEditTipoDoc = new javax.swing.JButton();
                    jLabelMensaje = new javax.swing.JLabel();
                    jLabel3 = new javax.swing.JLabel();
                    jTextFieldFiltroArbol = new javax.swing.JTextField();
                    jButtonBuscarPersonaVisitada = new javax.swing.JButton();
                    jButtonLimpiarPersonaVisitada = new javax.swing.JButton();
                    jScrollPanePersonasVisitadas = new javax.swing.JScrollPane();
                    jTreePersonaVisitada = new javax.swing.JTree();
                    jButtonNuevaVisita = new javax.swing.JButton();
                    jLabelIconStatus = new javax.swing.JLabel();
                    jCheckBoxReimprimir = new javax.swing.JCheckBox();
                    jButtonVisitasActivas = new javax.swing.JButton();
                    jPanelVisitante = new javax.swing.JPanel();
                    jLabel2 = new javax.swing.JLabel();
                    jTextFieldNombre = new javax.swing.JTextField();
                    jTextFieldApellido = new javax.swing.JTextField();
                    jComboBoxNacionalidad = new javax.swing.JComboBox();
                    jComboBoxSexo = new javax.swing.JComboBox();
                    jComboBoxEstadoCivil = new javax.swing.JComboBox();
                    jButtonNacionalidad = new javax.swing.JButton();
                    jFormattedTextFieldFechaNac = new javax.swing.JFormattedTextField();
                    jTextFieldOrganizacion = new javax.swing.JTextField();
                    jButtonBuscarOrganizacion = new javax.swing.JButton();
                    jButtonNuevoOrganizacion = new javax.swing.JButton();
                    jLabelNomb = new javax.swing.JLabel();
                    jLabelApe = new javax.swing.JLabel();
                    jLabelNAcion = new javax.swing.JLabel();
                    jLabelEstCiv1 = new javax.swing.JLabel();
                    jLabelEstCiv = new javax.swing.JLabel();
                    jLabelOrganizacion = new javax.swing.JLabel();
                    jLabelFechaNac = new javax.swing.JLabel();
                    jSeparator1 = new javax.swing.JSeparator();
                    jLabelFotografia = new javax.swing.JLabel();
                    jButtonTomarFotografia = new javax.swing.JButton();
                    jButtonNacionalidadEdit = new javax.swing.JButton();
                    jButtonAbout = new javax.swing.JButton();
                    jButtonAyuda = new javax.swing.JButton();
                    jPanelVisita = new javax.swing.JPanel();
                    jLabel4 = new javax.swing.JLabel();
                    jSeparator3 = new javax.swing.JSeparator();
                    jLabel8 = new javax.swing.JLabel();
                    jTextFieldAreaVisitada = new javax.swing.JTextField();
                    jLabel6 = new javax.swing.JLabel();
                    jComboBoxMotivo = new javax.swing.JComboBox();
                    jLabel5 = new javax.swing.JLabel();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    jTextAreaObservacion = new javax.swing.JTextArea();
                    jLabel7 = new javax.swing.JLabel();
                    jTextFieldPersonaVisitada = new javax.swing.JTextField();
                    jButtonNuevoMotivo = new javax.swing.JButton();
                    jButtonMotivoEdit = new javax.swing.JButton();
                    jPanelInformativo = new javax.swing.JPanel();
                    jScrollPane2 = new javax.swing.JScrollPane();
                    jTableVisitas = new javax.swing.JTable();
                    jLabel10 = new javax.swing.JLabel();
                    jSeparator4 = new javax.swing.JSeparator();

                }catch (py.gov.itaipu.controlacceso.model.exception.ErrorInesperado ei) {
                    JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }

            } catch (ErrorInesperado ei) {
                JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }

        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de visita");
        setModalityType(null);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanelArbolVisita.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Número de documento.");
        jPanelArbolVisita.add(jLabel1);
        jLabel1.setBounds(10, 10, 160, 15);
        jPanelArbolVisita.add(jSeparator2);
        jSeparator2.setBounds(10, 30, 380, 10);

        buttonGroupExtranjero.add(jRadioButtonNacional);
        jRadioButtonNacional.setSelected(true);
        jRadioButtonNacional.setText("Paraguayo");
        jRadioButtonNacional.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonNacionalItemStateChanged(evt);
            }
        });
        jPanelArbolVisita.add(jRadioButtonNacional);
        jRadioButtonNacional.setBounds(10, 40, 100, 25);

        buttonGroupExtranjero.add(jRadioButtonExtranjero);
        jRadioButtonExtranjero.setText("Extranjero");
        jRadioButtonExtranjero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonExtranjeroItemStateChanged(evt);
            }
        });
        jPanelArbolVisita.add(jRadioButtonExtranjero);
        jRadioButtonExtranjero.setBounds(120, 40, 100, 25);

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listTipoDocumento, jComboBoxTipoDoc);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBoxTipoDoc.setSelectedItem(null);
        jPanelArbolVisita.add(jComboBoxTipoDoc);
        jComboBoxTipoDoc.setBounds(220, 40, 130, 25);

        jTextFieldDocumentoPersona.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextFieldDocumentoPersona.setForeground(new java.awt.Color(204, 0, 0));
        jTextFieldDocumentoPersona.setToolTipText("Nro de Documento");
        jTextFieldDocumentoPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDocumentoPersonaKeyPressed(evt);
            }
        });
        jPanelArbolVisita.add(jTextFieldDocumentoPersona);
        jTextFieldDocumentoPersona.setBounds(10, 70, 300, 43);

        jButtonNuevoTipoDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/new.jpg"))); // NOI18N
        jButtonNuevoTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoTipoDocActionPerformed(evt);
            }
        });
        jPanelArbolVisita.add(jButtonNuevoTipoDoc);
        jButtonNuevoTipoDoc.setBounds(350, 40, 20, 20);

        jButtonEditTipoDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/edit.png"))); // NOI18N
        jButtonEditTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditTipoDocActionPerformed(evt);
            }
        });
        jPanelArbolVisita.add(jButtonEditTipoDoc);
        jButtonEditTipoDoc.setBounds(370, 40, 20, 20);

        jLabelMensaje.setForeground(new java.awt.Color(153, 0, 0));
        jLabelMensaje.setText("Introduzca un número de documento.");
        jPanelArbolVisita.add(jLabelMensaje);
        jLabelMensaje.setBounds(10, 116, 310, 14);

        jLabel3.setText("Area/Persona: ");
        jPanelArbolVisita.add(jLabel3);
        jLabel3.setBounds(10, 150, 130, 14);
        jPanelArbolVisita.add(jTextFieldFiltroArbol);
        jTextFieldFiltroArbol.setBounds(90, 150, 170, 20);

        jButtonBuscarPersonaVisitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarPersonaVisitada.setToolTipText("Buscar Persona");
        jButtonBuscarPersonaVisitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPersonaVisitadaActionPerformed(evt);
            }
        });
        jPanelArbolVisita.add(jButtonBuscarPersonaVisitada);
        jButtonBuscarPersonaVisitada.setBounds(260, 150, 20, 20);

        jButtonLimpiarPersonaVisitada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonLimpiarPersonaVisitada.setToolTipText("Buscar Persona");
        jButtonLimpiarPersonaVisitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPersonaVisitadaActionPerformed(evt);
            }
        });
        jPanelArbolVisita.add(jButtonLimpiarPersonaVisitada);
        jButtonLimpiarPersonaVisitada.setBounds(280, 150, 20, 20);

        DefaultMutableTreeNode root = null;
        try{
            root = UtilesArbol.crearArbol("ORGANIGRAMA", true,false,false);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        jTreePersonaVisitada = new JTree(root);
        jTreePersonaVisitada.setCellRenderer(new CustomIconRenderer());
        jTreePersonaVisitada.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreePersonaVisitadaValueChanged(evt);
            }
        });
        jScrollPanePersonasVisitadas.setViewportView(jTreePersonaVisitada);

        jPanelArbolVisita.add(jScrollPanePersonasVisitadas);
        jScrollPanePersonasVisitadas.setBounds(10, 180, 380, 340);

        jButtonNuevaVisita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/save48.png"))); // NOI18N
        jButtonNuevaVisita.setToolTipText("Registrar Visita");
        jButtonNuevaVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaVisitaActionPerformed(evt);
            }
        });
        jPanelArbolVisita.add(jButtonNuevaVisita);
        jButtonNuevaVisita.setBounds(340, 70, 48, 48);
        jPanelArbolVisita.add(jLabelIconStatus);
        jLabelIconStatus.setBounds(340, 70, 50, 50);

        jCheckBoxReimprimir.setText("Reimprimir");
        jPanelArbolVisita.add(jCheckBoxReimprimir);
        jCheckBoxReimprimir.setBounds(310, 80, 90, 20);

        jButtonVisitasActivas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/clear.jpeg"))); // NOI18N
        jButtonVisitasActivas.setToolTipText("Buscar Persona");
        jButtonVisitasActivas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitasActivasActionPerformed(evt);
            }
        });
        jPanelArbolVisita.add(jButtonVisitasActivas);
        jButtonVisitasActivas.setBounds(360, 150, 20, 20);

        getContentPane().add(jPanelArbolVisita);
        jPanelArbolVisita.setBounds(0, 0, 400, 520);

        jPanelVisitante.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Visitante.");
        jPanelVisitante.add(jLabel2);
        jLabel2.setBounds(10, 10, 100, 15);
        jPanelVisitante.add(jTextFieldNombre);
        jTextFieldNombre.setBounds(140, 40, 180, 22);
        jPanelVisitante.add(jTextFieldApellido);
        jTextFieldApellido.setBounds(140, 70, 180, 22);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listNacionalidades, jComboBoxNacionalidad);
        bindingGroup.addBinding(jComboBoxBinding);

        jPanelVisitante.add(jComboBoxNacionalidad);
        jComboBoxNacionalidad.setBounds(140, 160, 180, 22);

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MASCULINO", "FEMENINO" }));
        jPanelVisitante.add(jComboBoxSexo);
        jComboBoxSexo.setBounds(140, 190, 180, 22);

        jComboBoxEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SOLTERO", "CASADO", "DIVORCIADO", "VIUDO" }));
        jPanelVisitante.add(jComboBoxEstadoCivil);
        jComboBoxEstadoCivil.setBounds(140, 100, 180, 22);

        jButtonNacionalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/new.jpg"))); // NOI18N
        jButtonNacionalidad.setFocusable(false);
        jButtonNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNacionalidadActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonNacionalidad);
        jButtonNacionalidad.setBounds(320, 160, 20, 20);

        jFormattedTextFieldFechaNac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jPanelVisitante.add(jFormattedTextFieldFechaNac);
        jFormattedTextFieldFechaNac.setBounds(140, 130, 180, 22);

        jTextFieldOrganizacion.setEditable(false);
        jPanelVisitante.add(jTextFieldOrganizacion);
        jTextFieldOrganizacion.setBounds(140, 220, 180, 22);

        jButtonBuscarOrganizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/view.png"))); // NOI18N
        jButtonBuscarOrganizacion.setToolTipText("Buscar Organizacion");
        jButtonBuscarOrganizacion.setFocusable(false);
        jButtonBuscarOrganizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarOrganizacionActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonBuscarOrganizacion);
        jButtonBuscarOrganizacion.setBounds(320, 220, 20, 20);

        jButtonNuevoOrganizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/new.jpg"))); // NOI18N
        jButtonNuevoOrganizacion.setFocusable(false);
        jButtonNuevoOrganizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoOrganizacionActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonNuevoOrganizacion);
        jButtonNuevoOrganizacion.setBounds(340, 220, 20, 20);

        jLabelNomb.setText("* Nombre:");
        jPanelVisitante.add(jLabelNomb);
        jLabelNomb.setBounds(10, 40, 80, 22);

        jLabelApe.setText("* Apellido:");
        jPanelVisitante.add(jLabelApe);
        jLabelApe.setBounds(10, 70, 80, 22);

        jLabelNAcion.setText("Nacionalidad:");
        jPanelVisitante.add(jLabelNAcion);
        jLabelNAcion.setBounds(10, 160, 80, 22);

        jLabelEstCiv1.setText("Sexo:");
        jPanelVisitante.add(jLabelEstCiv1);
        jLabelEstCiv1.setBounds(10, 190, 80, 22);

        jLabelEstCiv.setText("Estado Civil:");
        jPanelVisitante.add(jLabelEstCiv);
        jLabelEstCiv.setBounds(10, 100, 80, 22);

        jLabelOrganizacion.setText("Organizacion:");
        jPanelVisitante.add(jLabelOrganizacion);
        jLabelOrganizacion.setBounds(10, 220, 80, 22);

        jLabelFechaNac.setText("Fecha de Nacimiento:");
        jPanelVisitante.add(jLabelFechaNac);
        jLabelFechaNac.setBounds(10, 130, 140, 22);
        jPanelVisitante.add(jSeparator1);
        jSeparator1.setBounds(10, 30, 725, 10);

        jLabelFotografia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg"))); // NOI18N
        jLabelFotografia.setToolTipText("Fotografia");
        jLabelFotografia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelFotografia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFotografiaMouseClicked(evt);
            }
        });
        jPanelVisitante.add(jLabelFotografia);
        jLabelFotografia.setBounds(460, 70, 270, 180);

        jButtonTomarFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/CAMARA.png"))); // NOI18N
        jButtonTomarFotografia.setToolTipText("Actualizar Fotografia");
        jButtonTomarFotografia.setEnabled(false);
        jButtonTomarFotografia.setFocusable(false);
        jButtonTomarFotografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTomarFotografiaActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonTomarFotografia);
        jButtonTomarFotografia.setBounds(460, 40, 270, 25);

        jButtonNacionalidadEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/edit.png"))); // NOI18N
        jButtonNacionalidadEdit.setFocusable(false);
        jButtonNacionalidadEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNacionalidadEditActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonNacionalidadEdit);
        jButtonNacionalidadEdit.setBounds(340, 160, 20, 20);

        jButtonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/icon_about.png"))); // NOI18N
        jButtonAbout.setToolTipText("Acerca de...");
        jButtonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAboutActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonAbout);
        jButtonAbout.setBounds(690, 0, 20, 20);

        jButtonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/help.jpeg"))); // NOI18N
        jButtonAyuda.setToolTipText("Ayuda");
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });
        jPanelVisitante.add(jButtonAyuda);
        jButtonAyuda.setBounds(710, 0, 20, 20);

        getContentPane().add(jPanelVisitante);
        jPanelVisitante.setBounds(400, 0, 860, 260);

        jPanelVisita.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Visita.");
        jPanelVisita.add(jLabel4);
        jLabel4.setBounds(10, 10, 100, 15);
        jPanelVisita.add(jSeparator3);
        jSeparator3.setBounds(10, 30, 725, 10);

        jLabel8.setText("Area Visitada:");
        jPanelVisita.add(jLabel8);
        jLabel8.setBounds(10, 70, 90, 14);

        jTextFieldAreaVisitada.setEditable(false);
        jPanelVisita.add(jTextFieldAreaVisitada);
        jTextFieldAreaVisitada.setBounds(140, 70, 180, 22);

        jLabel6.setText("Motivo:");
        jPanelVisita.add(jLabel6);
        jLabel6.setBounds(10, 40, 90, 14);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listMotivos, jComboBoxMotivo);
        bindingGroup.addBinding(jComboBoxBinding);

        jPanelVisita.add(jComboBoxMotivo);
        jComboBoxMotivo.setBounds(140, 40, 180, 22);

        jLabel5.setText("Observación:");
        jPanelVisita.add(jLabel5);
        jLabel5.setBounds(370, 40, 90, 14);

        jTextAreaObservacion.setColumns(20);
        jTextAreaObservacion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacion);

        jPanelVisita.add(jScrollPane1);
        jScrollPane1.setBounds(460, 40, 270, 80);

        jLabel7.setText("Persona Visitada:");
        jPanelVisita.add(jLabel7);
        jLabel7.setBounds(10, 100, 100, 14);

        jTextFieldPersonaVisitada.setEditable(false);
        jPanelVisita.add(jTextFieldPersonaVisitada);
        jTextFieldPersonaVisitada.setBounds(140, 100, 180, 22);

        jButtonNuevoMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/new.jpg"))); // NOI18N
        jButtonNuevoMotivo.setToolTipText("Actualizar Fotografia");
        jButtonNuevoMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoMotivoActionPerformed(evt);
            }
        });
        jPanelVisita.add(jButtonNuevoMotivo);
        jButtonNuevoMotivo.setBounds(320, 40, 20, 20);

        jButtonMotivoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/edit.png"))); // NOI18N
        jButtonMotivoEdit.setFocusable(false);
        jButtonMotivoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMotivoEditActionPerformed(evt);
            }
        });
        jPanelVisita.add(jButtonMotivoEdit);
        jButtonMotivoEdit.setBounds(340, 40, 20, 20);

        getContentPane().add(jPanelVisita);
        jPanelVisita.setBounds(400, 260, 860, 130);

        jPanelInformativo.setPreferredSize(new java.awt.Dimension(930, 210));
        jPanelInformativo.setLayout(null);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listUltimasVisitas, jTableVisitas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechaIngreso}"));
        columnBinding.setColumnName("Fecha Visita");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${organizacionInterna.nombre}"));
        columnBinding.setColumnName("Area Visitada");
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${personaVisitada.nombre} ${personaVisitada.apellido}"));
        columnBinding.setColumnName("Persona Visitada");
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTableVisitas);
        jTableVisitas.getColumnModel().getColumn(0).setCellRenderer(rendererTime);

        jPanelInformativo.add(jScrollPane2);
        jScrollPane2.setBounds(10, 40, 720, 90);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Ultimas visitas.");
        jPanelInformativo.add(jLabel10);
        jLabel10.setBounds(10, 10, 90, 15);
        jPanelInformativo.add(jSeparator4);
        jSeparator4.setBounds(10, 30, 725, 10);

        getContentPane().add(jPanelInformativo);
        jPanelInformativo.setBounds(400, 390, 860, 150);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gestionVisita() {
        try {
            //En caso que la persona tenga visita pendiente procedemos al registro de la salida
            Visita v = visitaAction.findPendienteByPersona(persona);
            if (v == null) {
                jLabelMensaje.setText("Introduzca los datos de la visita.");
                jButtonNuevaVisita.setVisible(true);
                jCheckBoxReimprimir.setVisible(false);
                jTextFieldFiltroArbol.requestFocusInWindow();
                jLabelIconStatus.setIcon(null);
            } else {
                if (jCheckBoxReimprimir.isSelected()) {
                    imprimirTicket(v);
                    jButtonNuevaVisita.setVisible(false);
                    jCheckBoxReimprimir.setVisible(true);
                    limpiarDatosPersona();
                    inhabilitarEdicionPersona();
                    jTextFieldDocumentoPersona.setText("");
                    jTextFieldDocumentoPersona.requestFocusInWindow();
                    jLabelIconStatus.setIcon(null);
                    jLabelMensaje.setText("Reimpresión.");
                } else {
                    registrarSalida(v);
                    jButtonNuevaVisita.setVisible(false);
                    jCheckBoxReimprimir.setVisible(true);
                    limpiarDatosPersona();
                    inhabilitarEdicionPersona();
                    jTextFieldDocumentoPersona.setText("");
                    jTextFieldDocumentoPersona.requestFocusInWindow();
                    jLabelIconStatus.setIcon(null);
                    jLabelMensaje.setText("Registro de salida con éxito.");
                }
            }
        } catch (ErrorInesperado ex) {
            Logger.getLogger(JDialogVisita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarVisita() throws ErrorInesperado {
        Visita visita = new Visita();
        visitaAction.setVisita(visita);
        visita.setPersona(persona);
        visita.setPersonaVisitada(personaVisitada);

        visita.setFechaIngreso(Calendar.getInstance().getTime());
        visita.setOrganizacionInterna(areaVisitada);

        visita.setMotivo((Motivo) jComboBoxMotivo.getSelectedItem());
        visita.setObservacion(jTextAreaObservacion.getText());
        visita.setAnulado("N");
        visitaAction.crear();
        if (JOptionPane.showConfirmDialog(this, "Desea imprimir el ticket?", "Visita registrada", 0) == 0) {
            imprimirTicket(visita);
        }
        jLabelMensaje.setText("Visita registrada.");

    }

    private void registrarSalida(Visita v) throws ErrorInesperado {
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
        visitaAction.setVisita(v);
        visitaAction.guardar();
        JOptionPane.showMessageDialog(this, "Se ha registrado la salida correctamente", "Info", 1);
    }

    private void cargarDatosPersona() throws ErrorInesperado {
        jTextFieldApellido.setText(persona.getApellido().toUpperCase());
        jTextFieldNombre.setText(persona.getNombre().toUpperCase());
        jTextFieldDocumentoPersona.setText(persona.getNumeroDocumento().toUpperCase());

        if (persona.getOrganizacion() != null) {
            jTextFieldOrganizacion.setText((persona.getOrganizacion().getNombre()));
        } else {
            jTextFieldOrganizacion.setText("");
        }
        jFormattedTextFieldFechaNac.setValue(persona.getFechaNacimiento());
        jComboBoxEstadoCivil.setSelectedItem(persona.getEstadoCivil().toUpperCase());
        jComboBoxSexo.setSelectedItem(persona.getSexo().toUpperCase());
        jComboBoxNacionalidad.setSelectedItem(persona.getNacionalidad());
        jComboBoxTipoDoc.setSelectedItem(persona.getTipoDocumento());
        listUltimasVisitas.clear();
        listUltimasVisitas.addAll(visitaAction.findByPersona(persona));
        mostrarFotoPersona();
    }

    private void limpiarDatosPersona() {
        jTextFieldApellido.setText("");
        jTextFieldNombre.setText("");
        jTextFieldOrganizacion.setText("");
        jFormattedTextFieldFechaNac.setValue(null);
        jTextFieldPersonaVisitada.setText("");
        jTextFieldAreaVisitada.setText("");
        listUltimasVisitas.clear();
        jTreePersonaVisitada.setSelectionPath(null);
        jLabelFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/sin_foto_small.jpg")));
    }

    private void imprimirTicket(Visita visita) {
        try {
            InputStream bis = generaBarCode(visita.getPersona().getNumeroDocumento().toString());
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

    private void mostrarFotoPersona() {
        BufferedImage image;
        try {
            if (persona.getFotografiaPath() != null && !persona.getFotografiaPath().equals("")) {
                String rutaImagen = persona.getFotografiaPath();
                File fileImagen = new File(rutaImagen);
                image = ImageIO.read(fileImagen);
                //REGULAR TAMAÑO    
                Image imageScale = image.getScaledInstance(260, 170, image.SCALE_FAST);
                ImageIcon iconoFoto = new javax.swing.ImageIcon(imageScale);
                jLabelFotografia.setIcon(iconoFoto);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se puede Mostrar la Fotografia en este momento", "Error", 0);
        }
    }

    private void inhabilitarEdicionPersona() {
        jTextFieldApellido.setEditable(false);
        jTextFieldNombre.setEditable(false);
        jTextFieldOrganizacion.setEditable(false);
        jFormattedTextFieldFechaNac.setEditable(false);
        jComboBoxEstadoCivil.setEnabled(false);
        jComboBoxSexo.setEnabled(false);
        jComboBoxNacionalidad.setEnabled(false);
        jButtonBuscarOrganizacion.setVisible(false);
        jButtonNuevoOrganizacion.setVisible(false);
        jButtonNacionalidad.setVisible(false);
        jButtonNacionalidadEdit.setVisible(false);
    }

    private void habilitarEdicionPersona() {
        jTextFieldApellido.setEditable(true);
        jTextFieldNombre.setEditable(true);
        jTextFieldOrganizacion.setEditable(true);
        jFormattedTextFieldFechaNac.setEditable(true);
        jComboBoxEstadoCivil.setEnabled(true);
        jComboBoxSexo.setEnabled(true);
        jComboBoxNacionalidad.setEnabled(true);
        jButtonBuscarOrganizacion.setVisible(true);
        jButtonNuevoOrganizacion.setVisible(true);
        jButtonNacionalidad.setVisible(true);
        jButtonNacionalidadEdit.setVisible(true);
    }

    private void jButtonNuevoTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoTipoDocActionPerformed
        // TODO add your handling code here:
        try {
            TipoDocumento td = (TipoDocumento) jComboBoxTipoDoc.getSelectedItem();
            JDialogTipoDocumento dialogTipoDocumento = new JDialogTipoDocumento(null, rootPaneCheckingEnabled);
            dialogTipoDocumento.setTipoDocumento(new TipoDocumento());
            WindowUtil.centerWindow(dialogTipoDocumento);
            dialogTipoDocumento.setVisible(true);
            listTipoDocumento.clear();
            listTipoDocumento.addAll(tipoDocAction.findAll());
            if (dialogTipoDocumento.getTipoDocumento().getId() != null) {
                jComboBoxTipoDoc.setSelectedItem(dialogTipoDocumento.getTipoDocumento());
            } else {
                jComboBoxTipoDoc.setSelectedItem(td);
            }
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonNuevoTipoDocActionPerformed

    private void jButtonEditTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditTipoDocActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            JDialogTipoDocumento dialogTipoDocumento = new JDialogTipoDocumento(null, rootPaneCheckingEnabled);
            dialogTipoDocumento.setTipoDocumento((TipoDocumento) jComboBoxTipoDoc.getSelectedItem());
            WindowUtil.centerWindow(dialogTipoDocumento);
            dialogTipoDocumento.setVisible(true);
            listTipoDocumento.clear();
            listTipoDocumento.addAll(tipoDocAction.findAll());
            jComboBoxTipoDoc.setSelectedItem(dialogTipoDocumento.getTipoDocumento());
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonEditTipoDocActionPerformed

    private void jButtonNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNacionalidadActionPerformed
        // TODO add your handling code here:
        try {
            Nacionalidad nac = (Nacionalidad) jComboBoxNacionalidad.getSelectedItem();
            JDialogoNacionalidad dialogNacionalidad = new JDialogoNacionalidad(null, rootPaneCheckingEnabled);
            dialogNacionalidad.setNacionalidad(new Nacionalidad());
            WindowUtil.centerWindow(dialogNacionalidad);
            dialogNacionalidad.setVisible(true);
            listNacionalidades.clear();
            listNacionalidades.addAll(nacionalidadAction.findAll());
            if (dialogNacionalidad.getNacionalidad().getId() != null) {
                jComboBoxNacionalidad.setSelectedItem(dialogNacionalidad.getNacionalidad());
            } else {
                jComboBoxNacionalidad.setSelectedItem(nac);
            }
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonNacionalidadActionPerformed

    private void jButtonMotivoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMotivoEditActionPerformed
        // TODO add your handling code here:
        try {
            JDialogMotivo dialogMotivo = new JDialogMotivo(null, true);
            dialogMotivo.setMotivo((Motivo) jComboBoxMotivo.getSelectedItem());
            WindowUtil.centerWindow(dialogMotivo);
            dialogMotivo.setVisible(true);
            listNacionalidades.clear();
            listNacionalidades.addAll(nacionalidadAction.findAll());
            jComboBoxNacionalidad.setSelectedItem(dialogMotivo.getMotivo());
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonMotivoEditActionPerformed

    private void jButtonBuscarOrganizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarOrganizacionActionPerformed
        // TODO add your handling code here:
        JInternalFrameOrganizacion jFrameOrganizacion = new JInternalFrameOrganizacion();
        jFrameOrganizacion.setModoBuscador(true);
        jFrameOrganizacion.setVisible(true);
        JDialogBuscador buscador = new JDialogBuscador(null, rootPaneCheckingEnabled);
        buscador.setSize(jFrameOrganizacion.getSize());
        jFrameOrganizacion.setClosable(false);
        jFrameOrganizacion.setResizable(false);
        jFrameOrganizacion.setTitle("Buscador de organizaciones");
        buscador.getjDesktopPaneBuscador().add(jFrameOrganizacion);
        WindowUtil.centerWindow(buscador);
        buscador.setVisible(true);
        if (jFrameOrganizacion.getOrganizacionSeleccionada() != null) {
            persona.setOrganizacion(jFrameOrganizacion.getOrganizacionSeleccionada());
            jTextFieldOrganizacion.setText(persona.getOrganizacion().getNombre());
        }
    }//GEN-LAST:event_jButtonBuscarOrganizacionActionPerformed

    private void jButtonNuevoOrganizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoOrganizacionActionPerformed
        // TODO add your handling code here:

        Organizacion or = persona.getOrganizacion();
        JDialogOrganizacionExterna jdO = new JDialogOrganizacionExterna(null, true);
        jdO.setOrganizacion(new Organizacion());
        WindowUtil.centerWindow(jdO);
        jdO.setVisible(true);
        if (jdO.getOrganizacion().getId() != null) {
            persona.setOrganizacion(jdO.getOrganizacion());
            jTextFieldOrganizacion.setText(persona.getOrganizacion().getNombre());
        }

    }//GEN-LAST:event_jButtonNuevoOrganizacionActionPerformed

    private void jRadioButtonNacionalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonNacionalItemStateChanged
        // TODO add your handling code here:
        try {
            jComboBoxTipoDoc.setVisible(false);
            //  jLabelTipoDeDoc.setVisible(false);
            jButtonEditTipoDoc.setVisible(false);
            jButtonNuevoTipoDoc.setVisible(false);
            jComboBoxTipoDoc.setSelectedItem(tipoDocAction.findByNamedQuery("TipoDocumento.findCI").get(0));
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jRadioButtonNacionalItemStateChanged

    private void jRadioButtonExtranjeroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonExtranjeroItemStateChanged
        // TODO add your handling code here:
        jComboBoxTipoDoc.setVisible(true);
        // jLabelTipoDeDoc.setVisible(true);
        jButtonEditTipoDoc.setVisible(true);
        jButtonNuevoTipoDoc.setVisible(true);
    }//GEN-LAST:event_jRadioButtonExtranjeroItemStateChanged

    private void jTextFieldDocumentoPersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDocumentoPersonaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && jTextFieldDocumentoPersona.getText() != null && !jTextFieldDocumentoPersona.getText().equals("")) {
            // Enter was pressed. Your code goes here.
            iniciarAccion();
        } else if (jTextFieldDocumentoPersona.getText() == null || jTextFieldDocumentoPersona.getText().equals("")) {
            limpiarDatosPersona();
            inhabilitarEdicionPersona();
            jLabelMensaje.setText("Introduzca un número de documento.");
            jButtonTomarFotografia.setEnabled(false);
            jButtonNuevaVisita.setVisible(false);
            jCheckBoxReimprimir.setVisible(true);
            jLabelIconStatus.setIcon(null);
        }

    }//GEN-LAST:event_jTextFieldDocumentoPersonaKeyPressed

    private void iniciarAccion() {
        try {
            persona = personaAction.findByNumeroDocumento(jTextFieldDocumentoPersona.getText(), (TipoDocumento) jComboBoxTipoDoc.getSelectedItem());
            if (persona == null) {
                persona = new Persona();
                persona.setNumeroDocumento(jTextFieldDocumentoPersona.getText());
                persona.setTipoDocumento((TipoDocumento) jComboBoxTipoDoc.getSelectedItem());
                limpiarDatosPersona();
                habilitarEdicionPersona();
                jLabelMensaje.setText("Nueva persona, ingrese sus datos.");
                jButtonNuevaVisita.setVisible(true);
                jCheckBoxReimprimir.setVisible(false);
                jTextFieldNombre.requestFocusInWindow();
                jButtonTomarFotografiaActionPerformed(null);
                jButtonTomarFotografia.setEnabled(
                        true);
            } else {
                cargarDatosPersona();
                inhabilitarEdicionPersona();
                if (persona.getEstado() != null && persona.getEstado().getNombre().equals("INHABILITADO")) {
                    Toolkit.getDefaultToolkit().beep();
                    jLabelMensaje.setText("ATENCIÓN. La persona está inhabilitada.");
                    jLabelIconStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/Semaforo_rojo.png")));
                    jButtonNuevaVisita.setVisible(false);
                    jCheckBoxReimprimir.setVisible(false);
                    return;
                } else {
                    gestionVisita();
                }
                jButtonTomarFotografia.setEnabled(
                        true);
            }
            if (jTextFieldDocumentoPersona.getText()
                    == null || jTextFieldDocumentoPersona.getText().equals("")) {
                limpiarDatosPersona();
                inhabilitarEdicionPersona();
                jLabelMensaje.setText("Introduzca un número de documento.");
                jButtonTomarFotografia.setEnabled(false);
                jButtonNuevaVisita.setVisible(false);
                jCheckBoxReimprimir.setVisible(true);
                jLabelIconStatus.setIcon(null);
            }
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }
    private void jLabelFotografiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFotografiaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && persona != null && !persona.getFotografiaPath().equals("")) {
            try {
                WindowUtil.centerWindow(AdminCamera.formulario);
                AdminCamera.formulario.mostrarFormulario("VER", persona);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se puede Mostrar la Fotografia en este momento", "Error", 0);
                return;
            }
        }
    }//GEN-LAST:event_jLabelFotografiaMouseClicked

    private void jButtonTomarFotografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTomarFotografiaActionPerformed
        try {
            // JDialogFotografia dialogFotografia = new JDialogFotografia(null, rootPaneCheckingEnabled, "CAPTURAR", persona);
            WindowUtil.centerWindow(AdminCamera.formulario);
            //            dialogFotografia.setPersona(persona);
            AdminCamera.formulario.mostrarFormulario("CAPTURAR", persona);
            //  dialogFotografia.setVisible(true);        // TODO add your handling code here:
            if (AdminCamera.formulario.isCapturado()) {
                mostrarFotoPersona();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "En este momento no se puede inicializar la camara, verifique el dispositivo e intente de nuevo", "Error", 0);
            return;
        }
    }//GEN-LAST:event_jButtonTomarFotografiaActionPerformed

    private void jButtonNuevoMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoMotivoActionPerformed
        // TODO add your handling code here:
        try {
            Motivo mot = (Motivo) jComboBoxMotivo.getSelectedItem();
            JDialogMotivo dialogMotivo = new JDialogMotivo(null, rootPaneCheckingEnabled);
            dialogMotivo.setMotivo(new Motivo());
            WindowUtil.centerWindow(dialogMotivo);
            dialogMotivo.setVisible(true);
            listMotivos.clear();
            listMotivos.addAll(motivoAction.findAll());
            if (dialogMotivo.getMotivo().getId() != null) {
                jComboBoxMotivo.setSelectedItem(dialogMotivo.getMotivo());
            } else {
                jComboBoxMotivo.setSelectedItem(mot);
            }
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonNuevoMotivoActionPerformed

    private void jButtonBuscarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        if (jTextFieldFiltroArbol.getText() != null && !jTextFieldFiltroArbol.equals("")) {
            try {
                DefaultMutableTreeNode root = UtilesArbol.crearArbolFiltrado("ORGANIGRAMA", jTextFieldFiltroArbol.getText().toUpperCase(), true,false,false);
                jTreePersonaVisitada = new JTree(root);
                jTreePersonaVisitada.setCellRenderer(new CustomIconRenderer());
                jTreePersonaVisitada.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                    public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                        jTreePersonaVisitadaValueChanged(evt);
                    }
                });
                jScrollPanePersonasVisitadas.setViewportView(jTreePersonaVisitada);
            } catch (ErrorInesperado ei) {
                JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        }
    }//GEN-LAST:event_jButtonBuscarPersonaVisitadaActionPerformed

    private void jButtonLimpiarPersonaVisitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPersonaVisitadaActionPerformed
        // TODO add your handling code here:
        try {
            jTextFieldFiltroArbol.setText("");
            DefaultMutableTreeNode root = UtilesArbol.crearArbol("ORGANIGRAMA", true,false,false);
            jTreePersonaVisitada = new JTree(root);
            jTreePersonaVisitada.setCellRenderer(new CustomIconRenderer());
            jTreePersonaVisitada.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                    jTreePersonaVisitadaValueChanged(evt);
                }
            });
            jScrollPanePersonasVisitadas.setViewportView(jTreePersonaVisitada);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

    }//GEN-LAST:event_jButtonLimpiarPersonaVisitadaActionPerformed

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

    private void jButtonNuevaVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaVisitaActionPerformed
        try {
            // TODO add your handling code here:
            if (persona.getId() == null) {
                if (jTextFieldNombre.getText() != null && !jTextFieldNombre.getText().equals("") && jTextFieldApellido.getText() != null && !jTextFieldApellido.getText().equals("")) {
                    try {
                        fillPersona(persona);
                        personaAction.setPersona(persona);
                        personaAction.crear();
                    } catch (EntidadExiste ex) {
                        //No debe pasar
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre y apellido son obligatorios.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (jTextFieldAreaVisitada.getText() == null || jTextFieldAreaVisitada.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe visitar una área o persona.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            registrarVisita();
            jButtonNuevaVisita.setVisible(false);
            jCheckBoxReimprimir.setVisible(true);
            limpiarDatosPersona();
            inhabilitarEdicionPersona();
            jTextFieldDocumentoPersona.setText("");
            jTextFieldDocumentoPersona.requestFocusInWindow();
        } catch (ErrorInesperado ex) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonNuevaVisitaActionPerformed

    private void jButtonNacionalidadEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNacionalidadEditActionPerformed
        // TODO add your handling code here:
        try {
            Nacionalidad nac = (Nacionalidad) jComboBoxNacionalidad.getSelectedItem();
            JDialogoNacionalidad dialogNacionalidad = new JDialogoNacionalidad(null, rootPaneCheckingEnabled);
            dialogNacionalidad.setNacionalidad(nac);
            WindowUtil.centerWindow(dialogNacionalidad);
            dialogNacionalidad.setVisible(true);
            listNacionalidades.clear();
            listNacionalidades.addAll(nacionalidadAction.findAll());
            jComboBoxNacionalidad.setSelectedItem(nac);
        } catch (ErrorInesperado ei) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_jButtonNacionalidadEditActionPerformed

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
                File myFile = new File(abspath + "/manuales/SCAModuloOperativo.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
                JOptionPane.showMessageDialog(null, "El manual no se encuentra, verifique con el administrador del sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonAyudaActionPerformed

    private void jButtonVisitasActivasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitasActivasActionPerformed
        // TODO add your handling code here:
        JDialogVisitasActivas jDialog=new JDialogVisitasActivas(null, true);
        jDialog.setModoBuscador(true);
        jDialog.setSize(532, 552);
        WindowUtil.centerWindow(jDialog);
        jDialog.setVisible(true);
    }//GEN-LAST:event_jButtonVisitasActivasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            //</editor-fold>
            AdminCamera.iniciar();

        } catch (IOException ex) {
            Logger.getLogger(JDialogVisita.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(JDialogVisita.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(JDialogVisita.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogVisita dialog = new JDialogVisita(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setSize(1260, 600);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupExtranjero;
    private javax.swing.JButton jButtonAbout;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonBuscarOrganizacion;
    private javax.swing.JButton jButtonBuscarPersonaVisitada;
    private javax.swing.JButton jButtonEditTipoDoc;
    private javax.swing.JButton jButtonLimpiarPersonaVisitada;
    private javax.swing.JButton jButtonMotivoEdit;
    private javax.swing.JButton jButtonNacionalidad;
    private javax.swing.JButton jButtonNacionalidadEdit;
    private javax.swing.JButton jButtonNuevaVisita;
    private javax.swing.JButton jButtonNuevoMotivo;
    private javax.swing.JButton jButtonNuevoOrganizacion;
    private javax.swing.JButton jButtonNuevoTipoDoc;
    private javax.swing.JButton jButtonTomarFotografia;
    private javax.swing.JButton jButtonVisitasActivas;
    private javax.swing.JCheckBox jCheckBoxReimprimir;
    private javax.swing.JComboBox jComboBoxEstadoCivil;
    private javax.swing.JComboBox jComboBoxMotivo;
    private javax.swing.JComboBox jComboBoxNacionalidad;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JComboBox jComboBoxTipoDoc;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelApe;
    private javax.swing.JLabel jLabelEstCiv;
    private javax.swing.JLabel jLabelEstCiv1;
    private javax.swing.JLabel jLabelFechaNac;
    private javax.swing.JLabel jLabelFotografia;
    private javax.swing.JLabel jLabelIconStatus;
    private javax.swing.JLabel jLabelMensaje;
    private javax.swing.JLabel jLabelNAcion;
    private javax.swing.JLabel jLabelNomb;
    private javax.swing.JLabel jLabelOrganizacion;
    private javax.swing.JPanel jPanelArbolVisita;
    private javax.swing.JPanel jPanelInformativo;
    private javax.swing.JPanel jPanelVisita;
    private javax.swing.JPanel jPanelVisitante;
    private javax.swing.JRadioButton jRadioButtonExtranjero;
    private javax.swing.JRadioButton jRadioButtonNacional;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPanePersonasVisitadas;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTableVisitas;
    private javax.swing.JTextArea jTextAreaObservacion;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldAreaVisitada;
    private javax.swing.JTextField jTextFieldDocumentoPersona;
    private javax.swing.JTextField jTextFieldFiltroArbol;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldOrganizacion;
    private javax.swing.JTextField jTextFieldPersonaVisitada;
    private javax.swing.JTree jTreePersonaVisitada;
    private java.util.List listMotivos;
    private java.util.List listNacionalidades;
    private java.util.List listTipoDocumento;
    private java.util.List listUltimasVisitas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
