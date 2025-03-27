DROP TABLE IF EXISTS quiz_result CASCADE;

DROP SEQUENCE IF EXISTS quiz_result_seq_id_seq;
CREATE SEQUENCE quiz_result_seq_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE quiz_result
(
    id          int8        NOT NULL PRIMARY KEY DEFAULT nextval('quiz_result_seq_id_seq'::regclass),
    quiz_id     int8        NOT NULL,
    question_id int8        NOT NULL,
    answer_id   int8        ,
    answer_text text        ,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id),
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (answer_id) REFERENCES answer(id)
);