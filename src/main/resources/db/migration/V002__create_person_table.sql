CREATE TABLE person (
  id int NOT NULL AUTO_INCREMENT,
  id_person_type int NOT NULL,
  name varchar(100) NOT NULL,
  document varchar(20) NOT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  id_user_created binary(16) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY document_UNIQUE (document),
  KEY fk_person_type_idx (id_person_type),
  CONSTRAINT fk_person_type FOREIGN KEY (id_person_type) REFERENCES person_type (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

