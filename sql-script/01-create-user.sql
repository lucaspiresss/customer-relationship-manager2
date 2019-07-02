-- -----------------------------------------------------
-- Creating user `usercrm`
-- -----------------------------------------------------
CREATE USER 'usercrm'@'localhost' IDENTIFIED BY 'usercrm';

GRANT ALL PRIVILEGES ON * . * TO 'usercrm'@'localhost';

ALTER USER 'usercrm'@'localhost' IDENTIFIED WITH mysql_native_password BY 'usercrm';