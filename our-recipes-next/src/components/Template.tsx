import React from "react"

interface TemplateProps {
    children: React.ReactNode
}

export const Template: React.FC<TemplateProps> = (props:TemplateProps) => {
    return (
        <>
            <Header/>
                <div className="container mx-auto mt-8 py-4">
                    {props.children}
                </div>               
            <Footer />
        </>    
    )
}

const Header: React.FC = () =>{
    return(
        <header className="bg-red-900 text-white py-3">
            <div className="container mx-auto flex justify-between items-center px-4">
                <h1 className="text-3xl font-bold">Our Recipes</h1>
            </div>
        </header>
    )
}

const Footer: React.FC = () =>{
    return(
        <footer className="bg-red-900 text-white py-4">
            <div className="container mx-auto text-center">
                Desenvolvido por Eduardo E Oliveira
            </div>
        </footer>
    )
}