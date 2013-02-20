/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.utils.windows;

import groovy.swing.factory.WidgetFactory;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author fboy
 */
public class WindowUtil {
    
    public static void centerWindow (Window w){
        int width = w.getWidth();
        int height = w.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);
        w.setLocation(x, y);
    }
    public static void centerWindow (JInternalFrame w){
        int width = w.getWidth();
        int height = w.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);
        w.setLocation(x, y);
    }
    
}
