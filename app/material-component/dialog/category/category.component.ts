import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldAppearance } from '@angular/material/form-field';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { CategoryService } from 'src/services/category.service';
import { SnackbarService } from 'src/services/snackbar.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{

  onAddCategory =new EventEmitter();
  onEditCategory = new EventEmitter();
  categoryForm:any = FormGroup;
  dialogAction:any ="Add";
  action:any="Add";
  responseMessage: any;
  appearance: MatFormFieldAppearance = 'fill';

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData:any,
  private formBuilder:FormBuilder,
  private categoryService:CategoryService,
  public dialogRef:MatDialogRef<CategoryComponent>,
  private snackbarService:SnackbarService){}

  ngOnInit(): void {
    this.categoryForm = this.formBuilder.group({
      name:[null,[Validators.required]]
    });
    if(this.dialogData.action ==='Edit'){
      this.dialogAction="Edit";
      this.action ="Update";
      this.categoryForm.patchValue(this.dialogData.data);
    }
  }
  handleSubmit(){
    if(this.dialogAction === "Edit"){
      this.edit();
    }
    else{
      this.add();

    }
  }
 
  add(){
    var formData = this.categoryForm.value;
    var data ={
      name: formData.name
    }
    this.categoryService.add(data).subscribe((response:any)=>{
      this.dialogRef.close();
      this.onAddCategory.emit();
      this.responseMessage=response?.message;
      this.snackbarService.openSnackBar(this.responseMessage,"success");
    },(error)=>{
      this.dialogRef.close();
      console.error(error);
      if(error.error?.message){
        this.responseMessage = error.error?.message;
      }else{
        this.responseMessage=GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
    
    });
  }

  edit(){
    var formData = this.categoryForm.value;
    var data ={
      id:this.dialogData.data.id,
      name: formData.name
    }
    this.categoryService.update(data).subscribe((response:any)=>{
      this.dialogRef.close();
      this.onEditCategory.emit();
      this.responseMessage=response?.message;
      this.snackbarService.openSnackBar(this.responseMessage,"success");
    },(error)=>{
      this.dialogRef.close();
      console.error(error);
      if(error.error?.message){
        this.responseMessage = error.error?.message;
      }else{
        this.responseMessage=GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage,GlobalConstants.error);
    
    });
  }
}