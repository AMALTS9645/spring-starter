```java
//code-start
package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }
}
//code-end

package com.example.loginapi.controller;

import com.example.loginapi.model.User;
import com.example.loginapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Handles user login requests.
     *
     * @param user The user credentials.
     * @return A ResponseEntity indicating login success or failure.
     */
    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        // Security: Proper validation should be implemented on user inputs to prevent injection attacks.
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }
}

package com.example.loginapi.model;

public class User {
    private String username;
    private String password;

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.loginapi.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * Authenticates user credentials
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if credentials are valid, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // Security: Ensure this method checks credentials against a secure data source.
        // This is a placeholder implementation. Please replace it with the actual authentication logic.
        return "admin".equals(username) && "password".equals(password);
    }
}

package com.example.loginapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException across the whole application.
     *
     * @param ex The IllegalArgumentException instance.
     * @return A ResponseEntity with the error message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Specific exception handling with logging.
        // Implement logging logic here.
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
```