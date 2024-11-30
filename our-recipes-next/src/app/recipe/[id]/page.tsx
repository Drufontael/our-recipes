'use client'

import { Template } from "@/components";
import { ReviewCard } from "@/components/ReviewCard";
import ReviewModal from "@/components/ReviewModal";
import { Recipe } from "@/resource/recipe/recipe.resource";
import { useRecipeService } from "@/resource/recipe/recipe.service";
import { Review } from "@/resource/recipe/review.resource";
import { useUserService } from "@/resource/user/user.service";
import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function RecipePage() {

  const recipeService = useRecipeService();
  const userService = useUserService();
  const router = useRouter(); 
  const { id }=useParams();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [recipe,setRecipe] = useState<Recipe>();
  const [review,setReview] = useState<Review>();
  const [author,setAuthor] = useState<string>();


  async function fetchRecipe() {
      try {
          const result = await recipeService.getRecipeById(id);
          setRecipe(result);
          setAuthor(userService.getUser()||'');
      } catch (error) {
          console.error('Failed to fetch recipes:', error);
      }
  }

  useEffect(() => {
    fetchRecipe();
  }, []);



  const handleBack = () => {
    window.history.back();
  };

  const handleEvaluate = () => {
    setIsModalOpen(true);
  };

  const handleSubmitReview = async(newReview:Review) => {
    const sucess=newReview.id?await recipeService.updateReview(newReview,id):await recipeService.addReview(newReview,id);
    if(sucess){
      fetchRecipe();
      setReview({})
      setIsModalOpen(false);
    }
  };

  const handleEditReview = (editReview:Review)=>{
    setReview(editReview);
    setIsModalOpen(true);
  }

 
  

  return (
    
    <Template
      headerActions={{
        listRecipes:true,
      }}  
    >
      <div className="bg-cream min-h-screen">
        
        <header className="bg-orange-200 py-8 px-4 text-center shadow-lg">
          <h1 className="text-4xl font-title text-orange-600 mb-4">
            {recipe?.name}
          </h1>
          <p className="text-brown-600 font-body max-w-2xl mx-auto">
            {recipe?.description}
          </p>
          <div className="flex justify-center items-center flex-wrap gap-6 mt-4">
            <div className="text-center">
              <span className="block text-lg font-semibold text-brown-700">
                Tempo de Preparo
              </span>
              <span className="text-sm text-gray-600">
                {recipe?.preparationTime}
              </span>
            </div>
            <div className="text-center">
              <span className="block text-lg font-semibold text-brown-700">
                Porções
              </span>
              <span className="text-sm text-gray-600">
                {recipe?.servingSize}
              </span>
            </div>
          </div>
          <div className="mt-4 flex justify-center items-center flex-wrap gap-4">
            <span className="block text-green-600 font-semibold text-lg">
              Classificação: {recipe?.rating?.toFixed(1)} ★
            </span>
            <div className="flex gap-2">
              {recipe?.tags?.map((tag, index) => (
                <span
                  key={index}
                  className="px-3 py-1 text-sm font-medium text-brown-700 bg-yellow-200 rounded-full"
                >
                  {tag.name}
                </span>
              ))}
            </div>
          </div>
        </header>

        {/* Ingredients and Steps */}
        <section className="py-8 px-4 container mx-auto grid grid-cols-1 md:grid-cols-2 gap-8">
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h2 className="text-2xl font-title text-orange-600 mb-4">
              Ingredientes
            </h2>
            <ul className="list-disc list-inside text-brown-700 space-y-2">
              {recipe?.ingredients?.map((ingredient, index) => (
                <div key={index} className="flex">
                  <li>
                    <span>
                      {ingredient?.ingredient?.name} 
                    </span>
                    <span className="ml-4">
                      {ingredient.quantity} {ingredient.measurementUnit?.abbreviation}
                    </span>
                  </li>
                </div>
              ))}
            </ul>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h2 className="text-2xl font-title text-orange-600 mb-4">Etapas</h2>
            <ol className="list-decimal list-inside text-brown-700 space-y-4">
              {recipe?.steps?.map((step, index) => (
                <li key={index}>{step.description}</li>
              ))}
            </ol>
          </div>
        </section>

        {/* Reviews */}
        <section className="bg-orange-100 py-8 px-4">
          <h2 className="text-2xl font-title text-orange-600 text-center mb-6">
            Avaliações
          </h2>
          <ul className="container mx-auto space-y-4">
            {recipe?.reviews?.map(review=>(
              <li
                key={review.id}
                className="bg-white p-4 rounded-lg shadow-md text-brown-700"
              >
                <ReviewCard
                  review={review}
                  author={author||""}
                  onEdit={handleEditReview}
                  onDelete={()=>{}}
                />
              </li>

            ))}

          </ul>

        </section>

        {/* Buttons */}
        <div className="py-8 px-4 container mx-auto flex justify-center gap-4">
          <button
            onClick={handleBack}
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded"
          >
            Voltar
          </button>
          <button
            onClick={handleEvaluate}
            className="bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded"
          >
            Avaliar
          </button>
        </div>
      </div>
      <ReviewModal
      isOpen={isModalOpen}
      onClose={() => setIsModalOpen(false)}
      onSubmit={handleSubmitReview}
      review={review}
      />
    </Template>    
  );
}

