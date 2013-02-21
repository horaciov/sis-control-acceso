/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
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

        try {
            EntityManagerCA.iniciarContexto();
            MDIControlAcceso mdi = new MDIControlAcceso();
            JDialogAutenticar login = new JDialogAutenticar(mdi, true);
            WindowUtil.centerWindow(login);
            login.setVisible(true);
            WindowUtil.centerWindow(mdi);
            mdi.setVisible(true);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


    }
}
