import { Routes, RouterModule } from '@angular/router';
import { AltaUsuarioComponent } from './alta-usuario/alta-usuario.component';
import { AuthGuard } from './guards/auth-auditor.guards';
import { ListadoUsuariosComponent } from './listado-usuarios/listado-usuarios.component';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'altaUsuario',
    component: AltaUsuarioComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'usuarios',
    component: ListadoUsuariosComponent,
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: 'login' }
];
export const appRoutes: any = RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload' });
