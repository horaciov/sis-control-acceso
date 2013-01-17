/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.visita;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import py.gov.itaipu.controlacceso.model.Organizacion;
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
        em = EntityManagerCA.getEntityManger();
    }

    public VisitaAction(Visita visita) {
        em = EntityManagerCA.getEntityManger();
        this.visita = visita;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public List<Visita> findVisitasPendientes() {
        Query query;
        query = em.createQuery(" from Visita v where v.fechaSalida is null)");
        return query.getResultList();
    }

    public List<Visita> findByParameters(Visita visita, Date fechaHasta, Organizacion organizacionExterna) {
        String sQuery = " from Visita v where 1=1";

        if (visita.getPersona() != null && visita.getPersona().getId() != null) {
            sQuery = sQuery + " and v.persona.id = :persona ";
        }

        if (visita.getPersonaVisitada() != null && visita.getPersonaVisitada().getId() != null) {
            sQuery = sQuery + " and v.personaVisitada.id = :personaVisitada ";
        }

        if (visita.getMotivo() != null && visita.getMotivo().getId() != null) {
            sQuery = sQuery + " and v.motivo.id = :motivo ";
        }

        if (visita.getOrganizacionInterna() != null && visita.getOrganizacionInterna().getId() != null) {
            sQuery = sQuery + " and v.organizacionInterna.id = :organizacionInterna ";
        }

        if (organizacionExterna != null && organizacionExterna.getId() != null) {
            sQuery = sQuery + " and v.persona.organizacion.id = :organizacionExterna ";
        }

        if (visita.getObservacion() != null) {
            sQuery = sQuery + " and v.observacion like '%'||:obvservacion||'%' ";
        }

        if (visita.getFechaIngreso() != null) {
            if (fechaHasta != null) {
                sQuery = sQuery + " and ( v.fechaIngreso >= :desde and v.fechaIngreso <= :hasta ) ";
            } else {
                sQuery = sQuery + " and v.fechaIngreso >= :desde";
            }
        } else if (fechaHasta != null) {
            sQuery = sQuery + " and v.fechaIngreso <= :hasta";
        }


        Query query = em.createQuery(sQuery);

        if (visita.getPersona() != null && visita.getPersona().getId() != null) {
            query.setParameter("persona", visita.getPersona().getId());
        }

        if (visita.getPersonaVisitada() != null && visita.getPersonaVisitada().getId() != null) {
            query.setParameter("personaVisitada", visita.getPersonaVisitada().getId());
        }

        if (visita.getMotivo() != null && visita.getMotivo().getId() != null) {
            query.setParameter("motivo", visita.getMotivo().getId());
        }

        if (visita.getOrganizacionInterna() != null && visita.getOrganizacionInterna().getId() != null) {
            query.setParameter("organizacionInterna", visita.getOrganizacionInterna().getId());
        }

        if (organizacionExterna != null && organizacionExterna.getId() != null) {
            query.setParameter("organizacionExterna", organizacionExterna.getId());
        }

        if (visita.getObservacion() != null && !visita.getObservacion().equals("")) {
            query.setParameter("obvservacion", visita.getObservacion().toUpperCase());
        }

        if (visita.getFechaIngreso() != null) {
            query.setParameter("desde", visita.getFechaIngreso());
        }
        if (fechaHasta != null) {
            query.setParameter("hasta", fechaHasta);
        }

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
