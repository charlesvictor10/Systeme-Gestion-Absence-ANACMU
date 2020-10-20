import {Observable} from 'rxjs';
import {Utilisateur} from './utilisateur';
import {Role} from './role';

export class UtilisateurRole {
  utilisateur:Observable<Utilisateur[]>;
  role:Observable<Role[]>;
}
