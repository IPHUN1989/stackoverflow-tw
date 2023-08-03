import './App.css';
import Question from "./components/question/Question";
import Header from "./components/header/Header";
import {useEffect, useState} from "react";
import QuestionForm from "./components/questionform/QuestionForm";

function App() {
    const [questions, setQuestions] = useState(null);
    const [currentPage, setCurrentPage] = useState("home");

    const fetchQuestions = async () => {
        const res = await fetch("/questions/all");
        const data = await res.json();
        console.log(data);
        setQuestions(data);
    }

    useEffect(() => {
        fetchQuestions();
    }, []);

    const header = () => <Header onNewQuestionClick={() => setCurrentPage("questionForm")}/>;

    switch (currentPage){
        case "home":
            if(questions){
                return (
                    <div className="App">
                        {header()}
                        <div className={"content"}>
                            {questions.map((question,i) => <Question key={i} id={question.id} title={question.title} description={question.description} timePosted={question.created} answers={question.answers}/>)}
                        </div>
                    </div>
                );
            }
            break;
        case "questionForm":
            return (
                <div>
                    {header()}
                    <QuestionForm onSubmitQuestion={() => {
                        setCurrentPage("home");
                        fetchQuestions();
                    }}/>
                </div>
            )
    }
}

export default App;
