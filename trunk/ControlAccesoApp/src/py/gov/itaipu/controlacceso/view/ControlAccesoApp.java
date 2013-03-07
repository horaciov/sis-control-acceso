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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import py.gov.itaipu.controlacceso.action.seguridad.AutenticadorAction;
import py.gov.itaipu.controlacceso.model.Rol;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;
import py.gov.itaipu.controlacceso.utils.windows.JFrameDummy;
import py.gov.itaipu.controlacceso.utils.windows.WindowUtil;
import py.gov.itaipu.controlacceso.view.seguridad.JDialogAutenticar;
import py.gov.itaipu.controlacceso.view.seguridad.JDialogSeguridadMant;
import py.gov.itaipu.controlacceso.view.visita.JDialogVisita;

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
            } catch (Exception e) {
                //no hacemos nada al respecto.
            }
            JFrameDummy frameDummy=new JFrameDummy();
            JDialogAutenticar login = new JDialogAutenticar(null, true);
            WindowUtil.centerWindow(login);
            login.setVisible(true);
            //Abrimos la app según rol del usuario.
            if (AutenticadorAction.getUsuarioConectado().getRoles().size() == 1) {
                Rol rol = AutenticadorAction.getUsuarioConectado().getRoles().get(0);
                if (rol.getNombre().equals("OPERADOR")) {
                    JDialogVisita dialog = new JDialogVisita(frameDummy, false);
                    dialog.setSize(1152, 600);
                    WindowUtil.centerWindow(dialog);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                    frameDummy.setVisible(false);
                } else if (rol.getNombre().equals("ADMINISTRATIVO")) {
                    JDialogAdministracion dialog = new JDialogAdministracion(frameDummy, false);
                    dialog.setSize(480, 230);
                    WindowUtil.centerWindow(dialog);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                    frameDummy.dispose();
                } else if (rol.getNombre().equals("ANALISTA")) {
                    JDialogAnalisis dialog = new JDialogAnalisis(frameDummy, false);
                    dialog.setSize(450, 250);
                    WindowUtil.centerWindow(dialog);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                    frameDummy.dispose();
                } else if (rol.getNombre().equals("SEGURIDAD SISTEMA")) {
                    JDialogSeguridadMant dialog = new JDialogSeguridadMant(frameDummy, false);
                    dialog.setSize(900, 320);
                    WindowUtil.centerWindow(dialog);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                    frameDummy.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No cuenta con un rol para acceder al sistema, pongase en contacto con el administrador.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (AutenticadorAction.getUsuarioConectado().getRoles().size() > 1) {
                JDialogRolSelector dialog = new JDialogRolSelector(frameDummy, false);
                dialog.setSize(350, 145);
                WindowUtil.centerWindow(dialog);
                dialog.setVisible(true);
                frameDummy.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No cuenta con un rol para acceder al sistema, pongase en contacto con el administrador.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


    }
}
