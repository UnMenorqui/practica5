<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check free rooms</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/tab.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/menu.css"/>
        
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
        <div class="container">
            <div class="login-box">
                <div class="box-header">
                    <h2>Check free rooms</h2>
                </div>
                <div class="tab">
                    <button class="tablinks" onclick="openCity(event, 'Con Rest')" style="color: black">Con Rest</button>
                    <button class="tablinks" onclick="openCity(event, 'Con Soap')" style="color: black">Con Soap</button>
                </div>
                
                <div id="Con Rest" class="tabcontent">
                    <form action="http://localhost:8080/HotelRest/webresources/generic/consultaLibres" method="get">
                        <label for="id_vuelo">Id Hotel</label>
                        <br/>
                        <input type="text" name="id" placeholder="id Hotel"/>
                        <br/>
                        <label for="Date">Date</label>
                        <br/>
                        <input type="text" name="fecha" placeholder="aaaammdd"/>
                        <br/>
                        <button type="submit">Check</button>
                    </form>
                </div>
                <div id="Con Soap" class="tabcontent">
                    <form method="POST" action="http://localhost:8080/P2/servletHotelConsulta">
                         <label for="id_vuelo">Id Hotel</label>
                        <br/>
                        <input type="text" name="id" placeholder="id Hotel"/>
                        <br/>
                        <label for="Date">Date</label>
                        <br/>
                        <input type="text" name="fecha" placeholder="aaaammdd"/>
                        <br/>
                        <button type="submit">Check</button>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function openCity(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }
        </script>
    </body>
</html>
