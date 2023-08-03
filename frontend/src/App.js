import './App.css';
import Question from "./components/question/Question";
import Header from "./components/header/Header";

function App() {
  return (
    <div className="App">
        <Header/>
        <div className={"content"}>
            <Question title={"asd"} description={"asdasd"} answers={[1,2,3,4]} timePosted={"2023-08-03T10:30:00-01:00"}/>
        </div>
    </div>
  );
}

export default App;
