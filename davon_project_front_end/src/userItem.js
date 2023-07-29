import React from "react";
import { useNavigate } from "react-router-dom";
import {deleteUser} from "./helper/api";

const UserItem = (prop) => {
  const data = prop.data;
  const navigate = useNavigate();
  const handleEditButton = async() => { 
    navigate('/details',{state:data})
  }

  const handleDeleteButton = async() =>{
    const id = data.userId;
    const response = await deleteUser(id);
    if(response.status === 200) {
      window.location.reload();
    }else if(response.status === 404) {

    }


  }

  
  return (
    <>
      <tr>
        <td>{data.name}</td>
        <td>{data.surname}</td>
        <td>{data.telephone}</td>
        <td>{data.studentNumber}</td>
        <td>
          <button
            type="button"
            className="btn btn-outline-primary"
            id="main-edit-button"
            onClick={handleEditButton}
          >
            Edit
          </button>
          <button type="button" className="btn btn-outline-danger" onClick={handleDeleteButton}>
            Delete
          </button>
        </td>
      </tr>
    </>
  );
};

export default UserItem;
