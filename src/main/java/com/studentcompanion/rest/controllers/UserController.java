package com.studentcompanion.rest.controllers;

import com.studentcompanion.rest.models.*;
import com.studentcompanion.rest.services.UserService;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    CourseCommentRepository courseCommentRepository;
    @Autowired
    ProfessorCommentRepository professorCommentRepository;

    public UserController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    boolean auth = false;

    @GetMapping("/users")
    public List <UserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/generate")
    public Token generateToken() {

        Token token = new Token(UUID.randomUUID().toString());

        return token;
    }

    @GetMapping("/get-by-username/{username}")
    public User getUserByUsername(@PathVariable String username){return userService.findUserByUsername(username);}

    @GetMapping("/get-courses-by-username/{username}")
    public List<Course> getCoursesByUsername(@PathVariable String username){return userService.findCoursesByUsername(username);}

    @GetMapping("/getTokens")
    public List<Token> getAllTokens (){return userService.getTokens();}

    @PostMapping("/updateToken")
    public User handlePostRequest(@RequestBody String requestBody) {
        //String responseBody = "OK";
        Object o1 = JSONValue.parse(requestBody);
        JSONObject jsonObj = (JSONObject) o1;
        String userID = (String) jsonObj.get("id");
        String token = (String) jsonObj.get("token");

        return userService.updateUserToken(Integer.parseInt(userID), token);
    }

    @PostMapping("/validateToken")
    public String validate(@RequestBody String requestBody)
    {
        JSONObject jsonObj = (JSONObject) JSONValue.parse(requestBody);
        String token = (String) jsonObj.get("token");

        //System.out.println(String.format("{\"result\":\"%s\"}", (tokenRepository.findTokenByString(token) != null)));
        return String.format("{\"result\":\"%s\"}", (tokenRepository.findTokenByString(token) != null));
    }

    @PostMapping("/getCourseComments")
    public CourseComment getCourseComments(@RequestBody String requestBody){
        JSONObject jsonObj = (JSONObject) JSONValue.parse(requestBody);
        String courseComment = (String) jsonObj.get("comment");
        int courseScore = Integer.parseInt(String.valueOf(jsonObj.get("rating")));
        String token = (String) jsonObj.get("token");
        String course = (String) jsonObj.get("course");
        System.out.println("token: "+ token);

        return userService.addComment(courseComment, courseScore, token, course);
    }

    @PostMapping("/getProfessorComments")
    public ProfessorComment getProfessorComments(@RequestBody String requestBody){
        JSONObject jsonObj = (JSONObject) JSONValue.parse(requestBody);
        String professorComment = (String) jsonObj.get("comment");
        String professor = (String) jsonObj.get("professor");
        int courseScore = Integer.parseInt(String.valueOf(jsonObj.get("rating")));
        String token = (String) jsonObj.get("token");
        System.out.println("token: "+ token);

        return userService.addProfessorComment(professorComment, professor,courseScore, token);
    }

    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){return userService.getAllCourses();}

    @GetMapping("/getAllProfessors")
    public List<Professor> getAllProfessors(){return userService.getAllProfessors();}

    @PostMapping("/getCommentsByCourse")
    public List<CourseComment> getCommentsByCourse(@RequestBody String requestBody){
        JSONObject jsonObj = (JSONObject) JSONValue.parse(requestBody);
        int courseID = Integer.parseInt(String.valueOf(jsonObj.get("id_course")));

        List<CourseComment> comments = courseCommentRepository.findCommentsByCourse(courseID);

        return comments;
    }

    @PostMapping("/getCommentsByProfessor")
    public List<ProfessorComment> getCommentsByProfessor(@RequestBody String requestBody){
        JSONObject jsonObj = (JSONObject) JSONValue.parse(requestBody);
        int professorID = Integer.parseInt(String.valueOf(jsonObj.get("id_professor")));

        List<ProfessorComment> comments = professorCommentRepository.findCommentsByProfessor(professorID);

        return comments;
    }

    @PostMapping("/updateCourseRating")
    public CourseComment updateCourseRating(@RequestBody String requestBody) {
        //String responseBody = "OK";
        Object o1 = JSONValue.parse(requestBody);
        JSONObject jsonObj = (JSONObject) o1;
        int commentID = Integer.parseInt((String.valueOf(jsonObj.get("id"))));
        int courseScore = Integer.parseInt(String.valueOf(jsonObj.get("rating")));
        boolean voted = (boolean) jsonObj.get("voted");

        return userService.updateCourseRating(commentID, courseScore, voted);
    }

    @PostMapping("/updateProfessorRating")
    public ProfessorComment updateProfessorRating(@RequestBody String requestBody) {
        //String responseBody = "OK";
        Object o1 = JSONValue.parse(requestBody);
        JSONObject jsonObj = (JSONObject) o1;
        int commentID = Integer.parseInt((String.valueOf(jsonObj.get("id"))));
        int professorScore = Integer.parseInt(String.valueOf(jsonObj.get("rating")));
        boolean voted = (boolean) jsonObj.get("voted");

        return userService.updateProfessorRating(commentID, professorScore, voted);
    }

    @PostMapping("/getVotedBoolean")
    public boolean getVotedBoolean(@RequestBody String requestBody){
        Object o1 = JSONValue.parse(requestBody);
        JSONObject jsonObj = (JSONObject) o1;
        int commentID = Integer.parseInt((String.valueOf(jsonObj.get("id"))));
        return userService.getCommentVotedBoolean(commentID);
    }

    @PostMapping("/getProfessorVotedBoolean")
    public boolean getProfessorVotedBoolean(@RequestBody String requestBody){
        Object o1 = JSONValue.parse(requestBody);
        JSONObject jsonObj = (JSONObject) o1;
        int commentID = Integer.parseInt((String.valueOf(jsonObj.get("id"))));
        return userService.getProfessorCommentVotedBoolean(commentID);
    }

}
