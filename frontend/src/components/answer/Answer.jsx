function Answer({username, answerDescription, timeAnswered}){

    return (
        <div className={"answerContainer"}>
            <div className={"answerHeader"}>
                <p className={"answerUsername"}>Answer by: {username}</p>
                <p className={"answerDate"}>{timeAnswered.getFullYear() + " " + timeAnswered.getMonth() + " " + timeAnswered.getDate() + ", " + timeAnswered.getHours() + ":" + timeAnswered.getMinutes()}</p>
            </div>
            <div className={"answerBody"}>
                <p className={answerDescription}>{answerDescription}</p>
            </div>
        </div>
    );
}

export default Answer;