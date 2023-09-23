CREATE TABLE `topicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(200) NOT NULL,
  `mensaje` VARCHAR(500) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `estatus` TINYINT(1) NOT NULL,
  `autor` VARCHAR(50) NOT NULL,
  `curso` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `autor_UNIQUE` (`autor` ASC) VISIBLE,
  UNIQUE INDEX `titulo_UNIQUE` (`titulo` ASC) VISIBLE)
ENGINE = InnoDB;