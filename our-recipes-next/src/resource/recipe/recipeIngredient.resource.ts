import { Ingredient } from "./ingredient.resource";
import { MeasurementUnit } from "./measurementUnit.resource";

export class RecipeIngredient{
    ingredient?:Ingredient;
    quantity?:number;
    measurementUnit?:MeasurementUnit;
}