/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.utils.tree;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.action.persona.PersonaAction;
import py.gov.itaipu.controlacceso.action.visita.VisitaAction;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.Visita;
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;

/**
 *
 * @author fboy
 */
public class UtilesArbol {

    public UtilesArbol() {
    }

    public static DefaultMutableTreeNode crearArbol(String tituloArbol, boolean incluyePersonas, boolean incluyeVisitasActivas, boolean incluyeVisitasTerminadas) throws ErrorInesperado {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(tituloArbol);
        CRUDAction<Organizacion> organizacionAction = new CRUDAction<Organizacion>(new Organizacion());
        DefaultMutableTreeNode node;

        List<Organizacion> listaOrganizacionesArbolAux = new ArrayList<Organizacion>();

       
        listaOrganizacionesArbolAux = organizacionAction.findByNamedQuery("Organizacion.findOrganizacionPadre");
        Organizacion orgPadre;
        if(listaOrganizacionesArbolAux.size()<1)
            return root;
        orgPadre = listaOrganizacionesArbolAux.get(0);
        node = new DefaultMutableTreeNode(orgPadre, true);
        root.add(node);
        agregarhijos(node, incluyePersonas,incluyeVisitasActivas,incluyeVisitasTerminadas);
        return root;
    }

    public static DefaultMutableTreeNode crearArbolFiltrado(String tituloArbol, String filtro, boolean incluyePersonas, boolean incluyeVisitasActivas, boolean incluyeVisitasTerminadas) throws ErrorInesperado {
        DefaultMutableTreeNode node;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(tituloArbol);
        
        
        CRUDAction<Organizacion> organizacionAction = new CRUDAction<Organizacion>(new Organizacion());
        List<Organizacion> listaOrganizacion = new ArrayList<Organizacion>();
        listaOrganizacion = organizacionAction.findAllByName(filtro);
        for (int i = 0; i < listaOrganizacion.size(); i++) {
            Organizacion organizacion = listaOrganizacion.get(i);
            if (!(organizacion.getEstado()!=null && organizacion.getEstado().getNombre().equals("INHABILITADO"))) {
                node = new DefaultMutableTreeNode(organizacion, true);
                root.add(node);
                agregarVisitas(node, incluyeVisitasActivas, incluyeVisitasTerminadas);
            }
        }
        
        
        PersonaAction personaAction = new PersonaAction(new Persona());
        List<Persona> listaPersonas = new ArrayList<Persona>();
        listaPersonas = personaAction.findByNombreApellido(filtro);
        for (int i = 0; i < listaPersonas.size(); i++) {
            Persona persona = listaPersonas.get(i);
            if ( !(persona.getEstado()!=null && persona.getEstado().getNombre().equals("INHABILITADO"))) {
                node = new DefaultMutableTreeNode(persona, true);
                root.add(node);
                agregarVisitas(node, incluyeVisitasActivas, incluyeVisitasTerminadas);
            }     
        }
         
        return root;
    }
    
    
    private static void agregarhijos(DefaultMutableTreeNode nodo, boolean incluyePersonas,boolean incluyeVisitasActivas, boolean incluyeVisitasTerminadas) {
        if (incluyePersonas) {
            agregarPersonas(nodo,incluyeVisitasActivas,incluyeVisitasTerminadas);
        }
        agregarVisitas(nodo,incluyeVisitasActivas,incluyeVisitasTerminadas);
                
        DefaultMutableTreeNode node;
        Organizacion org = (Organizacion) nodo.getUserObject();
        List<Organizacion> hijas = org.getOrganizacionesHijas();
        for (Organizacion organizacion : hijas) {
            if (!(organizacion.getEstado()!=null && organizacion.getEstado().getNombre().equals("INHABILITADO"))) {
                node = new DefaultMutableTreeNode(organizacion, true);
                nodo.add(node);
                agregarhijos(node, incluyePersonas,incluyeVisitasActivas,incluyeVisitasTerminadas);
            }
            
        }
    }

    private static void agregarPersonas(DefaultMutableTreeNode nodo,boolean incluyeVisitasActivas, boolean incluyeVisitasTerminadas) {
        DefaultMutableTreeNode node;
        Organizacion org = (Organizacion) nodo.getUserObject();
        List<Persona> personas = org.getPersonas();
        for (Persona persona : personas) {
            if ( !(persona.getEstado()!=null && persona.getEstado().getNombre().equals("INHABILITADO"))) {
                node = new DefaultMutableTreeNode(persona, true);
                nodo.add(node); 
                agregarVisitas(node,incluyeVisitasActivas,incluyeVisitasTerminadas);
                
            }
        }
    }
    
    private  static  void agregarVisitas(DefaultMutableTreeNode nodo, boolean activas, boolean terminadas){
        DefaultMutableTreeNode node;
        Date fecha = new Date();
        if (nodo.getUserObject().getClass().getSimpleName().toUpperCase().equals("PERSONA")) {
            try {
                Persona personaVisitada = (Persona) nodo.getUserObject();
                VisitaAction visAction = new VisitaAction();
                if (activas) {
                    List<Visita> visitasActivas = new ArrayList<Visita>();
                    visitasActivas=visAction.findActivasByPersona(personaVisitada,fecha);
                    for (int i = 0; i < visitasActivas.size(); i++) {
                        Visita visita = visitasActivas.get(i);
                        node = new DefaultMutableTreeNode(visita, true);
                        nodo.add(node); 
                    }
                }
                
                if (terminadas) {
                    List<Visita> visitasTerminadas = new ArrayList<Visita>();
                    visitasTerminadas=visAction.findTerminadasByPersona(personaVisitada,fecha);
                    for (int i = 0; i < visitasTerminadas.size(); i++) {
                        Visita visita = visitasTerminadas.get(i);
                        node = new DefaultMutableTreeNode(visita, true);
                        nodo.add(node); 
                    }
                }
              
            } catch (ErrorInesperado ex) {
                Logger.getLogger(UtilesArbol.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }else{
            try {
                Organizacion organizacionVisitada = (Organizacion) nodo.getUserObject();
                VisitaAction visAction = new VisitaAction();
                
                if (activas) {
                    List<Visita> visitasPendientes = new ArrayList<Visita>();
                    visitasPendientes=visAction.findActivasByArea(organizacionVisitada,fecha);
               
                    for (int i = 0; i < visitasPendientes.size(); i++) {
                        if (visitasPendientes.get(i).getPersonaVisitada()==null) {
                            Visita visita = visitasPendientes.get(i);
                            node = new DefaultMutableTreeNode(visita, true);
                            nodo.add(node); 
                        }
                    }
                }
                if (terminadas) {
                    List<Visita> visitasTerminadas = new ArrayList<Visita>();
                    visitasTerminadas=visAction.findTerminadasByArea(organizacionVisitada,fecha);
               
                    for (int i = 0; i < visitasTerminadas.size(); i++) {
                        if (visitasTerminadas.get(i).getPersonaVisitada()==null) {
                            Visita visita = visitasTerminadas.get(i);
                            node = new DefaultMutableTreeNode(visita, true);
                            nodo.add(node); 
                        }
                    }
                }
            } catch (ErrorInesperado ex) {
                Logger.getLogger(UtilesArbol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
   
}
