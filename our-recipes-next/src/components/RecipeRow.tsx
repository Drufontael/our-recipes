'use client';

import { useRouter } from "next/navigation";
import { RecipeSummary } from "@/resource/recipe/recipeSummary.resource";
import { FaEye, FaEdit, FaTrash } from "react-icons/fa";
import { useUserService } from "@/resource/user/user.service";
import { useRecipeService } from "@/resource/recipe/recipe.service";
import { useEffect, useState } from "react";

interface RecipeRowProps {
  recipe: RecipeSummary;
  onDelete: (id: any) => void;
}

export const RecipeRow: React.FC<RecipeRowProps> = ({ recipe, onDelete }: RecipeRowProps) => {
  const router = useRouter();
  const auth=useUserService();
  const recipeService =useRecipeService();
  const [hideButton,setHideButton] = useState<boolean>(false);

  const preventEdit = async() =>{
    try{
      const author=await recipeService.getAuthor(recipe.id)
      if(author){
        setHideButton(auth.getUser()!==author);
      }
    }catch(error){};
    return false    
  }

  useEffect(() => {
    preventEdit();
  }, []);

  const handleView = () => {
    router?.push(`/recipe/${recipe.id}`);
  };

  const handleEdit = () => {
    router?.push(`/recipe/${recipe.id}/edit`);
  };

  const handleDelete = (event: React.MouseEvent) => {
    event.preventDefault();
    if (confirm("Tem certeza de que deseja deletar esta receita?")) {
      event.defaultPrevented;
      onDelete(recipe.id);
    }
  };

  return (
    <div className="container mx-auto mt-4 p-6 rounded-lg bg-cream shadow-md hover:shadow-lg transition-all flex flex-row items-center gap-6">
      {/* Recipe Information */}
      <div className="flex-1 basis-1/3 relative group">
        <h1 className="text-xl font-bold text-brown-500 font-title">{recipe.name}</h1>
        <p className="absolute left-0 top-6 w-auto p-2 bg-brown-600 text-cream text-sm rounded-md shadow-md opacity-0 group-hover:opacity-100 transition-opacity duration-300">
          {recipe.description}
        </p>
      </div>

      {/* Rating */}
      <div className="flex basis-1/12 flex-col items-center justify-center w-16 h-16 bg-beige text-brown-700 font-semibold rounded-full shadow-md">
        {recipe.rating ?? "N/A"}
        <span className="text-brown-500">★</span>
      </div>

      {/* Tags */}
      <div className="flex basis-1/3 flex-wrap gap-2 min-w-[1.5rem]">
        {recipe.tags?.map((tag, index) => (
          <span
            key={index}
            className="px-3 py-1 text-sm font-medium text-brown-600 bg-orange-500 rounded-full shadow-sm"
          >
            {tag.name}
          </span>
        ))}
      </div>

      {/* Action Buttons */}
      <div className="flex basis-1/8 gap-3">
        <button
          onClick={handleView}
          className="bg-orange-500 text-cream rounded-lg p-2 shadow hover:bg-orange-600 hover:shadow-md transition-all duration-200 flex items-center justify-center"
        >
          <FaEye />
        </button>
        <button
          onClick={handleEdit}
          disabled={hideButton}
          className="p-2 bg-green-500 text-cream font-medium rounded-lg shadow hover:bg-green-600 hover:shadow-md disabled:bg-green-300 transition-all duration-200 flex items-center justify-center"
        >
          <FaEdit />
        </button>
        <button
          onClick={handleDelete}
          disabled={hideButton}
          className="p-2 bg-brown-500 text-cream font-medium rounded-lg shadow hover:bg-brown-600 hover:shadow-md disabled:bg-brown-300 transition-all duration-200 flex items-center justify-center"
        >
          <FaTrash />
        </button>
      </div>
    </div>

  );
};

