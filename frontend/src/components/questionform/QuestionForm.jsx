import {useState} from "react";
import "./QuestionForm.css";

function QuestionForm({onSubmitQuestion}){
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const submitQuestion = async () => {
        await fetch("/questions/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: title,
                description: description,
                created: new Date()
            })
        })
        onSubmitQuestion();
    }

    return (
        <div className={"questionForm"}>
            <div className={"questionFormContainer"}>
                <div className={"inputField"}>
                    <label htmlFor={"titleInput"}>Title:</label>
                    <input type={"text"} id={"titleInput"} onChange={(e) => setTitle(e.target.value)} autoComplete={"off"}/>
                </div>
                <div className={"inputField"}>
                    <label htmlFor={"descriptionInput"}>Description:</label>
                    <input type={"text"} id={"descriptionInput"} onChange={(e) => setDescription(e.target.value)} autoComplete={"off"}/>
                </div>
                <button className={"button"} onClick={submitQuestion}>Submit question</button>

            </div>
        </div>
    );
}

export default QuestionForm;