package org.ProyectoWeb.dao;

import org.ProyectoWeb.conexion.AdministradorConexiones;
import org.ProyectoWeb.entities.Empleado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    //Consultar a la Base de Datos los empleados
    // Insertar nuevos empleados
    // modificar 1 empleado
    // eliminar 1 empleado

    // insertar un nuevo empleado

    public static void insertar(Empleado empleado) {

        //2. String paraConsulta Sql de inserción

        String sql = "INSERT INTO empleados (idEmpleados,nombre,apellido,salarioBase)"
                + "VALUES ( " + empleado.getId() +
                ",'" + empleado.getNombre()
                + "','" + empleado.getApellido() +
                "'," + empleado.getSalarioBase() + ")";


        try {

            //1. Conectamos
            Connection conn = AdministradorConexiones.obtenerConexion();

            //Paso 3 Creamos el Statement
            Statement st = conn.createStatement();

            //Paso 4 Ejecuto
            st.execute(sql);

            //Paso 5 Cierro
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//Cierra el insert


    public static void actualizar(Empleado empleado) {

        //Veo si existe el id
        if (existeEmpleado(empleado.getId()) == true) {

            //Creo la consulta Actualizar
            String sql = "UPDATE empleados SET nombre = '" + empleado.getNombre()
                    + "', apellido = '" + empleado.getApellido() +
                    "', salarioBase = " + empleado.getSalarioBase() +
                    " WHERE idEmpleados =" + empleado.getId();

            //System.out.println(sql); /Por si no funciona te da la consulta y luego la metemos en workbench para ver donde le errmaos.

            try {
                //Abtimos la conexión
                Connection conn = AdministradorConexiones.obtenerConexion();

                //Creamos la consulta
                Statement st = conn.createStatement();

                //Ejecuto
                st.executeUpdate(sql);

                //Cerramos las conexiones
                st.close();
                conn.close();

                System.out.println("Empleado Actualizado");

            } catch (Exception e) {
                System.out.println("Error al actulizar el empleado");
            }

        }

    }


    public static boolean existeEmpleado(int idEmpleados) {

        //1- Creamos la conexión
        //2- Ejecutar consulta que busca el empleado id
        //3- Si encontro devulve V, sino F
        //4- Cierra la conexión

        String sql = "SELECT * FROM empleados WHERE idEmpleados = " + idEmpleados;
        boolean existe = false;


        try {

            //Abro la conexxion a la bd
            Connection conn = AdministradorConexiones.obtenerConexion();

            //Creo consulta
            Statement st = conn.createStatement();

            //Ejecuto y Guardo el resultado de la ejecución  de la consulta
            //En un objeto RESULSET
            ResultSet rs = st.executeQuery(sql);

            //Analizo si hay resultado si hay un proximo lo encontro
            if (rs.next())
                existe = true;
            else existe = false;

            rs.close();
            st.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return existe;
    }

    //Traer toda la lista de empleados
    public static List<Empleado> getAll() {
        try {
            String sql = "SELECT * FROM empleados";

            Connection conn = AdministradorConexiones.obtenerConexion();

            List<Empleado> lista = new ArrayList<>();
            Empleado empleado = new Empleado();

            Statement st = conn.createStatement();

            //Creo el objeto tabla(Resulset y ejecuto la ocnsulta)
            ResultSet rs = st.executeQuery(sql);

            //Si tiene ambos
            while (rs.next()) {
                empleado = new Empleado();
                empleado.setSalarioBase(rs.getDouble("SalarioBase"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setId(rs.getInt("idEmpleados"));

                //Guardo los datos de la fila en empleado
                //Agreso empleado a lña lista
                lista.add(empleado);
            }
            return lista;

        } catch (Exception e) {
            System.out.println("Error al obtener los empleados");
            return null;
        }
    }

    public static boolean delete(int idEmpleados) {
        String sql = "DELETE FROM empleados WHERE (idEmpleados = " + idEmpleados + ")";
        //Ver si existe

        if (existeEmpleado(idEmpleados) == true) {
            try {
                //Abro la conexión
                Connection conn = AdministradorConexiones.obtenerConexion();

                Statement st = conn.createStatement();

                //Ejecuto
                st.execute(sql);

                //Cierro
                st.close();
                conn.close();
                return true;
            } catch (Exception e) {
                System.out.println("Error al eliminar el empleado");
                return false;
            }

        } else System.out.println("El empleado no existe");

        //Creo el Statement

        return false;
    }

    //Buscar y devolver un empleado por id
    public static Empleado getById(int idEmpleados){

        String sql = "SELECT * FROM empleados WHERE idEmpleados = " + idEmpleados;

        try {

            //Abro la conexxion a la bd
            Connection conn = AdministradorConexiones.obtenerConexion();

            //Creo consulta
            Statement st = conn.createStatement();

            //Ejecuto y Guardo el resultado de la ejecución  de la consulta
            //En un objeto RESULSET
            ResultSet rs = st.executeQuery(sql);

            //Analizo si hay resultado si hay un proximo lo encontro
            if (rs.next()) {
                //Con los datos que encontro del empleado
                Empleado empleado = new Empleado();
                empleado.setApellido(rs.getString("apellido"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setSalarioBase(rs.getDouble("salarioBase"));
                empleado.setId(rs.getInt("idEmpleados"));

                return empleado;
            }

        }catch (Exception e){
            System.out.println("Error al encontrar el empleado");
            return null;
        }

        return  null;
    }



}

