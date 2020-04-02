package com.ram.projects.expensemanager.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.ram.projects.expensemanager.dto.UserDto;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT+"/user")
public class UserController {
   private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

   @PostMapping(path="/user/add",
                consumes="application/json",
                produces="application/json")
   @ResponseBody
   public UserDto addAUser(@RequestBody UserDto userDto){
   //TODO convert Dto  to entity to save
   //TODO Call userRepo to save the user data
   //TODO  return saved User data
return null;
   }
//TODO Remove user
//Update user data
 
}
