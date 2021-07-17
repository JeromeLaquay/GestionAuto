import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'car',
    // lazy loading du module simpsons via la méthode loadChildren
    loadChildren: () => import('./car/car.module').then(m => m.CarModule)
  },
  {
    path: '**',
    redirectTo: 'car',
    pathMatch: 'full'
  }
];

@NgModule({
  // on utilise forRoot pour le routing principal de l'application
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }