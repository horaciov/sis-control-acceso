/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.media.*;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JButton;

/**
 *
 * @author BUDDHIMA
 */
public class WebCam {
    public CaptureDeviceInfo di = null;
    public MediaLocator ml = null;
    CaptureDeviceInfo device;
    
    Player player;
    Component videoScreen;

    public static void main(String args[]) {
        new WebCam();// create a new instance of WebCam in main function
    }

    WebCam() {
        try {
//gets a list of devices how support the given videoformat
            
            // Boton Capturar
            JButton jButtonTomarFotografia = new javax.swing.JButton();
            jButtonTomarFotografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/img/CAMARA.png"))); // NOI18N
            jButtonTomarFotografia.setToolTipText("Actualizar Fotografia");
            jButtonTomarFotografia.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTomarFotografiaActionPerformed(evt);
                }
            }
                    );
            
            
            String str1 = "vfw:Microsoft WDM Image Capture (Win32):0";
            di = CaptureDeviceManager.getDevice(str1);
            ml = di.getLocator();

            player = Manager.createRealizedPlayer(ml);
            player.start();

            videoScreen = player.getVisualComponent();
            
            Frame frm = new Frame();
            frm.setBounds(10, 10, 810, 610);//sets the size of the screen
            frm.add(jButtonTomarFotografia);
            
// setting close operation to the frame
            frm.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });

//place player and video screen on the frame
            frm.add(videoScreen, BorderLayout.CENTER);
            frm.add(player.getControlPanelComponent(), BorderLayout.SOUTH);
            frm.setVisible(true);    

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void jButtonTomarFotografiaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    
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
}