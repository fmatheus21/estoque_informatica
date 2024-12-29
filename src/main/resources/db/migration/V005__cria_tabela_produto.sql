CREATE TABLE produto (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY nome_UNIQUE (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO produto (id, nome) VALUES
(1, 'DESKTOP'),
(2, 'NOTBOOK'),
(3, 'MONITOR'),
(4, 'MEMÓRIA'),
(5, 'SSD'),
(6, 'TECLADO'),
(7, 'MOUSE'),
(8, 'IMPRESSORA');
