import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelCreatorComponent } from './model-creator/model-creator.component';
import { ModelEditorComponent } from './model-editor/model-editor.component';
import { ModelListeComponent } from './model-liste/model-liste.component';


const routes: Routes = [
  {
    path: '',
    component: ModelListeComponent
  },
  {
    path: 'new',
    component: ModelCreatorComponent
  },
  {
    path: ':id',
    component: ModelEditorComponent
  }
];

@NgModule({
  // et ici forChild pour les routings secondaire
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModelRoutingModule { }