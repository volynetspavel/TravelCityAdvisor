-- -----------------------------------------------------
-- Schema city_advisor_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `city_advisor_db` DEFAULT CHARACTER SET utf8 ;
USE `city_advisor_db` ;

-- -----------------------------------------------------
-- Table `city_advisor_db`.`city_advisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `city_advisor_db`.`city_advisor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `advice` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;
