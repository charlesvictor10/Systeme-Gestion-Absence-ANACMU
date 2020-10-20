import {Observable} from 'rxjs';
import {Absence} from './absence';

export class DemandePermissionAbsence {
  id:number;
  dateDemande:Date;
  dateAcceptation:Date;
  etatDemande:string;
  absence:Observable<Absence[]>;
}
