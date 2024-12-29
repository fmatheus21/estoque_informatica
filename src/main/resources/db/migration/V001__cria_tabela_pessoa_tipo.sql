CREATE TABLE pessoa_tipo (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY nome_UNIQUE (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO pessoa_tipo(id, nome) VALUES
(1, 'PESSOA FÍSSICA'),
(2, 'PESSOA JURÍDICA');


