//Getting html elements
const likesBtns = document.getElementsByName("likesBtn");
const publishBtns = document.getElementsByName("publishBtn");
const addReminderBtns = document.getElementsByName("addReminder");
const posts = document.querySelectorAll(".post");
const sectionBtns = document.querySelectorAll(".likes__comments");
const commentSections = document.querySelectorAll(".comments");
const joinClubBtn = document.getElementById('joinBtn');

//Getting ticketBtn 
const ticketBtns = document.querySelectorAll("button[name=ticketBtn]");
//Getting accept members button
const acceptBtns = document.querySelectorAll(".btn1");
const refuseBtns = document.querySelectorAll(".btn2");
//Getting cards
const cards = document.querySelectorAll(".card");
//Getting the currentcard
let currentCard = undefined;
//Getting the current clicked post
let currentPost = undefined;
//Getting the current clicked btns section
let currentsectionBtn = undefined;
//Getting the current comment section
let currCommentSection = undefined;


//Functions
const ajaxCall = (path, callBackSuccess, callBackFailure, params) => {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", path, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(params);
    xhr.onload = () => {
        if (xhr.readyState == 4) {
            console.log("responseXML", xhr.responseXML);
            const messageTag = xhr.responseXML.getElementsByTagName("message")[0];
            const message = messageTag.childNodes[0].nodeValue;
            console.log(message);
            if (message == "success") {
                //test on callBack sometimes we need it sometimes not
                if (callBackSuccess != undefined) callBackSuccess();
            } else if (callBackFailure != undefined) callBackFailure();
        }
    };
};

//Handling reminder
const addReminder = (event) => {
    const parent = event.target.parentNode;
    const title = parent.children[0].value;
    const date = parent.children[1].value;
    const path = "http://localhost:8080/LinkedClubs/HandleReminder";
    const params = "title=" + title + "&date=" + date;
    //we need the parent var to append elements
    ajaxCall(path, () => appendReminder(parent, title, date),() =>parent.children[3].style.display="block", params);
};

const appendReminder = (parent, title, date) => {
    const section = document.createElement("section");
    const info = `
  		<span>${title}</span>
        <span>${date}</span>`;
    section.innerHTML = info;
    parent.after(section);
    //Hid error message
    parent.children[3].style.display="none";
};


//Handling Comment
const comment = (event) => {
    const submitBtn = event.target;
    const postId = submitBtn.getAttribute("data-id");
    const comment = submitBtn.parentNode.children[1].value;
    const path = "http://localhost:8080/LinkedClubs/HandleComments";
    const params = "comment=" + comment + "&postId=" + postId;
    //Passing the submitBtn to appendComment function to get the image of who has commented
    ajaxCall(path, () => appendComment(submitBtn, comment), undefined, params);
};

const appendComment = (submitBtn, comment) => {
    const src = submitBtn.getAttribute("data-img");
    const commentContainer = currCommentSection.firstElementChild;
    const div = `<div>
        <img src=${src} >
        <span>${comment}</span>
    </div>`;
    commentContainer.innerHTML = commentContainer.innerHTML + div;
    //increase nbr comments
    let nbrCommentELement = currentsectionBtn.children[1].firstElementChild;
    nbrCommentELement.textContent = parseInt(nbrCommentELement.textContent) + 1;
};

//Handling like post
const like = (event) => {
    const likeBtn = event.target;
    const isLiked = likeBtn.getAttribute("data-isLiked");
    const postId = likeBtn.getAttribute("data-id");
    const path = "http://localhost:8080/LinkedClubs/HandleLikes";
    const params = "id=" + postId + "&isLiked=" + isLiked;
    if (!JSON.parse(isLiked)) ajaxCall(path, () => changeLikeBtn(likeBtn, "#FF7556", "true"), undefined, params);
    else ajaxCall(path, undefined, () => changeLikeBtn(likeBtn, "black", "false"), params);
};

//Handle Join Club by student
const joinClub = (event) => {
 const joinBtn = event.target;
 const clubId = joinBtn.getAttribute("data-clubId");
 const path = "http://localhost:8080/LinkedClubs/HandleJoinClub";
 const params = "clubId=" + clubId;
 ajaxCall(path, () => changeJoinStatus(), undefined, params);
};

