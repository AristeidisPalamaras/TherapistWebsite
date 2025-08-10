const btn = document.getElementById("hamburger");
const menu = document.getElementById("mobile-menu");
const bars = document.getElementById("bars");
const close = document.getElementById("close");

btn.addEventListener("click", () => {
    menu.classList.toggle("hidden");
    bars.classList.toggle("hidden");
    close.classList.toggle("hidden");
})