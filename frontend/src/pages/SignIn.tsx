import { useState } from "react";
import Button from "../components/Button";
import Input from "../components/Input";
import { signIn } from "../services/authService";
import { useNavigate } from "react-router-dom";

export default function SignIn() {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const data = await signIn({email, password});
            // console.log(data);
            localStorage.setItem("token", data.token);
            navigate('/');
        } catch (error) {
            console.error("Sign in failed", error);
        }
    }

    return (
        <div className="flex justify-center min-h-screen w-full">
            <div className="flex flex-col w-1/5 justify-center items-center">
                <form onSubmit={handleSubmit}>
                    <h1 className="font-bold text-3xl">Sign in</h1>

                    <div className="flex flex-col mt-5 w-full">
                        <label>Email</label>
                        <Input value={email} onChange={(e) => setEmail(e.target.value)}/>
                    </div>

                    <div className="flex flex-col mt-5 w-full">
                        <label>Password</label>
                        <Input value={password} onChange={(e) => setPassword(e.target.value)} type="password"/>
                    </div>

                    <div className="flex flex-col mt-5 w-full">
                        <Button type="submit">Sign in</Button>
                    </div>

                    <p className="mt-5">New to Book Track? <a className="text-blue-500 hover:text-blue-600" href="#">Sign Up &gt;</a></p>
                </form>
            </div>
        </div>
    )   
}