import {Template,RecipeRow} from "@/components"

export default function Login(){
    return(
        
        <Template>
            <RecipeRow 
                name="Arroz branco"
                description="Nostrud id consequat labore nulla veniam esse irure sint deserunt aute reprehenderit ad aliqua."
                rating={4.5}
                tags={["Vegetariano","Vegano","Gluten-Free","tag4","outra tag"]}
            />
                        <RecipeRow 
                name="Arroz branco"
                description="Nostrud id consequat labore nulla veniam esse irure sint deserunt aute reprehenderit ad aliqua."
                rating={4.5}
                tags={["Vegetariano","Vegano","Gluten-Free","tag4","outra tag"]}
            />
        </Template>
        
    )
}
