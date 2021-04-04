<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Administration</title>

<link rel="icon" href="/LinkedClubs/Images/LCbg.png" type="image/icon type">

<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
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
</style>
</head>

<body>
	<header class="flex justify-between lg:px-12 px-2 py-4 shadow-xl">
		<h1 class="font-bold text-xl">
			<a href="./">LinkedClubs</a>
		</h1>
		<div class="flex flex-wrap">
		
			<h2 class="font-bold text-xl">Administration</h2>
			<form action="Logout" method="post" class="ml-3">
			
				<input type="submit" value="Sign Out" class="p-1 hover:bg-yellow-500 font-black hover:text-white outline-none">
			
			</form>
		
		</div>
	</header>
	<main class="flex flex-col">
		<div class="flex justify-center text-center flex-wrap">
			<section
				class="p-20 w-72 text-2xl bg-red-300 m-6 rounded-lg shadow-xl">
				<p>Number Of Members:</p>
				<p class="text-5xl font-black">${ nbrMembers }</p>
			</section>
			<section
				class="p-20 w-72 text-2xl bg-green-300 m-6 rounded-lg shadow-xl">
				<p>Number Of Clubs:</p>
				<p class="text-5xl font-black">${ nbrClubs }</p>
			</section>
			<section
				class="p-20 w-72 text-2xl bg-blue-300 m-6 rounded-lg shadow-xl">
				<p>Number Of Students:</p>
				<p class="text-5xl font-black">${ nbrStudents }</p>
			</section>
		</div>
		<section class="flex justify-center my-6">
			<form action="administration" method="post"
				class="flex flex-col main-bg-color p-12  rounded-lg shadow-xl">
				<h2 class="font-bold text-xl mb-4 text-white shadow-xl">Create
					New Club</h2>
				<label class="mb-6 text-lg font-bold" for="clubId"> Club Id
					<br> <input type="text" class="my-2 p-2 place-holder-color"
					placeholder="club id" name="ClubId" id="clubId" />
				</label> <label class="mb-6 text-lg font-bold" for="password">
					Password <br> <input type="password"
					class="my-2 p-2 place-holder-color" placeholder="password"
					name="password" id="password" />
				</label> <label class="mb-6 text-lg font-bold" for="confirm-password">
					Confirm Password <br> <input type="password"
					class="my-2 p-2 place-holder-color" placeholder="confirm password"
					name="confirmPassword" id="confirm-password" />
				</label> <input class="p-2 text-lg font-bold" type="submit" value="Create" />
			</form>
		</section>
	</main>
	<footer class="p-10 mt-6 text-gray-500">
		<p class="text-center">
			LinkedClubs Copyright © 2021 open sourced on <a href="">LinkedClubs</a>
		</p>
	</footer>
</body>

</html>