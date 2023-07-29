import React, { useEffect, useState } from "react";
import { addNewUser, updateUser } from "./helper/api";
import { useLocation, useNavigate } from "react-router-dom";

const User_form = () => {
  const [id,setId]  = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [telephone, setTelephone] = useState("");
  const [studentNumber, setStudentNumber] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const [isUpdateUser, setIsUpdateUser] = useState(false);

  useEffect( () => {
    
    const data = location.state;
    if(data) {
      setId(data.userId);
      setName(data.name);
      setStudentNumber(data.studentNumber)
      setSurname(data.surname);
      setTelephone(data.telephone);
      setIsUpdateUser(true);
    }
  }, []);

  const handleSaveButton = async() => {

    const data = {name,surname,telephone,studentNumber};
    const response = await addNewUser(data);
    if(response.status === 406) {
        
    }
    if(response.status === 200) {
      navigate('/');
    }
  }

  const handleUpdateButton = async () => {
    const data = {id,name,surname,telephone,studentNumber};
    const response = await updateUser(data);
    if(response.status === 406) {

    }
    if(response.status === 200) {
      navigate('/');
    }
  }

  return (
    <div className="container">
      <div className="form-container">
        <form>
          <div className="form-row">
            <div className="form-group col-md-6">
              <label htmlFor="inputEmail4">Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                placeholder="Name"
                onChange={(e) => {
                  setName(e.target.value);
                }}
                value={name}
              />
            </div>
            <div className="form-group col-md-6">
              <label htmlFor="inputPassword4">Surname</label>
              <input
                type="text"
                className="form-control"
                id="surname"
                placeholder="Surname"
                onChange={(e) => {
                  setSurname(e.target.value);
                }}
                value={surname}
              />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group col-md-6">
              <label htmlFor="inputEmail4">Telephone</label>
              <input
                type="tel"
                className="form-control"
                id="telephone"
                placeholder="Telephone"
                onChange={(e) => {
                  setTelephone(e.target.value);
                }}
                value={telephone}
              />
            </div>
            <div className="form-group col-md-6">
              <label htmlFor="inputPassword4">Student Number</label>
              <input
                type="text"
                className="form-control"
                id="student-number"
                placeholder="Student Number"
                onChange={(e) => {
                  setStudentNumber(e.target.value);
                }}
                value={studentNumber}
              />
            </div>
          </div>
          <div id="button-save">
            <button type="button" className="btn btn-primary btn-block" onClick={isUpdateUser ? handleUpdateButton : handleSaveButton}>
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};


export default User_form;
