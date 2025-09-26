import Input from "./Input";
import NavItem from "./NavItem";

export default function Navbar() {
    return (
    <nav className="">
        <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
        <a href="/" className="flex items-center space-x-5">
            <img src="" className="" alt="Logo"/>
            <span className="self-center text-2xl font-semibold whitespace-nowrap">Book Tracker</span>
        </a>
        <Input placeholder="Search..."/>
            <div className="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-search">
                <ul className="flex flex-row p-4 mt-4 space-x-5 font-medium rounded-lg">
                    <li>
                        <NavItem to="/profile" end>Profile</NavItem>
                    </li>
                    <li>
                        <NavItem to="/profile/books" end>Book History</NavItem>
                    </li>
                    <li>
                        <NavItem to="/auth/sign-in" end>Sign in</NavItem>
                    </li>
                    <li>
                        <NavItem to="/auth/sign-out" end>Sign out</NavItem>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    )
}