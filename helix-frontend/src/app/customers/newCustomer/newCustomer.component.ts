import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-new-customer',
  templateUrl: './newCustomer.component.html',
})
export class NewCustomerComponent {
  private _firstName: string;
  private _lastName: string;

  @Input()
  get firstName() {
    return this._firstName;
  }

  set firstName(val: string) {
    this._firstName = val;
  }

  get lastName() {
    return this._lastName;
  }

  set lastName(val: string) {
    this._lastName = val;
  }

  @Output() newItemEvent = new EventEmitter<{
    firstName: string;
    lastName: string;
  }>();

  createNewCustomer() {
    this.newItemEvent.emit({
      firstName: this._firstName,
      lastName: this._lastName,
    });
    this._firstName = '';
    this._lastName = '';
  }
}
