import { Component, OnInit } from '@angular/core';
import { DataService } from '../core/data.service';
import { IClient, IInsurancePolicy } from '../shared/interfaces';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
})
export class CustomersComponent implements OnInit {
  title: string;
  people: IClient[];
  insurancePolicyList: IInsurancePolicy[];

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    this.title = 'Customers';
    this.getListPolicy();
    this.getClients();
  }

  getClients() {
    this.dataService
      .getClients()
      .subscribe((customers: IClient[]) => (this.people = customers));
  }

  getListPolicy() {
    this.dataService
      .getAllInsurancePolicy()
      .subscribe(
        (policies: IInsurancePolicy[]) => (this.insurancePolicyList = policies)
      );
  }

  addCustomer(customer: { firstName: string; lastName: string }) {
    this.dataService
      .addClient(customer.firstName, customer.lastName)
      .subscribe(() => this.getClients());
  }

  addInsurancePolicy(insurancePolicy: { name: string; price: string }) {
    this.dataService
      .addInsurancePolicy(insurancePolicy.name, +insurancePolicy.price)
      .subscribe(() => this.getClients());
  }
}
