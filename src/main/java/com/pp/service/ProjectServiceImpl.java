package com.pp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pp.advice.ProjectNotFoundException;
import com.pp.dao.ProjectRepository;

import com.pp.model.Project;



@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public List<Project> fetchAllProjects() {
        return (List<Project>) repository.findAll();
    }

    @Override
    public String saveTheProject(Project project) {
         Project savedProject = repository.save(project);
         return "Project saved with id "+savedProject.getId();
    }

    @Override
    public String updateProject(Project project, Integer id) {
        Project oldProject = repository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Unable to update project. Project with ID " + id + " not found."));

        // Update the project details
        oldProject.setName(project.getName());
        oldProject.setDescription(project.getDescription());
        oldProject.setStartDate(project.getStartDate());
        oldProject.setEndDate(project.getEndDate());

        // Save the updated project
        repository.save(oldProject);

        return "Project with ID " + id + " is updated";
    }

    @Override
    public Project fetchSingleProject(Integer id) {
        Optional<Project> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found."));
    }

    @Override
    public String deleteProject(Integer id) {
        Optional<Project> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return "Project with ID " + id + " is deleted";
        } else {
            throw new ProjectNotFoundException("Unable to delete project. Project with ID " + id + " not found.");
        }
    }

    @Override
    public String registerAllProjects(List<Project> projects) {
        repository.saveAll(projects);
        return "Projects loaded successfully";
    }
}
