package com.user.service.UserService.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coreresources.required.JwtAuthRequest;
import com.coreresources.required.JwtAuthResponse;
import com.coreresources.required.UserDto;
import com.coreresources.required.JWTThings.JWTTokenHelper;
import com.user.service.UserService.services.UserService_IF;

@CrossOrigin
@RestController
@RequestMapping("/user-service")
public class UserController 
{
    @Autowired
    UserService_IF userService;
    
    @PostMapping("/generateToken")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception 
    {
    	userService.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
    	JWTTokenHelper tokenHelper = new JWTTokenHelper();
        UserDto user = userService.getUserByEmail(jwtAuthRequest.getUsername());
        String token = tokenHelper.generateToken(user.getEmail());
        
        JwtAuthResponse res = new JwtAuthResponse();
        res.setToken(token);
        res.setUser(user);
        
        return new ResponseEntity<JwtAuthResponse>(res, HttpStatus.OK);
    }
    
    @PostMapping("/registerUser")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto ut) {
        UserDto nut = userService.createuser(ut);
        return new ResponseEntity<UserDto>(nut, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateuser( @RequestBody UserDto ud, @PathVariable("id") int id) {
        UserDto nut = userService.updateuser(ud, id);
        return ResponseEntity.ok(nut);
    }

    @DeleteMapping("/{id}")
    public void deleteuser(@PathVariable("id") int id) {
        userService.deleteuser(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getallusers() {
        List<UserDto> li = userService.getall();
        return ResponseEntity.ok(li);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id) {
        UserDto u = userService.getUserbyId(id);
        return ResponseEntity.ok(u);
    }
    
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
        UserDto u = userService.getUserByEmail(email);
        return ResponseEntity.ok(u);
    }
    
    @GetMapping("/getByEmailAndPassword/{email}/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        UserDto u = userService.getUserByEmailAndPassword(email, password);
        return ResponseEntity.ok(u);
    }
}