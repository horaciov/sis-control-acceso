/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.action.persona;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        this.em = EntityManagerCA.getEntityManger();
    }
    
    public List<Persona> findByNumeroDocumento(String numeroDocumento,TipoDocumento tipoDocumento){
        Query query=em.createQuery(" from Persona p where numeroDocumento=:numeroDoc and tipoDocumento.id=:tipoDoc");
        query.setParameter("numeroDoc", numeroDocumento).setParameter("tipoDoc", tipoDocumento==null?0:tipoDocumento.getId());
        return query.getResultList();
    }
    
    
}
