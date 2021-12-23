<%-- 
    Document   : planes
    Created on : 21/12/2021, 08:34:33 PM
    Author     : jeiso
--%>

<%@page import="DTO.ServicioXplan"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Simulador de planes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="./css/style.css">
        
        <link rel="stylesheet" href="./css/planes.css">
        <link rel="stylesheet" href="./css/servicios.css">
        
    </head>
    <body>
        <header>
            <h1>Simulador de planes</h1>
            <a href="index.html"><img src="./img/logoCucutaPhone.png"></a>
        </header>
        <main>

            <% 
                 DTO.PlanMovil plan = (DTO.PlanMovil)request.getSession().getAttribute("plan");
                 String nombre = (String)request.getSession().getAttribute("nombre");
                 List<ServicioXplan> adicionales = (List<ServicioXplan>)request.getSession().getAttribute("servicios");
                 
                 String planNombre = plan.getNombrePlan();
                 String opciones = "";
                 for (ServicioXplan serv : adicionales) {
                     opciones+= "<label><input type='checkbox' name='"+"selectServ"+"' value='"+serv.getServicioXplanPK().getCodigoServicio()
                             +"-"+serv.getServiciosAdicionales().getNombreServicio()+
                             "'>"+"<span>"+serv.getServiciosAdicionales().getNombreServicio()+"</span></label>\n";
                 }
                 String idPlan ="<label> <input type='hidden' name='idplan' value='" +String.valueOf(plan.getCodigoPlan())+"'></label>";
                 String nombreU ="<label> <input type='hidden' name='nombre' value='" +nombre+"'></label>";
            %>
       
            <p>El plan seleccionado es "<span id="planId"><%=planNombre%></span>"</p>
            <p>Este plan cuenta con los siguientes servicios adicionales, ¿Deseas cotizar <span id="nombre"><%=nombre%></span> alguno? </p>
            <form action="./phone">
               
                    <%=opciones%>                   
                    <%=idPlan%>
                    <%=nombreU%>
                <input type="submit" value="Seguir cotizando">
            </form>
            


        </main>
        <footer>
            <section>
                <div>
                    <a href="https://www.instagram.com/jeisonferrerortega/"><img src="./img/foto.jpg" alt="foto del diseñador"></a>
                    
                    <div>
                        <p>Desarrollado por: Ing Jeison Omar Ferrer Ortega, email: jeisonomarfort@ufps.edu.co</p>
                        <p>Cúcuta, 23/12/2021</p>
                    </div>
                </div>
                <a href="https://ingsistemas.cloud.ufps.edu.co/"><img src="./img/logo_vertical.png" alt="logo del programa de ing de sistemas de la ufps"></a>
                
            </section>
        </footer>
        
    </body>
</html>