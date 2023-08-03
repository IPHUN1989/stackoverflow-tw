import {useState} from "react";
import "./AnswerForm.css";

function AnswerForm({questionId, onSubmitAnswer}){
    const [answer, setAnswer] = useState("");

    const submitAnswer = async () => {
        await fetch("/questions/ans", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                question_id: questionId,
                user_id: 1,
                description: answer,
                created: "asd",
                accepted: true
            })
        })
        window.location.reload();
    }

    return (
        <div className={"answerForm"}>
            <div className={"answerFormContainer"}>
                <div className={"inputField"}>
                    <label htmlFor={"answerInput"}>Your answer:</label>
                    <input type={"text"} id={"answerInput"} onChange={(e) => setAnswer(e.target.value)} autoComplete={"off"}/>
                </div>
                <button className={"button"} onClick={submitAnswer}>Submit answer</button>
            </div>
        </div>
    )
}

export default AnswerForm;