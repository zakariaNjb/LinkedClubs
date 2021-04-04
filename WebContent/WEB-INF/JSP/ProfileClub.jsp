<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/62e9ec17ac.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="/LinkedClubs/CSS/ProfileClub1.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="/LinkedClubs/CSS/ProfileClub2.css" media="screen" />

<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />


<title>Profile Club</title>

<link rel="icon" href="/LinkedClubs/Images/LCbg.png"
	type="image/icon type">

<style>
#create_post_form {
	width: 100%;
	background-color: #FFFAFA;
	padding: 20px 10px;
	box-sizing: border-box;
	margin-bottom: 20px;
}

#create_post_form div {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

#create_post_form img {
	width: 50px;
	height: 50px;
	border-radius: 100px;
}

#create_post_form div input {
	width: 100%;
	border-radius: 10px;
	padding: 10px 10px;
	margin-left: 10px;
	background-color: #f1ebebc9;
	border: none;
	outline: none;
	color: black;
}

#is_event_span {
	width: 60px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

#is_event_span label {
	display: block;
}

#create_post_form section div label {
	width: 60px;
	display: flex;
	justify-content: center;
}

#create_post_form section div label i {
	color: rgb(243, 121, 65);
}

#create_post_form section div {
	margin-bottom: 20px;
}

#create_post_form section {
	margin-top: 20px;
	display: none;
}

#add_post_btn {
	width: 100%;
	padding: 10px 0px;
	border: none;
	outline: none;
	transition-duration: 200ms;
	background-color: #cacaca;
	font-weight: bolder;
}

#add_post_btn:hover {
	background-color: rgb(243, 121, 65);
	color: white;
}

#update-post-container {
	position: fixed;
	top: 0;
	height: 100%;
	width: 100%;
	background-color: rgba(0, 0, 0, 0.795);
	z-index: 2;
}
</style>

