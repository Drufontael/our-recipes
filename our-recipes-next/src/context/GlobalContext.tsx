import { Ingredient } from "@/resource/recipe/ingredient.resource";
import { MeasurementUnit } from "@/resource/recipe/measurementUnit.resource";
import { Tag } from "@/resource/recipe/tag.resource";
import React, { createContext, useContext, useEffect, useState } from "react";


interface GlobalContextProps {
    ingredients:Ingredient[];
    measurementUnits:MeasurementUnit[];
    tags:Tag[];
    loadGlobals: () => Promise<void>;
    loading:boolean;
}

const GlobalContext = createContext<GlobalContextProps>({
    ingredients:[],
    measurementUnits:[],
    tags:[],
    loadGlobals: async () =>{},
    loading:true
})

export const GlobalProvider: React.FC<{ children: React.ReactNode}> = ({children}) =>{
    const [ingredients,setIngredients] = useState<Ingredient[]>([]);
    const [measurementUnits,setMeasurementUnits] = useState<MeasurementUnit[]>([]);
    const [tags,setTags] = useState<Tag[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    const loadGlobals = async () => {
        try {
            const baseUrl = 'http://localhost:8080/v1/api';
            const ingredientsData = await fetch(`${baseUrl}/ingredients`).then((res) => res.json());
            const measurementUnitsData = await fetch(`${baseUrl}/measurement-units`).then((res) => res.json());
            const tagsData = await fetch(`${baseUrl}/tags`).then((res) => res.json());
            setIngredients(ingredientsData);
            setMeasurementUnits(measurementUnitsData);
            setTags(tagsData);
        } catch (error) {
            console.error("Erro ao carregar dados globais:", error);
        } finally {
            setLoading(false); // Sempre finaliza o estado de carregamento
        }
    };  

    useEffect(() => {
        loadGlobals();
    },[]);

    return (
        <GlobalContext.Provider value={{ingredients,measurementUnits,tags,loadGlobals,loading}}>
            {children}
        </GlobalContext.Provider>
    );
};

export const useGlobalContext = () =>useContext(GlobalContext);