import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModelListeComponent } from './model-liste/model-liste.component';
import { ModelRoutingModule } from './model-routing.module';
import { ModelCardComponent } from './model-card/model-card.component';



@NgModule({
  declarations: [
    ModelListeComponent,
    ModelCardComponent
  ],
  imports: [
    CommonModule,
    ModelRoutingModule
  ]
})
export class ModelModule { }
