'use client';

import React, { useState } from 'react';
import { useGlobalContext } from '../context/GlobalContext'; // Ajuste o caminho conforme necessário
import { Ingredient } from '@/resource/recipe/ingredient.resource';
import { Tag } from '@/resource/recipe/tag.resource';

interface Filter {
  user: boolean;
  tags: Tag[];
  ingredients: Ingredient[];
  status: boolean;
}

interface FilterModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (filters: Filter) => void;
}

export default function FilterModal({
  isOpen,
  onClose,
  onSubmit,
}: FilterModalProps) {
  const { tags, ingredients } = useGlobalContext(); // Obtemos os dados diretamente do contexto
  const [userFilter, setUserFilter] = useState(false);
  const [selectedTags, setSelectedTags] = useState<Tag[]>([]);
  const [selectedIngredients, setSelectedIngredients] = useState<Ingredient[]>([]);

  if (!isOpen) return null;

  const handleSubmit = () => {
    const hasFilters = userFilter || selectedTags.length > 0 || selectedIngredients.length > 0;
    onSubmit({
      user: userFilter,
      tags: selectedTags,
      ingredients: selectedIngredients,
      status: hasFilters, // Ativo se ao menos 1 filtro for selecionado
    });
    onClose(); // Fecha o modal após o envio
  };

  const handleClearFilters = () => {
    setUserFilter(false);
    setSelectedTags([]);
    setSelectedIngredients([]);
  };

  const toggleTag = (tag: Tag) => {
    setSelectedTags((prev) =>
      prev.some((t) => t.name === tag.name)
        ? prev.filter((t) => t.name !== tag.name)
        : [...prev, tag]
    );
  };

  const toggleIngredient = (ingredient: Ingredient) => {
    setSelectedIngredients((prev) =>
      prev.some((i) => i.id === ingredient.id)
        ? prev.filter((i) => i.id !== ingredient.id)
        : [...prev, ingredient]
    );
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-brown-500/60">
      <div className="bg-cream p-6 rounded-lg shadow-lg w-11/12 max-w-md">
        <h2 className="text-lg font-title text-orange-600 text-center mb-4">
          Filtrar Receitas
        </h2>
        <div className="space-y-4">
          {/* Filtro Minhas Receitas */}
          <div className="flex items-center">
            <input
              type="checkbox"
              id="userFilter"
              checked={userFilter}
              onChange={() => setUserFilter(!userFilter)}
              className="w-4 h-4 text-orange-500 focus:ring-orange-500 border-brown-300 rounded"
            />
            <label
              htmlFor="userFilter"
              className="ml-2 text-sm font-body text-brown-500"
            >
              Minhas Receitas
            </label>
          </div>

          {/* Filtro Tags */}
          <div>
            <label className="block text-sm font-body text-brown-500 mb-2">
              Tags:
            </label>
            <div className="border border-brown-300 rounded-lg p-2 max-h-40 overflow-y-auto">
              {tags.map((tag: Tag) => (
                <div key={tag.name} className="flex items-center mb-1">
                  <input
                    type="checkbox"
                    id={`tag-${tag.name}`}
                    checked={selectedTags.some((t) => t.name === tag.name)}
                    onChange={() => toggleTag(tag)}
                    className="w-4 h-4 text-orange-500 focus:ring-orange-500 border-brown-300 rounded"
                  />
                  <label
                    htmlFor={`tag-${tag.name}`}
                    className="ml-2 text-sm font-body text-brown-500"
                  >
                    {tag.name}
                  </label>
                </div>
              ))}
            </div>
          </div>

          {/* Filtro Ingredientes */}
          <div>
            <label className="block text-sm font-body text-brown-500 mb-2">
              Ingredientes:
            </label>
            <div className="border border-brown-300 rounded-lg p-2 max-h-40 overflow-y-auto">
              {ingredients.map((ingredient: Ingredient) => (
                <div key={ingredient.id} className="flex items-center mb-1">
                  <input
                    type="checkbox"
                    id={`ingredient-${ingredient.id}`}
                    checked={selectedIngredients.some((i) => i.id === ingredient.id)}
                    onChange={() => toggleIngredient(ingredient)}
                    className="w-4 h-4 text-orange-500 focus:ring-orange-500 border-brown-300 rounded"
                  />
                  <label
                    htmlFor={`ingredient-${ingredient.id}`}
                    className="ml-2 text-sm font-body text-brown-500"
                  >
                    {ingredient.name}
                  </label>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Botões */}
        <div className="flex justify-between mt-4 space-x-4">
          <button
            type="button"
            onClick={handleClearFilters}
            className="px-4 py-2 text-sm font-body text-white bg-gray-400 rounded-lg hover:bg-gray-500 focus:outline-none"
          >
            Limpar Filtros
          </button>
          <div className="flex space-x-4">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 text-sm font-body text-white bg-brown-400 rounded-lg hover:bg-brown-500 focus:outline-none"
            >
              Cancelar
            </button>
            <button
              type="button"
              onClick={handleSubmit}
              className="px-4 py-2 text-sm font-body text-white bg-orange-500 rounded-lg hover:bg-orange-600 focus:outline-none"
            >
              Aplicar Filtros
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

