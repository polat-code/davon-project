import React from 'react'

const UserItem = () =>  {
  return (
    <>
        <tr>
            <td>Özgürhan</td>
            <td>Polat</td>
            <td>05531521381</td>
            <td>260201035</td>
            <td><button type="button" className="btn btn-outline-primary" id='main-edit-button'>Edit</button>
                <button type="button" className="btn btn-outline-danger">Delete</button>
            </td>
        </tr>
    </>
  )
}

export default UserItem;
