import { Link, useSearchParams } from "react-router-dom";
import { searchBookByTitle } from "../services/bookService";
import { useEffect, useState } from "react";
import type Book from "../types/book";

export default function Search() {
    const [searchParams] = useSearchParams();
    const query = searchParams.get("q");

    const [loading, setLoading] = useState(false);
    const [books, setBooks] = useState<Book[]>([]);

    useEffect(() => {
        searchByTitle(query!);
    }, [query]);

    const searchByTitle = async (query: string) => {
        setLoading(true);
        try {
            const data = await searchBookByTitle(query!);
            console.log(data);
            setBooks(data);
        } catch (error) {
            console.error("Search failed", error);
        } finally {
            setLoading(false);
        }
    }
    
    return (
        <>
            <div className="h-16"></div>
            <div className="flex justify-center min-h-screen">
                <div className="flex flex-col w-1/3 books-center text-center">
                    <h1 className="font-bold text-3xl mt-5">Search "{query}"</h1>

                    <div className="flex flex-col mt-10 w-full">
                        {loading && <p>Loading...</p>}
                        <ul className="border-t border-gray-600 w-full text-start rounded-md">
                            {books.map((book, index) => (
                                <Link key={index} to={`/book/${book.id}`}>
                                    <li className="border border-t-0 border-gray-600 p-2 hover:text-gray-600 rounded-md">
                                        <div className="flex">
                                            <img className="p-1" src="https://placehold.co/50x74"></img>
                                            <div className="flex flex-col">
                                                <h1 className="font-bold">{book.title}</h1>
                                                <p className="hover:text-gray-600 text-gray-600">{book.published}</p>
                                                <p className="hover:text-gray-600 text-gray-600">{book.author}</p>
                                            </div>
                                        </div>
                                    </li>
                                </Link>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        </>
    )
}