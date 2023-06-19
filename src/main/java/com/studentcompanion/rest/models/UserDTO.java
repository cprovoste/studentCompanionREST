package com.studentcompanion.rest.models;

public class UserDTO {

    private int userDTO_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public UserDTO(int id, String username, String password, String firstName, String lastName) {
        this.userDTO_id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDTO() {

    }

    public int getId() {
        return userDTO_id;
    }

    public void setId(int id) {
        this.userDTO_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
