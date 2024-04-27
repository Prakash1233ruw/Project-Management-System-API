package com.pp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.controller.ProjectController;
import com.pp.model.Project;
import com.pp.service.IProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProjectService projectService;
    
    @Autowired
    private ObjectMapper objectMapper;

    private Project project;
    private List<Project> projectList;

    @BeforeEach
    void setUp() {
        project = new Project(1, "Project 1", "Description 1", LocalDate.of(2023, 4, 1), LocalDate.of(2023, 6, 1));
        projectList = Arrays.asList(project, new Project(2, "Project 2", "Description 2", LocalDate.of(2023, 5, 1), LocalDate.of(2023, 7, 1)));
    }

    @Test
    void fetchProjectsData() throws Exception {
        when(projectService.fetchAllProjects()).thenReturn(projectList);

        mockMvc.perform(get("/api/projectsList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Project 1")))
                .andExpect(jsonPath("$[0].description", is("Description 1")))
                .andExpect(jsonPath("$[0].startDate", is("2023-04-01")))
                .andExpect(jsonPath("$[0].endDate", is("2023-06-01")));
    }

    @Test
    void registerProject() throws Exception {
        when(projectService.saveTheProject(any(Project.class))).thenReturn("Project saved with id 1");

        mockMvc.perform(post("/api/registerProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(project)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Project saved with id 1")));
    }

    @Test
    void updateExistingProject() throws Exception {
        when(projectService.updateProject(any(Project.class), anyInt())).thenReturn("Project with ID 1 is updated");

        mockMvc.perform(put("/api/updateProject/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Project with ID 1 is updated")));
    }

    @Test
    void getProject() throws Exception {
        when(projectService.fetchSingleProject(anyInt())).thenReturn(project);

        mockMvc.perform(get("/api/oneProject/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Project 1")))
                .andExpect(jsonPath("$.description", is("Description 1")))
                .andExpect(jsonPath("$.startDate", is("2023-04-01")))
                .andExpect(jsonPath("$.endDate", is("2023-06-01")));
    }

    @Test
    void deleteProject() throws Exception {
        when(projectService.deleteProject(anyInt())).thenReturn("Project with ID 1 is deleted");

        mockMvc.perform(delete("/api/deleteProject/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Project with ID 1 is deleted")));
    }

    @Test
    void registerProjects() throws Exception {
        when(projectService.registerAllProjects(any(List.class))).thenReturn("Projects loaded successfully");

        mockMvc.perform(post("/api/load")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(projectList)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Projects loaded successfully")));
    }

    private String asJsonString(Object obj) {
        try {
           return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}