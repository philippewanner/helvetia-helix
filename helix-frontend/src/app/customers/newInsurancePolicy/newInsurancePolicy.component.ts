import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-new-insurance-policy',
  templateUrl: './newInsurancePolicy.component.html',
})
export class NewInsurancePolicyComponent {
  private _name: string;
  private _price: string;

  @Input()
  get name() {
    return this._name;
  }

  set name(val: string) {
    this._name = val;
  }

  get price() {
    return this._price;
  }

  set price(val: string) {
    this._price = val;
  }

  @Output() newItemEvent = new EventEmitter<{
    name: string;
    price: string;
  }>();

  createNewInsurancePolicy() {
    this.newItemEvent.emit({
      name: this._name,
      price: this._price,
    });
    this._name = '';
    this._price = '';
  }
}
