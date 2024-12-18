export interface LoginRequest {
    login: string,
    password: string
}

export interface LoginResponse {
    usuario: Usuario,
    token: string
}

export interface RegisterRequest {
    login?: string,
    password?: string,
    nome?: string,
    role?: string
}

export interface Usuario {
    nome: string,
    login: string,
    role: string
}