package com.propfile.profile.controller;

import com.propfile.profile.model.Post;
import com.propfile.profile.model.User;
import com.propfile.profile.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081/")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> apiFindAllUser(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> apiFindAllPosts(){
        List<Post> posts = userService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User>findUser(@PathVariable("id") Long id){
        Optional<User> user = userService.findUserById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    
    @PostMapping("/api/post/save")
    public ResponseEntity<Post>savePostToUser(Long id, @RequestBody Post post){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post/save").toUriString());

        return ResponseEntity.created(uri).body(userService.savePostToUSer(1L,post));
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }





}