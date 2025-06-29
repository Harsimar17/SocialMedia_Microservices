package com.post.service.PostService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.post.service.PostService.service.PostService_IF;
import com.socialmediaapp.required.PostDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/post-service")
public class PostController {
    @Autowired
    PostService_IF postService;
    
    @PostMapping("/user/{uid}/category/{cid}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDetails, @PathVariable("uid") int userId,
            @PathVariable("cid") int categoryId) throws Exception {
        return new ResponseEntity<PostDto>(postService.createPost(postDetails, userId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/getUserPosts/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") int userId) {
        return new ResponseEntity<List<PostDto>>(postService.getAllPostByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/getPostByCategory/{cid}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int cid) {
        return new ResponseEntity<List<PostDto>>(postService.getPostByCategory(cid), HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDto>> getAllPost() {
        return new ResponseEntity<List<PostDto>>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/getSpecificPost/{pid}")
    public ResponseEntity<PostDto> getPostbyId(@PathVariable("pid") int pid) {
        return new ResponseEntity<PostDto>(postService.getPost(pid), HttpStatus.OK);
    }

    @PutMapping("/updatePost/{pid}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto pd, @PathVariable("pid") int pid) {
        return new ResponseEntity<PostDto>( postService.updatePost(pd, pid), HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{pid}")
    public void deletePostById(@PathVariable("pid") int pid) throws IOException {
        postService.deletePost(pid);
    }

    @GetMapping("/search/post/{key}")
    public ResponseEntity<List<PostDto>> searchByKeyWords(@PathVariable String key) {
        List<PostDto> li = postService.searchPost(key);
        return new ResponseEntity<List<PostDto>>(li, HttpStatus.OK);
    }
}