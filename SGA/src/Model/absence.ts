import {Observable} from 'rxjs';
import {Agent} from './agent';
import {CongesNonDeductible} from './congesNonDeductible';

export class Absence {
  id:number;
  typeAbsence:string;
  dateDebutAbsence:string;
  dateFinAbsence:string;
  nbJourAbsence:number;
  nbJourOuvrable:number;
  nbJourDeductible:number;
  nbJourNonDeductible:number;
  agent:Observable<Agent[]>;
  congesNonDeductible:Observable<CongesNonDeductible[]>;
  etat:string;
  motif:string;

  constructor(dateDebutAbsence: string, dateFinAbsence: string, nbJourAbsence: number, nbJourDeductible: number, nbJourNonDeductible: number, congesNonDeductible: Observable<CongesNonDeductible[]>, motif: string) {
    this.nbJourAbsence = nbJourAbsence;
    this.nbJourDeductible = nbJourDeductible;
    this.nbJourNonDeductible = nbJourNonDeductible;
    this.congesNonDeductible = congesNonDeductible;
    this.motif = motif;
  }
}
