import { Template } from "@/components";

export default function Login() {
    return (
        <Template>
            <div className="container h-96 flex flex-col px-4 py-4 justify-center items-center">
                <h1 className="py-3 text-3xl font-title text-brown-500">
                    Acesse nossas receitas!
                </h1>
                <div className="flex flex-col py-6 px-8 bg-cream rounded-lg shadow-lg">
                    <form>
                        <input
                            id="username"
                            type="text"
                            className="w-full mb-4 px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            placeholder="Usuário"
                        />
                        <input
                            id="password"
                            type="password"
                            className="w-full mb-4 px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                            placeholder="Senha"
                        />
                    </form>
                    <div className="flex flex-row mt-4 space-x-4">
                        <button className="w-full bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded-lg">
                            Acessar
                        </button>
                        <button className="w-full bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-lg">
                            Cadastrar
                        </button>
                    </div>
                </div>
            </div>
        </Template>
    );
}

