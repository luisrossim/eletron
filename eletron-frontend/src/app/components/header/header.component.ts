import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { ReformadoService } from '../../core/services/reformado.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, ButtonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  reformadosCount = 0;

  constructor(private reformadoService: ReformadoService){}

  ngOnInit() {
    this.reformadoService.eletronicosCount$.subscribe(count => {
      this.reformadosCount = count;
    });
  }

}
