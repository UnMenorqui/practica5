<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
	<link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <form action="register" method="post">
            <div class="container">
                <div class="login-box">
                    <div class="box-header">
                        <h2>Register</h2>
                    </div>
                    <label for="username">Username</label>
                    <br/>
                    <input type="text" placeholder="Enter Username" name="name" required>
                    <br/>
                    <label for="password">Password</label>
                    <br/>
                    <input type="password" placeholder="Enter Password" name="password" required>
                    <br/>
                    <label for="password">Repeat Password</label>
                    <br/>
                    <input type="password" placeholder="Enter Password" name="password1" required>
                    <br/>
                    <button type="submit">Register</button>
                    <br/>
                    <a href="login.jsp"><p class="small"> Already have an account?</p></a>
                </div>   
            </div>    
        </form>
    </body>
</html>
