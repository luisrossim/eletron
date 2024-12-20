import { AfterContentChecked, Component, inject, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProgressBarModule } from 'primeng/progressbar';
import { ToastModule } from "primeng/toast";
import { ToastService } from "./utils/services/toast.service";
import { MessageService } from "primeng/api";
import { UtilitiesService } from './utils/services/utilities.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ProgressBarModule, ToastModule],
  providers: [MessageService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, AfterContentChecked {
  title="eletron-frontend"
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
    this.toastService.send$.subscribe({
      next: newMessage => {
        if(newMessage && newMessage.summary !== this.toastService.INIT_STATE) {
          this.messageService.add(newMessage);
        }
      }
    })
  }
}
