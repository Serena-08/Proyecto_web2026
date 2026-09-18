package org.ProyectoWeb.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministradorConexiones {

    public static Connection obtenerConexion() {

        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbCadenaConexion = "jdbc:mysql://127.0.0.1:3306/progiempleados";
        String dbUsuario = "root";
        String dbPassword = "root";

        Connection conn = null;

        try {
            Class.forName(dbDriver);

            conn = DriverManager.getConnection(dbCadenaConexion, dbUsuario, dbPassword);

            System.out.println("Se conecto a la Base de Datos xD");

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el Driver");

        } catch (SQLException e) {
            System.out.println("No se pudo establecer la conexión a la Base de Datos");
        }
        return conn;
    }


}
