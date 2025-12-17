ALTER TABLE passenger
    ADD phone_number VARCHAR(255) NULL;

ALTER TABLE passenger
    MODIFY phone_number VARCHAR(255) NOT NULL;