interface InputProps {
    type?: string;
    placeholder?: string;
}

export default function Input({ type = "text", placeholder="" }: InputProps) {
    return <input className="border border-gray-300 rounded-md px-4 py-2 shadow-md hover:shadow-lg focus:outline-none" type={type} placeholder={placeholder}/>
}