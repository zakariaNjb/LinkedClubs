<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/LinkedClubs/CSS/StudentSetting.css">
    <title>Setting student profile</title>
    
    <link rel="icon" href="/LinkedClubs/Images/LCbg.png"
	type="image/icon type">
    
</head>

<body>

<h1>${err.get("errUpdate")}</h1>

    <form id="container" method="post" action="" enctype = "multipart/form-data">
    	<input type="text" value="<c:out value="${sessionScope.student.CNE}"/>" name="CNE" hidden /> 
        <h2>Setting your profile</h2>
        <span></span>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Type your new Email" value="${sessionScope.student.email}">
            <span class="err">${err.get("errEmail")}</span>
        </div>
        <div class="wrapper">
            <div>
                <label for="password1">Password:</label>
                <input type="password" id="password1" name="password1" placeholder="Type your new password">
            </div>
            <div>
                <label for="password2">Confirmation:</label>
                <input type="password" id="password2" name="password2" placeholder="Confirm your new password">
            </div>
        </div>
        <div>
            <div>
                <label for="Instagram">Instagram:</label>
                <input type="text" id="Instagram" name="instagram" placeholder="Type your instagram" value="${sessionScope.student.instagram}">
            	<span class="err">${err.get("errInstagram")}</span>         	
            </div>
            <div>
                <label for="Facebook">Facebook:</label>
                <input type="text" id="Facebook" name="facebook" placeholder="Type your facebook" value="${sessionScope.student.facebook}">
				<span class="err">${err.get("errFacebook")}</span>
            </div>
        </div>
        <div class="wrapper">
            <div>
                <label for="level">Level:</label>
                <select name="level" id="level" required>
                	<option value="${sessionScope.student.level}" selected>${sessionScope.student.level}</option>
                    <option value="1st_year">1st Year</option>
                    <option value="2nd_year">2nd Year</option>
                    <option value="3rd_year">3rd Year</option>
                    <option value="4th_year">4th Year</option>
                    <option value="5th_year">5th Year</option>
                </select>
            </div>
            <div>
                <label for="level">Major:</label>
                <select name="major" id="level" required>
                	<option value="${sessionScope.student.major}" selected>${sessionScope.student.major}</option>
                    <option value="G.Info">G. Info</option>
                    <option value="G.Indus">G. Indus</option>
                    <option value="BTP">BTP</option>
                    <option value="G.Mecanic">G. Mecanic</option>
                </select>
            </div>
        </div>
        <div class="wrapper">
            <div>
                <label for="profileImage">Profile Image:</label>
                <input type="file" name="profileImage" value="<c:out value="${sessionScope.student.profileImage}"/>" id="profileImage">
                <span class="err">${err.get("errProfileImage")}</span>
            </div>
            <div>
                <label for="profileImage">Background Image:</label>
                <input type="file" name="backgroundImage" value="<c:out value="${sessionScope.student.coverImage}"/>" id="profileImage">
                <span class="err">${err.get("errCoverImage") }</span>
            </div>
        </div>
        <div>
            <label for="Bio">Bio:</label>
            <textarea name="bio" id="Bio" cols="30" rows="2" placeholder="Type your bio"> <c:out value="${sessionScope.student.studentBio}"/></textarea>
        	<span class="err">${err.get("errbio")}</span>
        </div>
        <div>
            <input id="submit" style="margin: 15px 0px;" type="submit" value="Submit">
            <input id="reset" type="reset" value="Reset">
        </div>
    </form>
</body>
</html>