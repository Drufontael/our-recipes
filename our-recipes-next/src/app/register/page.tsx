import { Template } from "@/components";

export default function RegisterPage() {
    return (
        <Template>
            <div className="container h-auto flex flex-col px-4 py-4 justify-center items-center">
                <h1 className="py-3 text-3xl font-title text-brown-500">
                    Faça seu cadastro:
                </h1>
                <div className="flex flex-col py-6 px-8 bg-cream rounded-lg shadow-lg w-full max-w-md">
                    <form className="flex flex-col space-y-4">
                        <input
                            type="text"
                            placeholder="Usuário"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                        />
                        <input
                            type="email"
                            placeholder="Email"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                        />
                        <input
                            type="password"
                            placeholder="Senha"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                        />
                        <input
                            type="password"
                            placeholder="Confirme a senha"
                            className="w-full px-4 py-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"
                        />
                    </form>
                    <div className="flex flex-row mt-6 space-x-4">
                        <button className="w-full bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded-lg">
                            Cadastrar
                        </button>
                        <button className="w-full bg-brown-400 hover:bg-brown-500 text-white font-bold py-2 px-4 rounded-lg">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </Template>
    );
}

