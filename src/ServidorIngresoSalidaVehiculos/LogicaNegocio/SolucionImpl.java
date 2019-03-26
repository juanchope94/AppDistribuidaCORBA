/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorIngresoSalidaVehiculos.LogicaNegocio;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.in;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import sop_corba.ClsRespuestaDTO;
import sop_corba.ClsDatosNotifyDTO;
import sop_corba.CallBack;
import sop_corba.CallBackHelper;
import ServidorIngresoSalidaVehiculos.AccesoDatos.DAO.audioDAO;
import ServidorIngresoSalidaVehiculos.AccesoDatos.DAO.registroDAO;
import sop_corba.SolucionOperations;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author USUARIO
 */
public class SolucionImpl implements SolucionOperations {

    registroDAO regDAO = new registroDAO();
    audioDAO audDAO= new audioDAO();

    @Override
    public ClsRespuestaDTO enviarPlacaAutomovilEntrada(String placa) {
        
        System.out.println("Enviar placa para registro de entrada!!!!");
        ClsDatosNotifyDTO objNotify= new ClsDatosNotifyDTO();
        int resp = 0;

        if (determinarRegistroAutomovil(placa) ==-1) {
            resp = 1;
            Date fecha = new Date();
            regDAO.ingresarRegistroEntrada(placa, fecha);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-mm-dd HH:mm:ss");
            String fechalarga=dateFormat.format(fecha);
            String[] partes=fechalarga.split(" ");
            objNotify.setOpcion(1);
            objNotify.setPlaca(placa);
            objNotify.setFecha(partes[0]);
            objNotify.setHora(partes[1]);
            cliente(objNotify);
        } 
        else {
            resp = 2;
        }

        ClsRespuestaDTO objResp = new ClsRespuestaDTO();
       
        try {
            byte[] audio = audDAO.obtenerAudio("1");
            objResp.setOpcion(resp);
            objResp.setMensajeAudio(audio);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SolucionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SolucionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
   
            
        return objResp;

    }

    @Override
    public ClsRespuestaDTO enviarPlacaAutomovilSalida(String placa) {
       

        System.out.println("Enviar placa para registro de salida!!!!");
        ClsDatosNotifyDTO objNotify= new ClsDatosNotifyDTO();
        int resp = 0;
        int id=determinarRegistroAutomovil(placa) ;
        if (id!=-1) {
            int consultaPago=verificarPago(id);
            if(consultaPago==3)
            {
                resp = 1;
                Date fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-mm-dd HH:mm:ss");
                String fechalarga=dateFormat.format(fecha);
                String[] partes=fechalarga.split(" ");
                regDAO.ingresarRegistroSalida(placa, fecha);
                objNotify.setOpcion(2);
                objNotify.setPlaca(placa);
                objNotify.setFecha(partes[0]);
                objNotify.setHora(partes[1]);
                cliente(objNotify);
            }
            else if(consultaPago==2)
                 resp=3;
            
            else
                resp=4;
            
        } else 
            resp = 2;
        
        ClsRespuestaDTO objResp = new ClsRespuestaDTO();
        try {
           byte[] audio = audDAO.obtenerAudio("2");
            objResp.setOpcion(resp);
            objResp.setMensajeAudio(audio);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SolucionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SolucionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
        return objResp;


     
    }
    
    
    public int determinarRegistroAutomovil(String placa) {
        System.out.println("Consultar registro de un vehiculo!!!!");
        return regDAO.consultarRegistro(placa);
    }

    public int verificarPago(int id) {
        System.out.println("Consultar pago de un vehiculo!!!!");
        int resp=0;
        Date fechaIni=null;
        fechaIni=regDAO.consultarPago(id);
        if(fechaIni!=null)
        {
            Date fechaFin = new Date();
            int minutos=0;
            int diferencia=(int) ((fechaFin.getTime()-fechaIni.getTime())/1000);
            if(diferencia>60) {
                minutos=(int)Math.floor(diferencia/60);
               
            }
            
            if(minutos>15)
                resp=2;
            else
                resp=3;
          
        }
        else
            resp=1;
        return resp;
    }

    public void cliente(ClsDatosNotifyDTO objNotify)
    {
        System.out.println("Lanzar cliente!!!!");
          CallBack ref;

        try{
            // crea e inicia el ORB

            String[] vector= new String[4];
                        vector[0]="–ORBInitialHost";
                        vector[1]="localhost";
                        vector[2]="-ORBInitialPort";
                        vector[3]="2020";
            ORB orb = ORB.init(vector, null);
    
            // obtiene la base del naming context
            org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");     
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
     
            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objCallBack";
            ref = CallBackHelper.narrow(ncRef.resolve_str(name));
    
            System.out.println("Obtenido el manejador sobre el servidor de objetos: " +ref);
            
            ref.recibirNoti(objNotify);
            
    
        } catch (Exception e) {
              System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }

    }


    

}
