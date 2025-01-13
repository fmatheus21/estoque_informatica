CREATE TABLE manufacturer (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (name),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO manufacturer (id, name) VALUES
(1, 'DELL'),
(2, 'HP'),
(3, 'AMD'),
(4, 'COOLER MASTER'),
(5, 'REDRAGON'),
(6, 'INTEL'),
(7, 'ASUS'),
(8, 'GIGABYTE'),
(9, 'RISE'),
(10, 'CORSAIR'),
(11, 'KINGSTON'),
(12, 'NVIDIA'),
(13, 'SAMSUNG');

