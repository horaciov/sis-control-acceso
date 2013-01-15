/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.administracion.parametrogeneral;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
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

    public List<E> findAll(){
        Query query;
        query = em.createQuery("from "+entity.getClass().getSimpleName());        
        return query.getResultList();
    }
    
    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public void crear() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(entity);
        tx.commit();
    }

    public void guardar() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(entity);
        tx.commit();
    }

    public void eliminar() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(entity);
        tx.commit();
    }
}
