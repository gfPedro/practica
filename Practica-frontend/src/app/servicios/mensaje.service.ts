import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MensajeService {

  private emisor = new Subject<string>();

  cambioDetectado = this.emisor.asObservable();

  emitir(mensaje: string) {
    this.emisor.next(mensaje);
  }
  
}
