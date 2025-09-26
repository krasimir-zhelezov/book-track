import NavItem from "./NavItem";
import SearchInput from "./SearchInput";

export default function Navbar() {
    return (
        <nav className="fixed top-0 left-0 w-full bg-white shadow">
            <div className="max-w-screen-xl mx-auto px-4 flex items-center justify-between h-16">
                
                <a href="/" className="flex items-center space-x-2">
                <img src="" className="h-8 w-8" alt="Logo"/>
                <span className="text-2xl font-semibold">Book Tracker</span>
                </a>

                <div className="flex mx-4 w-1/3">
                    <SearchInput/>
                </div>

                <div className="flex space-x-5">
                    <NavItem to="/profile" end>Profile</NavItem>
                    <NavItem to="/profile/books" end>Book History</NavItem>
                    <NavItem to="/auth/sign-in" end>Sign in</NavItem>
                    <NavItem to="/auth/sign-out" end>Sign out</NavItem>
                </div>

            </div>
        </nav>
    )
}