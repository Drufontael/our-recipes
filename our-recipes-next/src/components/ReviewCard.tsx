import React from "react";
import { Review } from "@/resource/recipe/review.resource";

interface ReviewCardProps {
  review: Review;
  author: string;
  onEdit: (review: Review) => void;
  onDelete: (review: Review) => void;
}

export const ReviewCard: React.FC<ReviewCardProps> = ({
  review,
  author,
  onEdit,
  onDelete,
}) => {
  const isAuthor = review.author === author;
  console.log(review)

  return (
    <div className="bg-cream p-4 rounded-lg shadow-md space-y-3">
      {/* Autor */}
      <div className="flex justify-between items-center">
        <span className="text-brown-500 font-medium font-body">
          {review.author || "Anônimo"}
        </span>
        {review.date && (
          <span className="text-sm text-brown-400">
            {new Date(review.date).toLocaleDateString()}
          </span>
        )}
      </div>

      {/* Comentário */}
      <p className="text-brown-600 font-body">{review.comment}</p>

      {/* Avaliação */}
      <span className="text-orange-500 text-lg font-semibold">
        {review.rating && review.rating > 0 ? `★`.repeat(review.rating) : ""}
      </span>

      {/* Botões de ação */}
      {isAuthor && (
        <div className="flex space-x-3 mt-3">
          <button
            onClick={() => onEdit(review)}
            className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg font-body"
          >
            Editar
          </button>
          <button
            onClick={() => onDelete(review)}
            className="bg-orange-500 hover:bg-orange-600 text-white px-4 py-2 rounded-lg font-body"
          >
            Remover
          </button>
        </div>
      )}
    </div>
  );
};

