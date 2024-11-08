import { Component, inject, OnInit } from '@angular/core';
import { ToastService } from "../../core/services/toast.service";
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  toastService = inject(ToastService);

  ngOnInit(): void {
    this.toastService.send({
      severity: "error", detail: "Error when fetching the listing", summary: "Error",
    });
  }
}
