import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrandListeComponent } from './brand-liste/brand-liste.component';
import { BrandRoutingModule } from './brand-routing.module';
import { BrandCardComponent } from './brand-card/brand-card.component';
import { BrandCreatorComponent } from './brand-creator/brand-creator.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrandEditorComponent } from './brand-editor/brand-editor.component';



@NgModule({
  declarations: [
    BrandListeComponent,
    BrandCardComponent,
    BrandCreatorComponent,
    BrandEditorComponent
  ],
  imports: [
    CommonModule,
    BrandRoutingModule,
    ReactiveFormsModule
  ]
})
export class BrandModule { }
