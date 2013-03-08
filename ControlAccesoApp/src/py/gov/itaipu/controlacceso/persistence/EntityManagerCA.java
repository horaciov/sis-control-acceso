/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;

/**
 *
 * @author vimartih
 */
public class EntityManagerCA {

    private static final String NAME_PU = "ControlAccesoAppPU";
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static Connection conexion;

    public static void iniciarContexto() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(NAME_PU);
        }
    }

    public static EntityManager getEntityManger() {
        if (em == null) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(NAME_PU);
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    public static Connection getConexion() throws SQLException {
        if (conexion == null) {
            conexion = ((Session)em.getDelegate()).connection();
        }        
        return conexion;
    }

    public static void setConexion(Connection conexion) {
        EntityManagerCA.conexion = conexion;
    }

    public static void cerrar() {
        em.close();
        em = null;
        emf.close();
        emf = null;
    }
}
