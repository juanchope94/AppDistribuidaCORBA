/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorIngresoSalidaVehiculos.AccesoDatos.DAO;


import ServidorIngresoSalidaVehiculos.AccesoDatos.BD.ConexionBD;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author USUARIO
 */
public class registroDAO {

    public boolean ingresarRegistroEntrada(String placa, Date fecha) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        int resultado = -1;
        try {
            PreparedStatement sentencia = null;
            String consulta = "insert into controlvehiculo(controlvehiculo.placa,controlvehiculo.fechaHoraIngreso) values(?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setString(1, placa);
            Timestamp timestamp = new Timestamp(fecha.getTime());
            sentencia.setTimestamp(2, timestamp);
            resultado = sentencia.executeUpdate();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la inserción: " + e.getMessage());
        }

        return resultado == 1;
    }

    public boolean ingresarRegistroSalida(String placa, Date fecha) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        int resultado = -1;
        try {
            PreparedStatement sentencia = null;
             String consulta = "update controlvehiculo set controlvehiculo.FechaHoraSalida=? "
                              +"where controlvehiculo.placa=? AND controlvehiculo.FechaHoraSalida IS NULL";                                               
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            java.sql.Date sDate = new java.sql.Date(fecha.getTime());
            Timestamp timestamp = new Timestamp(fecha.getTime());
            sentencia.setTimestamp(1, timestamp);
            sentencia.setString(2,placa);
            resultado = sentencia.executeUpdate();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la inserción: " + e.getMessage());
        }

        return resultado == 1;
    }

    public int consultarRegistro(String placa) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        boolean resultado = false;
        int identificador=-1;
        try {
            PreparedStatement sentencia = null;
            String consulta = "select controlvehiculo.idControlVehiculo from controlvehiculo where controlvehiculo.placa=? AND controlvehiculo.FechaHoraSalida IS NULL";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setString(1, placa);
            ResultSet res = sentencia.executeQuery();
            if (res.next()) {
                resultado = true;
                identificador=res.getInt("idControlVehiculo");

            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la consulta de un registro: " + e.getMessage());
        }

        return identificador;
    }

    public Date consultarPago(int id) {
        ConexionBD conexionABaseDeDatos = new ConexionBD();
        conexionABaseDeDatos.conectar();
        boolean resultado = false;
        Date fecha=null;

        try {
            PreparedStatement sentencia = null;
            String consulta = "select pago.fechaHoraPago from pago where pago.idControlVehiculo=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, id);
            ResultSet res = sentencia.executeQuery();
            if (res.next()) {
                resultado = true;
                fecha=res.getTimestamp("fechaHoraPago");
                           }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la consulta de un registro: " + e.getMessage());
        }

        return fecha;
    }

}
