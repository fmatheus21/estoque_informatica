CREATE TABLE modelo (
  id int NOT NULL AUTO_INCREMENT,
  id_fabricante int NOT NULL,
  id_produto int NOT NULL,
  partnumber varchar(30) NOT NULL,
  nome varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY partnumber_UNIQUE (partnumber),
  KEY fk_fabricante_idx (id_fabricante),
  KEY fk_produto_modelo_idx (id_produto),
  CONSTRAINT fk_fabricante FOREIGN KEY (id_fabricante) REFERENCES fabricante (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT fk_produto_modelo FOREIGN KEY (id_produto) REFERENCES produto (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO modelo (id, id_fabricante, id_produto, partnumber, nome) VALUES
(1, 2, 1, '784H3LA', 'ProDesk HP 280 G9 SFF'),
(2, 2, 2, '86Y74LA', 'HP 256 G9'),
(3, 2, 3, '6N4E8AA', 'HP E22 G5'),
(4, 2, 8, '1MR69C', 'HP OfficeJet Pro 9020');
