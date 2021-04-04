<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/LinkedClubs/CSS/Login.css">
    <title>Login</title>
    
    <link rel="icon" href="/LinkedClubs/Images/LCbg.png" type="image/icon type">
    
</head>

<body>
    <main>
        <form action="adminLogin" method="post">
            <h2>Sign in for LinkedClubs administration panel</h2>
            <span></span>
            <label for="">Username :</label>
            <input type="text" name="username" id="cne" placeholder="Username">
            <span class="err">${err.get("errUsername")}</span>
            <label for="">Password:</label>
            <input type="password" name="password" placeholder="Insert your password">
            <span class="err">${err.get("errPassword")}</span>
            <button type="submit" value="Submit">Enter</button>
            
        </form>
        
    </main>
</body>

</html>