//Graping elements
const commentBtns=document.getElementsByName("commentsBtn");
const notificationBtn=document.getElementById("notificationBtn");
const closeBtn=document.getElementById("closeNotificationBtn");

//Fucntions
const displayComments=(event)=>{
    //getting post div
    const post=event.target.parentNode.parentNode.parentNode;
    const commentSection=post.querySelector(".comments");
    commentSection.style.display="block";
};

const displayReminder=()=>{
    const reminderContainer=document.getElementById("reminder_contain_mobile");
    reminderContainer.style.display="block";
};

const hidReminder=()=>{
    const reminderContainer=document.getElementById("reminder_contain_mobile");
    reminderContainer.style.display="none";
};  

//Atach to event listeners
commentBtns.forEach(element=>{
    element.addEventListener("click",displayComments);
});
notificationBtn.addEventListener("click",displayReminder);
closeBtn.addEventListener("click",hidReminder);

function show(){
  document.getElementById("grid__container").style.display="grid";
  document.getElementById("posts__reminder").style.display="none";
  document.getElementById("About__container").style.display="none";
  document.getElementById("Club_sett").style.display="none";
}
function how(){
    document.getElementById("About__container").style.display="block";
    document.getElementById("posts__reminder").style.display="none";
    document.getElementById("grid__container").style.display="none";
   document.getElementById("Club_sett").style.display="none";
  }
  function wow(){
    document.getElementById("About__container").style.display="none";
    document.getElementById("posts__reminder").style.display="flex";
    document.getElementById("grid__container").style.display="none";
   document.getElementById("Club_sett").style.display="none";
  }
  
  function dow(){
    document.getElementById("About__container").style.display="none";
    document.getElementById("posts__reminder").style.display="none";
    document.getElementById("grid__container").style.display="none";
    document.getElementById("Club_sett").style.display="grid";
  }
  
const realFileBtn = document.getElementById("real_file");
const customBtn = document.getElementById("custom_button");
const customTxt = document.getElementById("custom_text");
customBtn.addEventListener("click", function(){
    realFileBtn.click();
});
realFileBtn.addEventListener("change", function(){
    if(realFileBtn.value) {
        customTxt.innerHTML = realFileBtn.value;
    } else {
        customTxt = "No image chosen"
    }
})