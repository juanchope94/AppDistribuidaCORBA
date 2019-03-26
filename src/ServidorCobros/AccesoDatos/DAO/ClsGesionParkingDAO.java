/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorCobros.AccesoDatos.DAO;


import ServidorCobros.AccesoDatos.BD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import sop_corba.ClsConsultaDTO;
import sop_corba.ClsPagoDTO;
import sop_corba.ClsPreInformeDTO;
import sop_corba.ClsVehiculoDTO;

/**
 *
 * @author USUARIO
 */
public class ClsGesionParkingDAO {

    public ClsConsultaDTO consultarFechaIngreso(String placa) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        ClsConsultaDTO objCons = new ClsConsultaDTO();

        try {
            PreparedStatement sentencia = null;
            String consulta = "select controlvehiculo.FechaHoraIngreso,controlvehiculo.idControlVehiculo from controlvehiculo where controlvehiculo.placa=? AND controlvehiculo.FechaHoraSalida IS NULL";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setString(1, placa);
            ResultSet res = sentencia.executeQuery();
            if (res.next()) {

                objCons.setFecha(res.getTimestamp("FechaHoraIngreso"));
                objCons.setId(res.getInt("idControlVehiculo"));

            } else {
                objCons.setFecha(null);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la consulta de un registro: " + e.getMessage());
        }

        return objCons;
    }

    public float consultarCostoHora(int id) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        float valorHora = 0;

        try {
            PreparedStatement sentencia = null;
            String consulta = "select organizacion.costoPorHora from organizacion where organizacion.id=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, id);
            ResultSet res = sentencia.executeQuery();
            if (res.next()) {
                valorHora = res.getFloat("costoPorHora");
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la consulta de un registro: " + e.getMessage());
        }

        return valorHora;
    }

    public boolean registrarPago(ClsPagoDTO objPag) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        int resultado = -1;
        try {
            PreparedStatement sentencia = null;
            String consulta = "insert into pago(pago.idControlVehiculo,pago.placa,pago.NoTicket,pago.fechaHoraPago) values(?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, objPag.getId());
            sentencia.setString(2, objPag.getPlaca());
            if (objPag.getNoTicket().equals("")) {
                sentencia.setString(3, null);
            } else {
                sentencia.setString(3, objPag.getNoTicket());
            }

            Date fechaPago = new Date();
            Timestamp timestamp = new Timestamp(fechaPago.getTime());
            sentencia.setTimestamp(4, timestamp);
            resultado = sentencia.executeUpdate();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la inserción: " + e.getMessage());
        }

        return resultado == 1;
    }

    public ArrayList<ClsVehiculoDTO> consultarVehiculosAdentro() {
        ArrayList<ClsVehiculoDTO> vehiculos = new ArrayList();
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        String fechaLarga;

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "select controlvehiculo.placa,controlvehiculo.FechaHoraIngreso from controlvehiculo where controlvehiculo.FechaHoraSalida IS NULL";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {

                ClsVehiculoDTO vehiculo = new ClsVehiculoDTO();
                vehiculo.setPlaca(res.getString("placa"));
                fechaLarga = res.getTimestamp("FechaHoraIngreso").toString();
                String[] partes = fechaLarga.split(" ");
                vehiculo.setFecha(partes[0]);
                vehiculo.setHora(partes[1]);
                vehiculos.add(vehiculo);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la inserción: " + e.getMessage());
        }

        return vehiculos;
    }

    public ArrayList<ClsPreInformeDTO> consultarPreInforme() {
        ArrayList<ClsPreInformeDTO> preInformes = new ArrayList();
        ConexionBD conexionABaseDeDatos = new ConexionBD();

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "SELECT controlvehiculo.placa,controlvehiculo.FechaHoraIngreso,controlvehiculo.FechaHoraSalida,pago.NoTicket "
                    + "FROM controlvehiculo JOIN pago ON controlvehiculo.idControlVehiculo = pago.idControlVehiculo "
                    + "WHERE controlvehiculo.FechaHoraSalida IS NOT NULL";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {

                ClsPreInformeDTO preInforme = new ClsPreInformeDTO();
                preInforme.setPlaca(res.getString("placa"));
                preInforme.setFechaEntrada(res.getTimestamp("FechaHoraIngreso"));
                preInforme.setFechaSalida(res.getTimestamp("FechaHoraSalida"));
                if(res.getString("NoTicket")==null)
                    preInforme.setNoTicket("");
                else
                      preInforme.setNoTicket(res.getString("NoTicket"));

                preInformes.add(preInforme);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la inserción: " + e.getMessage());
        }

        return preInformes;
    }

}
