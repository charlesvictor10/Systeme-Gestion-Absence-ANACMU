import {Observable} from 'rxjs';
import {DemandePermissionAbsence} from './demandePermissionAbsence';
import {NiveauValidation} from './niveauValidation';
import {Agent} from './agent';

export class ValidationDemande {
  id:number;
  dateValidation:Date;
  niveauValidation:Observable<NiveauValidation[]>;
  validateur:Observable<Agent[]>;
  demandePermissionAbsence:Observable<DemandePermissionAbsence[]>;
  observations:string;
  etatValidation:string;
}
