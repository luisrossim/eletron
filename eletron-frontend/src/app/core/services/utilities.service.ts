import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilitiesService {
  private loadingRequest: boolean = false;

  constructor() { }

  setLoading(x: boolean) {
    this.loadingRequest = x;
  }

  getLoading(): boolean {
    return this.loadingRequest;
  }

  limparEspacosVariavel(variavel: string): string {
    return variavel.replace(/\s{2,}/g, ' ').trim();
  }

  limparEspacosObjeto(objeto: any) {
    for (const key in objeto) {
      if (typeof objeto[key] == 'string') {
        objeto[key] = this.limparEspacosVariavel(objeto[key] + '');
      }
    }
    return objeto;
  }
}
