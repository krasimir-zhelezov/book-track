interface InputProps {
    type?: string;
    placeholder?: string;
    value?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function Input({ type = "text", placeholder="", value, onChange }: InputProps) {
    return (
        <input 
            className="border border-gray-300 rounded-md px-4 py-2 shadow-md hover:shadow-lg focus:outline-none" 
            type={type} 
            placeholder={placeholder}
            value={value}
            onChange={onChange}
        />
    )
}