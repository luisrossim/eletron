import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../../components/header/header.component";
import { FooterComponent } from "../../components/footer/footer.component";
import { InputIconModule } from 'primeng/inputicon';
import { IconFieldModule } from 'primeng/iconfield';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { MenuItem } from 'primeng/api';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { DividerModule } from 'primeng/divider';
import { ReformadoListComponent } from "../../components/reformado-list/reformado-list.component";

@Component({
  selector: 'app-status',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, InputTextModule, ButtonModule, InputIconModule, BreadcrumbModule, DividerModule, IconFieldModule, ReformadoListComponent],
  templateUrl: './status.component.html',
  styleUrl: './status.component.css'
})
export class StatusComponent implements OnInit {
  items: MenuItem[] | undefined;

  home: MenuItem | undefined;

  ngOnInit() {
      this.items = [
          { label: 'Consultar serviço' }
      ];

      this.home = { icon: 'pi pi-home', routerLink: '/' };
  }
}
