import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { DataService } from '../core/data.service';
import { IClient, IInsurancePolicy } from '../shared/interfaces';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent implements OnInit {
  customer: IClient;
  policyList: IInsurancePolicy[] = [];

  constructor(
    private dataService: DataService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.getCustomer(id);

    this.dataService
      .getAllInsurancePolicy()
      .subscribe((policyList: IInsurancePolicy[]) => {
        this.policyList = policyList;
      });
  }

  getCustomer(id: string) {
    this.dataService.getClient(id).subscribe((customer: IClient) => {
      this.customer = customer;
    });
  }

  addPolicy(policyId: string) {
    this.dataService
      .addInsurancePolicyTo(this.customer.id, policyId)
      .subscribe(() => this.getCustomer(this.customer.id));
  }

  removePolicy(policyId: string) {
    this.dataService
      .removeInsurancePolicyTo(this.customer.id, policyId)
      .subscribe(() => this.getCustomer(this.customer.id));
  }

  removeClient() {
    this.dataService
      .removeClient(this.customer.id)
      .subscribe(() => this.router.navigate(['/customers']));
  }
}
