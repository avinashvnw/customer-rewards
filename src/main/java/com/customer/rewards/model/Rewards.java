package com.customer.rewards.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rewards {
    private long CustomerId;
    private long lastMonthRewardPoints;
    private long lastSecondMonthRewardPoints;
    private long lastThirdMonthRewardPoints;
    private long totalRewards;
}
