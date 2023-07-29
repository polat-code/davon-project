import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./Main";
import User_detail from "./User_detail";
import { ToastContainer } from "react-toastify";
//import User_detail from "./User_detail";

function App() {
  return (
    <>
    
    <BrowserRouter>
    <ToastContainer />
      <Routes>
        <Route exact path="/" element={<Main />}></Route>
        <Route path="/details" element={<User_detail />}></Route>
      </Routes>
    </BrowserRouter>
    </>
    
  );
}

export default App;
