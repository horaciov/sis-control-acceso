/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model.exception;

/**
 *
 * @author vimartih
 */
public class EntidadExiste extends Exception{
    
    public EntidadExiste(String mensaje){
        super(mensaje);
    }
    
}
