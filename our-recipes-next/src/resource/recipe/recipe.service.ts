import { Recipe } from "./recipe.resource";
import { RecipeSummary } from "./recipeSummary.resource";


class RecipeService{
    baseUrl: string = 'http://localhost:8080/v1/api/recipes';
    token:string = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YWRvciIsImlhdCI6MTczMjMwODU5OCwiZXhwIjoxNzMyMzE3Mzk4LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXX0.fA-rSV0H0JAW1AT5KfmhjK3It8e1LP7zgfBeZ70Q64Y';

    async getRecipes() : Promise<RecipeSummary[]> {
        try{
                const response = await fetch(this.baseUrl, {
                method: 'GET', // Método HTTP
                headers: {
                    'Content-Type': 'application/json', // Tipo de conteúdo
                    'Authorization': this.token // Enviando o token no cabeçalho
                }
                });

            if (!response.ok) {
                // Tentando obter detalhes do erro
                const errorDetails = await response.json();
                console.error("Erro da API:", errorDetails);
                throw new Error(
                    `Erro ao buscar receitas: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error("Erro ao se comunicar com o servidor:", error);
            throw error;
        }        
        
    }

    async getRecipeById(id:any):Promise<Recipe>{
        try{
            const response = await fetch(`${this.baseUrl}/${id}`, {
            method: 'GET', // Método HTTP
            headers: {
                'Content-Type': 'application/json', // Tipo de conteúdo
                'Authorization': this.token // Enviando o token no cabeçalho
            }
            });

        if (!response.ok) {
            // Tentando obter detalhes do erro
            const errorDetails = await response.json();
            console.error("Erro da API:", errorDetails);
            throw new Error(
                `Erro ao buscar receitas: ${errorDetails.message} (Status: ${errorDetails.status})`
            );
        }
        return await response.json();
    }  catch (error) {
        console.error("Erro ao se comunicar com o servidor:", error);
        throw error;
    }
    }
}

export const useRecipeService = () => new RecipeService;