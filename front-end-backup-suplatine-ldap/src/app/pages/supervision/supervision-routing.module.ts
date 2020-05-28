import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SupervisionComponent} from "./supervision.component";
import {RecherchComponent} from "./recherch/recherch.component";

const routes: Routes = [
  {
    path: '',
    component: SupervisionComponent,
    children: [{
      path: 'recherch',
      component: RecherchComponent,
    }],
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SupervisionRoutingModule { }
