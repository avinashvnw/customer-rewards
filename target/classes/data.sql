ALTER TABLE TRANSACTION ADD FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (ID);


Insert into CUSTOMER values(1,'TJ');
Insert into CUSTOMER values(2,'Edward');
Insert into CUSTOMER values(3,'Wei');
Insert into CUSTOMER values(4,'Coddy');
Insert into CUSTOMER values(5,'Salman');
Insert into CUSTOMER values(6,'Brett');
Insert into CUSTOMER values(7,'Sassy');



Insert into Transaction values(1001,1,75,'2023-02-04');
Insert into Transaction values(1002,1,120,'2023-01-21');
Insert into Transaction values(1003,1,45,'2023-01-04');
Insert into Transaction values(1004,1,70,'2022-12-31');
Insert into Transaction values(1005,1,120,'2022-12-25');

Insert into Transaction values(1006,2,105,'2023-02-10');
Insert into Transaction values(1007,2,45,'2023-01-28');
Insert into Transaction values(1008,2,145,'2023-01-12');
Insert into Transaction values(1009,2,160,'2022-12-28');
Insert into Transaction values(1010,2,121,'2022-12-29');

Insert into Transaction values(1011,3,50,'2023-02-01');

Insert into Transaction values(1012,4,115,'2023-02-05');

Insert into Transaction values(1013,5,35,'2023-02-11');

Insert into Transaction values(1014,6,250,'2023-02-15');

Insert into Transaction values(1015,7,100,'2023-02-17');
