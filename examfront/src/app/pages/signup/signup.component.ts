import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from 'sweetalert2'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(
    private userService: UserService,
    private snack: MatSnackBar
  ) {
  }

  public showPassword: boolean = false;

  public user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log(this.user);
    if (this.user.username == "" || this.user.username == null) {
      this.snack.open("Name is required", 'OK', {
        duration: 3000,
        /*verticalPosition: "top",
        horizontalPosition: "right"*/
      });
      return;
    }
    // addUser:UserService
    this.userService.addUser(this.user).subscribe(
      // Success
      (data:any) => {
        console.log(data);
        /*alert("Success");*/
        Swal.fire('Success', 'User registration completed with id: '+data.id, 'success');
      },
      // Error
      (error) => {
        console.log(error);
        /*alert("Something went wrong");*/
        this.snack.open("Something went wrong", 'OK', {
          duration: 3000
        });
      }
    )

  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

}
