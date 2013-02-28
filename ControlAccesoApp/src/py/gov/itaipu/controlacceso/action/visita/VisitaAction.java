/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.visita;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import py.gov.itaipu.controlacceso.action.seguridad.AutenticadorAction;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;
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

    public List<Visita> findVisitasPendientes() throws ErrorInesperado {
        List<Visita> result = null;
        try {
            Query query;
            query = em.createQuery(" from Visita v where v.fechaSalida is null and v.anulado = 'N' order by v.id )");
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;
    }

    public List<Visita> findByPersona(Persona persona) throws ErrorInesperado {
        List<Visita> result = null;
        try {
            String sQuery = " from Visita v where v.persona.id = :persona and v.anulado = 'N' order by v.fechaIngreso desc ";
            Query query = em.createQuery(sQuery).setMaxResults(3);
            query.setParameter("persona", persona.getId());
            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;
    }

    public List<Visita> findByParameters(Visita visita, Date fechaHasta, Organizacion organizacionExterna, String incluyeAnulados) throws ErrorInesperado {
        List<Visita> result = null;
        try {
            String sQuery = " from Visita v where 1=1";

            if (incluyeAnulados.equals("N")) {
                sQuery = sQuery + " and v.anulado = 'N' ";
            }

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

            result = query.getResultList();
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }
        return result;
    }

    public Visita findPendienteById(Long id) throws ErrorInesperado {
        try {
            Query query;
            query = em.createQuery(" from Visita v where v.fechaSalida is null and v.anulado = 'N' and id=:id )");
            query.setParameter("id", id);
            List<Visita> result = query.getResultList();
            if (result.size() > 0) {
                return result.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }

    }

    public Visita findPendienteByPersona(Persona persona) throws ErrorInesperado {
        try {
            Query query;
            query = em.createQuery(" from Visita v where v.fechaSalida is null and v.anulado = 'N' and persona.id= :idPersona )");
            query.setParameter("idPersona", persona.getId());
            List<Visita> result = query.getResultList();
            if (result.size() > 0) {
                return result.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }

    }
    
    
    public List<Visita> findPendientesByPersona(Persona persona) throws ErrorInesperado {
        try {
            Query query;
            query = em.createQuery(" from Visita v where v.fechaSalida is null and v.anulado = 'N' and personaVisitada.id= :idPersona )");
            query.setParameter("idPersona", persona.getId());
            List<Visita> result = query.getResultList();
            if (result.size() > 0) {
                return result;
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }

    }
    
    public List<Visita> findPendientesByArea(Organizacion organizacion) throws ErrorInesperado {
        try {
            Query query;
            query = em.createQuery(" from Visita v where v.fechaSalida is null and v.anulado = 'N' and organizacionInterna.id= :idOrganizacion )");
            query.setParameter("idOrganizacion", organizacion.getId());
            List<Visita> result = query.getResultList();
            if (result.size() > 0) {
                return result;
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }

    }
    
    
    public List<Visita> findTerminadasByPersona(Persona persona) throws ErrorInesperado {
        try {
            Query query;
            query = em.createQuery(" from Visita v where v.fechaSalida is not null and v.anulado = 'N' and personaVisitada.id= :idPersona )");
            query.setParameter("idPersona", persona.getId());
            List<Visita> result = query.getResultList();
            if (result.size() > 0) {
                return result;
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            throw new ErrorInesperado("Error inesperado.");
        }

    }
    
    

    public void anular() throws ErrorInesperado {
        visita.setAnulado("S");
        guardar();
    }

    public void crear() throws ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            visita.setUsuarioCreacion(AutenticadorAction.getUsuarioConectado());
            visita.setFechaCreacion(Calendar.getInstance().getTime());
            em.persist(visita);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error inesperado.");
        }
    }

    public void guardar() throws ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            visita.setUsuarioCreacion(AutenticadorAction.getUsuarioConectado());
            visita.setFechaCreacion(Calendar.getInstance().getTime());
            em.merge(visita);
            tx.commit();
            em.clear();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error inesperado.");
        }
    }

    public void eliminar() throws ErrorInesperado {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(visita);
            tx.commit();
        } catch (RuntimeException e) {
            throw new ErrorInesperado("Error inesperado.");
        }
    }
}
