'use client'


import { Recipe } from '@/resource/recipe/recipe.resource';

import { useUserService } from '@/resource/user/user.service';
import { RecipeSummary } from './recipeSummary.resource';
import { Tag } from './tag.resource';
import { RecipeIngredient } from './recipeIngredient.resource';
import { Step } from './step.resource';
import { Review } from './review.resource';
import { useRouter } from 'next/navigation';


class RecipeService{
    userService= useUserService();
    baseUrl: string = process.env.NEXT_PUBLIC_API_URL+'/v1/api/recipes';
    token:string | null;
    router=useRouter();


    public constructor(){
        this.token = this.userService?.getToken() || '';

    }

    handleExpiredToken = (error:any) =>{
        if(error.message==='JWT token expired'){
            this.userService.closeSession();
            alert('Sessão expirada refaça o login!')
            this.router.push('/login')
        }

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
                console.error('Erro da API:', errorDetails);
                this.handleExpiredToken(errorDetails);
                throw new Error(
                    `Erro ao buscar receitas: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }        
        
    }

    async getFilteredRecipes(filter:any) : Promise<RecipeSummary[]> {
        console.log('Filtros enviados: ',filter)
        const filters={
            filters:{
                author:filter.author,
                tags:filter.tags,
                ingredients:filter.ingredients,
            }
        }
        const body=JSON.stringify(filters);
        console.log(body);
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response = await fetch(`${this.baseUrl}/filter`, {
                method: 'POST', // Método HTTP
                headers:headers,
                body:body,
            });

            if (!response.ok) {
                const errorDetails = await response.json();
                this.handleExpiredToken(errorDetails);
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao buscar receitas: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
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
                this.handleExpiredToken(errorDetails);
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao buscar receitas: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
        return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
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
                this.handleExpiredToken(errorDetails);
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao buscar receitas: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }  
    }

    async updateRecipe(id:any,recipe:Recipe):Promise<Recipe>{
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response = await fetch(`${this.baseUrl}/${id}`, {
            method: 'PUT', // Método HTTP
            headers:headers,
            body:JSON.stringify(recipe),
            });

            if (!response.ok) {
                const errorDetails = await response.json();
                this.handleExpiredToken(errorDetails);
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao alterar receita: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
        return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
    }

    async deleteRecipe(id:any):Promise<void>{
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            await fetch(`${this.baseUrl}/${id}`, {
            method: 'DELETE', // Método HTTP
            headers:headers,
            });

        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
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
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro adicionar tag: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
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
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro adicionar tag: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async addIngredient(ingredient:RecipeIngredient,id:any) {
        const request={
            ingredientId:ingredient.ingredient?.id,
            measurementUnitId:ingredient.measurementUnit?.id,
            quantity:ingredient.quantity
        }
        console.log(request);
        console.log(id);
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/ingredients`,
                    {
                        method:'POST',
                        headers:headers,
                        body:JSON.stringify(request),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro adicionar ingredient: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async deleteIngredient(ingredient:RecipeIngredient,id:any) {
        const request={
            ingredientId:ingredient.ingredient?.id,
            measurementUnitId:ingredient.measurementUnit?.id,
            quantity:ingredient.quantity
        }
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/ingredients`,
                    {
                        method:'DELETE',
                        headers:headers,
                        body:JSON.stringify(request),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao deletar ingrediente: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async addStep(step:Step,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/steps`,
                    {
                        method:'POST',
                        headers:headers,
                        body:JSON.stringify(step),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro adicionar passo: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async deleteStep(step:Step,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/steps`,
                    {
                        method:'DELETE',
                        headers:headers,
                        body:JSON.stringify(step),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro adicionar tag: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async addReview(review:Review,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/reviews`,
                    {
                        method:'POST',
                        headers:headers,
                        body:JSON.stringify(review),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro adicionar avaliação: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async updateReview(review:Review,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/reviews`,
                    {
                        method:'PUT',
                        headers:headers,
                        body:JSON.stringify(review),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao editar avaliação: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async deleteReview(review:Review,id:any) {
        try{
            const headers: HeadersInit = {
                'Content-Type': 'application/json',
            };
            if (this.token) {
                headers['Authorization'] = `Bearer ${this.token}`;
            }
            const response=await fetch(
                `${this.baseUrl}/${id}/reviews`,
                    {
                        method:'DELETE',
                        headers:headers,
                        body:JSON.stringify(review),
                    }
            )
            if (!response.ok) {
                const errorDetails = await response.json();
                console.error('Erro da API:', errorDetails);
                throw new Error(
                    `Erro ao deletar avaliação: ${errorDetails.message} (Status: ${errorDetails.status})`
                );
            }
            return await response.json();
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
        
    }

    async getAuthor(id:any):Promise<string>{
        try{
            const recipe= await this.getRecipeById(id);
            if(recipe){
                return recipe.author?recipe.author:'';
            }
            return '';
        }  catch (error) {
            console.error('Erro ao se comunicar com o servidor:', error);
            throw error;
        }
       
    }    
        

}

export const useRecipeService = () => new RecipeService;