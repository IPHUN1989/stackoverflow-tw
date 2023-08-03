import header_logo from "./images/header-logo.png"
import "./Header.css";
function Header({onNewQuestionClick}){
    return (
        <div className={"header"}>
            <div className={"headerContainer"}>
                <img src={header_logo} alt={""}/>
                <button className={"button"} onClick={onNewQuestionClick}>Submit new question</button>
            </div>
        </div>
    )
}

export default Header;