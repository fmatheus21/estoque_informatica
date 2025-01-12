CREATE TABLE supplier (
  id binary(16) NOT NULL,
  id_person int NOT NULL,
  date_updated datetime DEFAULT NULL,
  id_user_updated binary(16) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY id_person_UNIQUE (id_person),
  CONSTRAINT fk_person_supplier FOREIGN KEY (id_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
