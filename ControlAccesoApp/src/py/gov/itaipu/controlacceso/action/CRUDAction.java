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
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;
import py.gov.itaipu.controlacceso.model.exception.EntidadExiste;
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

    public List<E> findAll() {
        Query query;
        query = em.createQuery("from " + entity.getClass().getSimpleName());
        return query.getResultList();
    }

    public List<E> findAllProjection(String[] attributes) {
        String query = "select ";
        for (String a : attributes) {
            query += a + ",";
        }
        query = query.substring(0, query.length() - 1) + " from " + entity.getClass().getSimpleName();
        return em.createQuery(query).getResultList();
    }

    public List<E> findByNamedQuery(String queryNam) {
        Query query;
        query = em.createNamedQuery(queryNam);
        return query.getResultList();
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public void crear() throws EntidadExiste {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            em.flush();
            tx.commit();
        } catch (EntityExistsException re) {
            tx.rollback();
            throw new EntidadExiste("La entidad a persistir ya existe");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } finally {
            em.clear();
        }
    }

    public void guardar() throws EntidadExiste {
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
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public void eliminar() {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(entity);
            tx.commit();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }
}
