import {Annee} from './annee';
import {Observable} from 'rxjs';

export class JourFerie {
  id:number;
  lib:string;
  jour:Date;
  estRecurrent:boolean;
  estFixe:boolean;
  annee:Observable<Annee[]>;
}
