import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Usuario } from '../modelo/Usuario';
import { Page } from '../modelo/util/Pagina';
import { MensajeService } from '../servicios/mensaje.service';
import { UsuarioService } from '../servicios/usuario.service';

@Component({
  selector: 'app-listado-usuarios',
  templateUrl: './listado-usuarios.component.html',
  styleUrls: ['./listado-usuarios.component.css']
})
export class ListadoUsuariosComponent implements OnInit {

  // Configuración
  campos = ['id', 'nombre', 'edad', 'email'];
  tamaniosPaginaDisponibles = [10, 25, 50, 100];

  // Valores por defecto
  paginaUsuarios: Page<Usuario>;
  pagina = 1;
  paginasTotales = 0;
  tamanioPagina = 10;
  orden = 'nombre';

  constructor(
    private usuarioServicio: UsuarioService,
    private mensajeServicio: MensajeService
  ) { }

  ngOnInit() {
    this.obtenerPagina();
  }

  obtenerPagina() {
    this.usuarioServicio.obtenerListado(this.pagina, this.tamanioPagina, this.orden).toPromise()
    .then(pagina => {
      if (pagina) {
        pagina.number++;
        this.paginaUsuarios = pagina;
        this.paginasTotales = this.paginaUsuarios.totalPages;
      }
    }).catch(error => this.mensajeServicio.emitir(error));
  }

  ordenar(campo: string) {
    if (this.orden.endsWith(campo)) {
      this.orden = this.orden.startsWith('-') ? campo : `-${campo}`;
    } else {
      this.orden = campo;
    }
    this.obtenerPagina();
  }

  pasarPagina(pagina: number) {
    const paginasTotales = this.paginaUsuarios ? this.paginaUsuarios.totalPages : 0;
    pagina = Math.max(1 ,Math.min(paginasTotales, pagina));
    if (this.pagina === pagina) {
      return;
    }
    this.pagina = pagina;
    this.obtenerPagina();
  }

  cambiarTamanio(tamanioPagina: number) {
    if (this.tamanioPagina === tamanioPagina) {
      return;
    }
    this.pagina = 1;
    this.tamanioPagina = tamanioPagina;
    this.obtenerPagina();
  }

}
