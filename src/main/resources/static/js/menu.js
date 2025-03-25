let prevScrollPos = window.scrollY;
window.onscroll = function () {
    let currentScrollPos = window.scrollY;
    let ribbon = document.getElementById("ribbon");

    if (prevScrollPos > currentScrollPos) {
        ribbon.style.transform = "translateY(0)"; // Show on scroll up
    } else {
        ribbon.style.transform = "translateY(-100%)"; // Hide on scroll down
    }
    prevScrollPos = currentScrollPos;
};