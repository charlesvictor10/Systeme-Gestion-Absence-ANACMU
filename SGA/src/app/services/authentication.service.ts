import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {API_BASE_URL} from '../constants/constants';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  jwt:string;
  roles:Array<string>;

  constructor(private http:HttpClient) { }

  login(data){
    return this.http.post(API_BASE_URL+"/login",data,{observe:'response'});
  }

  saveToken(jwt:string) {
    localStorage.setItem('token',jwt);
    this.jwt = jwt;
    this.parseJWT();
  }

  private parseJWT() {
    let jwtHelper = new JwtHelperService();
    let objJWT = jwtHelper.decodeToken(this.jwt);
    this.roles = objJWT.roles;
  }

  loadToken() {
    this.jwt = localStorage.getItem('token');
    this.parseJWT();
  }

  isAdmin(){
    return this.roles.indexOf('ADMIN')>=0;
  }

  isRH(){
    return this.roles.indexOf("RH")>=0;
  }

  isDG(){
    return this.roles.indexOf("DG")>=0;
  }

  isSH(){
    return this.roles.indexOf("SH")>=0;
  }

  isAgent(){
    return this.roles.indexOf("AGENT")>=0;
  }

  isAuthenticated(){
    return this.roles && (this.isAdmin() || this.isRH() || this.isDG() || this.isAgent());
  }

  currentUser(){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/currentUser",{headers:headers});
  }

  logout() {
    localStorage.removeItem('token');
    this.jwt = undefined;
    this.roles = undefined;
  }

  postResource(url,data){
    return this.http.post(url,data);
  }
}
