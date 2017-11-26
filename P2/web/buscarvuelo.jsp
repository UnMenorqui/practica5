<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="P2.vuelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Flight</title>
        
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/tabla.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        
    </head>
    <body>
        <form action="buscarvuelo" method="post">
             
            <ul>
            <li><a href="menu.jsp">Home</a></li>
            <li class="dropdown">
                <a href="#">Flights</a>
                <div class="dropdown-content">
                    <a href="altavuelo.jsp">Register Flight</a>
                    <a href="buscarvuelo.jsp">Search Flight</a>
                    <a href="consultaplazasvuelo.jsp">Check free flight seats</a>
                    <a href="reservaplazavuelo.jsp">Book seat in flight</a>
                </div> 
            </li>
            
           <li class="dropdown">
                <a href="#">Hotels</a>
                <div class="dropdown-content">
                    <a href="altahotel.jsp">Register Hotel</a>
                    <a href="buscarhotel.jsp">Search Hotel</a>
                    <a href="consultahabitaciones.jsp">Check free rooms</a>
                    <a href="reservahabitacion.jsp">Reserve Room</a>
                </div> 
            </li>
            <li><a href="logout">Log Out</a></li>
        </ul>
            
            <div class="container">
                <%
                        Connection connection = null;
                            try {            
                                // load the sqlite-JDBC driver using the current class loader
                                Class.forName("org.sqlite.JDBC");               

                                // create a database connection
                                connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
                                Statement statement = connection.createStatement();
                                statement.setQueryTimeout(30);  // set timeout to 30 sec.

                                //Desplegable numero de vuelo


                                ResultSet rs = statement.executeQuery("select distinct id_vuelo from vuelos");
                                out.println("<h5>Selecciona el numero de vuelo: </h5>");
                                out.println("<select name='id_vuelo'>");

                                    out.println("<option value=todo_num></option>");
                                    while (rs.next()) {
                                        out.println("<option value=" + rs.getString("id_vuelo") + ">" + rs.getString("id_vuelo") + "</option>");
                                    }

                                out.println("</select>");
                                /*else{
                                    out.println("<option value='" + "No existe ningun hotel registrado" + "'>" + rs.getString("nom_hotel") + "</option>");
                                }*/

                                //Desplegable companyia aerea

                                rs = statement.executeQuery("select distinct companyia from vuelos");
                                out.println("<h5>Selecciona el nombre de la companyia aerea: </h5>");
                                out.println("<select name='companyia'>");

                                    out.println("<option value=todo_comp></option>");
                                    while (rs.next()) {
                                        out.println("<option value=" + rs.getString("companyia") + ">" + rs.getString("companyia") + "</option>");
                                    }

                                out.println("</select>");

                                //Desplegable ciudad origen

                                rs = statement.executeQuery("select distinct origen from vuelos");
                                out.println("<h5>Selecciona el nombre de la ciudad de origen: </h5>");
                                out.println("<select name='origen'>");

                                    out.println("<option value=todo_or></option>");
                                    while (rs.next()) {
                                        out.println("<option value=" + rs.getString("origen") + ">" + rs.getString("origen") + "</option>");
                                    }

                                out.println("</select>");

                                //Desplegable ciudad destino

                                rs = statement.executeQuery("select distinct destino from vuelos");
                                out.println("<h5>Selecciona el nombre de la ciudad de destino: </h5>");
                                out.println("<select name='destino'>");

                                    out.println("<option value=todo_des></option>");
                                    while (rs.next()) {
                                        out.println("<option value=" + rs.getString("destino") + ">" + rs.getString("destino") + "</option>");
                                    }

                                out.println("</select>");


                            } catch (SQLException e) {
                                System.err.println(e.getMessage());
                            } catch (ClassNotFoundException e) {
                                System.err.println(e.getMessage());
                            } finally {
                                try {
                                    if (connection != null)
                                        connection.close();
                                } catch (SQLException e) {
                                    // connection close failed.
                                    System.err.println(e.getMessage());
                                }
                            }
                        %> 
            </div>
            
   
        </form>
        <% 
            try {
                HttpSession sesion = request.getSession(false);
                ArrayList<vuelo> lista = (ArrayList)sesion.getAttribute("vuelos");
                vuelo vuelo = lista.get(0); //throws an exception if empty 
        %>
        <table id="customers">
            <tr>
                <th><strong>Flight Number</strong></th>
                <th><strong>Company</strong></th>
                <th><strong>Origin</strong></th>
                <th><strong>Departure Time</strong></th>
                <th><strong>Destination</strong></th>
                <th><strong>Arrival Time</strong></th>
         
            </tr> 
        <%for (int i=0; i<lista.size(); ++i) {%>
                <tr>
                    <td><%=lista.get(i).getIdvuelo()%></td>
                    <td><%=lista.get(i).getCompania()%></td>
                    <td><%=lista.get(i).getOrigen()%></td>
                    <td><%=lista.get(i).getHoraSalida()%></td>
                    <td><%=lista.get(i).getDestino()%></td>
                    <td><%=lista.get(i).getHoraLlegada()%></td> 
                </tr>
          <%}    
            }catch(NullPointerException e){} %>
        </table>
    </body>
</html>
