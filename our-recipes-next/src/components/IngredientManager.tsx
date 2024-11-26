import { useGlobalContext } from "@/context/GlobalContext";
import { Ingredient } from "@/resource/recipe/ingredient.resource";
import { MeasurementUnit } from "@/resource/recipe/measurementUnit.resource";
import { RecipeIngredient } from "@/resource/recipe/recipeIngredient.resource";
import { useState } from "react";

interface IngredientManagerProps {
    ingredients: RecipeIngredient[];
    onDelete: (ingredient: RecipeIngredient) => void;
    onAdd: (ingredient: RecipeIngredient) => void;
}

export const IngredientManager: React.FC<IngredientManagerProps> = ({
    ingredients,
    onDelete,
    onAdd,
}) => {
    const { ingredients: loadIngredients } = useGlobalContext();
    const { measurementUnits: loadMeasurementUnits } = useGlobalContext();
    const [newIngredient, setNewIngredient] = useState<Ingredient>({ name: '' });
    const [newMeasurementUnit, setNewMeasurementunit] = useState<MeasurementUnit>({ name: '' });
    const [newQuantity, setNewQuantity] = useState<number>(0);

    return (
        <div className="bg-beige p-6 rounded-lg shadow-md">
            {/* Ingredients List */}
            <div className="mb-6">
                <ul>
                    {ingredients.map((ingredient, index) => (
                        <li
                            key={index}
                            className="flex items-center justify-between p-4 mb-4 bg-cream rounded-lg shadow-sm"
                        >
                            <div className="flex flex-col">
                                <span className="text-lg font-medium text-brown-500">
                                    {ingredient.ingredient?.name}
                                </span>
                                <span className="text-sm text-brown-400">
                                    {ingredient.quantity} {ingredient.measurementUnit?.abbreviation}
                                </span>
                            </div>
                            <button
                                className="bg-orange-500 hover:bg-orange-600 text-white px-3 py-1 rounded-full text-sm"
                                onClick={() => onDelete(ingredient)}
                            >
                                Remover
                            </button>
                        </li>
                    ))}
                </ul>
            </div>

            {/* Add Ingredient Form */}
            <div className="bg-cream p-6 rounded-lg shadow-sm">
                <form className="space-y-4">
                    {/* Ingredient Select */}
                    <div>
                        <label htmlFor="ingredient-select" className="block text-brown-500 text-sm font-medium mb-1">
                            Ingrediente
                        </label>
                        <select
                            id="ingredient-select"
                            className="w-full border border-brown-300 rounded-md p-2"
                        >
                            {loadIngredients.map((ingredient, index) => (
                                <option key={index} value={ingredient.name}>
                                    {ingredient.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Quantity Input */}
                    <div>
                        <label htmlFor="quantity" className="block text-brown-500 text-sm font-medium mb-1">
                            Quantidade
                        </label>
                        <input
                            type="number"
                            id="quantity"
                            className="w-full border border-brown-300 rounded-md p-2"
                            value={newQuantity}
                            onChange={(e) => setNewQuantity(Number(e.target.value))}
                        />
                    </div>

                    {/* Measurement Unit Select */}
                    <div>
                        <label htmlFor="unit-select" className="block text-brown-500 text-sm font-medium mb-1">
                            Unidade de Medida
                        </label>
                        <select
                            id="unit-select"
                            className="w-full border border-brown-300 rounded-md p-2"
                        >
                            {loadMeasurementUnits.map((unit, index) => (
                                <option key={index} value={unit.name}>
                                    {unit.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Add Button */}
                    <div className="text-right">
                        <button
                            className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-md font-medium"
                            onClick={(e) => {
                                e.preventDefault();
                                onAdd({
                                    ingredient: newIngredient,
                                    quantity: newQuantity,
                                    measurementUnit: newMeasurementUnit,
                                });
                            }}
                        >
                            Adicionar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};
