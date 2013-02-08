/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.utils.tree;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import py.gov.itaipu.controlacceso.action.CRUDAction;
import py.gov.itaipu.controlacceso.model.Organizacion;
import py.gov.itaipu.controlacceso.model.Persona;

/**
 *
 * @author fboy
 */
public class UtilesArbol {
    
    

    public UtilesArbol() {
        
    }
    
    public static DefaultMutableTreeNode crearArbol(String tituloArbol,boolean incluyePersonas){
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(tituloArbol);
            CRUDAction<Organizacion> organizacionAction = new CRUDAction<Organizacion>(new Organizacion());
            
            List<Organizacion> listaOrganizacionesArbolAux = new ArrayList<Organizacion>();
            
            DefaultMutableTreeNode node;
            DefaultMutableTreeNode nodePadre;
            
            listaOrganizacionesArbolAux = organizacionAction.findByNamedQuery("Organizacion.findOrgMaxNivel");
            int maxNivel = listaOrganizacionesArbolAux.get(0).getNivelOrganigrama().intValue();
             
            //SE AGREGAN TODAS LAS ORGANIZACIONES AL ARBOL
            listaOrganizacionesArbolAux = organizacionAction.findByNamedQuery("Organizacion.findAllInterna");
            for (int i = 0; i < listaOrganizacionesArbolAux.size(); i++) {
                Organizacion organizacion = listaOrganizacionesArbolAux.get(i);
                nodePadre = new DefaultMutableTreeNode(organizacion,true);
                root.add(nodePadre);   
                if (incluyePersonas) {
                    ///SE AGREGAN LAS PERSONAS DEPENDIENTES DE CADA DEPARTAMENTO
                    List<Persona> listaPersonasDepto = new ArrayList<Persona>();
                    listaPersonasDepto = organizacion.getPersonas();
                    if (listaPersonasDepto!=null && listaPersonasDepto.size()>0) {
                        for (int j = 0; j < listaPersonasDepto.size(); j++) {
                            Persona persona1 = listaPersonasDepto.get(j);
                            node = new DefaultMutableTreeNode(persona1,false);
                            nodePadre.add(node);
                        }
                    }
                }
           }
               
            // SE RECORREN LAS ORGANIZACIONES DESDE NIVEL MAXIMO A MINIMO Y SE INTRODUCEN NODOS HIJOS
                int cantOrganizaciones = root.getChildCount();
                for (int b = maxNivel ; b > 0 ; b--) {
                     for (int j = 0; j < cantOrganizaciones; j++) {
                         node = (DefaultMutableTreeNode) root.getChildAt(j);
                         Organizacion org = (Organizacion)node.getUserObject();
                         if (org.getOrganizacionPadre()!=null) {
                                if (org.getNivelOrganigrama()==b) {
                                //buscamos su padre
                                for (int h = 0; h < cantOrganizaciones; h++) {
                                   nodePadre = (DefaultMutableTreeNode) root.getChildAt(h);
                                   Organizacion orgPadre = (Organizacion)nodePadre.getUserObject();
                                    if (org.getOrganizacionPadre().getId() == orgPadre.getId()) {
                                        //Ingreso el Nodo Hijo en el nodo padre y resto 1 a la cantidad inicial de nodos del nivel
                                        nodePadre.add(node);
                                        cantOrganizaciones = cantOrganizaciones - 1;
                                        j = j - 1;
                                        break;
                                    }
                                }
                             }
                         }
                     }
                }
    return root; 
    }
    
    
    
    
}
