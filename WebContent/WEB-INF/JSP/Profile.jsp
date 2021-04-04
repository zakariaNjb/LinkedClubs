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
	href="/LinkedClubs/CSS/ProfilePage.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="/LinkedClubs/CSS/NavBar.css" media="screen" />

<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />


<title>Profile</title>

<link rel="icon" href="/LinkedClubs/Images/LCbg.png" type="image/icon type">

</head>
<body>
	<c:import url="navbar.jsp" />
	<!----------------Personnal info sections--------------------->
	<!----------------Personnal info sections--------------------->
	<section id="personal__info"
		style="background-image: url('/LinkedClubs/Images/coverImages/<c:out value="${sessionScope.student.coverImage}"/>')">
		<main>
			<img
				src="/LinkedClubs/Images/profileImages/<c:out value="${sessionScope.student.profileImage}"/>"
				alt="profile picture">
			<div>
				<ul>
					<li><c:out value="${sessionScope.student.fullName}" /></li>
					<li><i class="fab fa-facebook-f"></i><span><c:out
								value="${sessionScope.student.facebook}" /></span></li>
					<li><i class="fab fa-instagram"></i><span><c:out
								value="${sessionScope.student.instagram}" /></span></li>
					<li><i class="fal fa-book-user"></i><span><c:out
								value="${sessionScope.student.studentBio}" /></span></li>
				</ul>
				<span>Clubs<span id="nbrClubsJoined" style="color: #FF7556;">${ nbrJoinedClubs }</span>
					<button>
						<a href="/LinkedClubs/StudentSetting" style="color: initial;"><i
							class="fas fa-cog"></i></a>
					</button></span>
			</div>
		</main>
	</section>
	<!--------------Post && Reminder section----------------------->
	<!--------------Post && Reminder section----------------------->
	<div id="posts__reminder">
		<div id="reminder">
			<section>
				<span style="color: #FF7556; font-size: 1.5em">Reminder</span> <span
					style="text-align: right;"><i class="fas fa-sticky-note"></i></span>
			</section>

			<div class="addReminder">
				<input type="text" placeholder="Title of the reminder..."> <input
					type="datetime-local"> <input type="submit"
					name="addReminder" value="Add reminder"> <span
					class="reminderErr">Check out title and Date</span>
			</div>

			<c:forEach items="${reminders}" var="reminder" varStatus="status">

				<section>
					<span>${ reminder.title }</span> <span>${ reminder.reminderDate }</span>
				</section>

			</c:forEach>
		</div>
		<div id="posts">
			<!---------------------Normal post with picture----------->
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
							<c:if test="${ post.notification !=null }">
								<button name="ticketBtn" data-clicked="false"
									data-title="${ post.notification.title } "
									data-date="${ post.notification.date }"
									data-notificationId="${ post.notification.id }">
									<i class="fas fa-scroll"></i> ticket
								</button>
							</c:if>
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

			<!---------------Rminder small devices------------------------->
			<div id="reminder__contain__mobile">
				<div id="reminder__mobile">
					<section>
						<span style="color: #FF7556; font-size: 1.5em">Reminder</span> <span
							style="text-align: right;"><i id="closeNotificationBtn"
							class="fas fa-times"></i></span>
					</section>

					<div class="addReminder">
						<input type="text" placeholder="Title of the reminder...">
						<input type="date"> <input type="submit"
							name="addReminder" value="Add reminder">
					</div>

					<c:forEach items="${reminders}" var="reminder" varStatus="status">

						<section>
							<span>${ reminder.title }</span> <span>${ reminder.reminderDate }</span>
						</section>

					</c:forEach>

				</div>
			</div>

			<!---------------Notification small devices------------------------->

			<div id="notification">
				<c:forEach items="${notifications}" var="notification"
					varStatus="status">
					<section>
						<span>${ notification.title }</span> <span>${ notification.date }</span>
					</section>
				</c:forEach>
			</div>



			<script src="/LinkedClubs/JS/ProfilePage.js"></script>
			<script src="/LinkedClubs/JS/AjaxCall.js"></script>
</body>
</html>