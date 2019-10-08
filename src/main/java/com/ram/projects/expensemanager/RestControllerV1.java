package com.ram.projects.expensemanager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/expmgr")
public class RestControllerV1 {

   @GetMapping("/ping/{pong}")
   public String  ping(@PathVariable("pong") String pong){
      return pong;
   }
}
