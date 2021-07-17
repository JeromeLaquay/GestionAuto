import { Model } from './model';

export interface Car {
    id: number;
    year: number;
    color: string;
    modelId: number;
    imagePath: string;
    model: Model;
  }
  