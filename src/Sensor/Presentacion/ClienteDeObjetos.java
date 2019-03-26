package Sensor.Presentacion;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import sop_corba.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ClienteDeObjetos {

    public static void main(String args[]) {

        try {
            
            System.out.println("1. Crea e inicia el ORB");
            ORB orb = ORB.init(args, null);
            System.out.println("2. Obtiene una referencia al servicio de nombrado por medio del orb");
            org.omg.CORBA.Object objRefNameService = orb.resolve_initial_references("NameService");

            System.out.println("3. Convierte la ref genérica a ref de NamingContextExt");
            NamingContextExt refContextoNombrado = NamingContextExtHelper.narrow(objRefNameService);

            System.out.println("4. Resuelve la referencia del objeto en el N_S.");

            String identificadorServant = "identificadorServant";

            NameComponent[] path = new NameComponent[1];
            path[0] = new NameComponent();
            path[0].id = identificadorServant;
            path[0].kind = "tipoServicio";

            org.omg.CORBA.Object objRef = refContextoNombrado.resolve(path);

            System.out.println("5. Convierte la referencia de un objeto generico a una referencia al servant ");

            Solucion objSolucion = SolucionHelper.narrow(objRef);
            MenuPrincipal(objSolucion);
            System.out.println("Invocación de los métodos como si fueran locales");

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    private static void MenuPrincipal(Solucion objSolucion) throws IOException {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Enviar placa para registro de entrada");
            System.out.println("2. Enviar placa para registro de salida");
            System.out.println("3. Salir");

            opcion = Utilidades.leerEntero();

            switch (opcion) {
                case 1:
                    opcion1(objSolucion);
                    break;
                case 2:
                    opcion2(objSolucion);
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 4);
    }

    private static void opcion1(Solucion objSolucion) throws IOException {
        int num;
        String op;
        do {
            System.out.println("Ingrese la placa del vehiculo:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            op = br.readLine();
            num = op.length();
        } while (num != 6);
          
        ClsRespuestaDTO resp=new ClsRespuestaDTO();
        resp=objSolucion.enviarPlacaAutomovilEntrada(op);
        if (resp.getOpcion()==1)
        {         
       
            System.out.println("Registro exitoso del vehículo de placa no [" + op + "] bienvenido a UniParking");
            byte[] byteArray = resp.getMensajeAudio();
            ByteArrayInputStream audio = new ByteArrayInputStream(byteArray);
            AudioStream as=new AudioStream(audio); 
            AudioPlayer.player.start(as);            
            
        }
         else 
            System.out.println("Error, el vehículo de placa no [" + op + "]  se encuentra dentro del parqueadero");
        

    }

    private static void opcion2(Solucion objSolucion) throws IOException {
        int num;
        String op;
        do {
            System.out.println("Ingrese la placa del vehiculo:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            op = br.readLine();
            num = op.length();
        } while (num != 6);

        ClsRespuestaDTO resp=objSolucion.enviarPlacaAutomovilSalida(op);
        switch (resp.getOpcion())
        {
            case 1:
              System.out.println("Salida exitosa del vehículo de placa no [" + op + "]  vuelva pronto a UniParking");
            byte[] byteArray = resp.getMensajeAudio();
            ByteArrayInputStream audio = new ByteArrayInputStream(byteArray);
            AudioStream as=new AudioStream(audio); 
            AudioPlayer.player.start(as);
             break;
            case 2:
              System.out.println("Error, el vehículo de placa no [" + op + "] no se encuentra dentro del parqueadero");        
             break;
            case 3:
              System.out.println("Error excedio el maximo de tiempo(15min) permitido para salir despues de realizar el pago, dirijase al punto de pago");        
             break;
            case 4:
              System.out.println("Error No existe un pago registrado para este vehiculo acerquese al puntgo de pago!!");
             break;
        }
        

    }

}
