<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="P2.hotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Hotel</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/tabla.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">   
    </head>
    <body>
        <form action="buscarhotel" method="post">
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

                            //Desplegable nombre hotel




                            ResultSet rs = statement.executeQuery("select distinct nom_hotel from hoteles");
                            out.println("<h5>Selecciona el nombre del hotel: </h5>");
                            out.println("<select name='nom_hotel'>");

                                out.println("<option value=todo_nom></option>");
                                while (rs.next()) {
                                    out.println("<option value='" + rs.getString("nom_hotel") + "'>" + rs.getString("nom_hotel") + "</option>");
                                }
                            out.println("</select>");
                            /*else{
                                out.println("<option value='" + "No existe ningun hotel registrado" + "'>" + rs.getString("nom_hotel") + "</option>");
                            }*/

                            //Desplegable cadena hotelera

                            rs = statement.executeQuery("select distinct cadena from hoteles");
                            out.println("<h5>Selecciona el nombre de la cadena hotelera: </h5>");
                            out.println("<select name='cadena'>");

                                out.println("<option value=todo_cad></option>");
                                while (rs.next()) {
                                    out.println("<option value='" + rs.getString("cadena") + "'>" + rs.getString("cadena") + "</option>");
                                }

                            out.println("</select>");

                            //Desplegable ciudad

                            rs = statement.executeQuery("select distinct ciudad from hoteles");
                            out.println("<h5>Selecciona el nombre de la ciudad: </h5>");
                            out.println("<select name='ciudad'>");

                                out.println("<option value=todo_ciu></option>");
                                while (rs.next()) {
                                    out.println("<option value='" + rs.getString("ciudad") + "'>" + rs.getString("ciudad") + "</option>");
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
                ArrayList<hotel> lista = (ArrayList)sesion.getAttribute("hoteles");
                hotel hotel = lista.get(0); //throws an exception if empty 
        %>
        <table id="customers">
            <tr>
                <th><strong>Hotel ID</strong></th>
                <th><strong>Hotel Name</strong></th>
                <th><strong>Hotel Chain</strong></th>
                <th><strong>Nº Rooms</strong></th>
                <th><strong>Street</strong></th>
                <th><strong>Number</strong></th>
                <th><strong>Postal Code</strong></th>
                <th><strong>City</strong></th>
                <th><strong>Province</strong></th>
                <th><strong>Country</strong></th>
         
            </tr> 
        <%for (int i=0; i<lista.size(); ++i) {%>
                <tr>
                    <td><%=lista.get(i).getIdhotel()%></td>
                    <td><%=lista.get(i).getNombrehotel()%></td>
                    <td><%=lista.get(i).getCadenaHotelera()%></td>
                    <td><%=lista.get(i).getNum_Habitaciones()%></td>
                    <td><%=lista.get(i).getCalle()%></td>
                    <td><%=lista.get(i).getNumero()%></td>
                    <td><%=lista.get(i).getCodigoPostal()%></td>
                    <td><%=lista.get(i).getCiudad()%></td>
                    <td><%=lista.get(i).getProvincia()%></td>
                    <td><%=lista.get(i).getPais()%></td>
                    
                </tr>
          <%}    
            }catch(NullPointerException e){} %>
        </table>
    </body>
</html>
