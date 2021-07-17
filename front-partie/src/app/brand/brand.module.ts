import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrandListeComponent } from './brand-liste/brand-liste.component';
import { BrandRoutingModule } from './brand-routing.module';
import { BrandCardComponent } from './brand-card/brand-card.component';



@NgModule({
  declarations: [
    BrandListeComponent,
    BrandCardComponent
  ],
  imports: [
    CommonModule,
    BrandRoutingModule
  ]
})
export class BrandModule { }
