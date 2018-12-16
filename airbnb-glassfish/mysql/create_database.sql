CREATE SCHEMA IF NOT EXISTS `airbnbdb` DEFAULT CHARACTER SET utf8 ;
USE `airbnbdb` ;


CREATE TABLE IF NOT EXISTS `airbnbdb`.`User` (
  `email` VARCHAR(100) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnbdb`.`Apartment` (
  `host` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `beds_adult` INT NOT NULL,
  `type` VARCHAR(255) NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(1500) NOT NULL,
  `beds_child` INT NOT NULL,
  `buildingNumber` VARCHAR(20) NOT NULL,
  `street` VARCHAR(150) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `country` VARCHAR(100) NOT NULL,
  `flatNumber` VARCHAR(20) NOT NULL,
  `picture` LONGBLOB NOT NULL,
  INDEX `host_email_idx` (`host` ASC),
  PRIMARY KEY (`host`, `buildingNumber`, `street`, `flatNumber`, `city`),
  CONSTRAINT `host_email`
    FOREIGN KEY (`host`)
    REFERENCES `airbnbdb`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnbdb`.`Reservation` (
  `userEmail` VARCHAR(100) NOT NULL,
  `date` DATE NOT NULL,
  `apartmentHost` VARCHAR(100) NOT NULL,
  `apartmentBuildingNumber` VARCHAR(20) NOT NULL,
  `apartmentStreet` VARCHAR(150) NOT NULL,
  `apartmentFlatNumber` VARCHAR(20) NOT NULL,
  `apartmentCity` VARCHAR(100) NOT NULL,
  `reservationStatus` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`userEmail`, `apartmentHost`, `apartmentBuildingNumber`, `apartmentStreet`, `apartmentFlatNumber`, `apartmentCity`, `date`),
  INDEX `account_email_idx` (`userEmail` ASC),
  INDEX `fk_Reservation_Apartment1_idx` (`apartmentHost` ASC, `apartmentBuildingNumber` ASC, `apartmentStreet` ASC, `apartmentFlatNumber` ASC, `apartmentCity` ASC),
  CONSTRAINT `account_email`
    FOREIGN KEY (`userEmail`)
    REFERENCES `airbnbdb`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_Apartment1`
    FOREIGN KEY (`apartmentHost` , `apartmentBuildingNumber` , `apartmentStreet` , `apartmentFlatNumber` , `apartmentCity`)
    REFERENCES `airbnbdb`.`Apartment` (`host` , `buildingNumber` , `street` , `flatNumber` , `city`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnbdb`.`Message` (
  `sender` VARCHAR(100) NOT NULL,
  `receiver` VARCHAR(100) NOT NULL,
  `message` VARCHAR(800) NOT NULL,
  `date` DATETIME NOT NULL,
  `isUnread` TINYINT NULL,
  PRIMARY KEY (`message`, `date`, `sender`, `receiver`),
  INDEX `receiver_email_idx` (`receiver` ASC),
  CONSTRAINT `sender_email`
    FOREIGN KEY (`sender`)
    REFERENCES `airbnbdb`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `receiver_email`
    FOREIGN KEY (`receiver`)
    REFERENCES `airbnbdb`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER IF NOT EXISTS 'userLQE' IDENTIFIED BY '2dAlhk2RqPhVlFOK';

GRANT ALL ON `airbnbdb`.* TO 'userLQE';
GRANT ALL ON `new_schema1`.* TO 'userLQE';
GRANT SELECT ON TABLE `airbnbdb`.* TO 'userLQE';
GRANT SELECT ON TABLE `new_schema1`.* TO 'userLQE';
GRANT SELECT, INSERT, TRIGGER ON TABLE `airbnbdb`.* TO 'userLQE';
GRANT SELECT, INSERT, TRIGGER ON TABLE `new_schema1`.* TO 'userLQE';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `airbnbdb`.* TO 'userLQE';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `new_schema1`.* TO 'userLQE';
GRANT EXECUTE ON `airbnbdb`.* TO 'userLQE';
GRANT EXECUTE ON `new_schema1`.* TO 'userLQE';