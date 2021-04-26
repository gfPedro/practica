import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { appRoutes } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { ListadoUsuariosComponent } from './listado-usuarios/listado-usuarios.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AltaUsuarioComponent } from './alta-usuario/alta-usuario.component';
import { InterceptorHttpService } from './servicios/interceptor-http.service';
import { MensajesComponent } from './mensajes/mensajes.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListadoUsuariosComponent,
    AltaUsuarioComponent,
    MensajesComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    appRoutes
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorHttpService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
