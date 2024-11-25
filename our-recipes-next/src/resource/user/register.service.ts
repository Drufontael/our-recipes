class RegisterService{

    async register(username:string, email:string, password:string) {
        try {
            const response = await fetch('http://localhost:8080/v1/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, email, password }),
            });
    
            if (!response.ok) {
                const error = await response.json();
                throw new Error(`Erro ao registrar usuário: ${error.message}`);
            }
    
            const data = await response.json();
            console.log('Usuário registrado com sucesso:', data);
            return true;
        } catch (error) {
            console.error('Erro ao registrar usuário:', error);
            return false;
        }
    }

}

export const useRegisterService = ()=>new RegisterService;