import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MensajeService } from '../servicios/mensaje.service';
import { UsuarioService } from '../servicios/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = new FormControl('', {validators: [Validators.required, Validators.email], updateOn: 'blur'});
  contrasenia = new FormControl('', {validators: [Validators.required], updateOn: 'blur'});

  constructor(
    private router: Router,
    private usuarioServicio: UsuarioService,
    private mensajeServicio: MensajeService
  ) { }

  ngOnInit(): void {
  }

  cambiarPantalla(pantalla: string) {
    this.router.navigate([pantalla]);
  }

  login() {
    this.email.markAsDirty();
    this.contrasenia.markAsDirty();
    if (this.email.invalid || this.contrasenia.invalid){
      return;
    }
    this.usuarioServicio.login({email: this.email.value, contrasenia: this.contrasenia.value}).toPromise()
    .then(respuesta => {
      this.cambiarPantalla('usuarios');
    })
    .catch(error => this.mensajeServicio.emitir(error));
  }
}
