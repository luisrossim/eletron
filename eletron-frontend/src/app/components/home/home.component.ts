import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import L, { latLng, marker, tileLayer, icon } from 'leaflet';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule, CardModule, LeafletModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  customIcon = icon({
    iconUrl: 'assets/img/logo-eletron.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32] 
  });

  options = {
    layers: [
      tileLayer('https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: '&copy; <a href="https://carto.com/">CartoDB</a> contributors'
      })
    ],
    zoom: 15,
    center: latLng(-18.707342062096952, -40.401385467076366),
  };

  marker = marker([-18.707342062096952, -40.401385467076366], { icon: this.customIcon }).bindPopup(
    '<div class="popup-content"><h4 class="mb-1">Eletrônica do Luis</h4><span class="flex gap-1"><i class="pi pi-star-fill text-yellow-400"></i><i class="pi pi-star-fill text-yellow-400"></i><i class="pi pi-star-fill text-yellow-400"></i><i class="pi pi-star-fill text-yellow-400"></i><i class="pi pi-star-half-fill text-yellow-400"></i></span></div>'
  );

  constructor(){}

  ngOnInit() {
    setTimeout(() => {
      this.marker.openPopup();
    }, 1500); 
  }
}
