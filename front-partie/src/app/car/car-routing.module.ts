import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarCreatorComponent } from './car-creator/car-creator.component';
import { CarEditorComponent } from './car-editor/car-editor.component';
import { CarListeComponent } from './car-liste/car-liste.component';


const routes: Routes = [
  {
    path: '',
    component: CarListeComponent
  },
  {
    path: 'new',
    component: CarCreatorComponent
  },
  {
    path: ':id',
    component: CarEditorComponent
  }
];

@NgModule({
  // et ici forChild pour les routings secondaire
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarRoutingModule { }