package com.propfile.profile.controller;

import com.propfile.profile.model.Post;
import com.propfile.profile.model.User;
import com.propfile.profile.repository.PostRepository;
import com.propfile.profile.service.PostService;
import com.propfile.profile.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@CrossOrigin("\"http://localhost:8081/\"")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostRepository postRepository;



    @GetMapping("/api/users/posts")
    public ResponseEntity<Post> getAllPosts(){
        return new ResponseEntity(postService.getPosts(), HttpStatus.OK);
    }

//    @PostMapping("/api/users/{id}/posts")
//    public ResponseEntity<Post> savePostToUser(@PathVariable(value = "id") Long id,
//                                               @RequestBody Post post){
//        return new ResponseEntity(userService.findUserById(id).map(user -> {
//            post.setUser(user);
//            return postService.savePost(post);
//        }), HttpStatus.CREATED);
//    }

    @PostMapping("/api/users/{id}/posts")
    public ResponseEntity<Post> savePostToUser(@PathVariable(value = "id") Long id,
                                               @RequestBody Post post){
        User user = userService.findUserById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        post.setUser(user);
        postService.savePost(post);
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/users/posts/{id}")
    public ResponseEntity<?> datePoster(@PathVariable(value = "id") Long id){
        Optional<Post> post = postRepository.findById(id);
        Post deletePost = post.orElse(null);

        if(deletePost == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = deletePost.getUser();
        user.getPosts().remove(deletePost);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> datePosterr(@PathVariable(value = "id") Long id) {
        postService.del2( id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/api/users/posts/{id}")
    public ResponseEntity<?> updateLikes(@PathVariable(value = "id") Long id){
        Optional<Post> post = postRepository.findById(id);
        Post foundPost = post.orElse(null);

        if(foundPost == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int count = foundPost.getLikes();
        foundPost.setLikes(++count);
        postService.updatePost(foundPost);
        return new ResponseEntity<>(foundPost, HttpStatus.OK);
    }
}
