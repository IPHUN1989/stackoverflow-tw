import header_logo from "./images/header-logo.png"
import "./Header.css";
function Header(){
    return (
        <div className={"header"}>
            <img src={header_logo} alt={""}/>
        </div>
    )
}

export default Header;