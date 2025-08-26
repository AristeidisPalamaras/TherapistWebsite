const open = document.getElementById("open-mobile-menu-btn");
const close = document.getElementById("close-mobile-menu-btn");
const menu = document.getElementById("mobile-menu");
const overlay = document.getElementById('overlay');


function openMenu() {
    menu.classList.remove('translate-x-full');
    overlay.classList.remove('hidden');
}

  function closeMenu() {
    menu.classList.add('translate-x-full');
    overlay.classList.add('hidden');
}

open.addEventListener('click', openMenu);
close.addEventListener('click', closeMenu);
overlay.addEventListener('click', closeMenu);