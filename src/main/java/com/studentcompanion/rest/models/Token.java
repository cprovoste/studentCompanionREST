package com.studentcompanion.rest.models;

import javax.persistence.*;

@Entity
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int token_id;

    @Column(unique = true)
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    @OneToOne(mappedBy = "token")
    private User user;


    public int getTokenId() {
        return token_id;
    }

    public void setTokenId(int id) {
        this.token_id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
