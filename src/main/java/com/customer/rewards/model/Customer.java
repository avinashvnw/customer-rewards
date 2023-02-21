package com.customer.rewards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@Setter
@Getter
public class Customer {

	@Id
	int id;

	@Column
	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Transactions> transactions;

	@Transient
	int firstMonthRewards;

	@Transient
	int secondMonthRewards;

	@Transient
	int thirdMonthRewards;

	@Transient
	int totalRewardAmount;

}