// changeJoinStatus function
const changeJoinStatus = () => {

	const joinedStatus = document.getElementById('joinedStatus');
	joinedStatus.style.display="block";	
	
	joinClubBtn.style.display="none";	

}

//Increment and decrement nbr of likes
const nbrLikes = (isTrue) => {
    const header = currentPost.firstElementChild;
    const nbrlikeElement = header.children[1].firstElementChild;
    if (JSON.parse(isTrue)) nbrlikeElement.textContent = parseInt(nbrlikeElement.textContent) + 1;
    else nbrlikeElement.textContent = parseInt(nbrlikeElement.textContent) - 1;
};

//Changing the style of like btn + and nbr of likes
const changeLikeBtn = (btn, color, isTrue) => {
    btn.style.color = color;
    btn.setAttribute("data-isLiked", isTrue);
    //isTrue to test if increment or decrement
    nbrLikes(isTrue);
};

//Accept refuse club menbers
const acceptRefuse=(event)=>{
	const studentId=event.target.getAttribute("data-studentId");
	const clickedBtn=event.target.getAttribute("name");
	const path="http://localhost:8080/LinkedClubs/HandleMembers";
	const params="cne="+studentId + "&btnClicked="+clickedBtn;
	ajaxCall(path, () => accept(), ()=>refuse(), params);
};
const accept=()=>{
	const h4=currentCard.querySelector("h4");
	h4.textContent="Payed : true";
};
const refuse=()=>{
	const container=document.getElementById("grid__container");
	container.removeChild(currentCard);
};

//Handle notification
const addNotification=(event)=>{
	const eventBtn=event.target;
	const title=eventBtn.getAttribute("data-title");
	const date=eventBtn.getAttribute("data-date");
	const notificationId=eventBtn.getAttribute("data-notificationId");
	const isClicked=eventBtn.getAttribute("data-clicked");	
	const path="http://localhost:8080/LinkedClubs/HandleNotification";
	const params="title="+title+"&date="+date+"&isClicked="+isClicked+"&notificationId="+notificationId;
	if(!JSON.parse(isClicked)) ajaxCall(path, () => appendNotification(eventBtn,title,date), undefined, params);
};
const appendNotification=(btn,title,date)=>{
	btn.style.color="#FF7556";
	btn.setAttribute("data-clicked","true");
	const nbrNotificattion=document.getElementById("nbrNotificattion");
	nbrNotificattion.textContent=parseInt(nbrNotificattion.textContent)+1;
	const notification=document.getElementById("notification");
    const section = `
    	<section>
	  		<span>${title}</span>
	        <span>${date}</span>
	     </section>
        `;
     notification.innerHTML=section+notification.innerHTML;
};

//Event listeners
likesBtns.forEach(Element => {
    Element.addEventListener("click", (event) => like(event));
});
publishBtns.forEach(Element => {
    Element.addEventListener("click", (event) => comment(event));
});
addReminderBtns.forEach(Element => {
    Element.addEventListener("click", (event) => addReminder(event));
});
posts.forEach(Element => {
    Element.addEventListener("click", (event) => { currentPost = event.currentTarget });
});
sectionBtns.forEach(Element => {
    Element.addEventListener("click", (event) => { currentsectionBtn = event.currentTarget; });
});
commentSections.forEach(Element => {
    Element.addEventListener("click", (event) => { currCommentSection = event.currentTarget; });
});
cards.forEach(Element => {
    Element.addEventListener("click", (event) => {currentCard = event.currentTarget;});
});
acceptBtns.forEach(Element => {
    Element.addEventListener("click", (event) => acceptRefuse(event));
});
refuseBtns.forEach(Element => {
    Element.addEventListener("click", (event) => acceptRefuse(event));
});
ticketBtns.forEach(Element => {
    Element.addEventListener("click", (event) => addNotification(event));
});

joinClubBtn.addEventListener("click", (event) => joinClub(event));