<%-- 
    Document   : smartphone
    Created on : 21/12/2021, 08:34:33 PM
    Author     : jeiso
--%>

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
                String nombre = (String)request.getSession().getAttribute("nombre");
                String selecciones = (String)request.getSession().getAttribute("selecciones");
                String infoPlan[]= (String[])request.getSession().getAttribute("planInfo");
                String equipos = (String)request.getSession().getAttribute("equipos");
                
                String idSelect = (String)request.getSession().getAttribute("idSelect");

                String nombrePlan = infoPlan[1];
                
                String nombrePlanL ="<label> <input type='hidden' name='plan' value='" +infoPlan[0]+"-"+nombrePlan+"-"+infoPlan[2]+"'></label>";
                String nombreU ="<label> <input type='hidden' name='nombre' value='" +nombre+"'></label>";
                String tagIdSelect = "<label> <input type='hidden' name='idSelect' value='" +idSelect+"'></label>";

            %>
        
            <p>El plan seleccionado es el <span id="plan"><%=nombrePlan%></span>, y tus servicios son:<span id="servicios"><%=selecciones%></span></p>
            <p><span id="nombre"><%=nombre%></span>, estás de suerte, tenemos estos equipos para que lo puedas comprar con tú plan, seleciona alguno</p>
            <form action="./Cotizacion">
                <%=equipos%>
                <%=nombreU%>
                <%=nombrePlanL%>
                <%=tagIdSelect%>
                <input type="submit" value="Dar valor de mi cotización">
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