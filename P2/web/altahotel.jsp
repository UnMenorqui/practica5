
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Hotel</title>
        
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
    </head>
    <body>
        <form action="altahotel" method="post">
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
                    <label for="numerovuelo"> Hotel Identifier </label>
                    <br/>
                    <input type="number" name="id_hotel" required pattern="[0-9]"/>
                    <br/>
                    <label for="compañia"> Hotel Name </label>
                    <br/>
                    <input type="text" name="nombrehotel" required/>
                    <br/>
                    <label for="origen"> Hotel Chain </label>
                    <br/>
                    <input type="text" name="cadenahotelera" required />
                    <br/>
                    <label for="horallegada"> Number of Rooms </label>
                    <br/>
                    <input type="number" name="num_habitaciones" required pattern="[0-9]"/>
                    <br/>
                    <label for="Hotel_Stret"> Hotel Street </label>
                    <br/>
                    <input type="text" name="calle" required/>
                    <br/>
                    <label for="destino"> Hotel Number </label>
                    <br/>
                    <input type="number" name="numero" required pattern="[0-9]"/>
                    <br/>
                    <label for="horallegada"> Postal Code </label>
                    <br/>
                    <input type="number" name="codigopostal" required pattern="[0-9]"/>
                    <br/>
                    <label for="horallegada"> City </label>
                    <br/>
                    <input type="text" name="ciudad" required/>
                    <br/>
                    <label for="horallegada"> Province </label>
                    <br/>
                    <input type="text" name="provincia" required/>
                    <br/>
                    <label for="horallegada"> Country </label>
                    <br/>
                    <input type="text" name="pais" required/>
                    <br/>
                    <button type="submit">Register Hotel</button>
                    <br/>
                </div>   
            </div>
        </form>
    </body>
</html>
