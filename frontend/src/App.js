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
                    {questions.map((question,i) => <Question key={i} title={question.title} description={question.description} timePosted={question.created} answers={question.answers}/>)}
                </div>
            </div>
        );
    }
}

export default App;
