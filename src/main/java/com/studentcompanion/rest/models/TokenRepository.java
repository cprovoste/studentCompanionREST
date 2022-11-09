package com.studentcompanion.rest.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT u from Token u WHERE u.token = ?1")
    Token getTokenData(String token);

}
