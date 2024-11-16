import { BehaviorSubject } from 'rxjs';
import { Reformado } from '../../models/reformado';
import { CrudService } from './crud.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ReformadoService extends CrudService<Reformado> {
  constructor() {
    super('reformado');
  }

  private reformadosCountSubject = new BehaviorSubject<number>(0);
  eletronicosCount$ = this.reformadosCountSubject.asObservable();

  updateReformadosCount(count: number): void {
    this.reformadosCountSubject.next(count);
  }
}
