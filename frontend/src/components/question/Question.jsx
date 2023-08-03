import "./Question.css";
import {useEffect, useState} from "react";
import Answer from "../answer/Answer";
import AnswerForm from "../answerForm/AnswerForm";

function Question({id, title, description, answers, timePosted}) {
    const postingDate = new Date(timePosted);
    const [showAnswers, setShowAnswers] = useState(false);
    const [showAnswerForm, setShowAnswerForm] = useState(false);


    if(answers){
        return (
            <div className={"questionContainer"}>
                <div className={"questionHeader"}>
                    <p className={"questionTitle"}>Title: {title}</p>
                    <p className={"answerDate"}>{postingDate.getFullYear() + " " + postingDate.getMonth() + " " + postingDate.getDate() + ", " + postingDate.getHours() + ":" + postingDate.getMinutes()}</p>
                </div>
                <div className={"questionBody"}>
                    <p className={"questionDescription"}>{description}</p>
                </div>
                <div className={"questionButtons"}>
                    <button className={"button"} onClick={() => setShowAnswerForm(!showAnswerForm)}>Answer question</button>
                    <button className={"button"} onClick={() => setShowAnswers(!showAnswers)}>Show answers ({answers.length})</button>
                </div>
                {showAnswerForm && (
                    <AnswerForm questionId={id} onSubmitAnswer={() => setShowAnswerForm(false)}/>
                )}
                {showAnswers && (
                    <div className={"answers"}>
                        {answers.map((answer, i) => <Answer key={i} answerDescription={answer.description} timeAnswered={answer.created}/>)}
                    </div>
                )}
            </div>
        );
    } else return (<div></div>);
}

export default Question;