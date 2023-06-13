package com.studentcompanion.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
public class Course
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String classroom;
	private int semester;
	private String days;
	private String times;


	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private List<User> users;

	@JsonIgnore
	@OneToMany(mappedBy = "course")
	private List<CourseComment> comments;

	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;

	public Course()
	{
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public List<CourseComment> getComments() {
		return comments;
	}

	public void setComments(List<CourseComment> comments) {
		this.comments = comments;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}
