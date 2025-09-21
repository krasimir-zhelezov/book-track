import { useParams } from "react-router-dom";
import Button from "../components/Button";

export default function BookView() {
    const { id } = useParams();
    
    return (
        <div className="flex flex-col items-center justify-center min-h-screen text-center">
            <div className="flex flex-col w-2/3">
                <h1 className="font-bold text-3xl text-left">Book Title</h1>

                <div className="flex flex-row w-full items-end justify-between">
                <img className="w-70 h-105" src="https://placehold.co/70x105" alt="Book Cover"></img>
                <Button variant="secondary">Add to history</Button>
                </div>

                <p className="text-gray-700 text-lg mt-5 text-left">This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. This is book description. </p>

                <table className="mt-5 border-t border-collapse w-full">
                    <tbody>
                        <tr>
                        <th className="text-left border-b p-2">Author:</th>
                        <td className="text-left border-b p-2">Author Name</td>
                        </tr>
                        <tr>
                        <th className="text-left border-b p-2">Genres:</th>
                        <td className="text-left border-b p-2">Genre1, Genre2, Genre3, Genre4, Genre5</td>
                        </tr>
                        <tr>
                        <th className="text-left border-b p-2">Published:</th>
                        <td className="text-left border-b p-2">0000</td>
                        </tr>
                        <tr>
                        <th className="text-left p-2">ISBN:</th>
                        <td className="text-left p-2">000-0-0000-0000-0</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}