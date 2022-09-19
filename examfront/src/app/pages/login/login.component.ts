import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  public showPassword: boolean = false;

  ngOnInit(): void {
  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

}
