<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

<link rel="icon" href="/LinkedClubs/Images/LCbg.png" type="image/icon type">

<title>LinkedClubs</title>

<style>

.main-bg-color {
  background-color: #FF7556;
}

.main-color {
  color: #FF7556;
}

.main-border-color {
  border-color: #FF7556;
}

.place-holder-color::placeholder {
	color: #FF7556;
	opacity: 1;
}

.place-holder-color::-ms-input-placeholder {
	color: #FF7556;
}

main {
	display: flex;
	position: fixed;
	z-index: 2;
	bottom: 20px;
	right: 20px;
}

#chat {
	display: none;
	box-shadow: 1px 1px 10px black;
}

main div:nth-of-type(2) {
	display: flex;
	align-items: flex-end;
}

main div:nth-of-type(2) img {
	cursor: pointer;
	box-shadow: 2px 2px 10px black;
	height: 70px;
	width: 70px;
	border-radius: 100px;
	transition-duration: 200ms;
}
</style>

</head>
<body>

	<c:import url="./WEB-INF/JSP/chatbot.jsp" />

	<header class="shadow-xl">
		<nav class="flex lg:justify-between justify-center flex-wrap p-2">
			<a href="" class="px-10 py-6 text-3xl font-black text-gray-700">LinkedClubs</a>
			<ul class="px-10 md:py-6">
				<li class="inline-block pr-3"><a
					href="/LinkedClubs/clubs?clubId=" class="clubIds"> <img
						src="/LinkedClubs/Images/clubProfileImages/"
						class="rounded-full transition transform duration-500 hover:rotate-180 w-10 h-10 images" />
				</a></li>
				<li class="inline-block pr-3"><a
					href="/LinkedClubs/clubs?clubId=" class="clubIds"> <img
						src="/LinkedClubs/Images/clubProfileImages/"
						class="rounded-full transition transform duration-500 hover:rotate-180 w-10 h-10 images" />
				</a></li>
				<li class="inline-block pr-3"><a
					href="/LinkedClubs/clubs?clubId=" class="clubIds"> <img
						src="/LinkedClubs/Images/clubProfileImages/"
						class="rounded-full transition transform duration-500 hover:rotate-180 w-10 h-10 images" />
				</a></li>
				<li class="inline-block pr-3"><a
					href="/LinkedClubs/clubs?clubId=" class="clubIds"> <img
						src="/LinkedClubs/Images/clubProfileImages/"
						class="rounded-full transition transform duration-500 hover:rotate-180 w-10 h-10 images" />
				</a></li>
			</ul>
		</nav>
	</header>
	<div class="container m-auto">
		<h1
			class="text-4xl font-bold lg:mx-6 mt-10 my-6 px-4 my-6 text-gray-500">
			Matching Students <br> with great Clubs.
		</h1>
		<div class="flex flex-wrap">
			<section class="m-10">
				<h2 class="text-xl font-bold text-gray-700">For Students</h2>
				<p class="text-gray-500 py-6 mb-6">
					We are the link between you, your school<br> and your clubs. <br>
					Join us and be part of our online <br> extracurricular.
				</p>
				<div>
				
					<a href="/LinkedClubs/Login"
					class="py-6 px-10 font-bold my-6 text-center border-2 main-border-color main-bg-color shadow-lg text-white">Log
					in</a>
					
					<a href="/LinkedClubs/Signup"
					class="py-6 px-10 font-bold my-6 text-center border-2 main-border-color main-color shadow-lg">Sign up</a>
				
				</div>
			</section>
			<section class="m-10">
				<h2 class="text-xl font-bold text-gray-700">For Clubs</h2>
				<p class="text-gray-500 py-6 mb-6">
					Create your own space to<br>share your activities and<br>Events with great <br>students.<br>
				</p>
				<a href="/LinkedClubs/ClubLogin"
					class="py-6 px-10 font-bold text-center shadow-lg border-2 main-border-color main-color">Login for club</a>
			</section>
		</div>
	</div>
	<aside class="flex justify-end absolute right-0 top-40 w-1/2 hidden lg:block">
		<img src="/LinkedClubs/Images/index.svg" />
	</aside>
	<footer class="p-10 mt-6 text-gray-500">
		<p class="text-center">
			LinkedClubs Copyright &copy; 2021 open sourced on <a href="">LinkedClubs</a>
		</p>
	</footer>
	<script defer src="/LinkedClubs/JS/getClubs.js"></script>
	<script defer src="/LinkedClubs/JS/chatbot.js"></script>
</body>

</html>