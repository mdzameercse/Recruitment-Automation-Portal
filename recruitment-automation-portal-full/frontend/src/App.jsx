import React from "react";
import ResumeUpload from "./components/ResumeUpload";
import CandidateList from "./components/CandidateList";
function App(){ return (<div style={{fontFamily:"Arial, sans-serif", maxWidth:900, margin:"20px auto", padding:20}}><h2>Recruitment Automation Portal - Prototype</h2><ResumeUpload /><hr /><CandidateList /></div>); }
export default App;