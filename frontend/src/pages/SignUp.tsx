import { useState } from "react";
import Button from "../components/Button";
import Input from "../components/Input";
import { useNavigate } from "react-router-dom";
import { signUp } from "../services/authService";

export default function SignUp() {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password1, setPassword1] = useState("");
    const [password2, setPassword2] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const data = await signUp({email, password1, password2});
            console.log(data);
            localStorage.setItem("token", data.token);
            navigate('/');
        } catch (error) {
            console.error("Sign up failed", error);
        }
    }

    return (
        <div className="flex justify-center min-h-screen w-full">
            <div className="flex flex-col w-1/5 justify-center items-center">
                
                <h1 className="font-bold text-3xl">Create account</h1>

                {/* <div className="flex flex-col mt-5 w-full">
                    <label>Your name</label>
                    <Input placeholder="First and last name"/>
                </div> */}
                <form onSubmit={handleSubmit}>
                    <div className="flex flex-col mt-5 w-full">
                        <label>Email</label>
                        <Input value={email} onChange={(e) => setEmail(e.target.value)}/>
                    </div>

                    <div className="flex flex-col mt-5 w-full">
                        <label>Password</label>
                        <Input value={password1} onChange={(e) => setPassword1(e.target.value)} type="password" placeholder="at least 8 characters"/>
                    </div>
                    <div className="flex flex-col mt-5 w-full">
                        <label>Re-enter password</label>
                        <Input value={password2} onChange={(e) => setPassword2(e.target.value)} type="password"/>
                    </div>

                    <div className="flex flex-col mt-5 w-full">
                        <Button type="submit">Create your account</Button>
                    </div>
                </form>

                <p className="mt-5">Already have an account? <a className="text-blue-500 hover:text-blue-600" href="#" onClick={() => navigate('/auth/sign-in')}>Sign In &gt;</a></p>
            </div>
        </div>
        
    )
}