import React, { useEffect, useState } from "react";
import UserItem from "./userItem";
import { getAllUsers } from "./helper/api";
import Header from "./Header";
import { useNavigate } from "react-router-dom";

const Main = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  const navigateUserDetail = () => {
    navigate("/details");
  }

  const getAllUsersFromDb = async () => {
    const response = await getAllUsers();
    setUsers(response);
  };

  useEffect(() => {
    getAllUsersFromDb();
  }, []);

  return (
    <div className="container">
      <Header />
      <div id="new-user-button-container">
        <button
          id="add-new-user-button"
          type="button"
          className="btn btn-success"
          onClick={navigateUserDetail}
        >
          Add New User
        </button>
      </div>

      <div id="table">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Surname</th>
              <th scope="col">Telephone</th>
              <th scope="col">Student Number</th>
              <th> </th>
            </tr>
          </thead>
          <tbody>
            {users?.map((user) => {
              return <UserItem key={user.userId} data={user} />;
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Main;
