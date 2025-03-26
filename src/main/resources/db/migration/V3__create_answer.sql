DROP TABLE IF EXISTS answer CASCADE;

DROP SEQUENCE IF EXISTS answer_id_seq;
CREATE SEQUENCE answer_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE answer
(
    id          int8         NOT NULL PRIMARY KEY DEFAULT nextval('question_id_seq'::regclass),
    name        varchar(255) NOT NULL,
    question_id int8 NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);