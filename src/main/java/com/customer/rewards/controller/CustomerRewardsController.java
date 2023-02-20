package com.customer.rewards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class CustomerRewardsController {

    @GetMapping
    public ResponseEntity<Object> getAllRewardsOfCustomer() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
