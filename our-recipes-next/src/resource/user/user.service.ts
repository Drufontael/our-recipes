'use client'

import { error } from "console";
import { useRouter } from "next/navigation";
import { useMemo } from "react";


class UserService{
    private static instance:UserService;
    baseUrl: string = process.env.NEXT_PUBLIC_API_URL+'/v1/api/users';
    private authToken: string | null = null;
    private authUser: string | null = null;

    private constructor(){}

    static getInstance():UserService{
        if (!UserService.instance) {
            UserService.instance = new UserService();
        }
        return UserService.instance;
    }

    async register(username:string, email:string, password:string) {
        try {
            const response = await fetch(`${this.baseUrl}/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, email, password }),
            });

            if (!response.ok) {
                console.error('Erro ao fazer login:', response.statusText);
                return false;
            }

            const data = await response.json();

            return true;
    
            
        } catch (error) {
            console.error('Erro ao registrar usuário:', error);
            return false;
        }
    }



    // Método para realizar login
    async login(username: string, password: string): Promise<boolean> {
        try {
            const response = await fetch(`${this.baseUrl}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });

            if (!response.ok) {
                console.error('Erro ao fazer login:', response.statusText);
                return false;
            }

            const data = await response.json();
            this.authToken = data.token;
            this.authUser = data.username;
            if(this.authToken && this.authUser){
                localStorage.setItem('authToken', this.authToken);
                localStorage.setItem('authUser',this.authUser)
                console.log('Login Ok');
            }
            return true;
        } catch (error) {
            console.error('Erro ao conectar com o servidor:', error);
            return false;
        }
    }




    getToken(): string | null {
        if (!this.authToken) {
            if (typeof window !== "undefined") {
                this.authToken = localStorage.getItem('authToken');
            }
        }
        return this.authToken;
    }

    getUser():string | null {
        if (!this.authUser) {
            if (typeof window !== "undefined") {
                this.authUser = localStorage.getItem('authUser');
            }
        }
        return this.authUser;
    }

    closeSession(){
        this.authToken=null;
        this.authUser=null;
        localStorage.removeItem('authToken');
        localStorage.removeItem('authUser');
    }
}

export const useUserService = () => {
    return useMemo(()=>UserService.getInstance(),[]);
}