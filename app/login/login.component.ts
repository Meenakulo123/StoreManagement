import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldAppearance } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/services/snackbar.service';
import { UserService } from 'src/services/user.service';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm:any=FormGroup;
  responseMessage:any;
  appearance: MatFormFieldAppearance = 'fill';
  
  

  constructor(private formBuilder:FormBuilder,
    private router:Router,
    private userService:UserService,
    private snackbarService:SnackbarService,
    public dialogRef:MatDialogRef<LoginComponent>,
    private ngxService:NgxUiLoaderService    ){}

    ngOnInit(): void {
      this.loginForm=this.formBuilder.group({
        email:[null,[Validators.required,Validators.pattern(GlobalConstants.emailRegex)]],
        password:[null,[Validators.required]]
      });
}

togglePasswordVisibility() {
  this.hide = !this.hide;
}

handleSubmit(){
  this.ngxService.start();
  var formData = this.loginForm.value;
  var data ={
    email:formData.email,
    password:formData.password

}
this.userService.login(data).subscribe((response:any)=>{
  this.ngxService.stop();
  this.dialogRef.close();
  localStorage.setItem('token',response.token);
  this.router.navigate(['/super_market/dashboard']);
},(error)=>{
  this.ngxService.stop();
  if(error.error?.message){
    this.responseMessage = error.error?.message;
  }else{
    this.responseMessage=GlobalConstants.genericError;
  }
  this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error); 
});


}

}
