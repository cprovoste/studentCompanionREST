package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {
/*
    @Query("SELECT u from Token")
    List<Token> findAll();
*/
    @Query("SELECT u from Token u WHERE u.token = ?1")
    Token findTokenByString(String token);


}
