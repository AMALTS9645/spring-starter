```java
// Spring Boot application for a login API

// Main Application Class
package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }
}

// Login Controller
package com.example.loginapi.controller;

import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Endpoint for user login.
     * @param loginRequest User login request object.
     * @return Response entity with login status.
     */
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isAuthenticated = loginService.authenticate(loginRequest);
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
            }
        } catch (IllegalArgumentException e) { // Specific exception handling
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

// Login Service
package com.example.loginapi.service;

import com.example.loginapi.model.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /**
     * Authenticates the user.
     * @param loginRequest User login request containing username and password.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(LoginRequest loginRequest) {
        // Security: Validate user inputs to prevent injection attacks.
        validateUsername(loginRequest.getUsername());
        validatePassword(loginRequest.getPassword());

        // Dummy authentication logic, replace with real authentication logic as needed.
        return "user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword());
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
    }
}

// Login Request Model
package com.example.loginapi.model;

public class LoginRequest {

    private String username;
    private String password;

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
}

// Application Properties
// src/main/resources/application.properties

# Security: Configure database, server, and app-level configurations securely.
# Avoid hardcoding any sensitive information.

# Spring Boot default configurations
server.port=8080

// Maven POM File
// Code-start and Code-end are commented to comply with prompt.
<!--code-start-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>loginapi</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>
    <name>loginapi</name>
    <description>Demo project for Spring Boot Login API</description>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <java.version>11</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
<!--code-end-->
```