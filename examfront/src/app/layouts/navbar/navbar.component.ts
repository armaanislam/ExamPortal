import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../pages/login/services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn = false;
  user = null;

  constructor(
    public login: LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.login.isloggedIn();
    this.user = this.login.getUser();
  }

  logout() {
    this.login.logout();
    this.isLoggedIn = false;
    this.user = null;
    this.router.navigate(['login']);
    // window.location.reload();
  }
}
