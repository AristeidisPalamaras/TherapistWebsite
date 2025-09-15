document.addEventListener("DOMContentLoaded", function () {
  const mapElement = document.getElementById("map");

  const centerCoords = [parseFloat(mapElement.dataset.lat), parseFloat(mapElement.dataset.lon)];
  const zoomLevel = 16;
  const radius = 150;

  const map = L.map('map').setView(centerCoords, zoomLevel);

  L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/">Humanitarian OpenStreetMap Team</a>',
    subdomains: 'abc',
    maxZoom: 20
  }).addTo(map);

  L.circle(centerCoords, {
    color: '#007bff',
    fillColor: '#007bff',
    fillOpacity: 0.15,
    weight: 2,
    radius: radius
  }).addTo(map);
});