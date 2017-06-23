import {Ingredient} from "./ingredient.model";
/**
 * Created by flo on 17/06/2017.
 */
export class Pizza {
    id: number;
    name: string;
    description: string;
    price: number;
    ingredients: Ingredient[];
}