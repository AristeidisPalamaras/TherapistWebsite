document.addEventListener("DOMContentLoaded", function () {
    const centerCoords = [37.9838, 23.7275]; // Replace with your approximate location
    const zoomLevel = 17; // Adjust zoom level here
    const radius = 100;

    const map = L.map('map').setView(centerCoords, zoomLevel);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    L.circle(centerCoords, {
      color: '#007bff',
      fillColor: '#007bff',
      fillOpacity: 0.3,
      radius: radius
    }).addTo(map).bindPopup('Approximate office location');

    // Optional: fix layout issues if container is inside a tab/modal
    setTimeout(() => map.invalidateSize(), 100);
  });