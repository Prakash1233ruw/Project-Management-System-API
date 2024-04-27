package com.pp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	@NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
	private String name;
	
	@NotBlank(message = "Description is required")
	@Column(name = "DESCRIPTION")
	private String description;
	
    @NotNull(message = "Start date is required")
	@Column(name = "STARTDATE")
	private LocalDate startDate;
	
    @NotNull(message = "End date is required")
	@Column(name = "ENDDATE")
	private LocalDate endDate;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public Project(Integer id, String name, String description, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Project() {
		super();
		System.out.println("Project Instantiated");
	}

	
	
	
	
	
	
}
