import { useUserService } from '@/resource/user/user.service';
import { Recipe } from "./recipe.resource";
import { RecipeSummary } from "./recipeSummary.resource";
import { Tag } from './tag.resource';


class RecipeService{
    userService= useUserService();
    baseUrl: string = 'http://localhost:8080/v1/api/recipes';
    token:string | null;


    public constructor(){
        this.token = this.userService?.getToken() || '';

    }

    async getRecipes() : Promise<RecipeSummary[]> {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response = await fetch(this.baseUrl, {
                method: 'GET', // Método HTTP
                headers:headers
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
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response = await fetch(`${this.baseUrl}/${id}`, {
            method: 'GET', // Método HTTP
            headers:headers
            });

            if (!response.ok) {
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

    async createRecipe(
                name?: string,
                description?: string,
                servingSize?: number,
                preparationTime?: number 
                ):Promise<Recipe>{
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const difficulty='BEGINNER';
            const response = await fetch(
                `${this.baseUrl}`,
                {
                    method:'POST',
                    headers:headers,
                    body:JSON.stringify({name,description,servingSize,preparationTime,difficulty})
                }
            )
            if (!response.ok) {
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

    async addTag(tag:Tag,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/tags`,
                    {
                        method:'POST',
                        headers:headers,
                        body:JSON.stringify(tag),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error("Erro da API:", errorDetails);
                throw new Error(
                    `Erro adicionar tag: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error("Erro ao se comunicar com o servidor:", error);
            throw error;
        }
        
    }

    async deleteTag(tag:Tag,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/tags`,
                    {
                        method:'DELETE',
                        headers:headers,
                        body:JSON.stringify(tag),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error("Erro da API:", errorDetails);
                throw new Error(
                    `Erro adicionar tag: ${errorDetails.message} (Status: ${errorDetails.status})`
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