import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';
import { CustomerComponent } from './customer.component';
import { CustomerRoutingModule } from './customer-routing.module';

@NgModule({
  imports: [CommonModule, FormsModule, SharedModule, CustomerRoutingModule],
  declarations: [CustomerComponent],
})
export class CustomerModule {}
