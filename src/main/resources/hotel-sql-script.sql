-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`room_price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room_price` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valid_from` DATE NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `capacity` SMALLINT NOT NULL,
  `type` ENUM('standart', 'deluxe', 'premium') NOT NULL,
  `number` SMALLINT NOT NULL,
  `is_blocked` TINYINT(1) NOT NULL,
  `room_price_id` INT NOT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_room_room_price1_idx` (`room_price_id` ASC) VISIBLE,
  CONSTRAINT `fk_room_room_price1`
    FOREIGN KEY (`room_price_id`)
    REFERENCES `hotel`.`room_price` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `is_admin` TINYINT(1) NOT NULL DEFAULT '0',
  `is_blocked` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hotel`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `room_capacity` SMALLINT NOT NULL,
  `room_class` ENUM('standart', 'deluxe', 'premium') NOT NULL,
  `is_approved` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `room_id` (`room_id` ASC) VISIBLE,
  CONSTRAINT `reservation_ibfk_1`
    FOREIGN KEY (`room_id`)
    REFERENCES `hotel`.`room` (`id`),
  CONSTRAINT `reservation_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `hotel`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
