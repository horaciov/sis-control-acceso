/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.persona;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import py.gov.itaipu.controlacceso.model.Nacionalidad;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.TipoDocumento;
import py.gov.itaipu.controlacceso.persistence.EntityManagerCA;

/**
 *
 * @author vimartih
 */
public class PersonaAction {

    private EntityManager em;

    public PersonaAction() {
        EntityManagerCA.iniciarContexto();
        this.em = EntityManagerCA.getEntityManger();
    }

    public List<Persona> findByNumeroDocumento(String numeroDocumento, TipoDocumento tipoDocumento) {
        Query query = em.createQuery(" from Persona p where numeroDocumento = :numeroDoc and tipoDocumento.id = :tipoDoc");
        query.setParameter("numeroDoc", numeroDocumento).setParameter("tipoDoc", tipoDocumento.getId());
        return query.getResultList();
    }

    public List<Persona> findByParameters(Persona persona) {
        String sQuery = " from Persona p where 1=1";
        if (persona.getNumeroDocumento()!=null) {
            sQuery = sQuery + " and p.numeroDocumento = :numeroDocumento ";
        }
        
        if (persona.getTipoDocumento()!=null && persona.getTipoDocumento().getId()!=null) {
            sQuery = sQuery + " and p.tipoDocumento.id = :tipoDocumento " ;
        }
        
        if (persona.getNombre()!=null) {
            sQuery = sQuery + " and p.nombre like '%'||:nombre||'%' " ;
        }
        
        if (persona.getApellido()!=null) {
            sQuery = sQuery + " and p.apellido like '%'||:apellido||'%' " ;
        }
                
        if (persona.getNacionalidad()!=null && persona.getNacionalidad().getId()!=null) {
            sQuery = sQuery + " and p.nacionalidad.id = :nacionalidad " ;
        }
        
        if (persona.getSexo()!=null) {
            sQuery = sQuery + " and p.sexo = :sexo " ;
        }
         
        if (persona.getEstado()!=null && persona.getEstado().getId()!=null) {
            sQuery = sQuery + " and p.estado.id = :estado " ;
        }
        
         if (persona.getEstadoCivil()!=null) {
            sQuery = sQuery + " and p.estadoCivil = :estadoCivil " ;
        }
        
        Query query = em.createQuery(sQuery);
        
        if (persona.getNumeroDocumento()!=null) {
            query.setParameter("numeroDocumento", persona.getNumeroDocumento().toUpperCase().toString());
        }
        
        if (persona.getTipoDocumento()!=null && persona.getTipoDocumento().getId()!=null) {
            query.setParameter("tipoDocumento", persona.getTipoDocumento().getId());
        }
        
        if (persona.getNombre()!=null ) {
            query.setParameter("nombre", persona.getNombre().toUpperCase());
        }
        
        if (persona.getApellido()!=null) {
            query.setParameter("apellido", persona.getApellido().toUpperCase());
        }
        
        if (persona.getNacionalidad()!=null && persona.getNacionalidad().getId()!=null) {
            query.setParameter("nacionalidad", persona.getNacionalidad().getId());
        }
        
        if (persona.getSexo()!=null) {
            query.setParameter("sexo", persona.getSexo());
        }
         
        if (persona.getEstado()!=null && persona.getEstado().getId()!=null) {
            query.setParameter("estado", persona.getEstado().getId());
        }
        
         if (persona.getEstadoCivil()!=null) {
            query.setParameter("estadoCivil", persona.getEstadoCivil());;
        }
        
             return query.getResultList();
    }

    public static void main(String arg[]) {
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
        List<Persona> ps = p.findByParameters(per);

    }
}
