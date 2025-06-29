//package com.auth.service.AuthenticationService.authcontroller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.auth.service.AuthenticationService.callmicroservice.UserService;
//import com.auth.service.AuthenticationService.security.CustomUserDetailsService;
//import com.auth.service.AuthenticationService.security.JWTTokenHelper;
//import com.auth.service.AuthenticationService.service.Service;
//import com.clone.DTOs.JwtAuthRequest;
//import com.clone.DTOs.JwtAuthResponse;
//import com.clone.DTOs.UserDto;
//
//@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class AuthController {
//	
//    @Autowired
//    JWTTokenHelper jwtTokenHelper;
//    
//    @Autowired
//    Service userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception 
//    {
//    	userService.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
//    	
//        UserDto user = userService.getUserByEmail(jwtAuthRequest.getUsername());
//        String token = jwtTokenHelper.generateToken(user.getEmail());
//        
//        JwtAuthResponse res = new JwtAuthResponse();
//        res.setToken(token);
//        res.setUser(user);
//        
//        return new ResponseEntity<JwtAuthResponse>(res, HttpStatus.OK);
//    }
//}
//package com;


