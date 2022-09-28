import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient
  ) { }

  // Get currently logged-in user
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }

  // Generate Token
  public generateToken(loginData: any) {
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  // Login user: set token in LocalStorage
  public loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  // isLogin: user is logged in or not
  public isloggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }

  // Logout: remove token from local storage
  public logout() {
    localStorage.removeItem('token');
    return true;
  }

  // Get token
  public getToken() {
    return localStorage.getItem('token');
  }

  // Set user details
  public setUser(user: any) {
    return localStorage.setItem('user', JSON.stringify(user));
  }

  // Get user details
  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

  // Get user role
  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }

}
