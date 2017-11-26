<%@page contentType="text/html" pageEncoding="UTF-8"%>         
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
        
        <%
        HttpSession sesion = request.getSession(false);
        String error = (String)sesion.getAttribute("error");
        if (error.equals("1")) {%>
            <h1 style="color:white">The user does not exist</h1>
            <a href="login.jsp" style="color:white"> Return to Login </a>
        <%}
        else if (error.equals("2")) {%>
            <h1 style="color:white">The user already exists</h1>
            <a href="login.jsp" style="color:white"> Return to Login </a>
        <%}
        else if (error.equals("3")) {%>
            <h1 style="color:white">The two passwords are not the same</h1>
            <a href="login.jsp" style="color:white"> Return to Login </a>
        <%}
        else if (error.equals("4")) {%>
            <h1 style="color:white">There is no flight with these characteristics;</h1>
            <a href="menu.jsp" style="color:white"> Return to Menu </a>
        <%}
        else if (error.equals("5")) {%>
            <h1 style="color:white">A flight with this number already exists</h1>
            <a href="menu.jsp" style="color:white"> Return to Menu </a>
        <%}
        else if (error.equals("7")) {%>
            <h1 style="color:white">An Hotel with this ID already exists</h1>
            <a href="menu.jsp" style="color:white"> Return to Menu </a>
        <%}
        else if (error.equals("6")) {%>
            <h1 style="color:white">There is no hotel with this characteristics</h1>
            <a href="menu.jsp" style="color:white"> Return to Menu </a>
        <%}
        else if (error.equals("8")) {%>
            <h1 style="color:white">Please Log In First</h1>
            <a href="login.jsp" style="color:white"> Return to Login </a>
        <%}%>
    </body>
</html>
