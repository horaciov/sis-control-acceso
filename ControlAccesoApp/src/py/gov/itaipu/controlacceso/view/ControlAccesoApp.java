/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;
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
            try {
                AdminCamera.iniciar();
            } catch (Exception e){
                //no hacemos nada al respecto.
            }
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
