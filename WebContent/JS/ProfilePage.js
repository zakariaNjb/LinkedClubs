//Graping elements
const commentBtns=document.getElementsByName("commentsBtn");
const notificationBtn=document.getElementById("notificationBtn");
const closeBtn=document.getElementById("closeNotificationBtn");
const notifyBtn=document.getElementById("notifyBtn");
let isNotificationOpen=false;

//Fucntions
const displayComments=(event)=>{
    //getting post div
    const post=event.target.parentNode.parentNode.parentNode;
    const commentSection=post.querySelector(".comments");
    commentSection.style.display="block";
};



const displayReminder=()=>{
	const notification=document.getElementById("notification");
	notification.style.display="none";
    const reminderContainer=document.getElementById("reminder__contain__mobile");
    reminderContainer.style.display="block";
};

const hidReminder=()=>{
    const reminderContainer=document.getElementById("reminder__contain__mobile");
    reminderContainer.style.display="none";
};  

const diplayHidNotification=()=>{
	const notification=document.getElementById("notification");
	notification.style.display="block";
};

const hidNotification=()=>{
	const notification=document.getElementById("notification");
	notification.style.display="none";
}

//Atach to event listeners
commentBtns.forEach(element=>{
    element.addEventListener("click",displayComments);
});
notificationBtn.addEventListener("click",displayReminder);
closeBtn.addEventListener("click",hidReminder);
notifyBtn.addEventListener("click",diplayHidNotification);

notifyBtn.addEventListener("blur",hidNotification);