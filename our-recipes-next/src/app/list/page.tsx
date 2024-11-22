'use client'

import {Template,RecipeRow} from '@/components'
import { Recipe } from '@/resource/recipe/recipe.resource';
import { useRecipeService } from '@/resource/recipe/recipe.service'
import { RecipeSummary } from '@/resource/recipe/recipeSummary.resource';
import { useEffect, useState } from 'react';

export default function List(){

    const useService = useRecipeService();
    const [recipes,setRecipes] = useState<Recipe[]>([]);

 

    useEffect(() => {
        async function fetchRecipes() {
            try {
                const result = await useService.getRecipes();
                setRecipes(result);
                console.table(result);
            } catch (error) {
                console.error('Failed to fetch recipes:', error);
            }
        }

        fetchRecipes();
    }, []); 

    function fillRow(recipe:RecipeSummary){
        return (            
            <RecipeRow 
                key={recipe.id}
                recipe={recipe}        
                />
        );
    }

    function showRows(){
        return recipes.map(fillRow);
    }


    return(
        <Template>
            <section>
                {showRows()}
            </section>
        </Template>

    )

}