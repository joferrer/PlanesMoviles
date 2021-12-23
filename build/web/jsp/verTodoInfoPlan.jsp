<%-- 
    Document   : verTodoInfoPlan
    Created on : 23/12/2021, 09:38:31 AM
    Author     : jeiso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Simulador de planes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/servicios.css">
    </head>
    <body>
        <header>
            <h1>Simulador de planes</h1>
            <a href="index.html"><img src="./img/logoCucutaPhone.png"></a>
        </header>
        <main>
            <p>La información de todos los planes que tenemos disponibles es:</p>
            <%
                String tabla = (String)request.getSession().getAttribute("tablaPlanesInfo");
            %>
            <%=tabla%>
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