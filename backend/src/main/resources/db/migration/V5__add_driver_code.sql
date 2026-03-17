ALTER TABLE questions ADD COLUMN method_signature TEXT;
ALTER TABLE questions ADD COLUMN driver_code TEXT;
ALTER TABLE questions DROP COLUMN starter_code;

ALTER TABLE test_cases ADD COLUMN display_input TEXT;
ALTER TABLE test_cases ADD COLUMN display_output TEXT;
