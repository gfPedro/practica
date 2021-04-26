import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from './servicios/usuario.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Practica-frontend';
  pantallas = ['login', 'altaUsuario', 'usuarios'];
  activa = 0;

  constructor(
    public router: Router,
    private usuarioServicio: UsuarioService
  ) { }

  ngOnInit() {
  }

  cambiarPantalla(pantalla: string) {
    this.router.navigate([pantalla]);
  }

  logout() {
    this.usuarioServicio.logout();
    this.cambiarPantalla('login');
  }

  estalogado(): boolean {
    return Boolean(sessionStorage.getItem('authToken'));
  }

}
