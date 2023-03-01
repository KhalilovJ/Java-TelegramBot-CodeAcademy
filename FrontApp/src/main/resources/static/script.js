let readMoreBtn = document.querySelectorAll(".card button")
let popup = document.querySelector(".popup")
let closePopup = document.querySelector(".close-popup")


readMoreBtn.forEach(btn => {
    btn.addEventListener("click", () => {
        popup.style.display = "block"
        popup.style.opacity = "1"
        popup.style.transition="all .9s ease-in"

    })
})


closePopup.addEventListener("click",()=>{
    popup.style.display = "none"
    popup.style.opacity = "0"
    popup.style.transition="all .9s ease-in"



})
