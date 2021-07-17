import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelListeComponent } from './model-liste/model-liste.component';


const routes: Routes = [
  {
    path: '',
    component: ModelListeComponent
  },
  {
    path: ':id',
    component: ModelListeComponent
  }
];

@NgModule({
  // et ici forChild pour les routings secondaire
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModelRoutingModule { }