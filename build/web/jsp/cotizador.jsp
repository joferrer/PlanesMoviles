<%-- 
    Document   : cotizador
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
        <title>Seleccion de plan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/style.css">
    </head>
    <body>
        <header>
            <h1>Simulador de planes</h1>
            <a href="index.html"><img src="./img/logoCucutaPhone.png"></a>
        </header>
        <main>
            <form action="./ServiciosAdicionales">
                <label for="nombre">
                    <span>Nombre: </span>
                    <input type="text" name="nombre" id="nombre" required>
                </label>
                <label for="plan">
                    <span>¿Qué plan desea cotizar?</span>
                    <!-- Aqui va el dropdown de los planes-->
                    <select name="planes" id="planes">
                        <% 
                            String planesOpc = (String)request.getSession().getAttribute("planesOpc");
                        %>
                    <%=planesOpc%>
                    </select>    
                </label>
                <input type="submit" value="Ver información del plan">
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