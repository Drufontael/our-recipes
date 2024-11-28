import { GlobalProvider } from "@/context/GlobalContext";
import React from "react";
import { useRouter } from "next/navigation"; // Use 'next/router' se estiver usando Next.js abaixo da versão 13.

interface TemplateProps {
  children: React.ReactNode;
  headerActions?: {
    listRecipes?: () => void;
    filterRecipes?: {
      isActive: boolean;
      action: () => void;
    };
    addRecipe?: () => void;
  };
}

export const Template: React.FC<TemplateProps> = (props: TemplateProps) => {
  return (
    <GlobalProvider>
      <div className="h-screen w-screen bg-cream flex flex-col">
        <Header headerActions={props.headerActions} />
        <main className="container mx-auto flex-1 mt-8 py-4 px-4">
          {props.children}
        </main>
        <Footer />
      </div>
    </GlobalProvider>
  );
};

const Header: React.FC<{ headerActions?: TemplateProps["headerActions"] }> = ({
  headerActions,
}) => {
  const router = useRouter();

  return (
    <header className="bg-brown-500 text-cream py-3 shadow-md">
      <div className="container mx-auto flex justify-between items-center px-4">
        <h1 className="text-3xl font-title">Our Recipes</h1>
        <div className="flex space-x-4">
          {/* Botão Listar Receitas */}
          {headerActions?.listRecipes && (
            <button
              onClick={headerActions.listRecipes}
              className="bg-orange-500 text-white py-2 px-6 rounded-lg font-body shadow-md hover:bg-orange-600 active:scale-95 transition-transform"
            >
              Listar Receitas
            </button>
          )}

          {/* Botão Filtrar Receitas */}
          {headerActions?.filterRecipes && (
            <button
              onClick={headerActions.filterRecipes.action}
              className={`py-2 px-6 rounded-lg font-body shadow-md transition-transform active:scale-95 ${
                headerActions.filterRecipes.isActive
                  ? "bg-green-500 text-white hover:bg-green-600"
                  : "bg-gray-400 text-gray-200 hover:bg-gray-500"
              }`}
            >
              Filtrar Receitas
            </button>
          )}

          {/* Botão Adicionar Receita */}
          {headerActions?.addRecipe && (
            <button
              onClick={headerActions.addRecipe}
              className="bg-orange-500 text-white py-2 px-6 rounded-lg font-body shadow-md hover:bg-orange-600 active:scale-95 transition-transform"
            >
              Adicionar Receita
            </button>
          )}

          {/* Botão Retornar */}
          <button
            onClick={() => router.back()}
            className="bg-brown-400 text-white py-2 px-6 rounded-lg font-body shadow-md hover:bg-brown-500 active:scale-95 transition-transform"
          >
            Retornar
          </button>
        </div>
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
