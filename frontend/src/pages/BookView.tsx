import { useParams } from "react-router-dom";
import Button from "../components/Button";
import { useEffect, useState } from "react";
import { getBookById } from "../services/bookService";
import type Book from "../types/book";

export default function BookView() {
    const { id } = useParams();
    const [book, setBook] = useState<Book | null>(null);
    
    useEffect(() => {
        fetchBookById(id!);
    }, [id]);

    const fetchBookById = async (id: string) => {
        try {
            const data = await getBookById(id);
            setBook(data);
            console.log(data);
        } catch (error) {
            console.error("Get book failed", error);
        }
    }

    return (
        <div className="flex flex-col items-center justify-center min-h-screen text-center">
            <div className="flex flex-col w-2/3">
                <h1 className="font-bold text-3xl text-left">{book?.title}</h1>

                <div className="flex flex-row w-full items-end justify-between">
                    <img className="w-70 h-105" src="https://placehold.co/70x105" alt="Book Cover"></img>
                    <div className="w-1/4">
                        <Button variant="secondary">Add to history</Button>
                    </div>
                </div>

                <p className="text-gray-700 text-lg mt-5 text-left">This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. </p>

                <table className="mt-5 border-t border-collapse w-full">
                    <tbody>
                        <tr>
                        <th className="text-left border-b p-2">Author:</th>
                        <td className="text-left border-b p-2">{book?.author}</td>
                        </tr>
                        <tr>
                        <th className="text-left border-b p-2">Genres:</th>
                        <td className="text-left border-b p-2">{book?.genres}</td>
                        </tr>
                        <tr>
                        <th className="text-left border-b p-2">Published:</th>
                        <td className="text-left border-b p-2">{book?.published}</td>
                        </tr>
                        <tr>
                        <th className="text-left p-2">ISBN:</th>
                        <td className="text-left p-2">{book?.isbn}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}