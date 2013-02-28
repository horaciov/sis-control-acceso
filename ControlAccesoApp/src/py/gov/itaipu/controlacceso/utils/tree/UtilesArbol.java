/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.utils.tree;

import java.util.ArrayList;
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

        //SE AGREGAN TODAS LAS ORGANIZACIONES AL ARBOL
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

    public static DefaultMutableTreeNode crearArbolFiltrado(String tituloArbol, String filtro, boolean incluyePersonas) throws ErrorInesperado {
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
            }     
        }
         
        return root;
    }
    
    
    private static void agregarhijos(DefaultMutableTreeNode nodo, boolean incluyePersonas,boolean incluyeVisitasActivas, boolean incluyeVisitasTerminadas) {
        if (incluyePersonas) {
            agregarPersonas(nodo,incluyeVisitasActivas,incluyeVisitasTerminadas);
        }
        if (incluyeVisitasActivas) {
            agregarVisitasActivas(nodo);
        }
        
        if (incluyeVisitasTerminadas) {
            agregarVisitasTerminadas(nodo);
        }
        
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
                if (incluyeVisitasActivas) {
                    agregarVisitasActivas(nodo);
                }
                
                if (incluyeVisitasTerminadas) {
                    agregarVisitasTerminadas(nodo);
                }
                
            }
        }
    }
    
    private  static  void agregarVisitasActivas(DefaultMutableTreeNode nodo){
        DefaultMutableTreeNode node;
        if (nodo.getUserObject().getClass().getSimpleName().equals("PERSONA")) {
            try {
                Persona personaVisitada = (Persona) nodo.getUserObject();
                List<Visita> visitasPendientes = new ArrayList<Visita>();
                VisitaAction visAction = new VisitaAction();
                visitasPendientes=visAction.findPendientesByPersona(personaVisitada);
                for (int i = 0; i < visitasPendientes.size(); i++) {
                    Persona visitante = visitasPendientes.get(i).getPersona();
                    node = new DefaultMutableTreeNode(visitante, true);
                    nodo.add(node); 
                }
             } catch (ErrorInesperado ex) {
                Logger.getLogger(UtilesArbol.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }else{
            try {
                Organizacion organizacionVisitada = (Organizacion) nodo.getUserObject();
                List<Visita> visitasPendientes = new ArrayList<Visita>();
                VisitaAction visAction = new VisitaAction();
                visitasPendientes=visAction.findPendientesByArea(organizacionVisitada);
                for (int i = 0; i < visitasPendientes.size(); i++) {
                    Persona visitante = visitasPendientes.get(i).getPersona();
                    node = new DefaultMutableTreeNode(visitante, true);
                    nodo.add(node); 
                }
                
            } catch (ErrorInesperado ex) {
                Logger.getLogger(UtilesArbol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private  static  void agregarVisitasTerminadas(DefaultMutableTreeNode nodo){
        if (nodo.getUserObject().getClass().getSimpleName().equals("PERSONA")) {
            
        }else{
        
        }
        
    }
    
  
}
