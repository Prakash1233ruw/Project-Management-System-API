package com.pp.service;

import java.util.List;

import com.pp.model.Project;

public interface IProjectService 
{
public List<Project> fetchAllProjects();

public String saveTheProject(Project project);

public String updateProject(Project project,Integer id);

public Project fetchSingleProject(Integer id);

public String deleteProject(Integer id);

public String registerAllProjects(List<Project> projects);


}
