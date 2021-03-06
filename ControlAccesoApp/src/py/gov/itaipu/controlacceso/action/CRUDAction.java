/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import py.gov.itaipu.controlacceso.action.seguridad.AutenticadorAction;
import py.gov.itaipu.controlacceso.model.Usuario;
import py.gov.itaipu.controlacceso.model.exception.EntidadExiste;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class CRUDAction<E> {

    private EntityManager em;
    private E entity;

    public CRUDAction(E entity) {
        em = EntityManagerCA.getEntityManger();
        this.entity = entity;
    }

    public CRUDAction() {
        em = EntityManagerCA.getEntityManger();
    }

    public List<E> findAll() throws ErrorInesperado {
        List<E> result = null;
        try {
            Query query;
            query = em.createQuery("from " + entity.getClass().getSimpleName());
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;

    }
    
    public List<E> findAllOrderbyId() throws ErrorInesperado {
        List<E> result = null;
        try {
            Query query;
            query = em.createQuery("from " + entity.getClass().getSimpleName()+" order by id");
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;

    }

    public List<E> findAllProjection(String[] attributes) throws ErrorInesperado {
        List<E> result = null;
        try {
            String query = "select ";
            for (String a : attributes) {
                query += a + ",";
            }
            query = query.substring(0, query.length() - 1) + " from " + entity.getClass().getSimpleName();
            result = em.createQuery(query).getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;

    }

    public List<E> findByNamedQuery(String queryNam) throws ErrorInesperado {
        List<E> result = null;
        try {
            Query query;
            query = em.createNamedQuery(queryNam);
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;
    }

    public List<E> findAllByName(String nombre) throws ErrorInesperado {
        List<E> result = null;
        try {
            Query query;
            query = em.createQuery(" from " + entity.getClass().getSimpleName() + " where nombre like '%'||:nombre||'%' ");
            query.setParameter("nombre", nombre.toUpperCase());
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;

    }

      public List<E> findAllByNameWithCondition(String nombre,String condition) throws ErrorInesperado {
        List<E> result = null;
        try {
            Query query;
            query = em.createQuery(" from " + entity.getClass().getSimpleName() + " where nombre like '%'||:nombre||'%' and "+condition);
            query.setParameter("nombre", nombre.toUpperCase());
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;

    }
    
    public boolean existeNombre(String nombre) throws ErrorInesperado {
        boolean existe = false;
        List<E> result = null;
        try {
            Query query;
            query = em.createQuery(" from " + entity.getClass().getSimpleName() + " where nombre = :nombre");
            query.setParameter("nombre", nombre);
            result = query.getResultList();
            if (result.size() > 0) {
                existe = true;
            }

        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }

        return existe;
    }

    public List<E> findEqualName(String nombre) throws ErrorInesperado {
        List<E> result = null;
        try {
            Query query;
            query = em.createQuery(" from " + entity.getClass().getSimpleName() + " where nombre = :nombre");
            query.setParameter("nombre", nombre);
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;

    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public void crear() throws EntidadExiste, ErrorInesperado {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            try {
                entity.getClass().
                        getMethod("setUsuarioCreacion", new Class<?>[]{Usuario.class}).
                        invoke(entity, AutenticadorAction.getUsuarioConectado());
                entity.getClass().
                        getMethod("setFechaCreacion", new Class<?>[]{Date.class}).
                        invoke(entity, Calendar.getInstance().getTime());
            } catch (Exception ex) {
                Logger.getLogger(CRUDAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            em.persist(entity);
            em.flush();
            tx.commit();
            em.clear();
        } catch (EntityExistsException re) {
            tx.rollback();
            throw new EntidadExiste("La entidad a persistir ya existe");
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        } finally {
        }
    }

    public void guardar() throws EntidadExiste, ErrorInesperado {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            try {
                entity.getClass().
                        getMethod("setUsuarioModificacion", new Class<?>[]{Usuario.class}).
                        invoke(entity, AutenticadorAction.getUsuarioConectado());
                entity.getClass().
                        getMethod("setFechaModificacion", new Class<?>[]{Date.class}).
                        invoke(entity, Calendar.getInstance().getTime());
            } catch (Exception ex) {
                Logger.getLogger(CRUDAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            em.merge(entity);
            em.flush();
            tx.commit();
        } catch (EntityExistsException re) {
            tx.rollback();
            throw new EntidadExiste("La entidad a persistir ya existe");
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }

    public void eliminar() throws ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(entity);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error Inesperado");
        }
    }
}
