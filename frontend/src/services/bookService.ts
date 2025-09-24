import type Book from "../types/book"
import api from "./api"

export const searchBookByTitle = async (query: string): Promise<Book[]> => {
    const response = await api.get<Book[]>(`/books/searchByTitle/${query}`);
    return response.data;
}

export const getBookById = async (bookId: string): Promise<Book> => {
    const response = await api.get<Book>(`/books/${bookId}`);
    return response.data;
}

export const readBookById = async (bookId: string): Promise<Book> => {
    const response = await api.get<Book>(`/books/read/${bookId}`);
    return response.data;
}