DROP TABLE IF EXISTS answer CASCADE;
DROP TABLE IF EXISTS question CASCADE;

DROP SEQUENCE IF EXISTS question_id_seq;
CREATE SEQUENCE question_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE question
(
    id          int8         NOT NULL PRIMARY KEY DEFAULT nextval('question_id_seq'::regclass),
    name        varchar(255) NOT NULL,
    type        varchar(255) NOT NULL,
    quiz_id     int8,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);