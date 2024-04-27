package com.pp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pp.advice.ProjectNotFoundException;
import com.pp.dao.ProjectRepository;
import com.pp.model.Project;
import com.pp.service.ProjectServiceImpl;

@SpringBootTest
class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void testFetchAllProjects() {
        // Mocking repository
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", "Description 1", LocalDate.now(), LocalDate.now()));
        projects.add(new Project(2, "Project 2", "Description 2", LocalDate.now(), LocalDate.now()));
        when(projectRepository.findAll()).thenReturn(projects);

        // Calling service method
        List<Project> result = projectService.fetchAllProjects();

        // Verifying result
        assertEquals(projects, result);
    }

    @Test
    public void testSaveTheProject() {
        // Creating a project
        Project project = new Project(1, "Project 1", "Description 1", LocalDate.now(), LocalDate.now());

        // Mocking repository
        when(projectRepository.save(project)).thenReturn(project);

        // Calling service method
        String result = projectService.saveTheProject(project);

        // Verifying result
        assertEquals("Project saved with id 1", result);
    }

    @Test
    public void testUpdateProject() {
        // Creating a project
        Project project = new Project(1, "Updated Project", "Updated Description", LocalDate.now(), LocalDate.now());

        // Mocking repository
        Optional<Project> optional = Optional.of(new Project(1, "Project 1", "Description 1", LocalDate.now(), LocalDate.now()));
        when(projectRepository.findById(1)).thenReturn(optional);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Calling service method
        String result = projectService.updateProject(project, 1);

        // Verifying result
        assertEquals("Project with ID 1 is updated", result);
    }

    @Test
    public void testFetchSingleProject() {
        // Mocking repository
        Optional<Project> optional = Optional.of(new Project(1, "Project 1", "Description 1", LocalDate.now(), LocalDate.now()));
        when(projectRepository.findById(1)).thenReturn(optional);

        // Calling service method
        Project result = projectService.fetchSingleProject(1);

        // Verifying result
        assertEquals(optional.get(), result);
    }

    @Test
    public void testDeleteProject() {
        // Mocking repository
        Optional<Project> optional = Optional.of(new Project(1, "Project 1", "Description 1", LocalDate.now(), LocalDate.now()));
        when(projectRepository.findById(1)).thenReturn(optional);

        // Calling service method
        String result = projectService.deleteProject(1);

        // Verifying result
        assertEquals("Project with ID 1 is deleted", result);
    }

    @Test
    public void testRegisterAllProjects() {
        // Creating projects
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", "Description 1", LocalDate.now(), LocalDate.now()));
        projects.add(new Project(2, "Project 2", "Description 2", LocalDate.now(), LocalDate.now()));

        // Mocking repository
        when(projectRepository.saveAll(projects)).thenReturn(projects);

        // Calling service method
        String result = projectService.registerAllProjects(projects);

        // Verifying result
        assertEquals("Projects loaded successfully", result);
    }
}
