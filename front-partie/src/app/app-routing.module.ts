import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'cars',
    // lazy loading du module simpsons via la méthode loadChildren
    loadChildren: () => import('./car/car.module').then(m => m.CarModule)
  },
  {
    path: 'models',
    // lazy loading du module simpsons via la méthode loadChildren
    loadChildren: () => import('./model/model.module').then(m => m.ModelModule)
  },
  {
    path: 'brands',
    // lazy loading du module simpsons via la méthode loadChildren
    loadChildren: () => import('./brand/brand.module').then(m => m.BrandModule)
  },
  {
    path: '**',
    redirectTo: 'cars',
    pathMatch: 'full'
  }
];

@NgModule({
  // on utilise forRoot pour le routing principal de l'application
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }