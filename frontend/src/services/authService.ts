import type User from "../types/user";
import api from "./api";

export interface SignInRequest {
    email: string,
    password: string
}

export interface SignUpRequest {
    email: string,
    password1: string,
    password2: string
}

export interface AuthResponse {
    user: User,
    token: string
}

export const signIn = async (data: SignInRequest): Promise<AuthResponse> => {
    const response = await api.post<AuthResponse>("/auth/sign-in", data);
    return response.data;
}

export const signUp = async (data: SignUpRequest): Promise <AuthResponse> => {
    const response = await api.post<AuthResponse>("/auth/sign-up", data);
    return response.data;
}

export const getCurrentUser = async (): Promise<User> => {
    const response = await api.get<User>("/auth/profile");
    return response.data;
}