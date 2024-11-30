'use client'

import { Template,IngredientManager,StepsManager,TagManager  } from "@/components";
import { Recipe } from "@/resource/recipe/recipe.resource";
import { useRecipeService } from "@/resource/recipe/recipe.service";
import { RecipeIngredient } from "@/resource/recipe/recipeIngredient.resource";
import { Step } from "@/resource/recipe/step.resource";
import { Tag } from "@/resource/recipe/tag.resource";
import { useParams, useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

export default function EditRecipe() {

    const { id }=useParams();
    const router =useRouter();    

    const recipeService=useRecipeService();
    const [recipe, setRecipe] = useState<Recipe>({
        id: 1,
        name: "Nome da Receita",
        description: "Lorem ipsum dolor sit amet, consectetur adipisicing elit.",
        preparationTime: 30,
        servingSize: 4,
        tags: [],
        ingredients: [],
        steps: [],
    });



    
    async function fetchRecipe() {
        try {
            const result=await recipeService.getRecipeById(id);
            if(result){
                setRecipe(result);                    
            }
        } catch (error) {
            console.error('Failed to fetch recipes:', error);
        }
    }
    
    
    useEffect(() => {       

        fetchRecipe();
    }, []); 

    // Atualizar valores dos inputs controlados
    function handleInputChange(event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) {
        const { name, value } = event.target;
        setRecipe((prev) => ({ ...prev, [name]: value }));
    }

    async function handleSubmit(event:React.FormEvent) {
        event.preventDefault();
        const sucess=await recipeService.updateRecipe(id,recipe);
        if(sucess){
            router.push(`/list`);
        }        
    }

    async function handleOnAddTag(tag:Tag) {
        const sucess=await recipeService.addTag(tag,id);
        if(sucess){
            fetchRecipe();
        }

    }

    async function handleOnDeleteTag(tag:Tag){
        const sucess=await recipeService.deleteTag(tag,id)
        if(sucess){
            fetchRecipe();
        }
    }

    async function handleOnaddIngredient(ingredient:RecipeIngredient) {
        const sucess=await recipeService.addIngredient(ingredient,id);
        if(sucess){
            fetchRecipe();
        }        
    }

    async function handleOnDeleteIngredient(ingredient:RecipeIngredient) {
        const sucess=await recipeService.deleteIngredient(ingredient,id);
        if(sucess){
            fetchRecipe();
        }  
        
    }

    async function handleOnAddStep(step:Step) {
        const sucess=await recipeService.addStep(step,id);
        if(sucess){
            fetchRecipe();
        }  
        
    }

    async function handleOnDeleteStep(step:Step) {
        const sucess=await recipeService.deleteStep(step,id);
        if(sucess){
            fetchRecipe();
        }          
    }


   


    return (
        <Template
            headerActions={{
                listRecipes:true,
            }}
        >
            <div className="min-h-screen bg-cream p-8">
                <div className="max-w-5xl mx-auto bg-white p-8 rounded-lg shadow-lg">
                    <h1 className="text-4xl font-bold text-orange-600 mb-6 text-center">
                        Editar Receita
                    </h1>
                    <form className="space-y-8" >
                        
                        <div>
                            <label htmlFor="name" className="block text-brown-600 font-medium mb-2">
                                Nome da Receita:
                            </label>
                            <input
                                type="text"
                                name="name"
                                value={recipe.name}
                                onChange={handleInputChange}
                                className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                            />
                        </div>
                        <div>
                            <label htmlFor="description" className="block text-brown-600 font-medium mb-2">
                                Descrição:
                            </label>
                            <textarea
                                name="description"
                                value={recipe.description}
                                onChange={handleInputChange}
                                className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                                rows={4}
                            ></textarea>
                        </div>

                        {/* Tempo de preparo e porções */}
                        <div className="grid grid-cols-2 gap-4">
                            <div>
                                <label htmlFor="preparationTime" className="block text-brown-600 font-medium mb-2">
                                    Tempo de Preparo (min):
                                </label>
                                <input
                                    type="number"
                                    name="preparationTime"
                                    value={recipe.preparationTime}
                                    onChange={handleInputChange}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                                />
                            </div>
                            <div>
                                <label htmlFor="servingSize" className="block text-brown-600 font-medium mb-2">
                                    Porções:
                                </label>
                                <input
                                    type="number"
                                    name="servingSize"
                                    value={recipe.servingSize}
                                    onChange={handleInputChange}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                                />
                            </div>
                        </div>
                    </form>

                        {/* Gerenciadores */}
                        <div>
                            <h2 className="text-xl font-semibold text-brown-700 mb-4 mt-4">Tags</h2>
                            <TagManager
                                tags={recipe.tags || []}
                                onAddTag={handleOnAddTag}
                                onDeleteTag={handleOnDeleteTag}
                            />
                        </div>
                        <div>
                            <h2 className="text-xl font-semibold text-brown-700 mb-4 mt-4">Ingredientes</h2>
                            <IngredientManager
                                ingredients={recipe.ingredients || []}
                                onAdd={handleOnaddIngredient}
                                onDelete={handleOnDeleteIngredient}
                            />
                        </div>
                        <div>
                            <h2 className="text-xl font-semibold text-brown-700 mb-4 mt-4">Passos</h2>
                            <StepsManager
                                steps={recipe.steps || []}
                                onAdd={handleOnAddStep}
                                onDelete={handleOnDeleteStep}
                            />
                        </div>

                        {/* Botão de salvar */}
                        <button
                            type="submit"
                            className="w-full bg-green-600 text-white py-3 rounded-lg font-bold hover:bg-green-700 shadow-md"
                            onClick={handleSubmit}
                        >
                            Salvar Alterações
                        </button>
                </div>
            </div>
        </Template>
    );
}
