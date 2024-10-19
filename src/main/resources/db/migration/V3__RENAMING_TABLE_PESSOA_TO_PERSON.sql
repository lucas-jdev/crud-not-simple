
ALTER TABLE pessoa RENAME TO tb_person;

ALTER TABLE tb_person
    RENAME Column id TO p_id;

ALTER TABLE tb_person
    RENAME Column nome TO p_name;

ALTER TABLE tb_person
    DROP COLUMN idade;

ALTER TABLE tb_person
    ADD COLUMN p_birth_date DATE;

ALTER TABLE tb_person
    RENAME Column sobrenome TO p_last_name;

ALTER TABLE tb_person
    RENAME Column email TO p_email;

ALTER TABLE tb_person
    DROP CONSTRAINT pk_pessoa;

ALTER TABLE tb_person
    ADD COLUMN p_active BOOLEAN;

ALTER TABLE tb_person
    ALTER COLUMN p_id SET DATA TYPE BIGINT USING (CAST(substring(p_id::text FROM '([0-9]+)') AS bigint)),
    ALTER COLUMN p_id SET NOT NULL;

ALTER TABLE tb_person
    ADD CONSTRAINT pk_person PRIMARY KEY (p_id);

ALTER TABLE tb_person
    ADD COLUMN p_public_id UUID;
