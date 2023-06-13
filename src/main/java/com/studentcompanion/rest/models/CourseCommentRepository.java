package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CourseCommentRepository extends JpaRepository<CourseComment, Integer> {

    @Query(value = "SELECT * from course_comments u WHERE u.course_id = ?1",nativeQuery = true)
    List<CourseComment> findCommentsByCourse(int course_id);
}
