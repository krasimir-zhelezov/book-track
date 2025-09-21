import { useSearchParams } from "react-router-dom";

export default function Search() {
    const [searchParams] = useSearchParams();
    const query = searchParams.get("q");
    
    return (
        <div className="flex justify-center min-h-screen">
            <div className="flex flex-col w-1/3 items-center text-center">
                <h1 className="font-bold text-3xl mt-5">Search "{query}"</h1>

                <div className="flex flex-col mt-10 w-full">
                    <ul className="border-t border-gray-600 w-full text-start rounded-md">
                        <a href="#">
                            <li className="border border-t-0 border-gray-600 p-2 hover:text-gray-600 rounded-md">
                                <div className="flex">
                                    <img className="p-1" src="https://placehold.co/50x74"></img>
                                    <div className="flex flex-col">
                                        <h1 className="font-bold">Book Title</h1>
                                        <p className="hover:text-gray-600 text-gray-600">0000</p>
                                        <p className="hover:text-gray-600 text-gray-600">John Doe</p>
                                    </div>
                                </div>
                            </li>
                        </a>
                        <a href="#">
                            <li className="border border-t-0 border-gray-600 p-2 hover:text-gray-600 rounded-md">
                                <div className="flex">
                                    <img className="p-1" src="https://placehold.co/50x74"></img>
                                    <div className="flex flex-col">
                                        <h1 className="font-bold">Book Title</h1>
                                        <p className="hover:text-gray-600 text-gray-600">0000</p>
                                        <p className="hover:text-gray-600 text-gray-600">John Doe</p>
                                    </div>
                                </div>
                            </li>
                        </a>
                        <a href="#">
                            <li className="border border-t-0 border-gray-600 p-2 hover:text-gray-600 rounded-md">
                                <div className="flex">
                                    <img className="p-1" src="https://placehold.co/50x74"></img>
                                    <div className="flex flex-col">
                                        <h1 className="font-bold">Book Title</h1>
                                        <p className="hover:text-gray-600 text-gray-600">0000</p>
                                        <p className="hover:text-gray-600 text-gray-600">John Doe</p>
                                    </div>
                                </div>
                            </li>
                        </a>
                        <a href="#">
                            <li className="border border-t-0 border-gray-600 p-2 hover:text-gray-600 rounded-md">
                                <div className="flex">
                                    <img className="p-1" src="https://placehold.co/50x74"></img>
                                    <div className="flex flex-col">
                                        <h1 className="font-bold">Book Title</h1>
                                        <p className="hover:text-gray-600 text-gray-600">0000</p>
                                        <p className="hover:text-gray-600 text-gray-600">John Doe</p>
                                    </div>
                                </div>
                            </li>
                        </a>
                        <a href="#">
                            <li className="border border-t-0 border-gray-600 p-2 hover:text-gray-600 rounded-md">
                                <div className="flex">
                                    <img className="p-1" src="https://placehold.co/50x74"></img>
                                    <div className="flex flex-col">
                                        <h1 className="font-bold">Book Title</h1>
                                        <p className="hover:text-gray-600 text-gray-600">0000</p>
                                        <p className="hover:text-gray-600 text-gray-600">John Doe</p>
                                    </div>
                                </div>
                            </li>
                        </a>
                    </ul>
                </div>
            </div>
        </div>
    )
}