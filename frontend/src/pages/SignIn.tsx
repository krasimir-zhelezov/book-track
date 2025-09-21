import Button from "../components/Button";
import Input from "../components/Input";

export default function SignIn() {
    return (
        <div className="flex justify-center min-h-screen w-full">
            <div className="flex flex-col w-1/5 justify-center items-center">
                <h1 className="font-bold text-3xl">Sign in</h1>

                <div className="flex flex-col mt-5 w-full">
                    <label>Email</label>
                    <Input/>
                </div>

                <div className="flex flex-col mt-5 w-full">
                    <label>Password</label>
                    <Input type="password"/>
                </div>

                <div className="flex flex-col mt-5 w-full">
                    <Button>Sign in</Button>
                </div>

                <p className="mt-5">New to Book Track? <a className="text-blue-500 hover:text-blue-600" href="#">Sign Up &gt;</a></p>
            </div>
        </div>
    )   
}