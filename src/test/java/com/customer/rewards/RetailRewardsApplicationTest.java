package com.customer.rewards;

import com.customer.rewards.model.Customer;
import com.customer.rewards.service.RewardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RetailRewardsApplicationTest {

    @Autowired
    RewardsService rewardsService;


    @Test
    void contextLoads() {
    }


    @Test
    void testCalculateRewardsforAmountBelowFifty() {
        Customer customer = rewardsService.calculateRewardsByCustomerId(5);
        if (customer.getId() == 5) {
            Assertions.assertEquals(0, customer.getTotalRewardAmount());
        }
    }

    @Test
    void testCalculateRewardsforAmountAboveHundred() {
        Customer customer = rewardsService.calculateRewardsByCustomerId(7);
        if (customer.getId() == 7) {
            Assertions.assertEquals(70, customer.getTotalRewardAmount());
        }
    }

    @Test
    void testCalculateRewardsforAmountAboveFiftyBelowHundred() {
        Customer customer = rewardsService.calculateRewardsByCustomerId(3);
        if (customer.getId() == 3) {
            Assertions.assertEquals(5, customer.getTotalRewardAmount());
        }
    }

    @Test
    void testCalculateRewardsBeyoundThreeMonths() {
        Customer customer = rewardsService.calculateRewardsByCustomerId(7);
        if (customer.getId() == 7) {
            Assertions.assertEquals(0, customer.getTotalRewardAmount());
        }
    }

    @Test
    void testCalculateRewardsforMultipleTransactions() {
        Customer customer = rewardsService.calculateRewardsByCustomerId(1);
        if (customer.getId() == 1) {
            Assertions.assertEquals(170, customer.getTotalRewardAmount());
        }
    }
}
