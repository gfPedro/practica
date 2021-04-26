import { Component, Input, OnInit } from '@angular/core';
import { MensajeService } from '../servicios/mensaje.service';

@Component({
  selector: 'app-mensajes',
  templateUrl: './mensajes.component.html',
  styleUrls: ['./mensajes.component.css']
})
export class MensajesComponent implements OnInit {

  _mensaje: string;

  constructor(
    private mensajeServicio: MensajeService
  ) { }

  ngOnInit(): void {
    this.mensajeServicio.cambioDetectado.subscribe(mensaje => {
      this._mensaje = mensaje;
      setTimeout(() => this.cerrar(), 5000);
    })
  }

  cerrar() {
    this._mensaje = undefined;
  }

}
