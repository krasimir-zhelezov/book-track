interface ButtonProps {
  children: React.ReactNode;
  onClick?: () => void;
  variant?: "primary" | "secondary";
}

export default function Button ({ children, onClick, variant="primary" }: ButtonProps) {
    const baseClasses = "font-bold py-2 px-4 rounded shadow-md hover:shadow-lg";

    const variantClasses = variant === "primary"
    ? "bg-blue-500 text-white hover:bg-blue-600 hover:cursor-pointer"
    : "bg-gray-300 text-gray-800 hover:bg-gray-400 hover:cursor-pointer";

    return (
        <button onClick={onClick} className={`${baseClasses} ${variantClasses}`}>
            {children}
        </button>
    )
}