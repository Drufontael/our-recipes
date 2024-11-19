import { RecipeSummary } from "./recipeSummary.resource";


class RecipeService{
    baseUrl: string = 'http://localhost:8080/v1/api/recipes';
    token:string = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YWRvciIsImlhdCI6MTczMjAzNTM0NSwiZXhwIjoxNzMyMDM3MTQ1LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXX0.XkcmKhbOcQJQnkpXVfHY_g2mDNXilxLiPlvbBJiV9l4';

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
}

export const useRecipeService = () => new RecipeService;