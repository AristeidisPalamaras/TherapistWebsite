document.addEventListener("DOMContentLoaded", function () {
  const centerCoords = [37.9838, 23.7275];
  const zoomLevel = 16;
  const radius = 100;

  const map = L.map('map').setView(centerCoords, zoomLevel);

  L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/">Humanitarian OpenStreetMap Team</a>',
    subdomains: 'abc',
    maxZoom: 20
  }).addTo(map);



  L.circle(centerCoords, {
    color: '#007bff', // 1
    fillColor: '#007bff', //1
    fillOpacity: 0.15,
    weight: 2,
    radius: radius
  }).addTo(map).bindPopup('Approximate office location'); //1

});