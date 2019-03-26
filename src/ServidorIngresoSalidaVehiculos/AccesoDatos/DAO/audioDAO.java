/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorIngresoSalidaVehiculos.AccesoDatos.DAO;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import sop_corba.ClsRespuestaDTO;

/**
 *
 * @author USUARIO
 */
public class audioDAO {
    
      

    
    public   byte[]  obtenerAudio(String opc) throws UnsupportedAudioFileException, IOException
    {
        
      
        File  file; 
        
        if(opc.equals("1"))
              file=new File("../bin/ServidorIngresoSalidaVehiculos/LogicaNegocio/Bienvenido.wav");
        else
             file=new File("../bin/ServidorIngresoSalidaVehiculos/LogicaNegocio/VuelvaPronto.wav");
       
        
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        AudioInputStream ais=AudioSystem.getAudioInputStream(file);
        AudioSystem.write(ais,AudioFileFormat.Type.WAVE, baout);
        ais.close();
        baout.close();
        byte[] data = baout.toByteArray();
              
        return data;
    }
    
    
}
