import { Component, OnInit } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { Reformado } from '../../models/reformado';
import { ReformadoService } from '../../core/services/reformado.service';

@Component({
  selector: 'app-reformado-list',
  standalone: true,
  imports: [CarouselModule, TagModule, ButtonModule],
  templateUrl: './reformado-list.component.html',
  styleUrl: './reformado-list.component.css'
})
export class ReformadoListComponent implements OnInit {
  reformados: Reformado[] = [];
  responsiveOptions: any[] = [];

  constructor(private reformadoService: ReformadoService){}

  ngOnInit(): void {
    this.fetchReformados();
  }

  fetchReformados(){
    this.reformadoService.getAll().subscribe({
      next: (reformados) => (
        this.reformados = reformados
      ),
      error: (error) => { 
        console.log(error); 
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
