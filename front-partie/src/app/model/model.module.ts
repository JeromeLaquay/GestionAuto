import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModelListeComponent } from './model-liste/model-liste.component';
import { ModelRoutingModule } from './model-routing.module';
import { ModelCardComponent } from './model-card/model-card.component';
import { ModelCreatorComponent } from './model-creator/model-creator.component';
import { ModelEditorComponent } from './model-editor/model-editor.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ModelListeComponent,
    ModelCardComponent,
    ModelCreatorComponent,
    ModelEditorComponent
  ],
  imports: [
    CommonModule,
    ModelRoutingModule,
    ReactiveFormsModule
  ]
})
export class ModelModule { }
