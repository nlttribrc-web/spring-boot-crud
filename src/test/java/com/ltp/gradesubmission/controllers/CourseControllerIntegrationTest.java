package com.ltp.gradesubmission.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @BeforeEach
    void setup() throws Exception {

        String loginRequest = """
        {
            "username": "username",
            "password": "password"
        }
        """;

        String response = mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(response);

        jwtToken = jsonNode.get("token").asText();
    }

    @Test
    void testGetCourseById() throws Exception {

        mockMvc.perform(get("/course/1")
            .header("Authorization", "Bearer " + jwtToken))
            .andExpect(status().isOk());
    }

    @Test
    void testCreateCourse() throws Exception {

        String courseJson = """
        {
            "subject": "Magic Combat",
            "code": "MC-01",
            "description": "Magic Combat"
        }
        """;

        mockMvc.perform(post("/course")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject").value("Magic Combat"))
                .andExpect(jsonPath("$.code").value("MC-01"))
                .andExpect(jsonPath("$.description").value("Magic Combat"));
    }

    @Test
    void testDeleteCourse() throws Exception {

        mockMvc.perform(delete("/course/1")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllCourses() throws Exception {

        mockMvc.perform(get("/course/all")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }
    
}
