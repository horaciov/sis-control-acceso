/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.seguridad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.Rol;
import py.gov.itaipu.controlacceso.model.Usuario;
import py.gov.itaipu.controlacceso.model.exception.EntidadExiste;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author fboy
 */
public class RolAction {
    private EntityManager em;
    Rol rol;
    
    public RolAction() {
            this.em = EntityManagerCA.getEntityManger();
    }
    public RolAction(Rol rol) {
            this.rol = rol;
            this.em = EntityManagerCA.getEntityManger();
    }
     
   public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
      public void crear() throws EntidadExiste, ErrorInesperado {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            rol.setUsuarioCreacion(AutenticadorAction.getUsuarioConectado());
            rol.setFechaCreacion(Calendar.getInstance().getTime());
            em.persist(rol);
            em.flush();
            tx.commit();
        } catch (EntityExistsException e) {
            tx.rollback();
            rol.setId(null);
            throw new EntidadExiste("Rol ya existe");
        } catch (RuntimeException e2) {
            throw new ErrorInesperado("Error Inesperado");
        } finally {
            em.clear();
        }
    }

    public void guardar() throws EntidadExiste, ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            rol.setUsuarioModificacion(AutenticadorAction.getUsuarioConectado());
            rol.setFechaModificacion(Calendar.getInstance().getTime());
            em.merge(rol);
            em.flush();
            tx.commit();
        } catch (EntityExistsException e) {
            throw new EntidadExiste("Rol ya existe");
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }

    public void eliminar() throws ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(rol);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }
    
        
}
