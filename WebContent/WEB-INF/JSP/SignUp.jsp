<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link rel="stylesheet" href="/LinkedClubs/CSS/signup_styling.css">
    <title>Sign up</title>
    
    <link rel="icon" href="/LinkedClubs/Images/LCbg.png"
	type="image/icon type">
    
</head>

<body>

    <h1>Register for LinkedClubs</h1>

    <form action="/LinkedClubs/Signup" method="post">
        <div class="form_container">

            <div class="general_info" id="general_info">

                <h2>General information</h2>
                <input type="text" name="cne" id="cne" placeholder="CNE" value="${ student.CNE }" required><br>
                <span class="erreur">${erreur.get("errCNE")}</span>

                <input type="text" name="fullname" id="fullname" placeholder="Fullname" value="${ student.fullName }" required><br>
                <span class="erreur">${erreur.get("errFullName")}</span>
                

                <input type="date" name="birthdate" id="birthdate" placeholder="Birthdate" value="${ student.birthDate }" required><br>
                <span class="erreur">${erreur.get("errBirthDate")}</span>
                

                <label for="male">Male</label> <input type="radio" name="gender" id="male" value="Male" required>
                <label for="male">Female</label> <input type="radio" name="gender" id="female" value="Female" required><br>
                <span class="erreur">${erreur.get("errGender")}</span>

                <label for="level">Level :</label><br>
                <select name="level" id="level" required>

                    <option value="1st_year">1st Year</option>
                    <option value="2nd_year">2nd Year</option>
                    <option value="3rd_year">3rd Year</option>
                    <option value="4th_year">4th Year</option>
                    <option value="5th_year">5th Year</option>

                </select>
                <span class="erreur">${erreur.get("errLevel")}</span>

                <br>
                <label for="level">Major :</label><br>
                <select name="major" id="level" required>

                    <option value="G.Info">G. Info</option>
                    <option value="G.Indus">G. Indus</option>
                    <option value="BTP">BTP</option>
                    <option value="G.Mecanic">G. Mecanic</option>

                </select>
                <span class="erreur">${erreur.get("errMajor")}</span>
                

                <br>

                <button class="next_btn" onclick="next_form()">Next</button>
            </div>

            <div class="signup_info" id="signup_info">
                <div id="return_btn">
                    <i class="fas fa-chevron-circle-left" onclick="previous_form()"></i>
                </div>
                <h2>Sign up information</h2>

                <input type="email" name="email" id="email" placeholder="Email" value="${ student.email }" required><br>
                <span class="erreur">${erreur.get("errEmail")}</span>
                

                <input type="password" name="password" id="password" placeholder="Password" required><br>

                <input type="password" name="password_confirmation" id="password_confirmation" placeholder="Password Confirmation" required><br>
				<span class="erreur">${erreur.get("errPassword")}</span>

                <input type="radio" name="accept" id="accept" required><label for="accept">I do accept the <a href="#">terms and conditions</a> of the website.</label>
				<span class="erreur">${erreur.get("errAccept")}</span>
				
				
                <input type="submit" value="Sign up" id="signup_btn">
                
                <p> Already have an account ? <a href="Login">sign in</a> here ! </p>

            </div>

        </div>
    </form>

    <script>
        function next_form() {
            document.getElementById('general_info').style.display = 'none';
            document.getElementById('signup_info').style.display = 'block';
            document.getElementById('return_btn').style.display = 'block';
            document.getElementById('signup_info').style.flex = '1';
        }

        function previous_form() {
            document.getElementById('general_info').style.display = 'block';
            document.getElementById('signup_info').style.display = 'none';
        }
    </script>


</body>
</html>