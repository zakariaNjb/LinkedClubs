<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav id="navBar">
	<main>
		<c:if test="${ sessionScope.student != null }">
			<a href="home">LinkedClubs</a>
		</c:if>

		<c:if test="${ sessionScope.student == null }">
			<a href="./">LinkedClubs</a>
		</c:if>

		<button>
			<input type="text" placeholder="search"> <i
				class="fab fa-searchengin"></i>
		</button>
		<c:if test="${ sessionScope.student != null }">
			<div>
				<button id="notificationBtn">
					<i class="fas fa-sticky-note"></i>
				</button>
				<span id="nbrReminder">${ nbrReminders }</span>
				<button id="notifyBtn">
					<i class="fas fa-bell"></i>
				</button>
				<span id="nbrNotificattion">${ nbrNotifications }</span> <img
					src="/LinkedClubs/Images/profileImages/<c:out value="${sessionScope.student.profileImage}"/>"
					alt="profile picture"> <a href="profile"><span
					id="fullName"><c:out
							value="${sessionScope.student.fullName}" /></span></a>

				<form action="Logout" method="post">

					<button id="logout_btn" type="submit"
						style="color: white; display: block; margin-left: 20px;">
						<i class="fas fa-sign-out-alt"></i>
					</button>

				</form>

			</div>
		</c:if>

		<c:if test="${ sessionScope.student == null }">
			<div>
				<img
					src="/LinkedClubs/Images/clubProfileImages/<c:out value="${club.image}"/>"
					alt="profile picture"> <span id="fullName"><c:out
						value="${club.clubName}" /></span>


			</div>
		</c:if>

	</main>
</nav>