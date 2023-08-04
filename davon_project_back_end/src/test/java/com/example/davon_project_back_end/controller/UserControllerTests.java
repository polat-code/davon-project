package com.example.davon_project_back_end.controller;

import com.example.davon_project_back_end.dto.request.AddUserRequest;
import com.example.davon_project_back_end.dto.request.UpdateUserRequest;
import com.example.davon_project_back_end.dto.response.UserResponse;
import com.example.davon_project_back_end.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        List<UserResponse> users = new ArrayList<>();
        users.add(new UserResponse(1L,"Özgürhan", "Polat","05531521381","260201035"));
        users.add(new UserResponse(2L,"Kadir","Polat","05531521382","245042012"));
        when(userService.getAllUsers()).thenReturn(ResponseEntity.ok(users));
        when(userService.addNewUser(Mockito.any(AddUserRequest.class))).thenReturn(ResponseEntity.ok("User added successfully"));
        when(userService.updateUser(Mockito.any(UpdateUserRequest.class))).thenReturn(ResponseEntity.ok("User updated successfully"));
        when(userService.deleteUser(Mockito.anyLong())).thenReturn(ResponseEntity.ok("User deleted successfully"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/all-users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Özgürhan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Polat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].telephone").value("05531521381"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentNumber").value("260201035"));
    }

    @Test
    public void testAddNewUser() throws Exception {
        AddUserRequest addUserRequest = new AddUserRequest("Özgürhan","Polat", "05531521381","260201035");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/add-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addUserRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User added successfully"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "Özgürhan","Polat", "05531521381","260201035");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/update-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User updated successfully"));


    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/delete-user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User deleted successfully"));
    }
}
