import React from 'react'

const User_form = () => {
  return (
    <div class="container">
        <div class="form-container">
            <form >
                <div class="form-row">
                    <div class="form-group col-md-6">
                      <label for="inputEmail4">Name</label>
                      <input type="text" class="form-control" id="name" placeholder="Name" />
                    </div>
                    <div class="form-group col-md-6">
                      <label for="inputPassword4">Surname</label>
                      <input type="text" class="form-control" id="surname" placeholder="Surname" />
                    </div>
                  </div>
                  <div class="form-row">
                    <div class="form-group col-md-6">
                      <label for="inputEmail4">Telephone</label>
                      <input type="tel" class="form-control" id="telephone" placeholder="Telephone" />
                    </div>
                    <div class="form-group col-md-6">
                      <label for="inputPassword4">Student Number</label>
                      <input type="text" class="form-control" id="student-number" placeholder="Student Number" />
                    </div>
                  </div>
                  <div id="button-save">
                    <button type="button" class="btn btn-primary btn-block" >Save</button>
                  </div>
                  
              </form>
        </div>
        
    </div>
  )
}

export default User_form
