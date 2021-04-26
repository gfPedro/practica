import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators';


@Injectable({ providedIn: 'root' })
export class InterceptorHttpService implements HttpInterceptor {

  readonly ERROR_SERVIDOR_NO_RESPONDE = 'El servicor no responde';
  readonly ERROR_PETICION_INCORRECTA = 'Petición incorrecta';
  readonly ERROR_NO_AUTORIZADO = 'No autorizado';
  readonly ERROR_ACCESO_PROHIBIDO = 'Acceso prohibido';
  readonly ERROR_NO_ENCONTRADO = 'No encontrado';
  readonly ERROR_INTERNO = 'Error interno';

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = this.obtenerToken();
    if (authToken && authToken.token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${authToken.token}`
        }
      });
    }
    return next.handle(req).pipe(catchError(error => this.controlarError(req, error)));
  }

  controlarError(req: HttpRequest<any>, error: HttpErrorResponse) {
    const esError = error.error.message;
    let mensajeError = 'Error desconocido';
    console.log(error);

    if(esError) {
      mensajeError = error.error.message;
    } else {
      switch (error.status) {
        case 0: mensajeError = this.ERROR_SERVIDOR_NO_RESPONDE; break;
        case 400: mensajeError = this.ERROR_PETICION_INCORRECTA; break;
        case 401: mensajeError = this.ERROR_NO_AUTORIZADO; break;
        case 403: mensajeError = this.ERROR_ACCESO_PROHIBIDO; break;
        case 404: mensajeError = this.ERROR_NO_ENCONTRADO; break;
        case 500: mensajeError = this.ERROR_INTERNO; break;
        default: break;
      }
    }
    return throwError(mensajeError);
  }

  obtenerToken() {
    if (sessionStorage.getItem('authToken')) {
      return JSON.parse(sessionStorage.getItem('authToken'));
    }
    return undefined;
  }
}
