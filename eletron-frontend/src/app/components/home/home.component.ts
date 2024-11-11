import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { latLng, marker, tileLayer, icon } from 'leaflet';
import { RouterModule } from '@angular/router';
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule, CardModule, LeafletModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, AfterViewInit  {
  @ViewChild('title') title!: ElementRef<HTMLDivElement>
  @ViewChild('map') map!: ElementRef<HTMLDivElement>

  customIcon = icon({
    iconUrl: 'assets/img/logo-eletron.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32] 
  });

  options = {
    layers: [
      tileLayer('https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: '&copy; <a href="https://carto.com/">CartoDB</a> contributors'
      })
    ],
    zoom: 15,
    center: latLng(-18.706542062096952, -40.401385467076366),
  };

  marker = marker([-18.707342062096952, -40.401385467076366], { icon: this.customIcon }).bindPopup(
    `<div class="popup-content">
      <h3 class="mb-1">Eletrônica do Luis</h3>
      <p class="flex text-yellow-400 mt-0 mb-2 gap-1">
        <span class="mr-1">4,6</span>
        <i class="pi pi-star-fill"></i>
        <i class="pi pi-star-fill"></i>
        <i class="pi pi-star-fill"></i>
        <i class="pi pi-star-fill"></i>
        <i class="pi pi-star-half-fill"></i>
      </p>
      <a href="https://maps.google.com/maps/dir//Eletr%C3%B4nica+do+Luis+R.+Esp%C3%ADrito+Santo,+121+-+Beira+Rio+Nova+Ven%C3%A9cia+-+ES+29830-000/@-18.707284,-40.4013411,16z/data=!4m5!4m4!1m0!1m2!1m1!1s0xb5db42e8029599:0x3edcf99222fca05a" target="_blank" class="no-underline" rel="noopener noreferrer">
        Como chegar?
      </a>
    </div>`
  );

  constructor(){}

  ngOnInit() {
    setTimeout(() => {
      this.marker.openPopup();
    }, 1200); 
  }

  ngAfterViewInit(){
    gsap.registerPlugin(ScrollTrigger)
    gsap.from(this.title.nativeElement, {x: "-50px", opacity: 0})
    gsap.to(this.title.nativeElement, {x: "0px", opacity: 1, ease:'power4.out', duration: 2})

    gsap.from(this.map.nativeElement, {scale: 0.7, opacity: 0})
    gsap.to(this.map.nativeElement, {scale: 1, opacity: 1, ease:'power4.out', duration: 2})
  }
}
