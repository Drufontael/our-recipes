'use client'

import { Template } from "@/components";
import { useUserService } from "@/resource/user/user.service";
import { log } from "console";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function Login() {

    const router= useRouter();
    const userService = useUserService();
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    async function handleLogin() {
        const success = await userService.login(username, password);
        if (success) {
            console.log('LOGIN OK'); 
            router.push('/list'); 
        } else {
            setErrorMessage('Falha no login. Verifique suas credenciais.');
        }
    }

    function toRegister(){
        router?.push('/register')
    }

    return (
        <Template>
            <div className="container h-96 flex flex-col px-4 py-4 justify-center items-center">
                <h1 className="py-3 text-3xl font-title text-brown-500">
                    Acesse nossas receitas!
                </h1>
                <div className="flex flex-col py-6 px-8 bg-cream rounded-lg shadow-lg">
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            handleLogin();
                        }}
                    >
                        <input
                            id="username"
                            type="text"
                            className="w-full mb-4 px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            placeholder="Usuário"
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <input
                            id="password"
                            type="password"
                            className="w-full mb-4 px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            placeholder="Senha"
                            onChange={(e)=> setPassword(e.target.value)}
                        />
                        {errorMessage && (
                            <p className="text-red-500 text-sm mb-4">{errorMessage}</p>
                        )}
                                    
                    
                        <div className="flex flex-row mt-4 space-x-4">
                            <button
                                type="submit" 
                                className="w-full bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded-lg"
                                >
                                Acessar
                            </button>
                            <button 
                                className="w-full bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-lg" 
                                onClick={toRegister}
                                >
                                Cadastrar
                            </button>
                        </div>
                    </form>   
                </div>
            </div>
        </Template>
    );
}

