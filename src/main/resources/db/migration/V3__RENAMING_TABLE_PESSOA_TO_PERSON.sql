
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

ALTER TABLE tb_person ADD COLUMN new_p_id BIGINT;
UPDATE tb_person SET new_p_id = CAST(SUBSTRING(p_id::text, 1, LENGTH(p_id::text)) AS BIGINT);
ALTER TABLE tb_person DROP COLUMN p_id;
ALTER TABLE tb_person RENAME COLUMN new_p_id TO p_id;
ALTER TABLE tb_person ALTER COLUMN p_id SET NOT NULL;

ALTER TABLE tb_person
    ADD CONSTRAINT pk_person PRIMARY KEY (p_id);

ALTER TABLE tb_person
    ADD COLUMN p_public_id UUID;
