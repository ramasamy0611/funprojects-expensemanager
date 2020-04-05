package com.ram.projects.expensemanager.rest;

import com.ram.projects.expensemanager.db.entity.User;
import com.ram.projects.expensemanager.db.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRepository userRepository;

//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping(path = "/user/add",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public User addAUser(@RequestBody User user) {
        return userRepository.save(user);
    }
//TODO Remove user
//Update user data

}
