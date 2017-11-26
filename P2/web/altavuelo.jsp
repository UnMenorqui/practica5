
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Flight</title>
        
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
    </head>
    <body>
        <form action="altavuelo" method="post">
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
                <div class="login-box">
                    <div class="box-header">
                        <h2>Flight Register</h2>
                    </div>
                    <label for="numerovuelo"> Flight Number </label>
                    <br/>
                    <input type="number" name="numero"/>
                    <br/>
                    <label for="compañia"> Company </label>
                    <br/>
                    <input type="text" name="compania" required/>
                    <br/>
                    <label for="origen"> Origin </label>
                    <br/>
                    <input type="text" name="origen" required />
                    <br/>
                    <label for="password"> Departure time </label>
                    <br/>
                    <input type="time" name="horasalida" required value="00:00"/>
                    <br/>
                    <label for="destino"> Destination </label>
                    <br/>
                    <input type="text" name="destino" required/>
                    <br/>
                    <label for="horallegada"> Arrival time </label>
                    <br/>
                    <input type="time" name="horallegada" required value="01:00"/>
                    <br/>
                    <button type="submit">Register Flight</button>
                    <br/>
                </div>   
            </div>
        </form>
    </body>
</html>
