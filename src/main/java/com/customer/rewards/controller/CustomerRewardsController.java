package com.customer.rewards.controller;

import com.customer.rewards.model.Customer;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.RewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CustomerRewardsController {

    Logger logger = LoggerFactory.getLogger(CustomerRewardsController.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RewardsService rewardsService;

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<Customer> getRewardsByCustomerId(@PathVariable("customerId") Integer customerId) {
        logger.info("Starting  getRewardsByCustomerId in customer rewards controller");
        Customer customerRewards = rewardsService.calculateRewardsByCustomerId(customerId);
        if (customerRewards == null) {
            throw new RuntimeException("Invalid customer Id ");
        }
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }
}
