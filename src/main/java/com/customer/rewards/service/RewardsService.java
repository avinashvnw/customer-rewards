package com.customer.rewards.service;

import com.customer.rewards.model.Rewards;

public interface RewardsService {
    public Rewards calculateRewardsByCustomerId(Long customerId);
}
