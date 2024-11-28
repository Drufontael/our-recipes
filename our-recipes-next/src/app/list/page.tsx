'use client';

import { Template, RecipeRow } from '@/components';
import FilterModal from '@/components/FilterModal';
import { Ingredient } from '@/resource/recipe/ingredient.resource';
import { Recipe } from '@/resource/recipe/recipe.resource';
import { useRecipeService } from '@/resource/recipe/recipe.service';
import { RecipeSummary } from '@/resource/recipe/recipeSummary.resource';
import { Tag } from '@/resource/recipe/tag.resource';
import { useUserService } from '@/resource/user/user.service';
import { useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';

interface Filter {
  user: boolean;
  tags: Tag[];
  ingredients: Ingredient[];
  status: boolean;
}

export default function List() {
  const recipeService = useRecipeService();
  const userService = useUserService();
  const router=useRouter();
  const [recipes, setRecipes] = useState<Recipe[]>([]);
  const [isFilterOpen, setIsFilterOpen] = useState<boolean>(false);
  const [isFilterActive, setIsFilterActive] = useState<boolean>(false);
  const [filter, setFilter] = useState({});

  async function fetchRecipes() {
    try {
      const result = isFilterActive
        ? await recipeService.getFilteredRecipes(filter)
        : await recipeService.getRecipes();
      setRecipes(result);
    } catch (error) {
      console.error('Failed to fetch recipes:', error);
    }
  }

  useEffect(() => {
    fetchRecipes();
  }, [isFilterActive, filter]);

  function handleFilterRecipes(): void {
    setIsFilterOpen(true);
  }

  const handleAddRecipe = () => router.push('/recipe/new');

  const handleOnSubmitFilter = (newFilter: Filter) => {
    if (newFilter.status) {
      const user = userService.getUser();
      const updatedFilter = {        
        author: newFilter.user ? user : null,
        tags: newFilter.tags.map((tag) => tag.name),
        ingredients: newFilter.ingredients.map((ingredient) => ingredient.name),
      };
      console.log(updatedFilter)
      

      setFilter(updatedFilter);
      setIsFilterActive(true);
    } else {
      setIsFilterActive(false);
      setFilter({});
    }

    setIsFilterOpen(false); // Fecha o modal
  };

  function fillRow(recipe: RecipeSummary) {
    return <RecipeRow key={recipe.id} recipe={recipe} onDelete={handleOnDelete}/>;
  }

  function showRows() {
    return recipes.map(fillRow);
  }

  const handleOnDelete = async(id:any) =>{
    try{
      await recipeService.deleteRecipe(id);
      fetchRecipes();
    } catch (error) {
      console.error("Erro ao deletar receita:", error);
      alert("Não foi possível deletar a receita. Tente novamente mais tarde.");
    }
  }

  return (
    <Template
      headerActions={{
        filterRecipes: {
          isActive: isFilterActive, // Reflete se há filtros ativos
          action: handleFilterRecipes,
        },
        addRecipe: handleAddRecipe,
      }}
    >
      <section>{showRows()}</section>
      <FilterModal
        isOpen={isFilterOpen}
        onSubmit={handleOnSubmitFilter} // Passa a função para aplicar filtros
        onClose={() => setIsFilterOpen(false)} // Fecha o modal ao clicar em "Cancelar"
      />
    </Template>
  );
}