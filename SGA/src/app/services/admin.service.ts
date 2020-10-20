import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_ADMIN_URL} from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  getUtilisateurList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ADMIN_URL+"/utilisateurs",{headers:headers});
  }

  getUtilisateur(id:number): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ADMIN_URL+"/utilisateurs/"+id,{headers:headers});
  }

  getRoleList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ADMIN_URL+"/roles",{headers:headers});
  }

  postResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.post(url,data,{headers:headers});
  }

  updateResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.put(url,data,{headers:headers});
  }

  deleteResource(url){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.delete(url,{headers:headers});
  }
}
