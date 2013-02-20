/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.view.seguridad.JDialogAutenticar;

/**
 *
 * @author vimartih
 */
public class ControlAccesoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerCA.iniciarContexto();
        MDIControlAcceso mdi = new MDIControlAcceso();
        JDialogAutenticar login = new JDialogAutenticar(mdi, true);
        int width = login.getWidth();
        int height = login.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);
        login.setLocation(x, y);
        login.setVisible(true);
        mdi.setVisible(true);
        mdi.setVisible(true);
    }
}
