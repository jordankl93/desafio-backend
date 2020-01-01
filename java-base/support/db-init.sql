CREATE DATABASE IF NOT EXISTS `users`;

USE `users`;

# Adicione a criação das tabelas necessárias para a sua aplicação.
# Você também pode criar a estrutura do seu banco utilizando o hibernate.

# Este arquivo será executado no startup do container do MySQL.

CREATE TABLE IF NOT EXISTS user (
   id INT AUTO_INCREMENT,
   cpf VARCHAR(14) NOT NULL UNIQUE,
   email VARCHAR(100) NOT NULL UNIQUE,
   full_name VARCHAR(100) NOT NULL,
   password VARCHAR(50) NOT NULL,
   phone_number VARCHAR(50) NOT NULL,
   PRIMARY KEY (id)   
) ENGINE=InnoDB;

