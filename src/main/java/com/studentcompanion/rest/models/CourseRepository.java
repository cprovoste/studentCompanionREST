package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long>
{
    @Query("SELECT u from Course u WHERE u.name = ?1")
    Course findCourseByName(String name);
}
