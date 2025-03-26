DROP TABLE IF EXISTS quiz CASCADE;

DROP SEQUENCE IF EXISTS quiz_id_seq;
CREATE SEQUENCE quiz_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE quiz
(
    id          int8         NOT NULL PRIMARY KEY DEFAULT nextval('quiz_id_seq'::regclass),
    name        varchar(255) NOT NULL,
    description TEXT
);