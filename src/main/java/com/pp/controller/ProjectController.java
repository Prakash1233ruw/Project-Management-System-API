package com.pp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.model.Project;
import com.pp.service.IProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
@Tag(name = "ProjectManagementSystem API", description = "API to Store  Projects")
public class ProjectController {

    @Autowired
    IProjectService service;

    @Operation(summary = "Get all projects", description = "Retrieve a list of all projects.")
    @GetMapping("/projectsList")
    public ResponseEntity<List<Project>> fetchProjectsData() {
        List<Project> projectList = service.fetchAllProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @Operation(summary = "Register a project", description = "Register a new project.")
    @PostMapping("/registerProject")
    public ResponseEntity<String> registerProject(@Valid @RequestBody Project project) {
        String message = service.saveTheProject(project);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a project", description = "Update an existing project by ID.")
    @PutMapping("/updateProject/{id}")
    public ResponseEntity<String> updateExistingProject(@Valid @RequestBody Project project, @PathVariable Integer id) {
        String message = service.updateProject(project, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Get a project by ID", description = "Retrieve a project by its ID.")
    @GetMapping("/oneProject/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Integer id) {
        Project project = service.fetchSingleProject(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @Operation(summary = "Delete a project", description = "Delete a project by its ID.")
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer id) {
        String message = service.deleteProject(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Register multiple projects", description = "Register multiple projects at once.")
    @PostMapping("/load")
    public ResponseEntity<String> registerProjects(@RequestBody List<Project> projects) {
        String message = service.registerAllProjects(projects);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
