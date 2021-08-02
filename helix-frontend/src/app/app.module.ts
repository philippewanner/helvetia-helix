import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { CustomersModule } from './customers/customers.module';
import { CustomerModule } from './customer/customer.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    CustomersModule,
    CustomerModule,
    SharedModule,
    CoreModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
