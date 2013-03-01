/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.utils.tree;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import py.gov.itaipu.controlacceso.model.Persona;
import py.gov.itaipu.controlacceso.model.Visita;

/**
 *
 * @author fboy
 */
public class CustomIconRenderer extends DefaultTreeCellRenderer {
    Icon personaIcon;
    Icon visitaActivaIcon;
    Icon visitaTerminadaIcon;
    Icon deptoIcon;
    public CustomIconRenderer() {
        deptoIcon = new ImageIcon(getClass().getResource("/resource/img/carpeta_cerrada.jpg"));
        personaIcon = new ImageIcon(getClass().getResource("/resource/img/persona.jpg"));
        visitaActivaIcon = new ImageIcon(getClass().getResource("/resource/img/circle_green.png"));
        visitaTerminadaIcon = new ImageIcon(getClass().getResource("/resource/img/circle_red.png"));
        
    }

    public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        Object nodeObj = ((DefaultMutableTreeNode)value).getUserObject();
        // check whatever you need to on the node user object
        if (nodeObj.getClass().getSimpleName().equals("Persona")) {
            setIcon(personaIcon);
        } else if (nodeObj.getClass().getSimpleName().equals("Visita")) {
            Visita vis = (Visita) nodeObj;
            if (vis.getFechaSalida()==null) {
                setIcon(visitaActivaIcon);
            }else{
                setIcon(visitaTerminadaIcon);
            }
           
        }else{
            setIcon(deptoIcon);
        }
    return this;
    }

}
