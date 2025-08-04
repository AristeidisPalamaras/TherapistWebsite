// Normalize heights of each section across cards in home.html
function normalizeHeights(selector) {
    const elements = document.querySelectorAll(selector);
    let maxHeight = 0;

    elements.forEach(el => {
        el.style.height = 'auto'; // reset height
        const height = el.offsetHeight;
        if (height > maxHeight) maxHeight = height;
    });

    elements.forEach(el => {
        el.style.height = maxHeight + 'px';
    });
}

function alignCardSections() {
    normalizeHeights('.card h2');
    // normalizeHeights('.card img');
    normalizeHeights('.card p');
    //   normalizeHeights('.card a');
}

window.addEventListener('load', alignCardSections);
window.addEventListener('resize', alignCardSections);