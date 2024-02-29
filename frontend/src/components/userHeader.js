import "../assets/styles/header.css";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import AuthService from "../api-service/authService";
import AuthVerify from "../api-service/authVerify";
import Logo from "./logo";

function UserHeader() {
  const [isNavOpen, setIsNavOpen] = useState(false);

  const handleNav = () => {
    setIsNavOpen(!isNavOpen);
  };

  const navigate = useNavigate();

  const logout = () => {
    AuthService.logout_req();
    navigate("/");
    window.location.reload();
  };

  return (
    <header className="app-header">
      <div className="logo-wrapper">
        <span>
          {isNavOpen ? (
            <i class="fa fa-times" aria-hidden="true" onClick={handleNav}></i>
          ) : (
            <i class="fa fa-bars" aria-hidden="true" onClick={handleNav}></i>
          )}
        </span>
        <span>
          <Link to="/">
            <Logo />
          </Link>
        </span>
      </div>
      <ul className={isNavOpen ? "nav-open" : "nav-close"}>
        <li>
          <Link to="/" className="nav-link">
            Home
          </Link>
        </li>
        <li>
          <Link to="/my/orders" className="nav-link">
            Orders
          </Link>
        </li>
        <li>
          <Link to="/my/profile" className="nav-link">
            Profile
          </Link>
        </li>
        <li onClick={logout} className="nav-link">
            <Link className="nav-link">Logout</Link>
        </li>
      </ul>

      <ul>
        <li>
          <Link to='/search'>
            <i class="fa fa-search" aria-hidden="true"></i>
          </Link>
        </li>
        <li>
          <Link to="/">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
            <span>(0)</span>
          </Link>
        </li>
      </ul>
      <AuthVerify logOut={logout} />
    </header>
  );

}
export default UserHeader;
