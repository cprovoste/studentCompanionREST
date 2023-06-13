package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorCommentRepository  extends JpaRepository<ProfessorComment, Integer> {

    @Query(value = "SELECT * from professor_comments u WHERE u.professor_id = ?1",nativeQuery = true)
    List<ProfessorComment> findCommentsByProfessor(int professor_id);
}
