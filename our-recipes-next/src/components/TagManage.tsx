import React from "react";
import { Tag } from "@/resource/recipe/tag.resource";
import { useGlobalContext } from "@/context/GlobalContext";

interface TagManagerProps {
    tags: Tag[]; // Lista de tags existentes
    onDeleteTag: (tag: Tag) => void; // Função para deletar uma tag
    onAddTag: (tag: Tag) => void; // Função para adicionar uma tag
}

export const TagManager: React.FC<TagManagerProps> = ({
    tags,
    onDeleteTag,
    onAddTag,
}) => {
    const { tags: possibleTags } = useGlobalContext();

    const handleAddTag = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedTagName = event.target.value;
        const selectedTag = possibleTags.find(tag => tag.name === selectedTagName);
        if (selectedTag) {
            onAddTag(selectedTag);
        }
        event.target.value = ""; // Reseta o select após a seleção
    };

    return (
        <div className="space-y-6 bg-beige p-6 rounded-lg shadow-md">
            {/* Lista de Tags */}
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
                {tags.map((tag, index) => (
                    <div
                        key={index}
                        className="bg-brown-300 text-brown-700 rounded-lg p-3 flex justify-between items-center shadow-sm"
                    >
                        <span className="font-medium">{tag.name}</span>
                        <button
                            onClick={() => onDeleteTag(tag)}
                            className="bg-orange-500 text-white rounded-full px-3 py-1 text-sm font-semibold hover:bg-orange-600"
                        >
                            X
                        </button>
                    </div>
                ))}
            </div>

            {/* Select para adicionar novas tags */}
            <div className="flex items-center space-x-4">
                <label
                    htmlFor="tag-select"
                    className="text-brown-500 text-sm font-medium"
                >
                    Adicionar Tag:
                </label>
                <select
                    id="tag-select"
                    onChange={handleAddTag}
                    className="border border-brown-400 bg-cream rounded-lg p-2 shadow-sm focus:ring-2 focus:ring-brown-500"
                >
                    <option value="">Selecione uma tag</option>
                    {possibleTags
                        .filter(tag => !tags.some(existingTag => existingTag.name === tag.name))
                        .map((tag, index) => (
                            <option key={index} value={tag.name}>
                                {tag.name}
                            </option>
                        ))}
                </select>
            </div>
        </div>
    );
};

