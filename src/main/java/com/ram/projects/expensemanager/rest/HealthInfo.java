package com.ram.projects.expensemanager.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/health")
public class HealthInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthInfo.class);

    @GetMapping("/ping/{pong}")
    public String ping(@PathVariable("pong") String pong) {
        LOGGER.debug("ping" + pong);
        return pong;
    }

}
