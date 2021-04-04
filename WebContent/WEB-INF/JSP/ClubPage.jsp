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

<link rel="icon" href="/LinkedClubs/Images/LCbg.png" type="image/icon type">

</head>
<body>
	<c:import url="navbar.jsp" />
	<!----------------Personnal info sections--------------------->
	<!----------------Personnal info sections--------------------->
	<section id="personal__info"
		style="background-image: url('/LinkedClubs/Images/clubCoverImages/<c:out value="${club.coverImage}"/>')">
		<main>
			<img
				src="/LinkedClubs/Images/clubProfileImages/<c:out value="${club.image}"/>"
				alt="profile picture">
			<div>
				<ul>
					<li><c:out value="${club.clubName}" /></li>
					<li><i class="fab fa-facebook"></i><span><c:out
								value="${club.facebook}" /></span></li>
					<li><i class="fab fa-instagram"></i><span><c:out
								value="${club.instagram}" /></span></li>
				</ul>
				<c:if test="${ sessionScope.student != null }">
					<c:if test="${ !joinedClubs.contains(club.clubId) }">
						<button id="joinBtn" data-clubId="${ club.clubId }">Join
							Club</button>
					</c:if>

					<span id="joinedStatus" style="color: green; display: none;">Joined <i class="fas fa-check"></i></span>

					<c:if test="${ joinedClubs.contains(club.clubId) }">
						<span style="color: green;">Joined <i class="fas fa-check"></i></span>
					</c:if>
				</c:if>
				<span>Members<span id="nbrClubsJoined"
					style="color: #FF7556;"><c:out value="${club.membersNumber}" /></span></span>
			</div>
		</main>
	</section>
	<!--------------Post && Reminder section----------------------->
	<nav>
		<div class="nav__container">
			<ul>
				<li><button onclick="wow();">Club</button></li>
				<li><button onclick="how();">About</button></li>
			</ul>
		</div>
	</nav>
	<!--------------Post && Reminder section----------------------->
	<div id="posts__reminder">
		<div id="reminder">
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

			<div id="update-post-container" class="hide-post-update">

				<form action="UpdatePost" method="post"
					enctype="multipart/form-data">

					<img src="" width="100px" height="100px" id="post-image"><br>

					<input type="text" name="description" id="description"
						placeholder="Description"><br> <input type="text"
						name="post_id" id="post-id" value=""> <input type="file"
						name="fileLink" id="file_input"><br> <input
						type="submit" value="Update">

				</form>

			</div>

			<c:forEach items="${posts}" var="post" varStatus="status">

				<div class="post event">
					<header>
						<a
							href="/LinkedClubs/clubs?clubId=<c:out value="${post.club.clubId}"/>"
							style="color: black; text-decoration: none;">

							<div>
								<img
									src="/LinkedClubs/Images/clubProfileImages/<c:out value="${post.club.image}"/>"
									alt="profile image"> <span>
									<li>${ post.club.clubName }</li>
									<li>${ post.publishedDate }</li>
								</span>
							</div>

						</a>
						<div>
							<li name="numberLikes">${ post.likeNumber }</li>
							<li><i class="fas fa-star"></i></li>
						</div>
					</header>
					<p>${ post.postDescription }</p>

					<c:if test="${ !post.fileLink.isEmpty() }">

						<c:if test="${ post.fileType.contains('image') }">
							<img class="current_post_image"
								src="/LinkedClubs/Images/postFiles/<c:out value="${post.fileLink}"/>"
								alt="picture">
						</c:if>

						<c:if test="${ post.fileType.contains('pdf') }">

							<embed style="width: 100%; height: 30em;"
								src="/LinkedClubs/Images/postFiles/<c:out value="${post.fileLink}"/>" />

						</c:if>

					</c:if>

					<header class="likes__comments">
						<div>

							<button name="likesBtn"
								data-isLiked="${ likedPosts.contains(post.id) }"
								<c:if test="${ likedPosts.contains(post.id) }">style="color:#FF7556;"</c:if>
								data-id="${ post.id }">
								<i class="fas fa-star"></i> like
							</button>

							<button name="commentsBtn">
								<i class="fas fa-comment-alt"></i> comment
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
						<form action="" method="post" onsubmit="return false;">
							<button name="publishBtn" data-id="${ post.id }"
								data-img="/LinkedClubs/Images/profileImages/<c:out value="${sessionScope.student.profileImage}"/>">Publish</button>
							<input type="text" name="commentInput"
								placeholder="Write a comment..."> <span
								style="color: red;">${err.get("errCommentContent")}</span>
						</form>
					</section>
				</div>

			</c:forEach>

		</div>
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

		var post_descriptions = document
				.getElementsByClassName('current_post_decription');

		var post_images = document.getElementsByClassName('current_post_image');

		// Get Inputs of update form
		var input_id = document.getElementById('post-id');

		var input_description = document.getElementById('description');

		var input_image = document.getElementById('post-image');

		var input_file = document.getElementById('file_input');

		// Toggle function
		function toggle_update_post(index) {

			console.log(typeof index);

			update_post_container.classList.toggle("hide-post-update");

			input_id.value = post_ids[index].value;
			input_description.value = post_descriptions[index].textContent;
			input_image.src = post_images[index].src;

			input_file.value = post_images[index].src;

		}
	</script>


</body>
</html>