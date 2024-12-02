-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema e_book
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema e_book
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `e_book` DEFAULT CHARACTER SET utf8mb4 ;
USE `e_book` ;

-- -----------------------------------------------------
-- Table `e_book`.`book_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e_book`.`book_genre` (
  `genre_id` INT NOT NULL,
  `genre_name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `e_book`.`book_genre` (`genre_id`, `genre_name`) VALUES 
(1, "판타지"),(2, "로맨스"),(3, "무협"),(4, "추리"),(5, "스릴러"),(6, "코미디"),(7, "현대 판타지"),(8, "게임 판타지"),(9, "시"),(10, "일반소설");

-- -----------------------------------------------------
-- Table `e_book`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e_book`.`book` (
  `book_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `book_title` VARCHAR(200) NOT NULL,
  `rating` INT NOT NULL,
  `author` VARCHAR(200) NOT NULL,
  `publisher` VARCHAR(200) NOT NULL,
  `publish_date` VARCHAR(200) NULL DEFAULT NULL,
  `genre1` INT NOT NULL,
  `genre2` INT NULL DEFAULT NULL,
  `genre3` INT NULL DEFAULT NULL,
  `is_rental` INT NOT NULL,
  `upd_date` VARCHAR(200) NOT NULL,
  `price` INT NOT NULL,
  `img_url` VARCHAR(200) NULL DEFAULT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `rent_cnt` INT NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE INDEX `book_id` (`book_id` ASC) VISIBLE,
  INDEX `fk_book_book_genre1_idx` (`genre1` ASC) VISIBLE,
  CONSTRAINT `fk_book_book_genre1`
    FOREIGN KEY (`genre1`)
    REFERENCES `e_book`.`book_genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `e_book`.`attribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e_book`.`attribute` (
  `fantasie` INT NULL DEFAULT NULL,
  `romance` INT NULL DEFAULT NULL,
  `action` INT NULL DEFAULT NULL,
  `reasoning` INT NULL DEFAULT NULL,
  `thriller` INT NULL DEFAULT NULL,
  `comedy` INT NULL DEFAULT NULL,
  `con_fantasie` INT NULL DEFAULT NULL,
  `game_fantasy` INT NULL DEFAULT NULL,
  `poet` INT NULL DEFAULT NULL,
  `literature` INT NULL DEFAULT NULL,
  `book_id` INT NOT NULL,
  INDEX `fk_attribute_book1_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_attribute_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `e_book`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `e_book`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e_book`.`user` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(200) NOT NULL,
  `user_password` VARCHAR(200) NOT NULL,
  `user_name` VARCHAR(200) NOT NULL,
  `address` VARCHAR(500) NOT NULL,
  `phone_number` VARCHAR(100) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `birthday` VARCHAR(200) NOT NULL,
  `sex` VARCHAR(200) NOT NULL,
  `admin` INT NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE INDEX `uid` (`uid` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `e_book`.`book_rental_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e_book`.`book_rental_history` (
  `uid` INT NOT NULL,
  `book_id` INT NULL DEFAULT NULL,
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `book_id` (`book_id` ASC) VISIBLE,
  INDEX `fk_book_rental_history_user1_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_book_rental_history_user1`
    FOREIGN KEY (`uid`)
    REFERENCES `e_book`.`user` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_rental_history_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `e_book`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `e_book`.`user_rental_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e_book`.`user_rental_history` (
  `uid` INT NOT NULL,
  `book_id` INT NULL DEFAULT NULL,
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `book_id` (`book_id` ASC) VISIBLE,
  INDEX `fk_user_rental_history_user_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_user_rental_history_user`
    FOREIGN KEY (`uid`)
    REFERENCES `e_book`.`user` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
