package com.studentcompanion.rest.models;

import javax.persistence.*;

@Entity
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String token_id;

    @Column(unique = true)
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    @OneToOne(mappedBy = "token")
    private User user;


    public String getId() {
        return token_id;
    }

    public void setId(String id) {
        this.token_id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
