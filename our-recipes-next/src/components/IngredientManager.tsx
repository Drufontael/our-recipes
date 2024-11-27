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
    const { ingredients: loadIngredients, measurementUnits: loadMeasurementUnits } = useGlobalContext();
    const [newIngredient, setNewIngredient] = useState<string>('');
    const [newMeasurementUnit, setNewMeasurementunit] = useState<string>('');
    const [newQuantity, setNewQuantity] = useState<number>(0);
   

    function handleOnAdd(event: React.FormEvent) {
        event.preventDefault();

        const selectedIngredient = loadIngredients.find((ingredient) => ingredient.name === newIngredient);
        const selectedUnit = loadMeasurementUnits.find((unit) => unit.name === newMeasurementUnit);
    
        if (!selectedIngredient || !selectedUnit) {
            alert("Selecione um ingrediente e uma unidade de medida válidos.");
            return;
        }
        const newRecipeIngredient: RecipeIngredient = {
            ingredient: selectedIngredient,
            quantity: newQuantity,
            measurementUnit: selectedUnit,
        };
        onAdd(newRecipeIngredient);

        setNewIngredient('');
        setNewMeasurementunit('');
        setNewQuantity(0);
    }
    

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


            <div className="bg-cream p-6 rounded-lg shadow-sm">
                <form className="space-y-4">

                    <div>
                        <label htmlFor="ingredient-select" className="block text-brown-500 text-sm font-medium mb-1">
                            Ingrediente
                        </label>
                        <select
                            id="ingredient-select"
                            className="w-full border border-brown-300 rounded-md p-2"
                            value={newIngredient}
                            onChange={(e)=>setNewIngredient(e.target.value)}
                        >
                            <option value="" disabled>Selecione um ingrediente</option>
                            {loadIngredients.map((ingredient, index) => (
                                <option key={index} value={ingredient.name}>
                                    {ingredient.name}
                                </option>
                            ))}
                        </select>
                    </div>


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


                    <div>
                        <label htmlFor="unit-select" className="block text-brown-500 text-sm font-medium mb-1">
                            Unidade de Medida
                        </label>
                        <select
                            id="unit-select"
                            className="w-full border border-brown-300 rounded-md p-2"
                            onChange={(e)=>setNewMeasurementunit(e.target.value)}
                            value={newMeasurementUnit}
                        >
                            <option value="" disabled>Selecione uma unidade</option>
                            {loadMeasurementUnits.map((unit, index) => (
                                <option key={index} value={unit.name}>
                                    {unit.name}
                                </option>
                            ))}
                        </select>
                    </div>


                    <div className="text-right">
                        <button
                            className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-md font-medium"
                            onClick={handleOnAdd}
                        >
                            Adicionar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};
