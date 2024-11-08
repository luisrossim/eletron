import { AfterContentChecked, Component, inject, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { AvatarModule } from 'primeng/avatar';
import { StyleClassModule } from 'primeng/styleclass';
import { Sidebar } from 'primeng/sidebar';
import { ProgressBarModule } from 'primeng/progressbar';
import { CardModule } from 'primeng/card';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { ToastModule } from "primeng/toast";
import { ToastService } from "./core/services/toast.service";
import { MessageService } from "primeng/api";
import { UtilitiesService } from './core/services/utilities.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, SidebarModule, 
    ButtonModule, RippleModule, 
    AvatarModule, StyleClassModule, 
    ProgressBarModule, CardModule, 
    MenubarModule, ToastModule
  ],
  providers: [
    MessageService
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, AfterContentChecked {
  @ViewChild('sidebarRef') sidebarRef!: Sidebar;

  sidebarVisible: boolean = false;
  isListingView = true;
  toastService = inject(ToastService);
  messageService = inject(MessageService);
  items: MenuItem[] | undefined;

  loading = this.utilities.getLoading();

  constructor(public utilities: UtilitiesService) {}

  ngAfterContentChecked(): void {
    this.loading = this.utilities.getLoading();
  }

  ngOnInit(): void {
    this.listenToastService();
    this.gerarMenu();
  }


  private listenToastService() {
    this.toastService.sendSub.subscribe({
      next: newMessage => {
        if(newMessage && newMessage.summary !== this.toastService.INIT_STATE) {
          this.messageService.add(newMessage);
        }
      }
    })
  }

  closeCallback(e:any): void {
      this.sidebarRef.close(e);
  }

  private gerarMenu() {
      this.items = [
          {
              label: 'Home',
              icon: 'pi pi-home'
          },
          {
              label: 'Features',
              icon: 'pi pi-star'
          },
          {
              label: 'Projects',
              icon: 'pi pi-search',
              items: [
                  {
                      label: 'Components',
                      icon: 'pi pi-bolt'
                  },
                  {
                      label: 'Blocks',
                      icon: 'pi pi-server'
                  },
                  {
                      label: 'UI Kit',
                      icon: 'pi pi-pencil'
                  },
                  {
                      label: 'Templates',
                      icon: 'pi pi-palette',
                      items: [
                          {
                              label: 'Apollo',
                              icon: 'pi pi-palette'
                          },
                          {
                              label: 'Ultima',
                              icon: 'pi pi-palette'
                          }
                      ]
                  }
              ]
          },
          {
              label: 'Contact',
              icon: 'pi pi-envelope'
          }
      ]
  }
}
