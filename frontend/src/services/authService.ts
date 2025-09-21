import type User from "../types/user";
import api from "./api";

export interface SignInRequest {
    email: string,
    password: string
}

export interface AuthResponse {
    user: User,
    token: string
}

export const signIn = async (data: SignInRequest): Promise<AuthResponse> => {
    const response = await api.post<AuthResponse>("/auth/sign-in", data);
    return response.data;
}