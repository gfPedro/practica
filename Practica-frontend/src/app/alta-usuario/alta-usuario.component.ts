import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CredencialesUsuario } from '../modelo/CredencialesUsuario';
import { DatosAltaUsuario } from '../modelo/DatosAltaUsuario';
import { MensajeService } from '../servicios/mensaje.service';
import { UsuarioService } from '../servicios/usuario.service';

@Component({
  selector: 'app-alta-usuario',
  templateUrl: './alta-usuario.component.html',
  styleUrls: ['./alta-usuario.component.css']
})
export class AltaUsuarioComponent implements OnInit {

  nombre = new FormControl('', {validators: [Validators.required], updateOn: 'blur'});
  edad = new FormControl(null, {validators: [Validators.required], updateOn: 'blur'});
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

  crear() {
    this.nombre.markAsDirty();
    this.edad.markAsDirty();
    this.email.markAsDirty();
    this.contrasenia.markAsDirty();
    if (this.nombre.invalid || this.edad.invalid || this.email.invalid || this.contrasenia.invalid){
      return;
    }
    const datosAlta: DatosAltaUsuario = {
      nombre: this.nombre.value,
      edad: this.edad.value,
      email: this.email.value,
      contrasenia: this.contrasenia.value
    };
    this.usuarioServicio.crear(datosAlta).toPromise()
    .then(respuesta => {
      if (respuesta) {
        const credenciales: CredencialesUsuario = { email: this.email.value, contrasenia: this.contrasenia.value };
        this.borrarCampos();
        this.usuarioServicio.login(credenciales).toPromise()
        .then(respuesta2 => {
          this.cambiarPantalla('usuarios');
        })
        .catch(error => this.mensajeServicio.emitir(error));
      }
    })
    .catch(error => this.mensajeServicio.emitir(error));
  }

  borrarCampos() {
    this.nombre.reset();
    this.edad.reset();
    this.email.reset();
    this.contrasenia.reset();
  }
}
