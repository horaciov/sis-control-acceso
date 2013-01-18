/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.test;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author fboy
 */
public class ImageFrame extends JFrame {

    /**
     * Initializes a JFrame instance that displays an graphic image centered on
     * the JFrame.
     *     
* @param title the title for display on the JFrame title bar
     * @param imageFileName the absolute path to the image file or the relative
     * path from the execution directory. The image file must be of a format
     * compatible with <code>javax.swing.ImageIcon</code>.
     */
    public ImageFrame(String title, String imageFileName) {
        this.setTitle(title);
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imageFileName));
        Container pane =   this.getContentPane();
        JLabel label = new JLabel(icon);
        pane.add(label);// adds to center region of default BorderLayout of pane
    }

    /**
     * Example of usage...
     *     
* @param args
     */
    public static void main(String[] args) {
//proper way to push graphics execution to the GUI event thread...
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                JFrame f = new ImageFrame("hola", "/resource/img/exit.png");
                f.setDefaultCloseOperation(HIDE_ON_CLOSE);
                f.pack();
                f.setVisible(true);

            }
        });

    }
}
