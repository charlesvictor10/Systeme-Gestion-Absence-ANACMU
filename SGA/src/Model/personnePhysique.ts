import {Observable} from 'rxjs';
import {Civilite} from './civilite';
import {Statut} from './statut';

export class PersonnePhysique {
  prenom:string;
  nomJeuneFille:string;
  civilite:Observable<Civilite[]>;
  statut:Observable<Statut[]>;
  genre:string;
}
