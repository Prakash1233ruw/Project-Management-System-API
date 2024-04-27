package com.pp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pp.model.Project;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

}
