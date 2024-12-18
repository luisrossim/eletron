import { Component, inject, OnInit } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { Reformado } from '../../models/reformado';
import { ReformadoService } from '../../core/services/reformado.service';
import { ToastService } from '../../utils/services/toast.service';

@Component({
  selector: 'app-reformado-list',
  standalone: true,
  imports: [CarouselModule, TagModule, ButtonModule],
  templateUrl: './reformado-list.component.html',
  styleUrl: './reformado-list.component.css'
})
export class ReformadoListComponent implements OnInit {
  toastService = inject(ToastService)
  reformados: Reformado[] = [];
  responsiveOptions: any[] = [];

  constructor(private reformadoService: ReformadoService){}

  ngOnInit(): void {
    this.fetchReformados();
  }

  fetchReformados(){
    this.reformadoService.getAll().subscribe({
      next: (reformados) => (
        this.reformados = reformados || []
      ),
      error: (error) => { 
        this.toastService.send({
          severity: "error",
          summary: "Error",
          detail: "Erro ao buscar reformados."
        });
      },
      complete: () => {
        this.reformadoService.updateReformadosCount(this.reformados.length);
      }
    });
    

    this.responsiveOptions = [
      {
          breakpoint: '1199px',
          numVisible: 3,
          numScroll: 1
      },
      {
          breakpoint: '991px',
          numVisible: 2,
          numScroll: 1
      },
      {
          breakpoint: '767px',
          numVisible: 1,
          numScroll: 1
      }
    ];
  }
}
