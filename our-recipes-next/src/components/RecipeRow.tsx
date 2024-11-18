import React from "react";

interface RecipeRowProps {
    name?:string;
    description?:string;
    rating?:number;
    tags?:string[]
}

export const RecipeRow: React.FC<RecipeRowProps> = ({ name, description, rating, tags }: RecipeRowProps) => {
    return (
      <div className="container mx-auto mt-4 p-4 rounded-lg bg-white shadow-md hover:bg-gray-100 cursor-pointer transition-all flex items-center gap-4">
        <div className="flex-1">
          <h1 className="text-lg font-bold text-gray-800">{name}</h1>
          <p className="text-sm text-gray-600">{description}</p>
        </div>
        <div className="flex items-center justify-center w-16 h-16 bg-yellow-100 text-yellow-600 rounded-full font-semibold">
          {rating ?? "N/A"}
          <span className="ml-1 text-yellow-500">★</span>
        </div>
        <div className="grid grid-cols-3 gap-2">
        {tags?.map((tag, index) => (
          <span
            key={index}
            className="px-2 py-1 text-sm text-gray-700 bg-gray-200 rounded-lg text-center"
          >
            {tag}
          </span>
        ))}
      </div>
      </div>
    );
  };