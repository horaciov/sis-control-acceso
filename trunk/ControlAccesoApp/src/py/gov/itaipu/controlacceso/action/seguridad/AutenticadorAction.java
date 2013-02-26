/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.seguridad;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.util.DigestUtils;
import py.gov.itaipu.controlacceso.model.Usuario;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class AutenticadorAction {

    private static Usuario usuarioConectado;

    public static Usuario getUsuarioConectado() {
        return usuarioConectado;
    }
            
    public boolean autenticar(String usuario, String password) {
        EntityManager em = EntityManagerCA.getEntityManger();
        List<Usuario> usuarioValido;
        usuarioValido = em.createQuery(" from Usuario where nombre = :nombre and password=:password")
                .setParameter("nombre", usuario)
                .setParameter("password", getDigest(password))
                .getResultList();
        if (usuarioValido.size() > 0) {
            usuarioConectado=usuarioValido.get(0);
            return true;
        }

        return false;
    }

    public static String getDigest(String mensaje) {
        String mensajeDigest = null;
        try {
            MessageDigest md = null;            
            md = MessageDigest.getInstance("SHA1");
            mensajeDigest = AutenticadorAction.bytesToHex(md.digest(mensaje.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AutenticadorAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return mensajeDigest;
    }

    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }
}
