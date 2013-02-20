/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;
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
        WindowUtil.centerWindow(login);
        login.setVisible(true);
        WindowUtil.centerWindow(mdi);
        mdi.setVisible(true);
        
    }
}
