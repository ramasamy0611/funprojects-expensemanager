package com.ram.projects.expensemanager.rest;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.domain.user.IUserManager;
import com.ram.projects.expensemanager.rest.converter.UserInputConverter;
import com.ram.projects.expensemanager.rest.converter.UserOutputConverter;
import com.ram.projects.expensemanager.rest.process.RestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final RestProcessor restProcessor;
    private final IUserManager userManager;
    private final UserInputConverter userInputConverter;
    private final UserOutputConverter userOutputConverter;

    public UserController(IUserManager userManager, RestProcessor restProcessor, UserInputConverter userInputConverter, UserOutputConverter userOutputConverter) {
        this.userManager = userManager;
        this.restProcessor = restProcessor;
        this.userInputConverter = userInputConverter;
        this.userOutputConverter = userOutputConverter;
    }

    @PostMapping(path = "/add",
            consumes = "application/json",
            produces = "application/json")
    public CompletableFuture<ResponseEntity<User>> addUser(@RequestBody User user) {
        LOGGER.info("[ExpMgrUser:] addUser {}", user);
        return restProcessor.process("Add a new User", user, userInputConverter, userOutputConverter, expmgrUser -> userManager.addUser((ExpMgrUser) expmgrUser));
    }

//TODO Remove user
//Update user data

}
