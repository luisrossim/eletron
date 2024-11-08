import { AfterContentChecked, Component, inject, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProgressBarModule } from 'primeng/progressbar';
import { ToastModule } from "primeng/toast";
import { ToastService } from "./utils/services/toast.service";
import { MessageService } from "primeng/api";
import { UtilitiesService } from './utils/services/utilities.service';
import { NavComponent } from './layout/nav/nav.component';
import { FooterComponent } from './layout/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ProgressBarModule, ToastModule, NavComponent, FooterComponent],
  providers: [MessageService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, AfterContentChecked {
  isListingView = true;
  toastService = inject(ToastService);
  messageService = inject(MessageService);
  loading = this.utilities.getLoading();

  constructor(public utilities: UtilitiesService) {}


  ngAfterContentChecked(): void {
    this.loading = this.utilities.getLoading();
  }

  ngOnInit(): void {
    this.listenToastService();
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
}
