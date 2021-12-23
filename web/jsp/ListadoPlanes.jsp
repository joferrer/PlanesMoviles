<%-- 
    Document   : ListadoPlanes
    Created on : 21/12/2021, 08:34:33 PM
    Author     : jeiso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Planes</title>
    </head>
    <body>
        <h1>Planes registrados</h1>
        
        <% 
            String planes = (String)request.getSession().getAttribute("listado");
        %>
        <%=planes%>
    </body>
</html>
