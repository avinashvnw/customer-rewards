# customer-rewards
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent between $50 and $100 in each transaction.
(e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three-month period, calculate the reward points earned for
each customer per month and total.


# Running the application
- git clone https://github.com/avinashvnw/customer-rewards
- mvn clean compile
- mvn spring-boot:run


# Test Cases
Create Test Cases under src/test/java for below scenarios:
1. Below 50
2. Above 100
3. Above 50 Below 100
4. Beyond Three months 
5. Multiple transactions


# Health API
http://localhost:8080/actuator/
http://localhost:8080/actuator/health
