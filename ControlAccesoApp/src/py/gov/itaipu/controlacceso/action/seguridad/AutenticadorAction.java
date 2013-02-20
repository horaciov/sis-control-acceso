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
import py.gov.itaipu.controlacceso.model.Usuario;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class AutenticadorAction {

    public boolean autenticar(String usuario, String password) {
        MessageDigest md = null;
        String passwordDigest=null;
        EntityManager em=EntityManagerCA.getEntityManger();
        List<Usuario> usuarioValido;
        try {
            password.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA-1");
            passwordDigest=md.digest(password.getBytes("UTF-8")).toString();            
            usuarioValido=em.createQuery(" from Usuario where nombre = :nombre and password=:password")
                    .setParameter("nombre", usuario)
                    .setParameter("password", passwordDigest)
                    .getResultList();
            if(usuarioValido.size()>0)
                return true;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AutenticadorAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
