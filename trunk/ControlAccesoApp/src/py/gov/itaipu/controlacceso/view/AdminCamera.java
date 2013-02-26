/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.view;

import java.io.IOException;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import py.gov.itaipu.controlacceso.view.visita.JDialogFotografia;

/**
 *
 * @author vimartih
 */
public class AdminCamera {

    public static CaptureDeviceInfo di = null;
    public static MediaLocator ml = null;
    public static CaptureDeviceInfo device;
    public static Player player;
    public static JDialogFotografia formulario;
    
    public static void iniciar() throws IOException, NoPlayerException, CannotRealizeException {
        String str1 = "vfw:Microsoft WDM Image Capture (Win32):0";
        di = CaptureDeviceManager.getDevice(str1);
        ml = di.getLocator();
        player = Manager.createRealizedPlayer(ml);        
        formulario = new JDialogFotografia(null, true);        
    }

    public static void start() {
        player.start();
    }

    public static void stop() {
        player.stop();
    }
    
    public static void close(){
        player.close();
    }
}
