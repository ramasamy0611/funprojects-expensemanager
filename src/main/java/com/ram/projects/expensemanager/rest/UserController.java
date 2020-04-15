package com.ram.projects.expensemanager.rest;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.domain.user.IUserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private IUserManager userManager;

    public UserController(IUserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(path = "/user/add",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public CompletableFuture<ExpMgrUser> addUser(@RequestBody ExpMgrUser expMgrUser) {
        LOGGER.info("ExpMgrUser {}",expMgrUser);
        return userManager.addUser(expMgrUser)
                .exceptionally(throwable -> handleFailure("Something went wrong while adding users to the database", throwable));
    }

    private ExpMgrUser handleFailure(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
        return null;
    }
//TODO Remove user
//Update user data

}
