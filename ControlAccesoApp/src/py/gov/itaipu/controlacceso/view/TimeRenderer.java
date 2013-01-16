/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vimartih
 */
public class TimeRenderer extends DefaultTableCellRenderer {

    private SimpleDateFormat formatter;

    public TimeRenderer() {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }
    
    public TimeRenderer(String pattern) {
        formatter = new SimpleDateFormat(pattern);
    }

    @Override
    public void setValue(Object value) {
        setText((value == null) ? "" : formatter.format(value));
    }
}