import './App.css';
import Question from "./components/question/Question";
import Header from "./components/header/Header";
import {useEffect, useState} from "react";

function App() {
    const [questions, setQuestions] = useState(null);

    const fetchQuestions = async () => {
        const res = await fetch("/questions/all");
        const data = await res.json();
        console.log(data);
        setQuestions(data);
    }

    useEffect(() => {
        fetchQuestions();
    }, []);

    if(questions){
        return (
            <div className="App">
                <Header/>
                <div className={"content"}>
                    <Question title={"asd"} description={"asdasd"} answers={[1,2,3,4]} timePosted={"2023-08-03T10:30:00-01:00"}/>
                </div>
            </div>
        );
    }
}

export default App;
