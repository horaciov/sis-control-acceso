/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.visita;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class VisitaAction {
    
    private EntityManager em;
    private Visita visita;

    public VisitaAction() {
        em=EntityManagerCA.getEntityManger();
    }
       
    public VisitaAction(Visita visita) {
        em=EntityManagerCA.getEntityManger();
        this.visita = visita;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
    
    public List<Visita> findVisitasPendientes(){
        Query query;
        query=em.createQuery(" from Visita v where v.fechaSalida is null)");
        return query.getResultList();
    }
    
    public void crear() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(visita);
        tx.commit();
    }

    public void guardar() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(visita);
        tx.commit();
    }

    public void eliminar() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(visita);
        tx.commit();
    }
    
}
