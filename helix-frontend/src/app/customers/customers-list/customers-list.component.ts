import { Component, OnInit, Input } from '@angular/core';

import { IClient, IInsurancePolicy } from '../../shared/interfaces';
import { SorterService } from '../../core/sorter.service';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
})
export class CustomersListComponent implements OnInit {
  private _customers: IClient[] = [];
  insurancePolicyList: IInsurancePolicy[] = [];

  @Input()
  get customers(): IClient[] {
    return this._customers;
  }

  set customers(value: IClient[]) {
    if (value) {
      this.filteredCustomers = this._customers = value;
    }
  }

  @Input()
  get policyList(): IInsurancePolicy[] {
    return this.insurancePolicyList;
  }

  set policyList(value: IInsurancePolicy[]) {
    if (value) {
      this.insurancePolicyList = value;
    }
  }

  filteredCustomers: any[] = [];

  constructor(private sorterService: SorterService) {}

  ngOnInit(): void {}

  filter(data: string) {
    if (data) {
      this.filteredCustomers = this.customers.filter((cust: IClient) => {
        return (
          cust.firstName.toLowerCase().indexOf(data.toLowerCase()) > -1 ||
          cust.lastName.toLowerCase().indexOf(data.toLowerCase()) > -1
        );
      });
    } else {
      this.filteredCustomers = this.customers;
    }
  }

  sort(prop: string) {
    this.sorterService.sort(this.filteredCustomers, prop);
  }

  getPolicy(id: string) {
    return this.insurancePolicyList.find(
      (element: IInsurancePolicy) => element.id === id
    );
  }
}
