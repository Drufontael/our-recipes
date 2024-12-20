'use client'

import { GlobalProvider } from "@/context/GlobalContext";
import React from "react";
import { useRouter } from "next/navigation"; // Use 'next/router' se estiver usando Next.js abaixo da versão 13.
import { useUserService } from "@/resource/user/user.service";

interface TemplateProps {
  children: React.ReactNode;
  headerActions?: {
    listRecipes?: boolean;
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
  const user:string|null = useUserService().getUser();
  const auth=useUserService();
  const logout = () =>{
    auth.closeSession();
    router.push('/login');
  }
  const showList = () =>{
    auth.getUser()?
    router.push('/list'):
    router.push('/login');
  }

  return (
    <header className="bg-brown-500 text-cream py-3 shadow-md">
      <div className="container mx-auto flex justify-between items-center px-4">
        {headerActions?.listRecipes?(
          <a href="#" onClick={showList}>
            <h1 className="text-3xl font-title">Our Recipes</h1>
          </a>
        ):(
          <h1 className="text-3xl font-title">Our Recipes</h1>
        )}
        
        <div className="flex space-x-4">

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

          {user &&
          <div className="flex text-cream items-center">
            <span className="py-3 px-4 text-md">Olá, {user}</span>
            <a href="#" onClick={logout}>
            <span className="py-3 px-4 text-sm">Sair</span>
            </a>
          </div>
          }
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
