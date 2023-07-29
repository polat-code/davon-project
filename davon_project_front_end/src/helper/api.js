import axios from "axios";


const baseUrl = "http://localhost:8080/api/v1/user";

export const getAllUsers = () => {
    const users = 
            axios.get(baseUrl + "/all-users")
            .then((response) => {
                return response.data
            })
            .catch((error) => {
                console.error(error);
            });
    return users;
};

export const addNewUser = (newUser) => {
    const response = 
            axios.post(baseUrl + "/add-user" , newUser)
            .then((response) => {
                 return response})
            .catch((error) => {
                return error;
            });

    return response;

}


export const updateUser = (updatedUser) => {
    const response = 
                axios.put(baseUrl + "/update-user", updatedUser)
                .then((response) =>{
                    return response
                })
                .catch((error) => {
                    return error;
                });

    return response;
}


export const deleteUser = (userId) => {
    const response = 
                axios.delete(baseUrl + "/delete-user/" + userId)
                .then( (response) => {
                    return response
                })
                .catch( (error) => {
                    return error;
                })
    return response;
}