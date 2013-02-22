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
import py.gov.itaipu.controlacceso.model.exception.ErrorInesperado;

/**
 *
 * @author fboy
 */
public class UtilesArbol {

    public UtilesArbol() {
    }

    public static DefaultMutableTreeNode crearArbol(String tituloArbol, boolean incluyePersonas) throws ErrorInesperado {
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
        agregarhijos(node, incluyePersonas);
        return root;
    }

    private static void agregarhijos(DefaultMutableTreeNode nodo, boolean incluyePersonas) {
        if (incluyePersonas) {
            agregarPersonas(nodo);
        }
        DefaultMutableTreeNode node;
        Organizacion org = (Organizacion) nodo.getUserObject();
        List<Organizacion> hijas = org.getOrganizacionesHijas();
        for (Organizacion organizacion : hijas) {
            node = new DefaultMutableTreeNode(organizacion, true);
            nodo.add(node);
            agregarhijos(node, incluyePersonas);
        }
    }

    private static void agregarPersonas(DefaultMutableTreeNode nodo) {
        DefaultMutableTreeNode node;
        Organizacion org = (Organizacion) nodo.getUserObject();
        List<Persona> personas = org.getPersonas();
        for (Persona persona : personas) {
            node = new DefaultMutableTreeNode(persona, true);
            nodo.add(node);
        }
    }
}
