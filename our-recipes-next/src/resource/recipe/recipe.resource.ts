import { RecipeIngredient } from "./recipeIngredient.resource";
import { Review } from "./review.resource";
import { Step } from "./step.resource";
import { Tag } from "./tag.resource";

export class Recipe {
    id?:number;
    name?:string;
    description?:string;
    servingSize?:number;
    preparationTime?:number;
    tags?:Tag[];
    ingredients?:RecipeIngredient[];
    steps?:Step[];
    reviews?:Review[];
    rating?:number;
    author?:string;
}