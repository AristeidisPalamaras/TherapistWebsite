const btn = document.getElementById("mobile-menu-btn");
const menu = document.getElementById("mobile-menu");
const hamburger = document.getElementById("hamburger-icon");
const close = document.getElementById("close-icon");

btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
    hamburger.classList.toggle("hidden");
    close.classList.toggle("hidden");
})