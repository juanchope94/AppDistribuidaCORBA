/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorCobros.LogicaNegocio;


import ServidorCobros.AccesoDatos.DAO.ClsGesionParkingDAO;
import java.util.ArrayList;
import java.util.Date;
import sop_corba.ClsCalculosDTO;
import sop_corba.ClsConsultaDTO;
import sop_corba.ServidorCobrosOperations;
import sop_corba.ClsInformeDTO;
import sop_corba.ClsPagoDTO;
import sop_corba.ClsPreInformeDTO;
import sop_corba.ClsVehiculoDTO;

/**
 *
 * @author USUARIO
 */
public class SolucionImpl implements ServidorCobrosOperations {

    ClsGesionParkingDAO regDAO = new ClsGesionParkingDAO();

    @Override
    public ClsCalculosDTO calcularCosto(String placa) {
        System.out.println("Calcular costo!!!!!");
        ClsCalculosDTO objCal = new ClsCalculosDTO();;
        ClsConsultaDTO objCons = new ClsConsultaDTO();
        objCons = regDAO.consultarFechaIngreso(placa);
        float costoTotal = 0;
        if (objCons.getFecha() != null) {
            Date fechaFin = new Date();
            int horas = calcularHoras(objCons.getFecha(), fechaFin);
            float costoHora = consultarCostoHora();
            costoTotal = calcularCostoParqueo(horas, costoHora);
            objCal.setCosto(costoTotal);
            objCal.setHoras(horas);
            objCal.setId(objCons.getId());
            objCal.setCostoHora(costoHora);

        } else {
            objCal.setCosto(-1);
        }

        return objCal;
    }

    @Override
    public boolean registrarPago(ClsPagoDTO objPago) {
        System.out.println("Registrar pago!!!!");
        return regDAO.registrarPago(objPago);
    }

    @Override
    public ClsVehiculoDTO[] listarVehiculosDentro() {
        System.out.println("Servidor: Listar vehiculos adentro!!!");
        ArrayList<ClsVehiculoDTO> vehiculos = regDAO.consultarVehiculosAdentro();
        ClsVehiculoDTO[] vehiculosVector = new ClsVehiculoDTO[vehiculos.size()];
        vehiculos.toArray(vehiculosVector);

        return vehiculosVector;
    }

    @Override
    public ClsInformeDTO[] consultarInformeCostos() {
        System.out.println("Servidor: Consultar informe!!!");
        ArrayList<ClsPreInformeDTO> preInformes = regDAO.consultarPreInforme();
        ClsInformeDTO[] vectorInformes = new ClsInformeDTO[preInformes.size()];
        for (int i = 0; i < preInformes.size(); i++) {
            ClsInformeDTO informe = new ClsInformeDTO();
            String fechaLargaEntrada;
            String fechaLargaSalida;
            String[] partesEntrada;
            String[] partesSalida;
            
            float costoTotal=0;
            int horas = calcularHoras(preInformes.get(i).getFechaEntrada(), preInformes.get(i).getFechaSalida());
            if(preInformes.get(i).getNoTicket().equals(""))
            {                
                float costoHora = consultarCostoHora();
                costoTotal = calcularCostoParqueo(horas, costoHora);
            }
            fechaLargaEntrada = preInformes.get(i).getFechaEntrada().toString();
            fechaLargaSalida = preInformes.get(i).getFechaSalida().toString();
            partesEntrada = fechaLargaEntrada.split(" ");
            partesSalida = fechaLargaSalida.split(" ");

            informe.setPlaca(preInformes.get(i).getPlaca());
            informe.setNoTicket(preInformes.get(i).getNoTicket());
            informe.setFechaEntrada(partesEntrada[0]);
            informe.setHoraEntrada(partesEntrada[1]);
            informe.setFechaSalida(partesSalida[0]);
            informe.setHoraSalida(partesSalida[1]);
            informe.setCostoParqueo(costoTotal);
            informe.setHorasPaqrueo(horas);

            vectorInformes[i] = informe;
        }

        return vectorInformes;
    }

    public float consultarCostoHora() {
        System.out.println("Consultar costo de una hora!!!!");
        return regDAO.consultarCostoHora(1);
    }

    public int calcularHoras(Date fecha, Date fechaFin) {
        System.out.println("Calcular Horas!!!!");
        int minutos = 0;
        int horas = 0;
        int diferencia = (int) ((fechaFin.getTime() - fecha.getTime()) / 1000);
        if (diferencia > 3600) {
            horas = (int) Math.floor(diferencia / 3600);
            diferencia = diferencia - (horas * 3600);
        }
        if (diferencia > 60) {
            minutos = (int) Math.floor(diferencia / 60);

        }

        if (minutos > 15) {
            horas = horas + 1;
        }

        return horas;

    }

    public float calcularCostoParqueo(int horas, float costoHora) {
        System.out.println("Calcular costo parqueo!!!!");
        float costoTotal = 0;
        costoTotal = costoHora * horas;
        return costoTotal;
    }

}
