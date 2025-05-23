DROP TABLE IF EXISTS car;

CREATE TABLE car (id int(10) NOT NULL AUTO_INCREMENT, 
    model varchar(50) NOT NULL, 
    year int(4) NOT NULL, 
    PRIMARY KEY (id));
 
INSERT INTO car (model, year) VALUES ('BMW', 2000);
INSERT INTO car (model, year) VALUES ('BENZ', 2010);
INSERT INTO car (model, year) VALUES ('PORCHE', 2005);
INSERT INTO car (model, year) VALUES ('PORCHE', 2004);

DELIMITER $$

DROP PROCEDURE IF EXISTS FIND_CARS_AFTER_YEAR$$
CREATE PROCEDURE FIND_CARS_AFTER_YEAR(IN year_in INT)
BEGIN 
    SELECT * FROM car WHERE year >= year_in ORDER BY year;
END$$

DROP PROCEDURE IF EXISTS GET_TOTAL_CARS_BY_MODEL$$
CREATE PROCEDURE GET_TOTAL_CARS_BY_MODEL(IN model_in VARCHAR(50), OUT count_out INT)
BEGIN 
    SELECT COUNT(*) into count_out from car WHERE model = model_in;
END$$

DELIMITER ;
