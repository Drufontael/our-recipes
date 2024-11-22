'use client';

import { Template } from "@/components";
import { useState } from "react";

export default function CreateRecipe() {
  const [message, setMessage] = useState<string | null>(null);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Lógica para salvar a receita (ex.: envio para API)
    console.log("Receita criada!");
    setMessage("Receita criada com sucesso!");
  };

  return (
    <Template>
      <div className="min-h-screen bg-cream p-8">
        <div className="max-w-2xl mx-auto bg-white p-6 rounded-lg shadow-md">
          <h1 className="text-3xl font-bold text-orange-600 mb-6 text-center">
            Criar Receita
          </h1>
          <form onSubmit={handleSubmit} className="space-y-6">
            {/* Nome */}
            <div>
              <label
                htmlFor="name"
                className="block text-brown-600 font-medium mb-2"
              >
                Nome:
              </label>
              <input
                type="text"
                id="name"
                name="name"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
                required
              />
            </div>

            {/* Descrição */}
            <div>
              <label
                htmlFor="description"
                className="block text-brown-600 font-medium mb-2"
              >
                Descrição:
              </label>
              <textarea
                id="description"
                name="description"
                rows={4}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
                required
              />
            </div>

            {/* Tempo de Preparo */}
            <div>
              <label
                htmlFor="prepTime"
                className="block text-brown-600 font-medium mb-2"
              >
                Tempo de Preparo (min):
              </label>
              <input
                type="number"
                id="prepTime"
                name="prepTime"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
                required
              />
            </div>

            {/* Porções */}
            <div>
              <label
                htmlFor="servings"
                className="block text-brown-600 font-medium mb-2"
              >
                Porções:
              </label>
              <input
                type="number"
                id="servings"
                name="servings"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
                required
              />
            </div>

            {/* Botão de envio */}
            <button
              type="submit"
              className="w-full bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded-lg transition"
            >
              Salvar Receita
            </button>
          </form>

          {/* Mensagem de sucesso */}
          {message && (
            <div className="mt-6 text-green-600 font-medium text-center">
              {message}
            </div>
          )}
        </div>
      </div>
    </Template>
  );
}