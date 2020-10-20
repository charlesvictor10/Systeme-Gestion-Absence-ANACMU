import {Observable} from 'rxjs';
import {Entite} from './entite';
import {Service} from './service';
import {Fonction} from './fonction';

export class Agent {
  id:number;
  matricule:string;
  cni:string;
  datePriseFonction:Date;
  dateRecrutement:Date;
  telProfessionnel:string;
  numeroPoste:string;
  emailProfessionnel:string;
  telBureau:string;
  entite:Observable<Entite[]>;
  service:Observable<Service[]>;
  fonction:Observable<Fonction[]>;
  superviseur:Observable<Agent[]>;
}
