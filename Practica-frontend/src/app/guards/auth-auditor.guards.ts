import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { rutasValidas } from "../utils/constantes";

@Injectable({ providedIn: 'root'})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const authToken = this.obtenerToken();
    if (this.rutaValida(authToken ? 1 : 0, route.routeConfig.path)) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }

  obtenerToken() {
    if (sessionStorage.getItem('authToken')) {
      return JSON.parse(sessionStorage.getItem('authToken'));
    }
    return undefined;
  }

  rutaValida(rol: number, path: string) {
    let rutasValidasParaRol: string[] = rutasValidas[rol];
    return rutasValidasParaRol.indexOf(path) !== -1;
  }
}
