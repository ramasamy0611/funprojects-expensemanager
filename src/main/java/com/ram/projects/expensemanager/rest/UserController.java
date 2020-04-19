package com.ram.projects.expensemanager.rest;

import com.ram.projects.expensemanager.domain.user.IUserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private IUserManager userManager;

    public UserController(IUserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(path = "/add",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        LOGGER.info("[ExpMgrUser:] addUser {}", user);
        return ResponseEntity
                .ok()
                .body(user);
        /*return userManager.addUser(user)
                .thenApply(expMgrUser1 -> ResponseEntity
                        .ok()
                        .headers(responseHeaders)
                        .body("{Success:True}"))
                .exceptionally(throwable -> handleFailure("Something went wrong while adding users to the database", throwable));
    */
    }

    private ResponseEntity<String> handleFailure(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{Failed:true}");
    }
//TODO Remove user
//Update user data

}
