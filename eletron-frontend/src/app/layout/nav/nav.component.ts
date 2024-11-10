import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { MenuItem } from 'primeng/api';
import { TabMenuModule } from 'primeng/tabmenu';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [CommonModule, ButtonModule, TabMenuModule],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit {
    items: MenuItem[] | undefined;

    activeItem: MenuItem | undefined;

    constructor(public router: Router){}

    ngOnInit() {
        this.items = [
            { label: 'Sobre', icon: 'pi pi-home' },
            { label: 'Localização', icon: 'pi pi-chart-line' },
            { label: 'Contato', icon: 'pi pi-list' },
        ];

        this.activeItem = this.items[0];
    }

    onActiveItemChange(event: MenuItem) {
        this.activeItem = event;
    }
}
