package com.customer.rewards.service;

import com.customer.rewards.model.Customer;

public interface RewardsService {
    Customer calculateRewardsByCustomerId(Integer customerId);
}
