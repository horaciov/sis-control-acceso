/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vimartih
 */

public class EntityManagerCA {
    private static final String NAME_PU = "ControlAccesoAppPU";
    private static  EntityManagerFactory emf;
    private static  EntityManager em;
    
    public static void iniciarContexto(){
        if(emf==null)
            emf = Persistence.createEntityManagerFactory(NAME_PU);
    }
    
    public static EntityManager getEntityManger(){      
        if(em==null)
            em = emf.createEntityManager();
        return em;
    }
    
    public static void cerrar(){
        em.close();
        em=null;
        emf.close();
        emf=null;
    }
}
