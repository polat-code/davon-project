import React, { useEffect, useState } from 'react'
import UserItem from './userItem';
import { getAllUsers } from './helper/api';


const Main = () => {
  const [users, setUsers] = useState([]);
  
  const getAllUsersFromDb = () => {
    const response = getAllUsers();
    setUsers(response);
  }

  useEffect(() => {
    getAllUsersFromDb();
  })
  
  return (
    <div className="container">
       
    
        <div id="new-user-button-container">
            <button id="add-new-user-button"type="button" className="btn btn-success">Add New User</button>
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
                  {users.map()}
                  <UserItem />
                  <UserItem />
                  
                  
                  
                </tbody>
              </table>
        </div>
        
    </div>
  )
}

export default Main;
