package com.ram.projects.expensemanager.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/health")
public class HealthInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthInfo.class);

//    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/ping/{pong}")
    public ResponseEntity ping(@PathVariable("pong") String pong) {
        LOGGER.debug("ping" + pong);
        return ResponseEntity
                .ok()
                .body(pong);
    }

}
