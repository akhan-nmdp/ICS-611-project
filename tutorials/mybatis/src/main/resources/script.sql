CREATE TABLE CUSTOMER (id number, name varchar(20), surname varchar(20), age number);
INSERT INTO CUSTOMER VALUES (1, 'John', 'Doe', 25);
INSERT INTO CUSTOMER VALUES (2, 'Jill', 'Johnson', 18);
INSERT INTO CUSTOMER VALUES (3, 'Jake', 'Peralta', 29);
INSERT INTO CUSTOMER VALUES (4, 'Jack', 'Ryan', 35);
UPDATE CUSTOMER SET AGE = 19 WHERE name = 'Jake';
SELECT * FROM CUSTOMER;
DELETE FROM CUSTOMER WHERE name='John';
SELECT * FROM CUSTOMER ORDER BY NAME;
SELECT * FROM CUSTOMER WHERE AGE > 18;