import { Model } from './model';

export interface Car {
    id: number;
    year: number;
    color: string;
    mileage: number,
    owner: string,
    modelId: number;
    model: Model;
  }
  