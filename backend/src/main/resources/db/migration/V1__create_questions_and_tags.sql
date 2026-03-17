CREATE TABLE questions (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT NOT NULL,
    difficulty      VARCHAR(20) NOT NULL,
    source          VARCHAR(100),
    starter_code    TEXT,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE tags (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL
);

CREATE TABLE question_tags (
    question_id BIGINT NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
    tag_id      BIGINT NOT NULL REFERENCES tags(id) ON DELETE CASCADE,
    PRIMARY KEY (question_id, tag_id)
);

CREATE TABLE test_cases (
    id              BIGSERIAL PRIMARY KEY,
    question_id     BIGINT NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
    input           TEXT NOT NULL,
    expected_output TEXT NOT NULL,
    is_sample       BOOLEAN NOT NULL DEFAULT TRUE,
    order_index     INT NOT NULL DEFAULT 0
);

CREATE INDEX idx_questions_difficulty ON questions(difficulty);
CREATE INDEX idx_tags_type ON tags(type);
CREATE INDEX idx_test_cases_question_id ON test_cases(question_id);
