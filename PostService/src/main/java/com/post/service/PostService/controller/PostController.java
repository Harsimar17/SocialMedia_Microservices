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

import com.clone.DTOs.PostDto;
import com.post.service.PostService.service.PostService_IF;

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
        PostDto np = postService.createPost(postDetails, userId, categoryId);
        return new ResponseEntity<PostDto>(np, HttpStatus.CREATED);
    }

    @GetMapping("/user/{uid}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int uid) {
        List<PostDto> li = postService.getAllPostByUser(uid);
        return new ResponseEntity<List<PostDto>>(li, HttpStatus.OK);
    }

    @GetMapping("/category/{cid}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int cid) {
        List<PostDto> li = postService.getPostByCategory(cid);
        return new ResponseEntity<List<PostDto>>(li, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPost() {
    	List<PostDto> allPosts = this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);

    }

    @GetMapping("/post/{pid}")
    public ResponseEntity<PostDto> getPostbyId(@PathVariable("pid") int pid) {
        PostDto p = postService.getPost(pid);
        return new ResponseEntity<PostDto>(p, HttpStatus.OK);
    }

    @PutMapping("/updatePost/{pid}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto pd, @PathVariable int pid) {
        PostDto np = postService.updatePost(pd, pid);
        return new ResponseEntity<PostDto>(np, HttpStatus.OK);
    }

    @DeleteMapping("/post/{pid}")
    public void deletePostById(@PathVariable int pid) throws IOException {
        postService.deletePost(pid);
    }

    @GetMapping("/search/post/{key}")
    public ResponseEntity<List<PostDto>> searchByKeyWords(@PathVariable String key) {
        List<PostDto> li = postService.searchPost(key);
        return new ResponseEntity<List<PostDto>>(li, HttpStatus.OK);
    }
}