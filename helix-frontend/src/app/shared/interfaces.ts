export interface IClient {
  id: string;
  firstName: string;
  lastName: string;
  policyIds: string[];
}

export interface IInsurancePolicy {
  id: string;
  name: string;
  price: number;
}
