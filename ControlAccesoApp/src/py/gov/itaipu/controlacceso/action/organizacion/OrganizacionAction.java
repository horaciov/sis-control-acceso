/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.organizacion;

import py.gov.itaipu.controlacceso.action.organizacion.*;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import py.gov.itaipu.controlacceso.action.seguridad.AutenticadorAction;
import py.gov.itaipu.controlacceso.model.Estado;
import py.gov.itaipu.controlacceso.model.Nacionalidad;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.TipoDocumento;
import py.gov.itaipu.controlacceso.model.exception.EntidadExiste;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class OrganizacionAction {

    private EntityManager em;
    private Organizacion organizacion;

    public OrganizacionAction() {
        this.em = EntityManagerCA.getEntityManger();
    }

    public OrganizacionAction(Organizacion organizacion) {
        this.organizacion = organizacion;
        this.em = EntityManagerCA.getEntityManger();
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

   
    public void habilitar() throws ErrorInesperado {
        try {
            Estado e = (Estado) em.createNamedQuery("Estado.findByNombre").setParameter("nombre", "HABILITADO").getSingleResult();
            organizacion.setEstado(e);
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            organizacion.setUsuarioModificacion(AutenticadorAction.getUsuarioConectado());
            organizacion.setFechaModificacion(Calendar.getInstance().getTime());
            em.merge(organizacion);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }

    public void inhabilitar() throws ErrorInesperado {
        try {
            Estado e = (Estado) em.createNamedQuery("Estado.findByNombre").setParameter("nombre", "INHABILITADO").getSingleResult();
            organizacion.setEstado(e);
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            organizacion.setUsuarioModificacion(AutenticadorAction.getUsuarioConectado());
            organizacion.setFechaModificacion(Calendar.getInstance().getTime());
            organizacion.setOrganizacionPadre(null);
            em.merge(organizacion);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }

    public void crear() throws EntidadExiste, ErrorInesperado {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            organizacion.setUsuarioCreacion(AutenticadorAction.getUsuarioConectado());
            organizacion.setFechaCreacion(Calendar.getInstance().getTime());
            em.persist(organizacion);
            em.flush();
            tx.commit();
        } catch (EntityExistsException e) {
            tx.rollback();
            organizacion.setId(null);
            throw new EntidadExiste("Le organizacion ya existe");
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
            organizacion.setUsuarioModificacion(AutenticadorAction.getUsuarioConectado());
            organizacion.setFechaModificacion(Calendar.getInstance().getTime());
            em.merge(organizacion);
            em.flush();
            tx.commit();
        } catch (EntityExistsException e) {
            throw new EntidadExiste("Le organizacion ya existe");
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }

    public void eliminar() throws ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(organizacion);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }

  
}
