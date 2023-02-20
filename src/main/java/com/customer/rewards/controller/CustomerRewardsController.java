package com.customer.rewards.controller;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.model.Rewards;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.RewardsService;
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

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RewardsService rewardsService;

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }
        Rewards customerRewards = rewardsService.calculateRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }
}
