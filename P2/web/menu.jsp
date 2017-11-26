<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <link rel="stylesheet" type="text/css" href="css/menu.css" />
    </head>
    <body>
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
        <div class="top">
                    <h1 id="title" class="hidden"><span id="logo">Welcome to our travel agency</span></h1>
        </div>
    </body>
</html>
