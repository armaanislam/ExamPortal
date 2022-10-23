import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user/services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from 'sweetalert2'
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private snack: MatSnackBar
  ) {
  }

  public showPassword: boolean = false;

  signupForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$')]),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    phone: new FormControl('', [Validators.required, Validators.pattern('^\\+?(88)?0?1[23456789][0-9]{8}\\b')])
  });

  get form() {
    return this.signupForm.controls;
  }

  ngOnInit(): void {

    // this.signupForm = this.formBuilder.group({
    //   username: '',
    //   password: '',
    //   firstName: '',
    //   lastName: '',
    //   email: '',
    //   phone: '',
    // });
  }

  formSubmit() {
    // if (this.form.username.value == "" || this.form.username.value == null) {
    //   this.snack.open("Name is required", 'OK', {
    //     duration: 3000
    //   });
    //   return;
    // }
    if (this.signupForm.valid) {
      this.userService.addUser(this.signupForm.value).subscribe(
        (data: any) => {
          Swal.fire('Success', 'User registration completed with id: ' + data.id, 'success');
        },
        (error) => {
          console.log(error);
          this.snack.open("Something went wrong", 'OK', {
            duration: 3000
          });
        });
    } else {
      Swal.fire('Warning!', 'Please enter all information correctly.', 'warning');
    }


  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

}
