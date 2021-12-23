/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DTO.Smartphone;
import Negocio.Telefonia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeiso
 */
public class phone extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet phone</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet phone at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        Telefonia t = new Telefonia();
        
        String nombre = request.getParameter("nombre");
        int idPlan=0;
        if(request.getParameter("idplan")!=null)
            idPlan = Integer.parseInt(request.getParameter("idplan"));
        else
            idPlan = (Integer)request.getSession().getAttribute("idplan");
        String[] selec = request.getParameterValues("selectServ");
        String equipos = t.checkBoxSmarphones();
        String planinfo[]= t.getPlanInfo(idPlan);
        
        if(!equipos.isEmpty()){
            request.getSession().setAttribute("equipos", equipos);
            request.getSession().setAttribute("nombre", nombre);
            request.getSession().setAttribute("planInfo", planinfo);
            request.getSession().setAttribute("selecciones", leerSeleciones(selec));
            request.getSession().setAttribute("idSelect", leerIdServiciosAdicc(selec));
            request.getRequestDispatcher("./jsp/smartphone.jsp").forward(request, response);
        }
        
    }
    private String leerIdServiciosAdicc(String select[]){
        String serv = "";
        if(select!=null){
        for (int i = 0; i < select.length; i++) {
            String string = select[i].split("-")[0];
            if(i!=select.length-1)
            serv+=string+"-";
            else 
                serv+=string;
            
        }
        }
        return serv;
        
    }
    private String leerSeleciones(String select[]){
        String seleccion = "";
        if(select!=null){
        for (int i = 0; i < select.length; i++) {
            String string = select[i].split("-")[1];
            if(select.length!=1&&i==select.length-1){  
                
                    seleccion+= " y "+string;
              
                    
            }
                           
            else if(select.length>2 && i!=select.length-2)
                    seleccion+= string+" , ";
            else 
                    seleccion+=string;
            
                
            
        }
        }
        return seleccion;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
