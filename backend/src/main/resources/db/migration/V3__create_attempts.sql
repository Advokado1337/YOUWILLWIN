CREATE TABLE attempts (
    id            BIGSERIAL PRIMARY KEY,
    question_id   BIGINT NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
    code          TEXT NOT NULL,
    status        VARCHAR(20) NOT NULL,
    passed_count  INT NOT NULL DEFAULT 0,
    total_count   INT NOT NULL DEFAULT 0,
    time_spent_ms BIGINT,
    executed_at   TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_attempts_question_id ON attempts(question_id);
CREATE INDEX idx_attempts_executed_at ON attempts(executed_at);
