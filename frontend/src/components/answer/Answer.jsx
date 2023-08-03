import "./Answer.css";

function Answer({answerDescription, timeAnswered}){
    const answeredDate = new Date(timeAnswered);

    return (
        <div className={"answerContainer"}>
            <div className={"answerHeader"}>
                <p className={"answerDate"}>{answeredDate.getFullYear() + " " + answeredDate.getMonth() + " " + answeredDate.getDate() + ", " + answeredDate.getHours() + ":" + answeredDate.getMinutes()}</p>
            </div>
            <div className={"answerBody"}>
                <p className={answerDescription}>{answerDescription}</p>
            </div>
        </div>
    );
}

export default Answer;