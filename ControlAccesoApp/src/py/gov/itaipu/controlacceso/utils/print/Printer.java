/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.print.*;

class Printer {

    public static PrintService[] listaDeImpresoras()
    {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        return printServices;
    }
}    