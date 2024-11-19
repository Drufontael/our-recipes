import { Tag } from "./tag.resource";

export class RecipeSummary{
    id?:number;
    name?:string;
    description?:string;
    rating?:number;
    tags?:Tag[];
}