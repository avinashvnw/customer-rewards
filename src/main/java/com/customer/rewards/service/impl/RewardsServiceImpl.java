package com.customer.rewards.service.impl;

import com.customer.rewards.constants.Constants;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.model.Rewards;
import com.customer.rewards.repository.TransactionRepository;
import com.customer.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Rewards calculateRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.DAYS_IN_MONTHS);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.DAYS_IN_MONTHS);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.DAYS_IN_MONTHS);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllTransactionDateBetweenByCustomerID(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllTransactionDateBetweenByCustomerID(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllTransactionDateBetweenByCustomerID(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;
    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > Constants.FIRST_REWARD_LIMIT && t.getTransactionAmount() <= Constants.SECOND_REWARD_LIMIT) {
            return Math.round(t.getTransactionAmount() - Constants.FIRST_REWARD_LIMIT);
        } else if (t.getTransactionAmount() > Constants.SECOND_REWARD_LIMIT) {
            return Math.round(t.getTransactionAmount() - Constants.SECOND_REWARD_LIMIT) * 2
                    + (Constants.SECOND_REWARD_LIMIT - Constants.FIRST_REWARD_LIMIT);
        } else
            return 0l;

    }

    private Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }
}
