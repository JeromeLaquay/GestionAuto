import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BrandService } from 'src/app/services/brand.service';
import { Brand } from '../../interfaces/brand';
import { FormGroup,  FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-brand-creator',
  templateUrl: './brand-creator.component.html',
  styleUrls: ['./brand-creator.component.css']
})
export class BrandCreatorComponent implements OnInit {

  public brand: Brand;
  public formGroup: FormGroup;
  constructor(private brandService: BrandService, private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      logo: new FormControl('', Validators.required)
    });  
  }

  onSubmit(){
    console.log(this.formGroup.value);
    this.brandService.create(this.formGroup.value)
    .subscribe(
      (brandData: Brand) => {
        this.router.navigate(['brands']);
      },
      (error) => {
        console.log('erreur : ' + error)
      }
    )
  }
}
