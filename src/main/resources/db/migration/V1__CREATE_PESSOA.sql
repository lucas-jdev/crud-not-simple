CREATE TABLE IF NOT EXISTS pessoa
(
    id    UUID NOT NULL,
    nome  VARCHAR(255),
    idade INT,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);
