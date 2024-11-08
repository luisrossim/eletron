import { Cliente } from '../../models/cliente';
import { CrudService } from './crud.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ClienteService extends CrudService<Cliente> {
  constructor() {
    super('cliente');
  }
}
