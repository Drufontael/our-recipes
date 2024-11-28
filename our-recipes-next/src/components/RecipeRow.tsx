'use client'

import { useRouter } from "next/navigation";
import { RecipeSummary } from "@/resource/recipe/recipeSummary.resource";



interface RecipeRowProps {
  recipe: RecipeSummary;
  onDelete:(id:any)=>void  
}



export const RecipeRow: React.FC<RecipeRowProps> = ({ recipe,onDelete }: RecipeRowProps) => {
  const router = useRouter();
  
  const handleView = () => {
    router?.push(`/recipe/${recipe.id}`);
  };

  const handleEdit = () => {
    router?.push(`/recipe/${recipe.id}/edit`);
  };

  const handleDelete = (event:React.MouseEvent) => {
    event.preventDefault();
    if (confirm("Tem certeza de que deseja deletar esta receita?")) {
      event.defaultPrevented;
      onDelete(recipe.id);
    }
  };

  return (
    <div className="container mx-auto mt-4 p-6 rounded-lg bg-myLight shadow-lg hover:shadow-xl transition-all flex items-center gap-6">
      {/* Recipe Information */}
      <div className="flex-1">
        <h1 className="text-xl font-bold text-myTitle">{recipe.name}</h1>
        <p className="text-sm text-myText mt-1">{recipe.description}</p>
      </div>

      {/* Rating */}
      <div className="flex items-center justify-center w-16 h-16 bg-secondaryLight text-secondary font-semibold rounded-full">
        {recipe.rating ?? "N/A"}
        <span className="ml-1 text-secondary">★</span>
      </div>

      {/* Tags */}
      <div className="flex flex-wrap gap-2">
        {recipe.tags?.map((tag, index) => (
          <span
            key={index}
            className="px-3 py-1 text-sm font-medium text-myText bg-highlightLight rounded-full"
          >
            {tag.name}
          </span>
        ))}
      </div>

      {/* Action Buttons */}
      <div className="flex gap-4">
        <button
          onClick={handleView}
          className="bg-orange-500 text-white rounded-lg py-2 px-4 shadow-md hover:bg-orange-600"
        >
          Visualizar
        </button>
        <button
          onClick={handleEdit}
          className="px-4 py-2 bg-green-500 text-white font-medium rounded hover:bg-green-600"
        >
          Editar
        </button>
        <button
          onClick={handleDelete}
          className="px-4 py-2 bg-red-500 text-white font-medium rounded hover:bg-red-600"
        >
          Deletar
        </button>
      </div>
    </div>
  );
};

