import { PlatformLocation } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router, UrlSerializer } from "@angular/router";
import { Page } from "../modelo/util/Pagina";
import { Usuario } from "../modelo/Usuario";
import { Observable } from "rxjs";
import { CredencialesUsuario } from "../modelo/CredencialesUsuario";
import { DatosAltaUsuario } from "../modelo/DatosAltaUsuario";
import { map } from "rxjs/internal/operators";

@Injectable({ providedIn: 'root' })
export class UsuarioService {

  URL_SERVICIO = `${this.platformLocation.getBaseHrefFromDOM()}servicios/usuarios`;

  constructor(private http: HttpClient,
    private platformLocation: PlatformLocation,
    private router: Router,
    private serializer: UrlSerializer
  ) { }

  obtenerListado(pagina?: number, tamanioPagina?: number, orden?: string): Observable<Page<Usuario>> {
    const url = this.serializer.serialize(
      this.router.createUrlTree([this.URL_SERVICIO], { queryParams: { pagina, tamanioPagina, orden }})
    );
    return this.http.get<Page<Usuario>>(url);
  }

  login(credenciales: CredencialesUsuario) {
    const url = `${this.URL_SERVICIO}/login`;
    return this.http.post<any>(url, credenciales).pipe(map(user => {
      if (user && user.token) {
        sessionStorage.setItem('authToken', JSON.stringify(user));
        return user;
      }
    }));
  }

  logout() {
    sessionStorage.clear();
  }

  crear(datosUsuario: DatosAltaUsuario) {
    const url = this.URL_SERVICIO;
    return this.http.post<any>(url, datosUsuario);
  }

}
