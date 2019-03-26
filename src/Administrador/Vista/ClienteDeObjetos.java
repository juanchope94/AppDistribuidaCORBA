package Administrador.Vista;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import sop_corba.*;

public class ClienteDeObjetos
{


  public static void main(String args[])
    {
        
        
      try{
           
            System.out.println("1. Crea e inicia el ORB");
	           ORB orb = ORB.init(args, null);
            System.out.println("2. Obtiene una referencia al servicio de nombrado por medio del orb");
            org.omg.CORBA.Object objRefNameService = orb.resolve_initial_references("NameService");
            
            System.out.println("3. Convierte la ref genérica a ref de NamingContextExt");
            NamingContextExt refContextoNombrado = NamingContextExtHelper.narrow(objRefNameService);
             
            System.out.println("4. Resuelve la referencia del objeto en el N_S.");
           
            String identificadorServant = "identificadorServant";
            
            NameComponent [] path = new NameComponent[1];
            path[0] = new NameComponent();
            path[0].id = identificadorServant;
            path[0].kind = "tipoServicio";     
            
            org.omg.CORBA.Object objRef= refContextoNombrado.resolve(path);
                    
            System.out.println("5. Convierte la referencia de un objeto generico a una referencia al servant ");
            
            ServidorCobros objSolucion = ServidorCobrosHelper.narrow(objRef);
            GUIAdministrador objMenu= new GUIAdministrador(objSolucion);
            objMenu.setVisible(true);
            System.out.println("Invocación de los métodos como si fueran locales");
           
        

	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }

}

