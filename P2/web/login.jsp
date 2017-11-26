<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

	<link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <form action="login" method="post">
            <div class="container">
                <div class="top">
                    <h1 id="title" class="hidden"><span id="logo">Travel Agency</span></h1>
                </div>
                <div class="login-box">
                    <div class="box-header">
                        <h2>Log In</h2>
                    </div>
                    <label for="username">Username</label>
                    <br/>
                    <input type="text" placeholder="Enter Username" name="name" required>
                    <br/>
                    <label for="password">Password</label>
                    <br/>
                    <input type="password" placeholder="Enter Password" name="password" required>
                    <br/>
                    <button type="submit">Login</button>
                    <br/>
                    <a href="register.jsp"><p class="small"> Don't you have an account?</p></a>
                </div>   
            </div>
        </form>
    </body>
</html>
