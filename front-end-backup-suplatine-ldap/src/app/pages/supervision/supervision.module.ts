import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SupervisionRoutingModule } from './supervision-routing.module';
import {SupervisionComponent} from "./supervision.component";
import { RecherchComponent } from './recherch/recherch.component';
import {NbButtonModule, NbCalendarModule, NbCardModule, NbInputModule} from "@nebular/theme";
import {Ng2SmartTableModule} from "ng2-smart-table";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [SupervisionComponent, RecherchComponent],
  imports: [
    CommonModule,
    SupervisionRoutingModule,
    NbCardModule,
    NbButtonModule,
    NbInputModule,
    Ng2SmartTableModule,
    NbCalendarModule,
    FormsModule,
  ],
})
export class SupervisionModule { }
