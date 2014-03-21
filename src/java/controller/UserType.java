/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sanjeev
 */
public class UserType extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserType</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UserType at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user = request.getParameter("username");
        session.setAttribute("user", user);
        String pwd = request.getParameter("password");
        session.setAttribute("pass", pwd);
        if(user.equalsIgnoreCase("Student")){
            if(pwd.equalsIgnoreCase("studpass"))
            {
            RequestDispatcher rd = request.getRequestDispatcher("StudentHome.view");
            rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("error.view");
                rd.forward(request, response);
            }
        }
        else if(user.equalsIgnoreCase("Faculty")){
            if(pwd.equalsIgnoreCase("facpass"))
            {
            RequestDispatcher rd = request.getRequestDispatcher("FacultyHome.view");
            rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("error.view");
                rd.forward(request, response);
            }
        }
        else if(user.equalsIgnoreCase("Admin")){
            if(pwd.equalsIgnoreCase("adminpass"))
            {
            RequestDispatcher rd = request.getRequestDispatcher("AdminHome.view");
            rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("error.view");
                rd.forward(request, response);
            }
        }
        else
            {
                RequestDispatcher rd = request.getRequestDispatcher("error.view");
                rd.forward(request, response);
            }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
