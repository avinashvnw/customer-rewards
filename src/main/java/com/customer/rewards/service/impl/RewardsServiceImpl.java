package com.customer.rewards.service.impl;


import com.customer.rewards.model.Customer;
import com.customer.rewards.model.Transactions;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.RewardsService;
import com.customer.rewards.utils.RewardsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class RewardsServiceImpl implements RewardsService {

    Logger logger = LoggerFactory.getLogger(RewardsServiceImpl.class);

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    RewardsUtil rewardsUtil;

    @Override
    public Customer calculateRewardsByCustomerId(Integer customerId) {
        logger.info("calculateRewardsbyId method In RewardServiceImpl start....");
        Customer customerReward = customerRepo.findById(customerId).orElse(null);
        if(customerReward != null) {
            Set<Transactions> setOfTransaction = customerReward.getTransactions();
            setRewardsPerMonth(setOfTransaction, customerReward);
            logger.debug("End of reward calculation for customer" + customerReward.getName());
        }


        logger.info("calculateRewardsbyId method In RewardServiceImpl end....");
        return customerReward;
    }

    private void setRewardsPerMonth(Set<Transactions> setOfTransaction, Customer customer) {
        LocalDate todayDate = LocalDate.now();

        for (Transactions transaction : setOfTransaction) {
            int transactionMon = transaction.getCreationDate().getMonth() + 1;
            int transactionYear = transaction.getCreationDate().getYear() + 1900;


            logger.debug("Start Calculating Rewards for Customer:::" + customer.getName() + " for Transaction Id::: "
                    + transaction.getId());

            if ((todayDate.getYear() == transactionYear) && (todayDate.getMonth().getValue() == transactionMon))
                customer.setThirdMonthRewards(customer.getThirdMonthRewards()
                        + rewardsUtil.calculateRewardAmountPerTrans(transaction.getTransactionAmount()));

            else if ((todayDate.minusMonths(1).getYear() == transactionYear)
                    && (todayDate.minusMonths(1).getMonth().getValue() == transactionMon))
                customer.setSecondMonthRewards(customer.getSecondMonthRewards()
                        + rewardsUtil.calculateRewardAmountPerTrans(transaction.getTransactionAmount()));
            else if ((todayDate.minusMonths(2).getYear() == transactionYear)
                    && (todayDate.minusMonths(2).getMonth().getValue() == transactionMon))
                customer.setFirstMonthRewards(customer.getFirstMonthRewards()
                        + rewardsUtil.calculateRewardAmountPerTrans(transaction.getTransactionAmount()));

            logger.debug("End Calculating Rewards for customer::::" + customer.getName() + " for Transaction Id:::"
                    + transaction.getId());

        }
    }

}
