/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Sanjeev
 */
public class UserType extends HttpServlet {
    @Resource(name = "DB")
    private DataSource DB;

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
        try{
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user = request.getParameter("username");
        session.setAttribute("user", user);
        String pwd = request.getParameter("password");
        session.setAttribute("pass", pwd);
        Connection con = DB.getConnection();
        Statement s = con.createStatement();
        String query="select acctype from users where username='"+user+"' and password ='"+pwd+"'";
    //    out.println(query);
        ResultSet rs = s.executeQuery(query);
        String usertype=null;
        while(rs.next())
        {
            usertype=(String)rs.getString(1);
        }
        if(usertype.equalsIgnoreCase("Student")){
            RequestDispatcher rd = request.getRequestDispatcher("StudentHome.view");
            rd.forward(request, response);
        }
        else if(usertype.equalsIgnoreCase("Faculty"))
            {
            RequestDispatcher rd = request.getRequestDispatcher("FacultyHome.view");
            rd.forward(request, response);
            }
            
        else if(user.equalsIgnoreCase("Admin"))
            
            {
            RequestDispatcher rd = request.getRequestDispatcher("AdminHome.view");
            rd.forward(request, response);
            }
            
        else
            {
                RequestDispatcher rd = request.getRequestDispatcher("error.view");
                rd.forward(request, response);
            }
        con.close();
        }
        catch(Exception e){}
        
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
