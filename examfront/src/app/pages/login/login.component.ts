import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from "../../services/loginService/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    username: '',
    password: ''
  }

  constructor(
    private snack: MatSnackBar,
    private loginService: LoginService
  ) {
  }

  public showPassword: boolean = false;

  ngOnInit(): void {
  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  formSubmit() {

    if (this.loginData.username.trim() == '' || this.loginData.password == null) {
      this.snack.open("Username is required", '', {
        duration: 3000
      });
      return;
    }

    if (this.loginData.password.trim() == '' || this.loginData.password == null) {
      this.snack.open("Password is required", '', {
        duration: 3000
      });
      return;
    }

    // Request server to generate token
    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log("Success");
        console.log(data);
      },
      (error) => {
        console.log("Error");
        console.log(error);
      }
    );
  }
}
