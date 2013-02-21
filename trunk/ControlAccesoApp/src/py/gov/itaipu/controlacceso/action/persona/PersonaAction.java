/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.persona;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import py.gov.itaipu.controlacceso.model.Estado;
import py.gov.itaipu.controlacceso.model.Nacionalidad;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.TipoDocumento;
import py.gov.itaipu.controlacceso.model.exception.EntidadExiste;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class PersonaAction {

    private EntityManager em;
    private Persona persona;

    public PersonaAction() {
        this.em = EntityManagerCA.getEntityManger();
    }

    public PersonaAction(Persona persona) {
        this.persona = persona;
        this.em = EntityManagerCA.getEntityManger();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> findByNumeroDocumento(String numeroDocumento, TipoDocumento tipoDocumento) {
        Query query = em.createQuery(" from Persona p where numeroDocumento = :numeroDoc and tipoDocumento.id = :tipoDoc");
        query.setParameter("numeroDoc", numeroDocumento).setParameter("tipoDoc", tipoDocumento.getId());
        return query.getResultList();
    }

    public List<Persona> findByNumeroDocumento(String numeroDocumento) {
        Query query = em.createQuery(" from Persona p where numeroDocumento = :numeroDoc ");
        query.setParameter("numeroDoc", numeroDocumento.toUpperCase().toString());
        return query.getResultList();
    }

    public List<Persona> findByParameters(Persona persona, Persona personaHasta, String tipoOrganizacion) {
        String sQuery = " Select p from Persona p left outer join p.organizacion where 1=1";
        if (persona.getNumeroDocumento() != null) {
            sQuery = sQuery + " and p.numeroDocumento = :numeroDocumento ";
        }

        if (persona.getTipoDocumento() != null && persona.getTipoDocumento().getId() != null) {
            sQuery = sQuery + " and p.tipoDocumento.id = :tipoDocumento ";
        }

        if (persona.getNombre() != null) {
            sQuery = sQuery + " and p.nombre like '%'||:nombre||'%' ";
        }

        if (persona.getApellido() != null) {
            sQuery = sQuery + " and p.apellido like '%'||:apellido||'%' ";
        }

        if (persona.getNacionalidad() != null && persona.getNacionalidad().getId() != null) {
            sQuery = sQuery + " and p.nacionalidad.id = :nacionalidad ";
        }

        if (persona.getOrganizacion() != null && persona.getOrganizacion().getId() != null) {
            sQuery = sQuery + " and p.organizacion.id = :organizacion ";
        }

        if (persona.getSexo() != null) {
            sQuery = sQuery + " and p.sexo = :sexo ";
        }

        if (persona.getEstado() != null && persona.getEstado().getId() != null) {
            sQuery = sQuery + " and p.estado.id = :estado ";
        }

        if (persona.getEstadoCivil() != null) {
            sQuery = sQuery + " and p.estadoCivil = :estadoCivil ";
        }

        if (tipoOrganizacion != null && !tipoOrganizacion.equals("")) {
            if (tipoOrganizacion.equals("INTERNA")) {
                sQuery = sQuery + " and p.organizacion.tipoOrganizacion = 'INTERNA' ";
            } else if (tipoOrganizacion.equals("EXTERNA")) {
                sQuery = sQuery + " and ( p.organizacion is null or p.organizacion.tipoOrganizacion = 'EXTERNA' ) ";
            }
        }

        if (persona.getFechaNacimiento() != null) {
            if (personaHasta.getFechaNacimiento() != null) {
                sQuery = sQuery + " and ( p.fechaNacimiento >= :fechaNacimientoDesde and p.fechaNacimiento <= :fechaNacimientoHasta ) ";
            } else {
                sQuery = sQuery + " and p.fechaNacimiento >= :fechaNacimientoDesde";
            }
        } else if (personaHasta.getFechaNacimiento() != null) {
            sQuery = sQuery + " and p.fechaNacimiento <= :fechaNacimientoHasta";
        }


        Query query = em.createQuery(sQuery);

        if (persona.getNumeroDocumento() != null) {
            query.setParameter("numeroDocumento", persona.getNumeroDocumento().toUpperCase().toString());
        }

        if (persona.getTipoDocumento() != null && persona.getTipoDocumento().getId() != null) {
            query.setParameter("tipoDocumento", persona.getTipoDocumento().getId());
        }

        if (persona.getNombre() != null) {
            query.setParameter("nombre", persona.getNombre().toUpperCase());
        }

        if (persona.getApellido() != null) {
            query.setParameter("apellido", persona.getApellido().toUpperCase());
        }

        if (persona.getNacionalidad() != null && persona.getNacionalidad().getId() != null) {
            query.setParameter("nacionalidad", persona.getNacionalidad().getId());
        }

        if (persona.getOrganizacion() != null && persona.getOrganizacion().getId() != null) {
            query.setParameter("organizacion", persona.getOrganizacion().getId());
        }

        if (persona.getSexo() != null) {
            query.setParameter("sexo", persona.getSexo());
        }

        if (persona.getEstado() != null && persona.getEstado().getId() != null) {
            query.setParameter("estado", persona.getEstado().getId());
        }

        if (persona.getEstadoCivil() != null) {
            query.setParameter("estadoCivil", persona.getEstadoCivil());;
        }
        if (persona.getFechaNacimiento() != null) {
            query.setParameter("fechaNacimientoDesde", persona.getFechaNacimiento());;
        }
        if (personaHasta.getFechaNacimiento() != null) {
            query.setParameter("fechaNacimientoHasta", personaHasta.getFechaNacimiento());;
        }
        return query.getResultList();
    }

    public void habilitar() {
        Estado e = (Estado) em.createNamedQuery("Estado.findByNombre").setParameter("nombre", "HABILITADO").getSingleResult();
        persona.setEstado(e);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(persona);
        tx.commit();
    }

    public void inhabilitar() {
        try {
            Estado e = (Estado) em.createNamedQuery("Estado.findByNombre").setParameter("nombre", "INHABILITADO").getSingleResult();
            persona.setEstado(e);
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(persona);
            tx.commit();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public void crear() throws EntidadExiste {
        EntityTransaction tx = null;
        try {    
            tx = em.getTransaction();
            tx.begin();
            em.persist(persona);
            em.flush();
            tx.commit();
        }catch(EntityExistsException e) {
            tx.rollback();
            persona.setId(null);
            throw new EntidadExiste("Le persona ya existe");
        }catch (RuntimeException e2) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }finally {
            em.clear();
        }
    }

    public void guardar() throws EntidadExiste {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(persona);
            em.flush();
            tx.commit();
        }catch(EntityExistsException e) {
            throw new EntidadExiste("Le persona ya existe");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public void eliminar() {
        try{
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(persona);
        tx.commit();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Verfique con el administrador la conexión a la base de datos y vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public static void main(String arg[]) {
        EntityManagerCA.iniciarContexto();
        PersonaAction p = new PersonaAction();
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(13L);
        Nacionalidad nac = new Nacionalidad();
        nac.setId(14L);

        Persona per = new Persona();
        per.setTipoDocumento(tipoDocumento);
        per.setNacionalidad(nac);
//        per.setSexo("MASCULINO");
//        per.setEstadoCivil("SOLTERO");
//        per.setNombre("HoR");
//        per.setApellido("vIL");
//        per.setEstadoCivil("SOLTERO");
        List<Persona> ps = p.findByParameters(per, new Persona(), null);

    }
}
