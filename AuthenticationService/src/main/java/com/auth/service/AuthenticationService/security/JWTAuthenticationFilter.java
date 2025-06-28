 package com.auth.service.AuthenticationService.security;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.service.AuthenticationService.callmicroservice.UserService;
import com.clone.DTOs.RoleDto;
import com.clone.DTOs.UserDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTTokenHelper jt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String reqtoken = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if (reqtoken != null && reqtoken.startsWith("Bearer")) {
           
            token = reqtoken.substring(7);
            try {
                username = jt.extractUsername(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	UserService userService = new UserService();
            UserDto ud = userService.getUserByEmail(username);
            Set<RoleDto> roles = ud.getSet();
            List<GrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(Collectors.toList());
            if (jt.validateToken(token, ud)) {
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(ud, null,
                		authorities);
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            } else {
                System.out.println("Jwt not valid");
            }
        } else {
//            System.out.println("Username is null");
        }
        filterChain.doFilter(request, response);
    }

}

