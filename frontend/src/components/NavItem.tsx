import { NavLink, type NavLinkProps } from "react-router-dom";

type NavItemProps = {
  children: React.ReactNode;
} & Pick<NavLinkProps, "to" | "end">;

export default function NavItem({ to, end, children }: NavItemProps) {
    return (
        <NavLink
            to={to}
            end={end}
            className={({ isActive }) =>
                `block py-2 px-3 rounded-sm ${
                isActive
                    ? "text-white font-semibold bg-blue-500"
                    : "text-gray-800 hover:bg-blue-500 hover:text-white transition-colors duration-500 ease-in-out"
                }`
            }
            >
            {children}
        </NavLink>
    )
}