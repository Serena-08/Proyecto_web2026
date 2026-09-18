package org.ProyectoWeb.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ProyectoWeb.dao.EmpleadoDAO;
import org.ProyectoWeb.entities.Empleado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/empleado")
public class EmpleadoServlet extends HttpServlet {

    EmpleadoDAO empleadoDao;

    @Override
    public void init(){
        empleadoDao = new EmpleadoDAO();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        List<Empleado> lista = new ArrayList<>();
        lista= EmpleadoDAO.getAll();

        //Seteo los atributos de la requets
        req.setAttribute("lista", lista);

        //Envio con distpacher
        RequestDispatcher rd = req.getRequestDispatcher("listado2.jsp");

        try {
            rd.forward(req,res);
        } catch (Exception e) {
            System.out.println("No se pudo obtener el listado");
        }


    }


}
