import "./Question.css";
import {useEffect, useState} from "react";
import Answer from "../answer/Answer";

function Question({title, description, answerIds, timePosted}) {
    const postingDate = new Date(timePosted);
    const [answers, setAnswers] = useState(null);
    const [showAnswers, setShowAnswers] = useState(false);

    const fetchAnswers = async (answerIds) =>{

    }

    useEffect(() => {
        fetchAnswers(answerIds);
    }, [answerIds]);

    if(answers){
        return (
            <div className={"questionContainer"}>
                <div className={"questionHeader"}>
                    <p className={"questionTitle"}>{title}</p>
                    <p className={"answerDate"}>{postingDate.getFullYear() + " " + postingDate.getMonth() + " " + postingDate.getDate() + ", " + postingDate.getHours() + ":" + postingDate.getMinutes()}</p>
                </div>
                <div className={"questionBody"}>
                    <p className={"questionDescription"}>{description}</p>
                </div>
                <div className={"questionButtons"}>
                    <button className={"answerButton"}>Answer question</button>
                    <button className={"showAnswersButton"} onClick={() => setShowAnswers(!showAnswers)}>Show answers ({answers.length})</button>
                </div>
                {showAnswers && (
                    <div className={"answers"}>
                        {answers.map((answer, i) => <Answer key={i} username={answer.username} answerDescription={answer.answerDescription} timeAnswered={answer.timeAnswered}/>)}
                    </div>
                )}
            </div>
        );
    } else return (<div></div>);
}

export default Question;