package com.ram.projects.expensemanager.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.ram.projects.expensemanager.dto.UserDto;

@RestController
@RequestMapping("/expmgr")
public class UserController {

   @GetMapping("/ping/{pong}")
   public String  ping(@PathVariable("pong") String pong){
      return pong;
   }

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