</head>
<body>

	<nav id="navBar">
		<main>
			<a href="/LinkedClubs">LinkedClubs</a>
			<button>
				<input type="text" placeholder="search"> <i
					class="fab fa-searchengin"></i>
			</button>
			<div>
				<img
					src="/LinkedClubs/Images/clubProfileImages/<c:out value="${sessionScope.club.image}"/>"
					alt="profile picture"> <span id="fullName"><c:out
						value="${sessionScope.club.clubName}" /></span>
			</div>

			<form action="Logout" method="post">

				<button type="submit" style="color: white; display: block;">
					<i class="fas fa-sign-out-alt"></i>
				</button>

			</form>

		</main>
	</nav>


	<!--  Update post form  -->

	<div id="update-post-container" class="hide-post-update">

		<form action="UpdatePost" method="post" enctype="multipart/form-data">

			<div style="display: flex; width: 100%;">

				<div id="img_update">

					

				</div>

				<div id="img_btn">

					<button onclick="closePopUp();" type="button" id="closeBtn"
						style="outline: none;" class="mx-2 top-0 text-white">
						<i class="fas fa-times"></i>
					</button>

				</div>

			</div>

			<input type="text" name="description" id="description"
				placeholder="Description"><br> <input type="text"
				name="post_id" id="post-id" value="" hidden> <input
				type="file" name="fileLink" id="file_input"><br> <input
				id="update_post" type="submit" value="Update">

		</form>

	</div>

	<!----------------Personnal info sections--------------------->
	<!----------------Personnal info sections--------------------->
	<section id="personal__info"
		style="background-image: url('/LinkedClubs/Images/clubCoverImages/<c:out value="${sessionScope.club.coverImage}"/>')">
		<main>
			<img
				src="/LinkedClubs/Images/clubProfileImages/<c:out value="${sessionScope.club.image}"/>"
				alt="profile picture">
			<div>
				<ul>
					<li><c:out value="${sessionScope.club.clubName}" /></li>
					<li><i class="fab fa-facebook"></i><span><c:out
								value="${sessionScope.club.facebook}" /></span></li>
					<li><i class="fab fa-instagram"></i><span><c:out
								value="${sessionScope.club.instagram}" /></span></li>
				</ul>
				<span>Members<span id="nbrClubsJoined"
					style="color: #FF7556;"><c:out
							value="${sessionScope.club.membersNumber}" /></span></span>
			</div>
		</main>
	</section>
	<!--------------Post && Reminder section----------------------->
	<nav>
		<div class="nav__container">
			<ul>
				<li><button onclick="wow();">Club</button></li>
				<li><button onclick="show();">Members</button></li>
				<li><button onclick="how();">About</button></li>
				<li><button onclick="dow();">Setting</button></li>
			</ul>
		</div>
	</nav>
	<!--------------Post && Reminder section----------------------->
	<div id="posts__reminder">

		<div id="reminder">
			<section>
				<span style="color: #FF7556; font-size: 1.5em">Calendar</span> <span
					style="text-align: right;"><i class="far fa-calendar-alt"></i></span>
			</section>
			<section>


				<div class="sideb">
					<div class="header">
						<i class="fa fa-angle-left" aria-hidden="true"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
							class="month"> </span><span class="year"></span></span><i
							class="fa fa-angle-right" aria-hidden="true"></i>
					</div>
					<div class="calender">
						<table>
							<thead>
								<tr class="weedays">
									<th data-weekday="sun" data-column="0">Sun</th>
									<th data-weekday="mon" data-column="1">Mon</th>
									<th data-weekday="tue" data-column="2">Tue</th>
									<th data-weekday="wed" data-column="3">Wed</th>
									<th data-weekday="thu" data-column="4">Thu</th>
									<th data-weekday="fri" data-column="5">Fri</th>
									<th data-weekday="sat" data-column="6">Sat</th>
								</tr>
							</thead>
							<tbody>
								<tr class="days" data-row="0">
									<td data-column="0"></td>
									<td data-column="1"></td>
									<td data-column="2"></td>
									<td data-column="3"></td>
									<td data-column="4"></td>
									<td data-column="5"></td>
									<td data-column="6"></td>
								</tr>
								<tr class="days" data-row="1">
									<td data-column="0"></td>
									<td data-column="1"></td>
									<td data-column="2"></td>
									<td data-column="3"></td>
									<td data-column="4"></td>
									<td data-column="5"></td>
									<td data-column="6"></td>
								</tr>
								<tr class="days" data-row="2">
									<td data-column="0"></td>
									<td data-column="1"></td>
									<td data-column="2"></td>
									<td data-column="3"></td>
									<td data-column="4"></td>
									<td data-column="5"></td>
									<td data-column="6"></td>
								</tr>
								<tr class="days" data-row="3">
									<td data-column="0"></td>
									<td data-column="1"></td>
									<td data-column="2"></td>
									<td data-column="3"></td>
									<td data-column="4"></td>
									<td data-column="5"></td>
									<td data-column="6"></td>
								</tr>
								<tr class="days" data-row="4">
									<td data-column="0"></td>
									<td data-column="1"></td>
									<td data-column="2"></td>
									<td data-column="3"></td>
									<td data-column="4"></td>
									<td data-column="5"></td>
									<td data-column="6"></td>
								</tr>
								<tr class="days" data-row="5">
									<td data-column="0"></td>
									<td data-column="1"></td>
									<td data-column="2"></td>
									<td data-column="3"></td>
									<td data-column="4"></td>
									<td data-column="5"></td>
									<td data-column="6"></td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>



			</section>

		</div>
		<div id="posts">
			<!---------------------Normal post with picture----------->

			<form method="post" action="ProfileClub" id="create_post_form"
				enctype="multipart/form-data">

				<div>

					<img
						src="/LinkedClubs/Images/clubProfileImages/<c:out value="${sessionScope.club.image}"/>">

					<input type="text" name="description"
						placeholder="Write your post description ..."> <span
						style="color: red;">${err.get("errPost")}</span>

				</div>

				<div>

					<span id="is_event_span"><label>Event</label><input
						style="margin: 0px;" type="checkbox" name="check"
						onclick="showInputs();" id="check_input"></span> <input
						type="file" name="postFile">


				</div>

				<section id="event_inputs">

					<div>

						<label><i class="far fa-calendar-alt"></i></label><input
							type="datetime-local" name="date">


					</div>
					<div>

						<label><i class="fas fa-clipboard"></i></label><input type="text"
							placeholder="Event title" name="title">

					</div>

				</section>

				<input id="add_post_btn" type="submit" value="Post">

			</form>



			<c:forEach items="${posts}" var="post" varStatus="status">

				<div class="post event">
					<header>
						<div id="messangerSender">
							<img
								src="/LinkedClubs/Images/clubProfileImages/<c:out value="${sessionScope.club.image}"/>"
								alt="profile image"> <span>
								<li><c:out value="${sessionScope.club.clubName}" /></li>
								<li>${ post.publishedDate }</li>
							</span>
						</div>

						<div>
							<li><button>
									<i class="fas fa-cog" style="font-size: 20px;"
										class="toggle-btn-update-post"
										onclick="toggle_update_post(${ status.index })"></i>
								</button></li>
							<li name="numberLikes">${ post.likeNumber }</li>

							<li><i class="fas fa-star"></i></li>
						</div>

						<input type="text" value="${ post.id }" class="current_post_id"
							hidden> <input type="text" value="${ post.fileType }"
							class="current_post_fileType" hidden>

					</header>
					<p class="current_post_decription">${ post.postDescription }</p>

					<c:if test="${ !post.fileLink.isEmpty() }">

						<c:if test="${ post.fileType.contains('image') }">
							<img class="current_post_file"
								src="/LinkedClubs/Images/postFiles/<c:out value="${post.fileLink}"/>"
								alt="picture">
						</c:if>

						<c:if test="${ post.fileType.contains('pdf') }">

							<embed class="current_post_file"
								style="width: 100%; height: 30em;"
								src="/LinkedClubs/Images/postFiles/<c:out value="${post.fileLink}"/>" />

						</c:if>

					</c:if>

					<header class="likes__comments">
						<div>
							<button name="commentsBtn">
								<i class="fas fa-comment-alt"></i> comments
							</button>
						</div>
						<div>
							<li name="numberComments">${ post.commentNumber }</li>
							<li>comments</li>
						</div>
					</header>
					<section class="comments">
						<main>

							<c:forEach items="${allComments}" var="comment">

								<c:if test="${ comment.post.id == post.id }">

									<div>
										<img
											src="/LinkedClubs/Images/profileImages/<c:out value="${comment.student.profileImage}"/>"
											alt="profile picture"> <span>${ comment.content }</span>
									</div>

								</c:if>

							</c:forEach>

						</main>
					</section>
				</div>

			</c:forEach>
		</div>
	</div>

	<!---------------------Normal post without picture----------->

	<!--------------------Listes of members-------------------------------->

	<div id="grid__container" style="display: none;">
		<c:forEach items="${members}" var="member" varStatus="status">

			<div class="card">
				<div class="top__container">
					<div class="cover__image">
						<img
							src="/LinkedClubs/Images/coverImages/<c:out value="${member.coverImage}"/>">
					</div>
					<div class="profile__image">
						<img
							src="/LinkedClubs/Images/profileImages/<c:out value="${member.profileImage}"/>">
					</div>
				</div>
				<div class="law__container">
					<div>
						<h3>${member.fullName}</h3>
						<h4>Payed : ${membersCNE.get(status.index).payed}</h4>
					</div>

					<form action="">
						<input name="accept" type="button" value="Accept" class="btn1"
							data-studentId="${member.CNE}" /> <input name="decline"
							type="button" value="Decline" class="btn2"
							data-studentId="${member.CNE}" />
					</form>

				</div>
				<br> <br>
			</div>

		</c:forEach>

	</div>


	<!--------------------About Club-------------------------------->
	<div id="About__container" style="display: none;">


		<form action="">
			<div class="form_container">
				<div class="left__cantainer" id="left__cantainer">

					<img
						src="/LinkedClubs/Images/clubProfileImages/<c:out value="${club.image}"/>"
						alt="">

				</div>
				<div class="general_info" id="general_info">

					<h2 class="title">General information</h2>
					<div class="content">
						<h3>Club Name :</h3>
						<h2
							style="padding-right: 50px; font-family: cursive; color: rgb(55, 55, 56);">
							<i>${ club.clubName }</i>
						</h2>
					</div>
					<br />
					<div class="content">
						<h3>Description :</h3>
						<p>${ club.clubBio }</p>
					</div>
					<br />
					<div class="content">
						<h3>Members :</h3>
						<h2 style="padding-top: 5px;">${ club.membersNumber }</h2>
					</div>
				</div>



			</div>
		</form>

	</div>

	<!-- Club setting -->
	<div id="Club_sett" style="display: none;">
		<form action="ClubSetting" method="post" enctype="multipart/form-data">
			<div class="form_container" style="margin-top: 10px; height: auto;">

				<div class="sett_info" id="general_info">


					<div class="input-container">
						<span>ClubName :</span> <input required type="text"
							name="ClubName" class="input"
							value="<c:out value="${sessionScope.club.clubName}"/>" />

					</div>
					<div class="input-container">
						<span>Password :</span> <input required type="password"
							name="password" class="input" />


					</div>
					<div class="input-container">
						<span>Confirm password :</span> <input required type="password"
							name="confirmPassword" class="input" />


					</div>


					<div class="input-container">
						<span>Facebook</span> <input required type="text" name="Facebook"
							class="input"
							value="<c:out value="${sessionScope.club.facebook}"/>" />


					</div>

					<div class="input-container">
						<span>Instagram</span> <input required type="text"
							name="Instagram" class="input"
							value="<c:out value="${sessionScope.club.instagram}"/>" />


					</div>

				</div>

				<div class="signup_info" id="signup_info">


					<div class="input-container">
						<span>Image</span> <input type="file" name="image" class="input" />


					</div>
					<div class="input-container">
						<span>Cover</span> <input type="file" name="cover" class="input" />


					</div>
					<div class="input-container textarea">
						<span id="sp_mess" style="padding-bottom: 6px;">ClubBio :</span>
						<textarea name="ClubBio" class="input"><c:out
								value="${sessionScope.club.clubBio}" /></textarea>


					</div>

					<input type="submit" value="Send" class="btn" />

				</div>

			</div>
		</form>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script src="/LinkedClubs/JS/ProfileClub1.js"></script>
	<script src="/LinkedClubs/JS/ProfileClub2.js"></script>
	<script src="/LinkedClubs/JS/AjaxCall.js"></script>


	<script>
	
		// Toggle post container
		var update_post_container = document
				.getElementById('update-post-container');
		
		// Get list of information
		var post_ids = document.getElementsByClassName('current_post_id');
		
		var post_descriptions = document.getElementsByClassName('current_post_decription');
		
		var post_files = document.getElementsByClassName('current_post_file');
		
		var post_fileType = document.getElementsByClassName('current_post_fileType');
		
		// Get Inputs of update form
		var input_id = document.getElementById('post-id');
		
		var input_description = document.getElementById('description');
		
		var input_file = document.getElementById('file_input');

		// Toggle function
		function toggle_update_post(index) {
			
			update_post_container.style.display = "block";
			
			console.log(post_fileType[index].value);
			
			//update_post_container.classList.toggle("hide-post-update");
			
			input_id.value = post_ids[index].value ;
			input_description.value = post_descriptions[index].textContent;
			
			
			// Check wether a post file is an image or pdf
			if(post_fileType[index].value == 'application/pdf'){
				
				document.getElementById('img_update').innerHTML = '<embed src=' + post_files[index].src + ' width="100px" height="100px" >'
				
			}
			
			if(post_fileType[index].value.includes('image')){
				
				document.getElementById('img_update').innerHTML = '<img src=' + post_files[index].src + ' width="100px" height="100px" >'
				
			}
		
			
		}
		
		// Function for displaying inputs for event post type
		function showInputs(){
	        if(document.getElementById("check_input").checked == true){
	        	document.getElementById("event_inputs").style.display="block";
	        	
	        }else{
	        	document.getElementById("event_inputs").style.display="none";
	        }
	    
	    }
		
		// closePopUp function
		function closePopUp(){
			update_post_container.style.display = "none";
		}
	</script>
</body>
</html>