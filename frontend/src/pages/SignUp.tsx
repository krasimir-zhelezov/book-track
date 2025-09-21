import Button from "../components/Button";
import Input from "../components/Input";

export default function SignUp() {
    return (
        <div className="flex justify-center min-h-screen w-full">
            <div className="flex flex-col w-1/5 justify-center items-center">
                <h1 className="font-bold text-3xl">Create account</h1>

                <div className="flex flex-col mt-5 w-full">
                    <label>Your name</label>
                    <Input placeholder="First and last name"/>
                </div>

                <div className="flex flex-col mt-5 w-full">
                    <label>Email</label>
                    <Input/>
                </div>

                <div className="flex flex-col mt-5 w-full">
                    <label>Password</label>
                    <Input type="password" placeholder="at least 8 characters"/>
                </div>
                <div className="flex flex-col mt-5 w-full">
                    <label>Re-enter password</label>
                    <Input type="password"/>
                </div>

                <div className="flex flex-col mt-5 w-full">
                    <Button>Create your account</Button>
                </div>
            </div>
        </div>
        
    )
}