import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { ToastService } from "../../utils/services/toast.service";
import { ButtonModule } from 'primeng/button';
import { AvatarModule } from 'primeng/avatar';
import { Sidebar } from 'primeng/sidebar';
import { ProgressBarModule } from 'primeng/progressbar';
import { CardModule } from 'primeng/card';
import { SidebarModule } from 'primeng/sidebar';
import { StyleClassModule } from 'primeng/styleclass';
import { ClienteService } from '../../core/services/cliente.service';
import { Cliente } from '../../models/cliente';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule, AvatarModule, ProgressBarModule, CardModule, SidebarModule, StyleClassModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  @ViewChild('sidebarRef') sidebarRef!: Sidebar;
  toastService = inject(ToastService);
  clienteService = inject(ClienteService)
  sidebarVisible: boolean = false;
  clientes: Cliente[] = [];

  ngOnInit(): void {
    this.fetchClientes();
  }

  closeCallback(e:any): void {
    this.sidebarRef.close(e);
  }

  private fetchClientes(){
    this.clienteService.getAll().subscribe({
      next: (clientes) => {
        this.clientes = clientes;
      },
      error: (error) => {
        this.toastService.send({
          severity: "error", detail: "Erro ao buscar clientes", summary: "Error",
        });
      }
    });
  }
}
