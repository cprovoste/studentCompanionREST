package com.studentcompanion.rest.models;

import javax.persistence.*;

@Entity
@Table(name = "ProfessorComments")
public class ProfessorComment {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int professorComment_id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name="professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private int rating;

    @Column
    private boolean voted = false;

    public ProfessorComment() {}

    public int getId() {
        return professorComment_id;
    }

    public void setId(int id) {
        this.professorComment_id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
