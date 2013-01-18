package py.gov.itaipu.controlacceso.test;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class ShowImage extends Panel {
  BufferedImage  image;
  public ShowImage() {
    try {
	File input = new File("C:/Users/fboy/Documents/NetBeansProjects/ControlAccesoApp/src/resource/img/actor.jpg");
	image = ImageIO.read(input);
    } catch (IOException ie) {
      System.out.println("Error:"+ie.getMessage());
    }
  }

  public void paint(Graphics g) {
    g.drawImage( image, 0, 0, null);
  }

  static public void main(String args[]) throws Exception {
    JFrame frame = new JFrame("Display image");
    Panel panel = new ShowImage();
    frame.getContentPane().add(panel);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}