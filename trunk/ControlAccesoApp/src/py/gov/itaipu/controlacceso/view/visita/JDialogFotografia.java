/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view.visita;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import py.gov.itaipu.controlacceso.action.persona.PersonaAction;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.view.persona.JDialogPersona;

/**
 *
 * @author fboy
 */
public class JDialogFotografia extends javax.swing.JDialog {
    private boolean capturado;
    private String modo;
    public CaptureDeviceInfo di = null;
    public MediaLocator ml = null;
    CaptureDeviceInfo device;
    Player player;
    Component videoScreen;
    Persona persona;
    
    
    
    /**
     * Creates new form JDialogFotografia
     */
    public JDialogFotografia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }

    public JDialogFotografia(java.awt.Frame parent, boolean modal,String modo) {
        super(parent, modal);
        initComponents();
        this.modo = modo;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jPanelCamara = new javax.swing.JPanel();
        jButtonCapturar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Capturar Fotografia");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Cargar Fotografia");

        jPanelCamara.setPreferredSize(new java.awt.Dimension(600, 470));

        javax.swing.GroupLayout jPanelCamaraLayout = new javax.swing.GroupLayout(jPanelCamara);
        jPanelCamara.setLayout(jPanelCamaraLayout);
        jPanelCamaraLayout.setHorizontalGroup(
            jPanelCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanelCamaraLayout.setVerticalGroup(
            jPanelCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );

        jButtonCapturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/CAMARA.png"))); // NOI18N
        jButtonCapturar.setText("Capturar");
        jButtonCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCapturarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanelCamara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCapturar)
                .addGap(315, 315, 315))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelTitulo)
                .addGap(26, 26, 26)
                .addComponent(jPanelCamara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCapturar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapturarActionPerformed
        // TODO add your handling code here:
        try {
            FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
            Buffer buf = fgc.grabFrame();//grab the current frame on video screen
            BufferToImage btoi = new BufferToImage((VideoFormat) buf.getFormat());
            Image img = btoi.createImage(buf);
            String pathFoto = "src/resource/fotografias/"+persona.getNumeroDocumento()+".jpg";
            saveImagetoFile(img, pathFoto);
            actualizarFotoPersona(pathFoto);
            capturado = true;
            player.close();
            this.dispose();
        } catch (Exception e) {
        }
         

    }//GEN-LAST:event_jButtonCapturarActionPerformed

    private void actualizarFotoPersona(String pathFoto){
            persona.setFotografiaPath(pathFoto);
            PersonaAction pAction = new PersonaAction();
            pAction.setPersona(persona);
            pAction.guardar();
        
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (modo.equals("CAPTURAR")) {
               player.close();
        }
      
      
    }//GEN-LAST:event_formWindowClosing

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        capturado = false;
          if (modo.equals("CAPTURAR")) {
              jLabelTitulo.setText("Capturar Fotografia");
              iniciarWebCam();
            
        }else if(modo.equals("VER")){
          
            jLabelTitulo.setText("Mostrar Fotografia");
            mostrarFotografia();
            jButtonCapturar.setVisible(false);
        }
    }//GEN-LAST:event_formWindowActivated
 
    private void mostrarFotografia(){
            javax.swing.JLabel jLabelFotografia = new JLabel();    
            BufferedImage image;
             try {
                String rutaImagen = persona.getFotografiaPath();
                File fileImagen = new File(rutaImagen) ;
                image = ImageIO.read(fileImagen);
                //REGULAR TAMAÑO    
//                Image imageScale =  image.getScaledInstance(570, 338, image.SCALE_FAST);
                ImageIcon iconoFoto = new javax.swing.ImageIcon(image);
                jLabelFotografia.setSize(600, 470);
                jLabelFotografia.setIcon(iconoFoto);
                jPanelCamara.add(jLabelFotografia);
                jLabelFotografia.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(JDialogPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void saveImagetoFile(Image img, String string) {
        try {
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bi.createGraphics();
            g2.drawImage(img, 0, 0, null);
            g2.dispose();
            String fileType = string.substring(string.indexOf('.') + 1);
            ImageIO.write(bi, fileType, new File(string));
        } catch (Exception e) {
        }
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
            java.util.logging.Logger.getLogger(JDialogFotografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogFotografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogFotografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogFotografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogFotografia dialog = new JDialogFotografia(new javax.swing.JFrame(), true);
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
    
    
  private void  iniciarWebCam() {
        try {
//gets a list of devices how support the given videoformat
            String str1 = "vfw:Microsoft WDM Image Capture (Win32):0";
            di = CaptureDeviceManager.getDevice(str1);
            ml = di.getLocator();
            
            player = Manager.createRealizedPlayer(ml);
            player.start();

            videoScreen = player.getVisualComponent();
            
            //place player and video screen on the frame
            jPanelCamara.add(videoScreen, BorderLayout.CENTER);
            jPanelCamara.add(player.getControlPanelComponent(), BorderLayout.SOUTH);
         
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCapturar;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCamara;
    // End of variables declaration//GEN-END:variables
}
