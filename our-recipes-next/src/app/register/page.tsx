'use client'

import { Template } from "@/components";
import { useRegisterService } from "@/resource/user/register.service";
import { useUserService } from "@/resource/user/user.service";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function RegisterPage() {

    const [username,setUsername]=useState<string>('');
    const [email,setEmail]=useState<string>('');
    const [password,setPassword]=useState<string>('');
    const [passConfirm,setPassConfirm]=useState<string | null>(null);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const userService=useUserService();
    const registerService=useRegisterService();
    const router=useRouter();

    async function handlerRegister(){
        if(password!==passConfirm){
            setErrorMessage("As senhas não coincidem!");
            return;
        }
        const sucess=await userService.register(username,email,password);
        console.log('Indo para lista')
        if(sucess){
            console.log('Register OK'); 
            //router.push('/list'); 
        } else {
            setErrorMessage("Erro ao realizar o cadastro. Tente novamente.");
        }
    }

    return (
        <Template>
            <div className="container h-auto flex flex-col px-4 py-4 justify-center items-center">
                <h1 className="py-3 text-3xl font-title text-brown-500">
                    Faça seu cadastro:
                </h1>
                <div className="flex flex-col py-6 px-8 bg-cream rounded-lg shadow-lg w-full max-w-md">
                    <form 
                    className="flex flex-col space-y-4"
                    onSubmit={handlerRegister}
                    >
                        <input
                            type="text"
                            placeholder="Usuário"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            onChange={(e)=>setUsername(e.target.value)}
                        />
                        <input
                            type="email"
                            placeholder="Email"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            onChange={(e)=>setEmail(e.target.value)}
                        />
                        <input
                            type="password"
                            placeholder="Senha"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            onChange={(e)=>setPassword(e.target.value)}
                        />
                        <input
                            type="password"
                            placeholder="Confirme a senha"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            onChange={(e)=>setPassConfirm(e.target.value)}
                        />
                        {errorMessage && (
                            <p className="text-red-500 text-sm">{errorMessage}</p>
                        )}
                        <div className="flex flex-row mt-6 space-x-4">
                            <button 
                                type="submit"
                                className="w-full bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded-lg"
                                >
                                Cadastrar
                            </button>
                            <button className="w-full bg-brown-400 hover:bg-brown-500 text-white font-bold py-2 px-4 rounded-lg">
                                Cancelar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </Template>
    );
}

