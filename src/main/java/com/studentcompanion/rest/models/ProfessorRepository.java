package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessorRepository extends JpaRepository<Professor, Long>
{
    @Query("SELECT u from Professor u WHERE u.name = ?1")
    Professor findProfessorByName(String name);
}
