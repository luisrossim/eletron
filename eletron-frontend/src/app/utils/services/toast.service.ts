import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";
import { Message } from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  INIT_STATE = "INIT";

  private sendSubject = new BehaviorSubject<Message>({summary: this.INIT_STATE});
  send$ = this.sendSubject.asObservable();

  public send(message: Message): void {
    this.sendSubject.next(message);
  }

  public showError403(): void {
    this.send({
      severity: "error",
      summary: "Acesso negado",
      detail: "Você não tem permissão para acessar esse recurso."
    });
  }
}
