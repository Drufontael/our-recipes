'use client';

import { Template } from "@/components";
import { Recipe } from "@/resource/recipe/recipe.resource";
import React, { useState } from "react";

export default function EditRecipe() {
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

    const [newTag, setNewTag] = useState("");
    const [newIngredient, setNewIngredient] = useState("");
    const [newStep, setNewStep] = useState("");

    // Atualizar valores dos inputs controlados
    function handleInputChange(event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) {
        const { name, value } = event.target;
        setRecipe((prev) => ({ ...prev, [name]: value }));
    }

    // Adicionar uma nova tag
    function handleAddTag() {
        setRecipe((prev) => ({
            ...prev,
            tags: [...(prev.tags || []), { name: newTag }],
        }));
        setNewTag("");
    }

    // Adicionar um novo ingrediente
    function handleAddIngredient() {
        setRecipe((prev) => ({
            ...prev,
            ingredients: [
                ...(prev.ingredients || []),
                { ingredient: { name: newIngredient }, quantity: 1, measurementUnit: { abbreviation: "un" } },
            ],
        }));
        setNewIngredient("");
    }

    // Adicionar uma nova etapa
    function handleAddStep() {
        setRecipe((prev) => ({
            ...prev,
            steps: [
                ...(prev.steps || []),
                { stepNumber: (prev.steps?.length || 0) + 1, description: newStep },
            ],
        }));
        setNewStep("");
    }

    // Renderização das listas
    function renderTags() {
        return recipe.tags?.map((tag, index) => (
            <span key={index} className="bg-orange-200 text-orange-700 px-2 py-1 rounded-full text-sm mr-2">
                {tag.name}
            </span>
        ));
    }

    function renderIngredients() {
        return recipe.ingredients?.map((ingredient, index) => (
            <li key={index} className="text-brown-600">
                {ingredient.ingredient?.name} {ingredient.quantity} {ingredient.measurementUnit?.abbreviation}
            </li>
        ));
    }

    function renderSteps() {
        return recipe.steps?.map((step) => (
            <li key={step.stepNumber} className="text-brown-600">
                {step.stepNumber}. {step.description}
            </li>
        ));
    }

    return (
        <Template>
            <div className="min-h-screen bg-cream p-8">
                <div className="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-md">
                    <h1 className="text-3xl font-bold text-orange-600 mb-6 text-center">Editar Receita</h1>
                    <form className="space-y-6">
                        {/* Nome */}
                        <div>
                            <label htmlFor="name" className="block text-brown-600 font-medium mb-2">
                                Nome da Receita:
                            </label>
                            <input
                                type="text"
                                name="name"
                                value={recipe.name}
                                onChange={handleInputChange}
                                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                            />
                        </div>

                        {/* Descrição */}
                        <div>
                            <label htmlFor="description" className="block text-brown-600 font-medium mb-2">
                                Descrição:
                            </label>
                            <textarea
                                name="description"
                                value={recipe.description}
                                onChange={handleInputChange}
                                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
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
                                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
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
                                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                                />
                            </div>
                        </div>

                        {/* Tags */}
                        <div>
                            <label className="block text-brown-600 font-medium mb-2">Tags:</label>
                            <div className="flex items-center space-x-4 mb-4">
                                {renderTags()}
                                <input
                                    type="text"
                                    value={newTag}
                                    onChange={(e) => setNewTag(e.target.value)}
                                    className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                                    placeholder="Nova tag"
                                />
                                <button
                                    type="button"
                                    onClick={handleAddTag}
                                    className="bg-orange-500 text-white px-4 py-2 rounded-lg hover:bg-orange-600"
                                >
                                    Adicionar
                                </button>
                            </div>
                        </div>

                        {/* Ingredientes */}
                        <div>
                            <label className="block text-brown-600 font-medium mb-2">Ingredientes:</label>
                            <ul className="list-disc pl-5 space-y-2">{renderIngredients()}</ul>
                            <div className="flex items-center space-x-4 mt-4">
                                <input
                                    type="text"
                                    value={newIngredient}
                                    onChange={(e) => setNewIngredient(e.target.value)}
                                    className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                                    placeholder="Novo ingrediente"
                                />
                                <button
                                    type="button"
                                    onClick={handleAddIngredient}
                                    className="bg-orange-500 text-white px-4 py-2 rounded-lg hover:bg-orange-600"
                                >
                                    Adicionar
                                </button>
                            </div>
                        </div>

                        {/* Etapas */}
                        <div>
                            <label className="block text-brown-600 font-medium mb-2">Etapas:</label>
                            <ol className="list-decimal pl-5 space-y-2">{renderSteps()}</ol>
                            <div className="flex items-center space-x-4 mt-4">
                                <input
                                    type="text"
                                    value={newStep}
                                    onChange={(e) => setNewStep(e.target.value)}
                                    className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                                    placeholder="Nova etapa"
                                />
                                <button
                                    type="button"
                                    onClick={handleAddStep}
                                    className="bg-orange-500 text-white px-4 py-2 rounded-lg hover:bg-orange-600"
                                >
                                    Adicionar
                                </button>
                            </div>
                        </div>

                        {/* Botão de salvar */}
                        <button
                            type="submit"
                            className="w-full bg-green-500 text-white py-2 px-4 rounded-lg font-bold hover:bg-green-600"
                        >
                            Salvar Alterações
                        </button>
                    </form>
                </div>
            </div>
        </Template>
    );
}
