import React from "react";

interface TemplateProps {
    children: React.ReactNode;
}

export const Template: React.FC<TemplateProps> = (props: TemplateProps) => {
    return (
        <div className="h-screen w-screen bg-cream flex flex-col">
            <Header />
            <main className="container mx-auto flex-1 mt-8 py-4 px-4">
                {props.children}
            </main>
            <Footer />
        </div>
    );
};

const Header: React.FC = () => {
    return (
        <header className="bg-brown-500 text-cream py-3 shadow-md">
            <div className="container mx-auto flex justify-between items-center px-4">
                <h1 className="text-3xl font-title">Our Recipes</h1>
            </div>
        </header>
    );
};

const Footer: React.FC = () => {
    return (
        <footer className="bg-brown-500 text-cream py-4 shadow-inner">
            <div className="container mx-auto text-center font-body">
                Desenvolvido por Eduardo E Oliveira
            </div>
        </footer>
    );
};
