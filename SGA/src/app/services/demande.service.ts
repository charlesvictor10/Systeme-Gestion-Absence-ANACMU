import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_DEMANDE_URL} from '../constants/constants';
import {DemandePermissionAbsence} from '../../Model/demandePermissionAbsence';
import {Service} from '../../Model/service';
import {Agent} from '../../Model/agent';
import {CongesNonDeductible} from '../../Model/congesNonDeductible';
import {Absence} from '../../Model/absence';

@Injectable({
  providedIn: 'root'
})
export class DemandeService {

  constructor(public http:HttpClient) {}

  getDemandePermission(id:number): Observable<Object>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/permissions/"+id,{headers:headers});
  }

  getDemandePermissionParService(service:Service): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/permissionsParService/?service="+service,{headers:headers});
  }

  getDemAbsAValiderParSH(agent:Agent): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/permissionsAValiderParSH/?agent="+agent,{headers:headers});
  }

  getDemAbsParAgent(agent:Agent): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/permissionsParAgent/?agent="+agent,{headers:headers});
  }

  getDemAbsAValiderParRH(): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/permissionsAValider",{headers:headers});
  }

  getDemAbsTraitees(): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/permissionsTraitees",{headers:headers});
  }

  getTypeAbsenceList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/typeAbsences",{headers:headers});
  }

  getNiveauValidationList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/niveauValidations",{headers:headers});
  }

  getValidationParDemandePermissionAbsence(demandePermissionAbsence:DemandePermissionAbsence): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/validationDemandeParDemandePermissionAbsence/?demandePermissionAbsence="+demandePermissionAbsence,{headers:headers});
  }

  postResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.post(url,data,{headers:headers});
  }

  updateResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.put(url,data,{headers:headers});
  }

  getSoldeAgent(agent:Agent): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/ajaxGetSoldeAgent/?agent="+agent,{headers:headers});
  }

  getNbJourNonDeductible(congesNonDeductible:CongesNonDeductible): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/ajaxGetNbJourNonDeductible/?congesNonDeductible="+congesNonDeductible,{headers:headers});
  }

  calculDate(dateDebut:Date,nbJourNonDeductible:number): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/ajaxCalculDate/?dateDebut="+dateDebut+"&nbJourNonDeductible="+nbJourNonDeductible,{headers:headers});
  }

  calculDateNbj(dateDebut:Date,dateFin:Date): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_DEMANDE_URL+"/ajaxCalculDateNbj/?dateDebut="+dateDebut+"&dateFin="+dateFin,{headers:headers});
  }

  exportPdf(id:number){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token"),'Content-Type':'application/pdf'});
    return this.http.get(API_BASE_DEMANDE_URL+"/exportPdf/"+id,{headers:headers, responseType:'blob'});
  }

  accepter(){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token"),'Content-Type':'application/pdf'});
    return this.http.get(API_BASE_DEMANDE_URL+"/accepter", {headers:headers})
  }

  refuser(){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token"),'Content-Type':'application/pdf'});
    return this.http.get(API_BASE_DEMANDE_URL+"/refuser", {headers:headers})
  }
}
