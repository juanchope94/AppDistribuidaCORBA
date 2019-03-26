
package Administrador.Vista;

import sop_corba.*;


/**
 *
 * @author USUARIO
 */
public class CallBackImpl extends CallBackPOA {

    GUIAdministrador GUI;

    public CallBackImpl(GUIAdministrador GUI)
    {
            this.GUI=GUI;

    }

    @Override
    public void recibirNoti(ClsDatosNotifyDTO objDatNotify) {
        GUI.mostrarNotify(objDatNotify);
    }

}