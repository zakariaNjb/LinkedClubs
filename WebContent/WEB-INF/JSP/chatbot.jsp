<main>
	<div id="chat" class="w-80 bg-white">
		<header class="flex justify-between main-bg-color pb-10 px-2 pt-2">
			<div class="flex mt-4">
				<img src="/LinkedClubs/Images/bot.png" class="rounded-full w-9 h-9 mx-2" />
				<p class="text-white">
					Any Questions? chat with us ! <br> <span
						class="text-sm text-gray-200">support is online</span>
				</p>
			</div>
			<button type="button" id="closeBtn" style="outline: none;"
				class="mx-2 top-0 text-white"><i class="fas fa-times"></i></button>
		</header>
		<hr>
		<section id="messageContainer" class="overflow-y-scroll h-64">
			<div class="flex my-4">
				<img src="/LinkedClubs/Images/bot.png" class="rounded-full w-7 h-7 mx-2" />
				<p class="shadow-xl p-2 text-white rounded-xl main-bg-color mr-2">How
					can i help you!</p>
			</div>
		</section>
		<hr>
		<section class="">
			<form onsubmit="return false;" class=" flex">
				<input style="outline: none;" type="text"
					placeholder="what's on your mind?"
					class="w-10/12 h-full p-3 place-holder-color" id="message" />
				<button style="outline: none;" type="submit"
					class=" mt-3 w-2/12 h-full main-color" id="sendBtn">
					<i class="fas fa-paper-plane text-2xl"></i>
				</button>
			</form>
		</section>
		<hr>
	</div>
	<div>
		<img id="robot" src="/LinkedClubs/Images/bot.png" alt="picture">
	</div>
</main>

<script>

//variables
const sendBtn = document.getElementById("sendBtn");
const robot = document.getElementById("robot");
const closeBtn = document.getElementById("closeBtn");

//Functions
const apiCall = async (path, data) => {
	await fetch(path).then(response => {
		if (response.ok) return response.json();
		else throw new Error("API call has failed");
	}).then(arrivedData => {
		console.log("arrived data", arrivedData);
		data = arrivedData;
	}).catch(err => console.log(err));
	return data;
};

const appendMessage = (message, isUserMessage) => {
	const messageContainer = document.getElementById("messageContainer");
	if (isUserMessage) {
		var div = '<div class="flex justify-end my-4"><p class="shadow-xl p-2 w-3/4 mb-2 bg-white rounded-xl">' + message + '</p></div>';
	} else {
		div = '<div class="flex my-4"><img src="/LinkedClubs/Images/bot.png" class="rounded-full w-7 h-7 mx-2" /><p class="shadow-xl p-2 text-white rounded-xl main-bg-color mr-2">' + message + '</p></div>';
	}
	messageContainer.innerHTML = messageContainer.innerHTML + div;
	messageContainer.scrollTo(0, messageContainer.scrollHeight);
};

const sendMessage = async () => {
	const input = document.getElementById("message");
	const message = input.value;
	if (message == "") return;
	appendMessage(message, true);
	input.value = "";
	const path = "https://ensachatbot.zeet.app/?message=" + message;
	let data = undefined;
	data = await apiCall(path, data);
	appendMessage(data.response, false);
};

const displayChat = () => {
	const chat = document.getElementById("chat");
	robot.style.transform = "scale(0.5)";
	setTimeout(() => {
		robot.style.display = "none";
		chat.style.display = "block";
	}, 200);
};
const hidChat = () => {
	const chat = document.getElementById("chat");
	chat.style.display = "none";
	robot.style.display = "block";
	robot.style.transform = "scale(1)";
};

//Atach to event listener
sendBtn.addEventListener("click", sendMessage);
robot.addEventListener("click", displayChat);
closeBtn.addEventListener("click", hidChat);

</script>

