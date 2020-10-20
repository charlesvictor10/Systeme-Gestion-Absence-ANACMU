import {Observable} from 'rxjs';
import {Agent} from './agent';

export class SoldeConges {
  id:number;
  nbCongesAcquis:number;
  nbJourPris:number;
  nbJourPrisDeductible:number;
  nbJourPrisNonDeductible:number;
  nbCongesReliquat:number;
  nbCongesSupplementaire:number;
  nbCongesEchus:number;
  nbCongesEncours:number;
  dateDernierCalcul:Date;
  agent:Observable<Agent[]>;


  constructor(nbCongesAcquis: number, nbJourPris: number, nbCongesReliquat: number, nbCongesSupplementaire: number, nbCongesEchus: number, nbCongesEncours: number) {
    this.nbCongesAcquis = nbCongesAcquis;
    this.nbJourPris = nbJourPris;
    this.nbCongesReliquat = nbCongesReliquat;
    this.nbCongesSupplementaire = nbCongesSupplementaire;
    this.nbCongesEchus = nbCongesEchus;
    this.nbCongesEncours = nbCongesEncours;
  }
}
