/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
